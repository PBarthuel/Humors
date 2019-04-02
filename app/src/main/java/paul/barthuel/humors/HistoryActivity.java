package paul.barthuel.humors;

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

        mHistoryLayout1 = findViewById(R.id.history_fl_1);
        mHistoryLayout2 = findViewById(R.id.history_fl_2);
        mHistoryLayout3 = findViewById(R.id.history_fl_3);
        mHistoryLayout4 = findViewById(R.id.history_fl_4);
        mHistoryLayout5 = findViewById(R.id.history_fl_5);
        mHistoryLayout6 = findViewById(R.id.history_fl_6);
        mHistoryLayout7 = findViewById(R.id.history_fl_7);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final List<DailyMood> moods = new MoodDao(this).readSevenDaysHistory();

        /*if (moods.size() >= 1) {
            mHistoryLayout1.setBackgroundResource(moods.get(0).getMood().getColorRes());
            if (moods.get(0).getComment() == null) {
                mHistoryBtnDay1.setVisibility(View.GONE);
            } else {
                mHistoryBtnDay1.setVisibility(View.VISIBLE);
            }
        }

        if (moods.size() >= 2) {
            mHistoryLayout2.setBackgroundResource(moods.get(1).getMood().getColorRes());
            if (moods.get(1).getComment() == null) {
                mHistoryBtnDay2.setVisibility(View.GONE);
            } else {
                mHistoryBtnDay2.setVisibility(View.VISIBLE);
            }
        }

        if (moods.size() >= 3) {
            mHistoryLayout3.setBackgroundResource(moods.get(2).getMood().getColorRes());
            if (moods.get(2).getComment() == null) {
                mHistoryBtnDay3.setVisibility(View.GONE);
            } else {
                mHistoryBtnDay3.setVisibility(View.VISIBLE);
            }
        }

        if (moods.size() >= 4) {
            mHistoryLayout4.setBackgroundResource(moods.get(3).getMood().getColorRes());
            if (moods.get(3).getComment() == null) {
                mHistoryBtnDay4.setVisibility(View.GONE);
            } else {
                mHistoryBtnDay4.setVisibility(View.VISIBLE);
            }
        }

        if (moods.size() >= 5) {
            mHistoryLayout5.setBackgroundResource(moods.get(4).getMood().getColorRes());
            if (moods.get(4).getComment() == null) {
                mHistoryBtnDay5.setVisibility(View.GONE);
            } else {
                mHistoryBtnDay5.setVisibility(View.VISIBLE);
            }
        }

        if (moods.size() >= 6) {
            mHistoryLayout6.setBackgroundResource(moods.get(5).getMood().getColorRes());
            if (moods.get(5).getComment() == null) {
                mHistoryBtnDay6.setVisibility(View.GONE);
            } else {
                mHistoryBtnDay6.setVisibility(View.VISIBLE);
            }
        }

        if (moods.size() >= 7) {
            mHistoryLayout7.setBackgroundResource(moods.get(6).getMood().getColorRes());
            if (moods.get(6).getComment() == null) {
                mHistoryBtnDay7.setVisibility(View.GONE);
            } else {
                mHistoryBtnDay7.setVisibility(View.VISIBLE);
            }
        }

        mHistoryBtnDay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView input = new EditText(HistoryActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setText(moods.get(0).getComment());
                input.setLayoutParams(lp);
                builder.setView(input)
                        .show();
            }
        });

        mHistoryBtnDay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView input = new EditText(HistoryActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setText(moods.get(1).getComment());
                input.setLayoutParams(lp);
                builder.setView(input)
                        .show();
            }
        });

        mHistoryBtnDay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView input = new EditText(HistoryActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setText(moods.get(2).getComment());
                input.setLayoutParams(lp);
                builder.setView(input)
                        .show();
            }
        });

        mHistoryBtnDay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView input = new EditText(HistoryActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setText(moods.get(3).getComment());
                input.setLayoutParams(lp);
                builder.setView(input)
                        .show();
            }
        });

        mHistoryBtnDay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView input = new EditText(HistoryActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setText(moods.get(4).getComment());
                input.setLayoutParams(lp);
                builder.setView(input)
                        .show();
            }
        });

        mHistoryBtnDay6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView input = new EditText(HistoryActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setText(moods.get(5).getComment());
                input.setLayoutParams(lp);
                builder.setView(input)
                        .show();
            }
        });

        mHistoryBtnDay7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView input = new EditText(HistoryActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setText(moods.get(6).getComment());
                input.setLayoutParams(lp);
                builder.setView(input)
                        .setTitle("Your say that ... One day !!!")
                        .show();
            }
        }); */
    }
}
