package ptt.vn.icaremobileapp.fragment.receive;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.fragmentutils.Directionez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentoz;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;

public class ReceivingTwo extends BaseFragment {
    private View view;
    private List<Fragmentoz> lstFragment = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.receiving_two, container, false);

        view.findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    //Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.RECEIVING_ONE, R.id.frameReceiving, null, Directionez.NEXT);
                }
            }
        });

        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.RECEIVING_ONE,false, R.id.frameReceiving, null, Directionez.NEXT);
                }
            }
        });

        return view;
    }

    @Override
    public void toolbarListener() {

    }
}
