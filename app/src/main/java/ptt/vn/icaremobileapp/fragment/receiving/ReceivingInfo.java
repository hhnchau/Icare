package ptt.vn.icaremobileapp.fragment.receiving;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;

public class ReceivingInfo extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.receiving_info, container, false);



        return view;
    }

    @Override
    public void toolbarListener() {

    }
}