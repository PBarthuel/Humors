package paul.barthuel.humors;

public class DailyMood {

    //create a dailyMood how have in parameters a String and an Enum
    private Mood mMood;
    private String mComment;
    private boolean mIsEmpty;

    public DailyMood(Mood mood, String comment) {
        this(mood, comment, false);
    }

    public DailyMood(Mood mood, String comment, boolean isEmpty) {
        this.mMood = mood;
        this.mComment = comment;
        this.mIsEmpty = isEmpty;
    }

    public Mood getMood() {
        return mMood;
    }

    public String getComment() {
        return mComment;
    }

    public boolean isEmpty() {
        return mIsEmpty;
    }
}
