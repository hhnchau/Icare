package ptt.vn.icaremobileapp.fragment.inpatient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.R;

public class ThamKham extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.thamkham, container, false);


        return view;
    }

    @Override
    public void toolbarListener() {

    }
}
