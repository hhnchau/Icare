package ptt.vn.icaremobileapp.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import ptt.vn.icaremobileapp.R;


/**
 * Created by kingpes on 9/13/18.
 */

public class Animationz {

    public static void setFadeEffect(final View v, final String s){
        Animation out = AnimationUtils.loadAnimation(v.getContext(), R.anim.fadeout);
        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (v instanceof TextView) {
                    ((TextView) v).setText(s);
                    Animation in = AnimationUtils.loadAnimation(v.getContext(), R.anim.fadein);
                    v.startAnimation(in);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        v.startAnimation(out);
    }



    public static void alphaz(View v){
        Animation zoom = AnimationUtils.loadAnimation(v.getContext(), R.anim.alphaz);
        v.startAnimation(zoom);
    }

    public static void zoom(View v){
        Animation zoom = AnimationUtils.loadAnimation(v.getContext(), R.anim.zoom);
        v.startAnimation(zoom);
    }

    public static void scale(View v){
        ScaleAnimation scale = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(500);
        scale.setRepeatCount(0);
        scale.setFillAfter(true);
        v.startAnimation(scale);
    }
}
