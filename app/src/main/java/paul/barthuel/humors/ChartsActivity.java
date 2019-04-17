package paul.barthuel.humors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.view.PieChartView;

public class ChartsActivity extends AppCompatActivity {

    PieChartView pieChartView;
    MoodDao moodDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        moodDao = new MoodDao(this);
        pieChartView = findViewById(R.id.charts_camembert);

        PieChartData data = new PieChartData();

    }
}
