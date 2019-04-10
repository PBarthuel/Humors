package paul.barthuel.humors;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;

public enum Mood {
    SUPER_HAPPY(R.color.banana_yellow, R.drawable.smiley_super_happy, 1f),
    HAPPY(R.color.light_sage, R.drawable.smiley_happy, 0.8f),
    NORMAL(R.color.cornflower_blue_65, R.drawable.smiley_normal, 0.6f),
    DISAPPOINTED(R.color.warm_grey, R.drawable.smiley_disappointed, 0.4f),
    SAD(R.color.faded_red, R.drawable.smiley_sad, 0.2f);

    @ColorRes
    private int mColorRes;
    @DrawableRes
    private int mDrawableRes;
    @FloatRange(from = 0, to = 1)
    private float mPercent;

    @FloatRange(from = 0, to = 1)
    public float getPercent() {
        return mPercent;
    }

    @ColorRes
    public int getColorRes() {
        return mColorRes;
    }

    @DrawableRes
    public int getDrawableRes() {
        return mDrawableRes;
    }

    Mood(@ColorRes int colorRes, @DrawableRes int drawableRes, @FloatRange(from = 0, to = 1) float percent) {
        mColorRes = colorRes;
        mDrawableRes = drawableRes;
        mPercent = percent;
    }
}
