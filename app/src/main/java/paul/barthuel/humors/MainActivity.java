package paul.barthuel.humors;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

        AndroidThreeTen.init(this);

        moodDao = new MoodDao(this);

        setContentView(R.layout.activity_main);

        //Initialize the images from the layout
        smiley = findViewById(R.id.main_iv_smiley);
        smiley.setImageResource(currentMood.getDrawableRes());

        mainRelativeLayout = findViewById(R.id.main_rl_parent);
        mainRelativeLayout.setBackgroundResource(currentMood.getColorRes());

        mCommentButton = findViewById(R.id.main_iv_comment);
        mHistoryButton = findViewById(R.id.main_iv_history);

        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this, this);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setTitle("Enter your commentary :D");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moodDao.insertTodayMood(new DailyMood(currentMood, input.getText().toString()));
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
            ordinalMood = currentMood.ordinal()+1;
        }else {
            ordinalMood = currentMood.ordinal()-1;
        }
        if (ordinalMood < 0) {
            ordinalMood = 0;
        }else if (ordinalMood > Mood.values().length - 1) {
            ordinalMood = Mood.values().length - 1;
        }
        currentMood = Mood.values()[ordinalMood];
        smiley.setImageResource(currentMood.getDrawableRes());
        mainRelativeLayout.setBackgroundResource(currentMood.getColorRes());
        Toast.makeText(this, moodDao.getDailyMood().getMood().name(), Toast.LENGTH_SHORT).show();
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
