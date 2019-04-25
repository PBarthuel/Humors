package paul.barthuel.humors;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class MainActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener {

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;
    public static final int HISTORY_ACTIVITY_REQUEST_CODE = 1;
    ImageView smiley;
    RelativeLayout mainRelativeLayout;
    Mood currentMood = Mood.SUPER_HAPPY;
    ImageView mCommentButton;
    ImageView mHistoryButton;
    MoodDao moodDao;

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        upsertDailyMood();


        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                final DailyMood dailyMood = new MoodDao(MainActivity.this).getDailyMood();
                final TextInputLayout input = new TextInputLayout(MainActivity.this);
                final TextInputEditText inputEditText = new TextInputEditText(MainActivity.this);

                inputEditText.setHint(R.string.enter_your_commentary);
                input.addView(inputEditText);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                if (dailyMood != null) {
                    inputEditText.setText(dailyMood.getComment());
                }
                input.setLayoutParams(lp);
                builder.setView(input)
                        .setTitle(R.string.commentary)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moodDao.insertTodayMood(new DailyMood(currentMood, inputEditText.getText().toString()));
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivityForResult(historyActivityIntent, HISTORY_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    private void init() {
        AndroidThreeTen.init(this);

        moodDao = new MoodDao(this);

        mDetector = new GestureDetectorCompat(this, this);

        findViews();
        bindViews();
    }

    private void upsertDailyMood() {
        if (moodDao.getDailyMood() != null) {
            moodDao.insertTodayMood(new DailyMood(currentMood, moodDao.getDailyMood().getComment()));
        } else {
            moodDao.insertTodayMood(new DailyMood(currentMood, null));
        }
    }

    private void bindViews() {
        smiley.setImageResource(currentMood.getDrawableRes());
        mainRelativeLayout.setBackgroundResource(currentMood.getColorRes());
    }

    private void findViews() {
        mCommentButton = findViewById(R.id.main_iv_comment);
        mHistoryButton = findViewById(R.id.main_iv_history);
        mainRelativeLayout = findViewById(R.id.main_rl_parent);
        smiley = findViewById(R.id.main_iv_smiley);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + velocityY);
        boolean getHappier = velocityY < 0;

        int ordinalMood;
        if (getHappier) {
            ordinalMood = currentMood.ordinal() + 1;
        } else {
            ordinalMood = currentMood.ordinal() - 1;
        }
        if (ordinalMood < 0) {
            ordinalMood = 0;
        } else if (ordinalMood > Mood.values().length - 1) {
            ordinalMood = Mood.values().length - 1;
        }

        currentMood = Mood.values()[ordinalMood];
        bindViews();
        moodDao.insertTodayMood(new DailyMood(currentMood, null));
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                            float distanceY) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }
}
