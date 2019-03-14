package paul.barthuel.humors;

public class DailyMood {

    private Mood mMood;
    private String mComment;

    public DailyMood(Mood mood, String comment) {
        this.mMood = mood;
        this.mComment = comment;
    }

    public Mood getMood() {
        return mMood;
    }

    public String getComment() {
        return mComment;
    }
}
