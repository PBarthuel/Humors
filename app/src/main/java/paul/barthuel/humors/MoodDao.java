package paul.barthuel.humors;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import org.threeten.bp.LocalDateTime;


public class MoodDao extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "user_history";
    private static final String COLUMN_COMMENT = "comment";
    private static final String COLUMN_MOOD = "mood";
    private static final String COLUMN_DATE = "date";

    public MoodDao (Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("+COLUMN_COMMENT+" TEXT, "+COLUMN_MOOD+" TEXT, "+COLUMN_DATE+" DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertTodayMood(DailyMood dailyMood) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMMENT, dailyMood.getComment());
        contentValues.put(COLUMN_MOOD, dailyMood.getMood().name());
        contentValues.put(COLUMN_DATE, LocalDateTime.now().toString());
        getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }

    @Nullable
    public DailyMood getDailyMood() {
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, COLUMN_DATE+" DESC", "1");
        if(cursor.moveToFirst()) {
            String comment = cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT));
            Mood mood = Mood.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_MOOD)));
            cursor.close();
            return new DailyMood(mood, comment);
        }else {
            return null;
        }
    }
}
