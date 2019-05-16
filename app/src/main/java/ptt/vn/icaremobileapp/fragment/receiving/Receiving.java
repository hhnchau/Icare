package ptt.vn.icaremobileapp.fragment.receiving;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.MainActivity;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.model.hi.HiCard;
import ptt.vn.icaremobileapp.model.hi.HiDomain;
import ptt.vn.icaremobileapp.scanner.Scanner;

import static android.app.Activity.RESULT_OK;


public class Receiving extends BaseFragment {
    private View view;

    private ReceivingInfo frgInfo;
    private ReceivingHi frgHi;
    private ReceivingBhi frgBhi;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.receiving, container, false);
        initFragment();
        toolbarListener();
        return view;
    }

    private void initFragment() {
        if (getActivity() != null) {
            frgInfo = new ReceivingInfo();
            frgHi = new ReceivingHi();
            frgBhi = new ReceivingBhi();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameInfo, frgInfo)
                    .add(R.id.frameHi, frgHi)
                    .add(R.id.frameBhi, frgBhi)
                    .commit();
        }
    }

    private void getHiInfo(String maThe, String hoTen, String ngaySinh) {
        ApiController.getInstance().getHiInfo(getActivity(), maThe, hoTen, ngaySinh, new Callback<HiDomain>() {
            @Override
            public void response(HiDomain hiDomain) {
                if (hiDomain != null && hiDomain.getMaKetQua().equals("000"))
                    Toast.makeText(getActivity(), "The OK", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getActivity(), "Thẻ Không Hợp Lệ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startScanner() {
        if (getActivity() != null) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //Request
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1000);
            } else {
                //Start Application
                onScanner();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1001) {
                HiCard hiCard = data.getParcelableExtra("SCANNER");
                if (hiCard != null)
                    getHiInfo(hiCard.getManagerCode(), hiCard.getName(), hiCard.getBirthday());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1000:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Start Application
                    onScanner();
                } else {
                    Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void onScanner() {
        startActivityForResult(new Intent(getActivity(), Scanner.class), 1001);
    }


    @Override
    public void toolbarListener() {
        if (getActivity() != null)
            ((MainActivity) getActivity()).toolbarClick(new MainActivity.OnToolbarListener() {
                @Override
                public void left(String frgName) {

                }

                @Override
                public void right() {
                    startScanner();
                }
            });
    }

    @Override
    public void onDestroy() {
        if (getActivity() != null)
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(frgInfo)
                    .remove(frgHi)
                    .remove(frgBhi)
                    .commit();
        super.onDestroy();
    }

}
