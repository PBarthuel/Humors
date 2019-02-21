package paul.barthuel.humors;

import android.content.DialogInterface;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener {

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;
    public static final int GAME_ACTIVITY_REQUEST_CODE = 1;
    ImageView smiley;
    RelativeLayout mainRelativeLayout;
    //int smileyCount;
    Mood currentMood = Mood.HAPPY;
    ImageView mCommentButton;
    ImageView mHistoryButton;

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                startActivityForResult(historyActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
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
        /*if (smileyCount == 0) {
            if (velocityY < 0) {
                smiley.setImageResource(R.drawable.smiley_happy);
                mainRelativeLayout.setBackgroundResource(R.color.light_sage);
                smileyCount = 1;
            }
        } else if (smileyCount == 1) {
            if (velocityY < 0) {
                smiley.setImageResource(R.drawable.smiley_normal);
                mainRelativeLayout.setBackgroundResource(R.color.cornflower_blue_65);
                smileyCount = 2;
            } else {
                smiley.setImageResource(R.drawable.smiley_super_happy);
                mainRelativeLayout.setBackgroundResource(R.color.banana_yellow);
                smileyCount = 0;
            }
        } else if (smileyCount == 2) {
            if (velocityY < 0) {
                smiley.setImageResource(R.drawable.smiley_disappointed);
                mainRelativeLayout.setBackgroundResource(R.color.warm_grey);
                smileyCount = 3;
            } else {
                smiley.setImageResource(R.drawable.smiley_happy);
                mainRelativeLayout.setBackgroundResource(R.color.light_sage);
                smileyCount = 1;
            }
        } else if (smileyCount == 3) {
            if (velocityY < 0) {
                smiley.setImageResource(R.drawable.smiley_sad);
                mainRelativeLayout.setBackgroundResource(R.color.faded_red);
                smileyCount = 4;
            } else {
                smiley.setImageResource(R.drawable.smiley_normal);
                mainRelativeLayout.setBackgroundResource(R.color.cornflower_blue_65);
                smileyCount = 2;
            }
        } else if (smileyCount == 4) {
            if (velocityY > 0) {
                smiley.setImageResource(R.drawable.smiley_disappointed);
                mainRelativeLayout.setBackgroundResource(R.color.warm_grey);
                smileyCount = 3;
            }
        } */
        boolean getHappier = velocityY < 0;
        if(getHappier){
            currentMood = Mood.values()[currentMood.ordinal()-1];
        }else {
            currentMood = Mood.values()[currentMood.ordinal()+1];
        }
        smiley.setImageResource(currentMood.getDrawableRes());
        mainRelativeLayout.setBackgroundResource(currentMood.getColorRes());
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
