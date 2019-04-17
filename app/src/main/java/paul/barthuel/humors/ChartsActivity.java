package paul.barthuel.humors;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
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
        List<SliceValue> sliceValues = new ArrayList<>(Mood.values().length);
        Map<Mood, Integer> totalHistoryMoods = moodDao.totalHistoryMoods();
        for (Map.Entry<Mood, Integer> entry : totalHistoryMoods.entrySet()) {
            SliceValue sliceValue = new SliceValue(entry.getValue(), ContextCompat.getColor(this,entry.getKey().getColorRes()));
            sliceValue.setLabel(entry.getKey().toString());
            sliceValues.add(sliceValue);
        }

        PieChartData data = new PieChartData(sliceValues);
        data.setHasLabels(true);
        pieChartView.setPieChartData(data);
    }
}
