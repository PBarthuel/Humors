package paul.barthuel.humors;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

public enum Mood {
    SUPER_HAPPY(R.color.banana_yellow, R.drawable.smiley_super_happy),
    HAPPY(R.color.light_sage, R.drawable.smiley_happy),
    NORMAL(R.color.cornflower_blue_65, R.drawable.smiley_normal),
    DISAPPOINTED(R.color.warm_grey, R.drawable.smiley_disappointed),
    SAD(R.color.faded_red, R.drawable.smiley_sad);

    @ColorRes
    private int mColorRes;
    @DrawableRes
    private int mDrawableRes;

    @ColorRes
    public int getColorRes() {
        return mColorRes;
    }

    @DrawableRes
    public int getDrawableRes() {
        return mDrawableRes;
    }

    Mood(@ColorRes int colorRes, @DrawableRes int drawableRes) {
        mColorRes = colorRes;
        mDrawableRes = drawableRes;
    }
}
