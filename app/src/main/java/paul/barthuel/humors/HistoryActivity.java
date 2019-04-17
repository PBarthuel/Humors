package paul.barthuel.humors;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    ImageView mHistoryBtnDay1;
    ImageView mHistoryBtnDay2;
    ImageView mHistoryBtnDay3;
    ImageView mHistoryBtnDay4;
    ImageView mHistoryBtnDay5;
    ImageView mHistoryBtnDay6;
    ImageView mHistoryBtnDay7;
    ImageView mHistoryBtnTaskBar;
    TextView mHistoryTextView1;
    TextView mHistoryTextView2;
    TextView mHistoryTextView3;
    TextView mHistoryTextView4;
    TextView mHistoryTextView5;
    TextView mHistoryTextView6;
    TextView mHistoryTextView7;
    MoodDao moodDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        constraintLayout = findViewById(R.id.history_cl);

        moodDao = new MoodDao(this);

        mHistoryBtnDay1 = findViewById(R.id.history_iv_day1);
        mHistoryBtnDay2 = findViewById(R.id.history_iv_day2);
        mHistoryBtnDay3 = findViewById(R.id.history_iv_day3);
        mHistoryBtnDay4 = findViewById(R.id.history_iv_day4);
        mHistoryBtnDay5 = findViewById(R.id.history_iv_day5);
        mHistoryBtnDay6 = findViewById(R.id.history_iv_day6);
        mHistoryBtnDay7 = findViewById(R.id.history_iv_day7);

        mHistoryBtnTaskBar = findViewById(R.id.history_iv_task_bar);

        mHistoryTextView1 = findViewById(R.id.history_tv_1);
        mHistoryTextView2 = findViewById(R.id.history_tv_2);
        mHistoryTextView3 = findViewById(R.id.history_tv_3);
        mHistoryTextView4 = findViewById(R.id.history_tv_4);
        mHistoryTextView5 = findViewById(R.id.history_tv_5);
        mHistoryTextView6 = findViewById(R.id.history_tv_6);
        mHistoryTextView7 = findViewById(R.id.history_tv_7);

        final List<DailyMood> moods = new MoodDao(this).readSevenDaysHistory();

        setupViews(mHistoryTextView1, mHistoryBtnDay1, moods, 0);
        setupViews(mHistoryTextView2, mHistoryBtnDay2, moods, 1);
        setupViews(mHistoryTextView3, mHistoryBtnDay3, moods, 2);
        setupViews(mHistoryTextView4, mHistoryBtnDay4, moods, 3);
        setupViews(mHistoryTextView5, mHistoryBtnDay5, moods, 4);
        setupViews(mHistoryTextView6, mHistoryBtnDay6, moods, 5);
        setupViews(mHistoryTextView7, mHistoryBtnDay7, moods, 6);

        mHistoryBtnTaskBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chartsActivityIntent = new Intent(HistoryActivity.this, ChartsActivity.class);
                startActivity(chartsActivityIntent);
            }
        });

    }

    private void setupViews(final View backGround, View commentary, final List<DailyMood> dailyMoods, final int index) {
        if (dailyMoods.size() > index) {

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.constrainPercentWidth(backGround.getId(), dailyMoods.get(index).getMood().getPercent());
            constraintSet.applyTo(constraintLayout);

            backGround.setBackgroundResource(dailyMoods.get(index).getMood().getColorRes());
            if (dailyMoods.get(index).getComment() == null) {
                commentary.setVisibility(View.GONE);
            } else {
                commentary.setVisibility(View.VISIBLE);
            }
            commentary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dailyMoods.get(index).getComment() != null) {
                        Toast.makeText(HistoryActivity.this, dailyMoods.get(index).getComment(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
