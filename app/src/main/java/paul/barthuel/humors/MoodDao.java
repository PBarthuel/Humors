package paul.barthuel.humors;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class MoodDao {

    private static final String KEY_COMMENT = "KEY_COMMENT";
    SharedPreferences mPreferences;

    public MoodDao (Context context) {
        mPreferences = context.getSharedPreferences("user_history", Context.MODE_PRIVATE);
    }

    public void insertTodayMood(DailyMood dailyMood) {
        String serialized = new Gson().toJson(dailyMood);
        mPreferences.edit().putString(KEY_COMMENT, serialized).apply();
    }

    public DailyMood getDailyMood() {
        String serialized = mPreferences.getString(KEY_COMMENT, null);
        return new Gson().fromJson(serialized, DailyMood.class);
    }
}
