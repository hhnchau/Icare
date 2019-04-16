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

public class Dashboard4 extends Fragment {
    private View view;
    private TextView mainText, patientNumber, hourNumber;

    static int s = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dashboard4_fragment, container, false);
        initView();
        return view;
    }

    private void initView() {
        mainText = view.findViewById(R.id.mainText);
        patientNumber = view.findViewById(R.id.patientNumber);
        hourNumber = view.findViewById(R.id.hourNumber);
    }

    public void updateData(int value){
        s = s + value;

        Animationz.setFadeEffect(mainText, String.valueOf(s));
        Animationz.setFadeEffect(patientNumber, String.valueOf(s));
    }
}
