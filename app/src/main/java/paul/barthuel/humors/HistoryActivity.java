package paul.barthuel.humors;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ImageView mHistoryBtnDay1;
    ImageView mHistoryBtnDay2;
    ImageView mHistoryBtnDay3;
    ImageView mHistoryBtnDay4;
    ImageView mHistoryBtnDay5;
    ImageView mHistoryBtnDay6;
    ImageView mHistoryBtnDay7;
    ImageView mHistoryBtnTaskBar;
    public static final int CHARTS_ACTIVITY_REQUEST_CODE = 2;
    FrameLayout mHistoryLayout1;
    FrameLayout mHistoryLayout2;
    FrameLayout mHistoryLayout3;
    FrameLayout mHistoryLayout4;
    FrameLayout mHistoryLayout5;
    FrameLayout mHistoryLayout6;
    FrameLayout mHistoryLayout7;
    MoodDao moodDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        moodDao = new MoodDao(this);

        mHistoryBtnDay1 = findViewById(R.id.history_iv_day1);
        mHistoryBtnDay2 = findViewById(R.id.history_iv_day2);
        mHistoryBtnDay3 = findViewById(R.id.history_iv_day3);
        mHistoryBtnDay4 = findViewById(R.id.history_iv_day4);
        mHistoryBtnDay5 = findViewById(R.id.history_iv_day5);
        mHistoryBtnDay6 = findViewById(R.id.history_iv_day6);
        mHistoryBtnDay7 = findViewById(R.id.history_iv_day7);
        mHistoryBtnTaskBar = findViewById(R.id.history_iv_task_bar);

        mHistoryLayout1 = findViewById(R.id.history_fl_1);
        mHistoryLayout2 = findViewById(R.id.history_fl_2);
        mHistoryLayout3 = findViewById(R.id.history_fl_3);
        mHistoryLayout4 = findViewById(R.id.history_fl_4);
        mHistoryLayout5 = findViewById(R.id.history_fl_5);
        mHistoryLayout6 = findViewById(R.id.history_fl_6);
        mHistoryLayout7 = findViewById(R.id.history_fl_7);

        final List<DailyMood> moods = new MoodDao(this).readSevenDaysHistory();

        setupViews(mHistoryLayout1, mHistoryBtnDay1, moods, 0);
        setupViews(mHistoryLayout2, mHistoryBtnDay2, moods, 1);
        setupViews(mHistoryLayout3, mHistoryBtnDay3, moods, 2);
        setupViews(mHistoryLayout4, mHistoryBtnDay4, moods, 3);
        setupViews(mHistoryLayout5, mHistoryBtnDay5, moods, 4);
        setupViews(mHistoryLayout6, mHistoryBtnDay6, moods, 5);
        setupViews(mHistoryLayout7, mHistoryBtnDay7, moods, 6);

        mHistoryBtnTaskBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chartsActivityIntent = new Intent(HistoryActivity.this, ChartsActivity.class);
                startActivityForResult(chartsActivityIntent, CHARTS_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    private void setupViews(View backGround, View commentary, final List<DailyMood> dailyMoods, final int index) {
        if (dailyMoods.size() > index) {
            backGround.setBackgroundResource(dailyMoods.get(index).getMood().getColorRes());
            if (dailyMoods.get(index).getComment() == null) {
                commentary.setVisibility(View.GONE);
            } else {
                commentary.setVisibility(View.VISIBLE);
            }
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            commentary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final TextView input = new EditText(HistoryActivity.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    input.setText(dailyMoods.get(index).getComment());
                    input.setLayoutParams(lp);
                    builder.setView(input)
                            .show();
                }
            });
        }
    }
}
