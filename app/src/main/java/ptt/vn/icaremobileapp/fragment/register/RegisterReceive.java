package ptt.vn.icaremobileapp.fragment.register;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.fragment.receiving.ReceivingBhi;
import ptt.vn.icaremobileapp.fragment.receiving.ReceivingHi;
import ptt.vn.icaremobileapp.fragment.receiving.ReceivingInfo;
import ptt.vn.icaremobileapp.model.shared.HiLiveData;


public class RegisterReceive extends BaseFragment {
    private View view;
    private RegisterReceiveHi frgHi;
    private RegisterReceiveBhi frgBhi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.register_receive, container, false);
        frgHi = new RegisterReceiveHi();
        frgBhi = new RegisterReceiveBhi();

        replaceFragment(frgBhi);

        return view;
    }

    private void replaceFragment(final Fragment frg) {
        if (getActivity() != null) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameInsurance, frg, frg.getClass().getName())
                            .commit();
                }
            });
        }
    }

    @Override
    public void toolbarListener() {

    }
}
