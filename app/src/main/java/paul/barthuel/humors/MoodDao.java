package paul.barthuel.humors;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.Nullable;

import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MoodDao extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "user_history";
    private static final String COLUMN_COMMENT = "comment";
    private static final String COLUMN_MOOD = "mood";
    private static final String COLUMN_DATE = "date";

    public MoodDao(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        disableWal(db);

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_COMMENT + " TEXT," +
                " " + COLUMN_MOOD + " TEXT," +
                " " + COLUMN_DATE + " TEXT)");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        disableWal(db);

        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void disableWal(SQLiteDatabase db) {

        // Disables WAL. We don't need such a dev-unfriendly feature on a simple project.
        // With this, .wal and .smh files are no longer generated, and the db is easy to extract & open
        //
        // Source : https://www.sqlite.org/wal.html
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.disableWriteAheadLogging();
        }
    }

    public void insertTodayMood(DailyMood dailyMood) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMMENT, dailyMood.getComment());
        contentValues.put(COLUMN_MOOD, dailyMood.getMood().name());
        contentValues.put(COLUMN_DATE, LocalDate.now().toString());

        if (getDailyMood() != null) {
            // We need to escape the parameter "contentValues.get(COLUMN_DATE)" because if it's not
            // explicitly considered as a String, I guess maths get in and try to actually compute
            // the date, like 2019-02-04 would result as 2019 - 02 - 04 which is, mathematically,
            // equal to 2013.
            String whereClause = COLUMN_DATE + " = \"" + contentValues.get(COLUMN_DATE) + "\"";

            getWritableDatabase().update(TABLE_NAME, contentValues, whereClause, null);
        } else {
            getWritableDatabase().insert(TABLE_NAME, null, contentValues);
        }
    }

    @Nullable
    public DailyMood getDailyMood() {

        Cursor cursor = getReadableDatabase().query(TABLE_NAME,
                null,
                COLUMN_DATE + " = \"" + LocalDate.now().toString() + "\"",
                null,
                null,
                null,
                COLUMN_DATE + " DESC",
                "1");

        if (cursor.moveToFirst()) {
            String comment = cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT));
            Mood mood = Mood.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_MOOD)));

            cursor.close();

            return new DailyMood(mood, comment);
        } else {
            return null;
        }
    }

    public List<DailyMood> readSevenDaysHistory() {

        List<DailyMood> humors = new ArrayList<>();

        Cursor cursor = getReadableDatabase().query(TABLE_NAME,
                null,
                COLUMN_DATE + " < '" + LocalDate.now().toString() + "'",
                null,
                null,
                null,
                COLUMN_DATE + " DESC",
                "7");

        LocalDate previousDate = LocalDate.now();
        while (cursor.moveToNext()) {
            String comment = cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT));
            Mood mood = Mood.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_MOOD)));
            LocalDate date = LocalDate.parse(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));

            long deltaDays = ChronoUnit.DAYS.between(date, previousDate) - 1;

            for (long i = 0; i < deltaDays; i++) {
                humors.add(new DailyMood(null, null, true));
            }

            humors.add(new DailyMood(mood, comment));

            previousDate = date;
        }
        cursor.close();

        return humors;
    }

    public Map<Mood, Integer> totalHistoryMoods() {

        Map<Mood, Integer> results = new HashMap<>(Mood.values().length);

        Cursor cursor = getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Mood mood = Mood.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_MOOD)));
            Integer counts = results.get(mood);

            if (counts == null) {
                counts = 0;
            }

            counts++;
            results.put(mood, counts);
        }

        cursor.close();

        return results;
    }
}
