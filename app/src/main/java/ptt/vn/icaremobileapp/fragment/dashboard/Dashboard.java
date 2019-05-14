package ptt.vn.icaremobileapp.fragment.dashboard;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.fragment.dashboard.Dashboard1;
import ptt.vn.icaremobileapp.fragment.dashboard.Dashboard2;
import ptt.vn.icaremobileapp.fragment.dashboard.Dashboard3;
import ptt.vn.icaremobileapp.fragment.dashboard.Dashboard4;
import ptt.vn.icaremobileapp.fragment.dashboard.Dashboard5;
import ptt.vn.icaremobileapp.fragment.dashboard.Dashboard6;

public class Dashboard extends Fragment {
    private TextView header1, header2, header3, header4;

    int i = 0;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;

    int ii = 0;
    int ii1 = 0;
    int ii2 = 0;
    int ii3 = 0;

    int iii = 0;
    int iii1 = 0;
    int iii2 = 0;
    int iii3 = 0;

    int iiii = 0;


    int iiiii = 0;


    int iiiiii = 0;


    private Dashboard1 frame1;
    private Dashboard2 frame2;
    private Dashboard3 frame3;
    private Dashboard4 frame4;
    private Dashboard5 frame5;
    private Dashboard6 frame6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard, container, false);


        frame1 = new Dashboard1();
        frame2 = new Dashboard2();
        frame3 = new Dashboard3();
        frame4 = new Dashboard4();
        frame5 = new Dashboard5();
        frame6 = new Dashboard6();

        if (getActivity() != null)
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame1, frame1, Dashboard1.class.getName())
                    .add(R.id.frame2, frame2, Dashboard2.class.getName())
                    .add(R.id.frame3, frame3, Dashboard3.class.getName())
                    .add(R.id.frame4, frame4, Dashboard4.class.getName())
                    .add(R.id.frame5, frame5, Dashboard5.class.getName())
                    .add(R.id.frame6, frame6, Dashboard6.class.getName())
                    .commit();


        header1 = view.findViewById(R.id.header1);
        header2 = view.findViewById(R.id.header2);
        header3 = view.findViewById(R.id.header3);
        header4 = view.findViewById(R.id.header4);

        header1.setText("NGOẠI TRÚ");
        header2.setText("NỘI TRÚ");
        header3.setText("PHÒNG KHÁM");
        header4.setText("NHẬP VIỆN");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scheduleFragment1();
                scheduleFragment2();
                scheduleFragment3();
                scheduleFragment4();
                scheduleFragment5();
                scheduleFragment6();

            }
        }, 300);


        return view;
    }

    private void scheduleFragment1() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getActivity() != null) {

                    int min = 1;
                    int max = 3;
                    i = new Random().nextInt(max - min) + min;
                    i1 = new Random().nextInt(max - min) + min;
                    i2 = new Random().nextInt(max - min) + min;
                    i3 = new Random().nextInt(max - min) + min;

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Dashboard1 f1 = (Dashboard1) getActivity().getSupportFragmentManager().findFragmentByTag(Dashboard1.class.getName());
                            if (f1 != null) {
                                f1.updateData(i, i1, i2, i3);
                            }
                        }
                    });
                }
            }
        }, 0, 5000);

    }

    private void scheduleFragment2() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getActivity() != null) {

                    int min = 1;
                    int max = 3;
                    ii = new Random().nextInt(max - min) + min;
                    ii1 = new Random().nextInt(max - min) + min;
                    ii2 = new Random().nextInt(max - min) + min;
                    ii3 = new Random().nextInt(max - min) + min;

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Dashboard2 f2 = (Dashboard2) getActivity().getSupportFragmentManager().findFragmentByTag(Dashboard2.class.getName());
                            if (f2 != null) {
                                f2.updateData(ii, ii1, ii2, ii3);
                            }
                        }
                    });
                }
            }
        }, 0, 7000);

    }

    private void scheduleFragment3() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getActivity() != null) {

                    int min = 1;
                    int max = 3;
                    iii = new Random().nextInt(max - min) + min;
                    iii1 = new Random().nextInt(max - min) + min;
                    iii2 = new Random().nextInt(max - min) + min;
                    iii3 = new Random().nextInt(max - min) + min;

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Dashboard3 f3 = (Dashboard3) getActivity().getSupportFragmentManager().findFragmentByTag(Dashboard3.class.getName());
                            if (f3 != null) {
                                f3.updateData(iii, iii1, iii2, iii3);
                            }
                        }
                    });
                }
            }
        }, 0, 10000);

    }

    private void scheduleFragment4() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getActivity() != null) {

                    int min = 1;
                    int max = 3;
                    iiii = new Random().nextInt(max - min) + min;

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Dashboard4 f4 = (Dashboard4) getActivity().getSupportFragmentManager().findFragmentByTag(Dashboard4.class.getName());
                            if (f4 != null) {
                                f4.updateData(iiii);
                            }
                        }
                    });
                }
            }
        }, 0, 10000);

    }

    private void scheduleFragment5() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getActivity() != null) {

                    int min = 1;
                    int max = 10;
                    iiiii = new Random().nextInt(max - min) + min;

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Dashboard5 f5 = (Dashboard5) getActivity().getSupportFragmentManager().findFragmentByTag(Dashboard5.class.getName());
                            if (f5 != null) {
                                f5.updateData(iiiii * 100000);
                            }
                        }
                    });
                }
            }
        }, 0, 10000);

    }

    private void scheduleFragment6() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getActivity() != null) {

                    int min = 1;
                    int max = 3;
                    iiiiii = new Random().nextInt(max - min) + min;

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Dashboard6 f6 = (Dashboard6) getActivity().getSupportFragmentManager().findFragmentByTag(Dashboard6.class.getName());
                            if (f6 != null) {
                                f6.updateData(iiiiii);
                            }
                        }
                    });
                }
            }
        }, 0, 10000);

    }

    @Override
    public void onDestroy() {
        if (getActivity() != null)
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(frame1)
                    .remove(frame2)
                    .remove(frame3)
                    .remove(frame4)
                    .remove(frame5)
                    .remove(frame6)
                    .commit();
        super.onDestroy();

    }
}
