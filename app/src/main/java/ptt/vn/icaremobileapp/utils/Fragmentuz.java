package ptt.vn.icaremobileapp.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;
import java.util.Objects;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.enums.Directionez;
import ptt.vn.icaremobileapp.enums.Fragmentez;
import ptt.vn.icaremobileapp.fragment.dashboard.Dashboard;
import ptt.vn.icaremobileapp.fragment.inpatient.Diagnose;
import ptt.vn.icaremobileapp.fragment.inpatient.DrugOrder;
import ptt.vn.icaremobileapp.fragment.inpatient.DrugOrderOutside;
import ptt.vn.icaremobileapp.fragment.inpatient.Happening;
import ptt.vn.icaremobileapp.fragment.inpatient.InpatientList;
import ptt.vn.icaremobileapp.fragment.inpatient.Instruction;
import ptt.vn.icaremobileapp.fragment.inpatient.ServiceItem;
import ptt.vn.icaremobileapp.fragment.inpatient.ThamKham;

public class Fragmentuz {
    public static final String BUNDLE_KEY_INPATIENT = "INPATIENT";
    public static final String BUNDLE_KEY_HAPPENING = "HAPPENING";

    public static void addMainFrame(FragmentManager fragmentManager, Bundle bundle, Fragmentez fzg, int frame, Directionez direction) {

        Fragment frg = null;
        String name = null;
        String stack = null;

        int enter, exit, pop_enter, pop_exit;
        if (direction == Directionez.NEXT) {
            enter = R.anim.enter_from_right;
            exit = R.anim.exit_to_left;

            pop_enter = R.anim.enter_from_left;
            pop_exit = R.anim.exit_to_right;
        } else {
            enter = R.anim.enter_from_left;
            exit = R.anim.exit_to_right;

            pop_enter = R.anim.enter_from_right;
            pop_exit = R.anim.exit_to_left;
        }

        switch (fzg) {
            case INPATIENT_LIST:
                name = InpatientList.class.getName();
                frg = new InpatientList();
                stack = Fragmentez.INPATIENT_LIST.name();
                break;
            case HAPPENING:
                name = Happening.class.getName();
                frg = new Happening();
                stack = Fragmentez.HAPPENING.name();
                break;
            case INSTRUCTION:
                name = Instruction.class.getName();
                frg = new Instruction();
                stack = Fragmentez.INSTRUCTION.name();
                break;
            case DASHBOARD:
                name = Dashboard.class.getName();
                frg = new Dashboard();
                stack = Fragmentez.DASHBOARD.name();
                break;
        }

        if (frg != null && bundle != null)
            frg.setArguments(bundle);

        if (fragmentManager != null && frg != null)
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(enter, exit, pop_enter, pop_exit)
                    .addToBackStack(stack)
                    .replace(frame, frg, name)
                    .commit();
    }

    public static void addFrame(FragmentManager fragmentManager, Fragmentez fzg, int frame, Directionez direction) {

        Fragment frg = null;
        String name = null;

        int enter, exit;
        if (direction == Directionez.NEXT) {
            enter = R.anim.enter_from_right;
            exit = R.anim.exit_to_left;
        } else {
            enter = R.anim.enter_from_left;
            exit = R.anim.exit_to_right;
        }

//        boolean isExist = false;
//        for (Fragmentez frgez : lstFragmentez) {
//            if (frgez == fzg) {
//                isExist = true;
//                break;
//            }
//        }
//
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//        if (isExist) {
//            //Hide
//            for (int i = 0; i < lstFragment.size(); i++) {
//                transaction.hide(lstFragment.get(i));
//            }
//            transaction.setCustomAnimations(enter, exit);
//            transaction.show(getFragment(fzg, lstFragment, lstFragmentez));
//            transaction.commit();
//
//            //Show
//        } else {
//            //Add New
//        }

        switch (fzg) {

            case THAM_KHAM:
                name = ThamKham.class.getName();
                frg = new ThamKham();
                break;
            case SERVICE_ITEM:
                name = ServiceItem.class.getName();
                frg = new ServiceItem();
                break;
            case DRUG_ORDER:
                name = DrugOrder.class.getName();
                frg = new DrugOrder();
                break;
            case DRUG_ORDER_OUTSIDE:
                name = DrugOrderOutside.class.getName();
                frg = new DrugOrderOutside();
                break;
            case DIAGNOSE:
                name = Diagnose.class.getName();
                frg = new Diagnose();
                break;
        }

//        fragmentManager.beginTransaction()
//                .setCustomAnimations(enter, exit)
//                .show(frg)
//                .commit();
//
//        fragmentManager.beginTransaction()
//                .hide(frg)
//                .setCustomAnimations(enter, exit)
//                .show(frg)
//                .commit();

        if (fragmentManager != null && frg != null)
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(enter, exit)
                    .add(frame, frg, name)
                    .commit();
    }

    private static BaseFragment getFragment(Fragmentez fzg, List<BaseFragment> lstFragment, List<Fragmentez> lstFragmentez) {
        for (int i = 0; i < lstFragmentez.size(); i++) {
            if (fzg == lstFragmentez.get(i)) {
                return lstFragment.get(i);
            }
        }
        return null;
    }

    public static void clearPopBackStack(FragmentManager fragmentManager) {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
    }
}
