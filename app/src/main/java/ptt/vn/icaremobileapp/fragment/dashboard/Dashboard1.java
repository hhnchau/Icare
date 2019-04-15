package ptt.vn.icaremobileapp.fragment.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.animation.Animationz;


/**
 * Created by kingpes on 9/13/18.
 */

public class Dashboard1 extends Fragment {
    private View view;
    private TextView mainText, content1, content2, content3;
    static int s = 0;
    static int s1 = 0;
    static int s2 = 0;
    static int s3 = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dashboard1_fragment, container, false);
        initView();
        return view;
    }

    private void initView(){
        mainText = view.findViewById(R.id.mainText);
        content1 = view.findViewById(R.id.content1);
        content2 = view.findViewById(R.id.content2);
        content3 = view.findViewById(R.id.content3);
    }

    public void updateData(final int i, final int i1, final int i2, final int i3){

        s += i;
        s1 += i1;
        s2 += i2;
        s3 += i3;

        Animationz.setFadeEffect(mainText, String.valueOf(s));
        Animationz.setFadeEffect(content1, String.valueOf(s1));
        Animationz.setFadeEffect(content2, String.valueOf(s2));
        Animationz.setFadeEffect(content3, String.valueOf(s3));


    }
}
