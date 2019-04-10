package paul.barthuel.humors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChartsActivity extends AppCompatActivity {

    MoodDao moodDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        moodDao = new MoodDao(this);

    }
}
