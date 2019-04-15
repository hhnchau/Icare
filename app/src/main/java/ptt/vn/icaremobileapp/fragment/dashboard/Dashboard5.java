package ptt.vn.icaremobileapp.fragment.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.animation.Animationz;
import ptt.vn.icaremobileapp.utils.Utils;


/**
 * Created by kingpes on 9/13/18.
 */

public class Dashboard5 extends Fragment {
    private View view;
    private View frameProgressbar;
    private ProgressBar progressBar;
    private TextView tvProgressBar;
    private TextView revenueTodayNumber, targetNumber, averageNumber;

    static private double revenue = 0;
    static private double target = 300000000;
    static private int percent = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dashboard5_fragment, container, false);
        initView();


        targetNumber.setText(Utils.formatCurrency((int) target));



        return view;
    }

    private void initView() {
        frameProgressbar = view.findViewById(R.id.frameProgressBar);
        progressBar = view.findViewById(R.id.progressBar);
        tvProgressBar = view.findViewById(R.id.tvProgressbar);
        tvProgressBar.setText(String.valueOf(0));

        revenueTodayNumber = view.findViewById(R.id.revenueTodayNumber);
        targetNumber = view.findViewById(R.id.targetNumber);
        averageNumber = view.findViewById(R.id.averageNumber);
    }

    public void updateData(int value) {

        revenue = revenue + value;

        Animationz.setFadeEffect(revenueTodayNumber, Utils.formatCurrency((int) revenue));

        int p = (int) ((100 * revenue) /target);


        if (p > percent) {

            percent = p;

            Animationz.scale(frameProgressbar);
            progressBar.setProgress(percent);
            tvProgressBar.setText(String.valueOf(percent));
        }
    }
}
