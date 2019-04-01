package paul.barthuel.humors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ImageView mHistoryBtnDay1;
    ImageView mHistoryBtnDay2;
    ImageView mHistoryBtnDay3;
    ImageView mHistoryBtnDay4;
    ImageView mHistoryBtnDay5;
    ImageView mHistoryBtnDay6;
    ImageView mHistoryBtnDay7;
    FrameLayout mHistoryLayout1;
    FrameLayout mHistoryLayout2;
    FrameLayout mHistoryLayout3;
    FrameLayout mHistoryLayout4;
    FrameLayout mHistoryLayout5;
    FrameLayout mHistoryLayout6;
    FrameLayout mHistoryLayout7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mHistoryBtnDay1 = findViewById(R.id.history_iv_day1);
        mHistoryBtnDay2 = findViewById(R.id.history_iv_day2);
        mHistoryBtnDay3 = findViewById(R.id.history_iv_day3);
        mHistoryBtnDay4 = findViewById(R.id.history_iv_day4);
        mHistoryBtnDay5 = findViewById(R.id.history_iv_day5);
        mHistoryBtnDay6 = findViewById(R.id.history_iv_day6);
        mHistoryBtnDay7 = findViewById(R.id.history_iv_day7);

        mHistoryLayout1 = findViewById(R.id.history_fl_1);
        mHistoryLayout2 = findViewById(R.id.history_fl_2);
        mHistoryLayout3 = findViewById(R.id.history_fl_3);
        mHistoryLayout4 = findViewById(R.id.history_fl_4);
        mHistoryLayout5 = findViewById(R.id.history_fl_5);
        mHistoryLayout6 = findViewById(R.id.history_fl_6);
        mHistoryLayout7 = findViewById(R.id.history_fl_7);

        List<DailyMood> moods = new MoodDao(this).readSevenDaysHistory();
        if (moods.size() >= 1) {
            mHistoryLayout7.setBackgroundResource(moods.get(0).getMood().getColorRes());
            if (moods.get(0).getComment() == null) {
                mHistoryBtnDay7.setVisibility(View.GONE);
            } else {
                mHistoryBtnDay7.setVisibility(View.VISIBLE);
            }
        }
    }
}
