package paul.barthuel.humors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class HistoryActivity extends AppCompatActivity {

    ImageView mHistoryBtnDay1;
    ImageView mHistoryBtnDay2;
    ImageView mHistoryBtnDay3;
    ImageView mHistoryBtnDay4;
    ImageView mHistoryBtnDay5;
    ImageView mHistoryBtnDay6;
    ImageView mHistoryBtnDay7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }
}
