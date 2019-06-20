package ptt.vn.icaremobileapp.fragmentutils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.fragment.dashboard.Dashboard;
import ptt.vn.icaremobileapp.fragment.history.History;
import ptt.vn.icaremobileapp.fragment.history.HistoryClinic;
import ptt.vn.icaremobileapp.fragment.history.HistoryRegister;
import ptt.vn.icaremobileapp.fragment.inpatient.Diagnose;
import ptt.vn.icaremobileapp.fragment.inpatient.DrugOrder;
import ptt.vn.icaremobileapp.fragment.inpatient.DrugOrderOutside;
import ptt.vn.icaremobileapp.fragment.inpatient.Happening;
import ptt.vn.icaremobileapp.fragment.inpatient.HappeningFrame;
import ptt.vn.icaremobileapp.fragment.inpatient.Inpatient;
import ptt.vn.icaremobileapp.fragment.inpatient.Instruction;
import ptt.vn.icaremobileapp.fragment.inpatient.Resolved;
import ptt.vn.icaremobileapp.fragment.inpatient.ServiceItem;
import ptt.vn.icaremobileapp.fragment.inpatient.ThamKham;
import ptt.vn.icaremobileapp.fragment.receiving.Receiving;
import ptt.vn.icaremobileapp.fragment.receiving.ReceivingHi;
import ptt.vn.icaremobileapp.fragment.receiving.ReceivingInfo;
import ptt.vn.icaremobileapp.fragment.register.Register;
import ptt.vn.icaremobileapp.fragment.register.RegisterInfo;
import ptt.vn.icaremobileapp.fragment.register.RegisterReceive;
import ptt.vn.icaremobileapp.fragment.register.RegisterServiceItem;
import ptt.vn.icaremobileapp.fragment.search.PatientList;

public class Fragmentuz {
    public static final String BUNDLE_KEY_INPATIENT = "INPATIENT";
    public static final String BUNDLE_KEY_HAPPENING = "HAPPENING";
    public static final String BUNDLE_KEY_PATIENT = "PATIENT";

    public static void replaceFragment(FragmentManager fragmentManager, Fragmentez fzg, boolean backStack, int frame, Bundle bundle, Directionez direction) {

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

        Fragment frg = getFragment(fzg);

        if (frg != null && bundle != null)
            frg.setArguments(bundle);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (frg != null) {

            if (backStack) transaction.addToBackStack(frg.getClass().getName());

            transaction
                    .setCustomAnimations(enter, exit, pop_enter, pop_exit)
                    .replace(frame, frg, frg.getClass().getName())
                    .commit();
        }
    }

    public static void addFragment(List<Fragmentoz> lstFragment, FragmentManager fragmentManager, Fragmentez fzg, boolean backStack, int frame, Bundle bundle, Directionez direction) {
        boolean exist = false;
        for (Fragmentoz item : lstFragment)
            if (fzg == item.getFzg()) {
                exist = true;
                break;
            }


        int enter, exit;
        if (direction == Directionez.NEXT) {
            enter = R.anim.enter_from_right;
            exit = R.anim.exit_to_left;
        } else {
            enter = R.anim.enter_from_left;
            exit = R.anim.exit_to_right;
        }

        if (exist) {
            //Exist
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Fragment frg = null;

            for (Fragmentoz item : lstFragment) {
                transaction.hide(item.getFrg());
                if (fzg == item.getFzg()) frg = item.getFrg();
            }
            if (frg != null) {
                transaction.setCustomAnimations(enter, exit)
                        .show(frg)
                        .commit();
            }
        } else {
            //Add
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            for (Fragmentoz item : lstFragment) {
                if (fzg != item.getFzg()) transaction.hide(item.getFrg());
            }
            Fragment frg = getFragment(fzg);

            if (frg != null) {

                if (bundle != null) frg.setArguments(bundle);

                if (backStack) transaction.addToBackStack(frg.getClass().getName());

                transaction
                        .setCustomAnimations(enter, exit)
                        .add(frame, frg, frg.getClass().getName())
                        .commit();
                lstFragment.add(new Fragmentoz(fzg, frg));
            }
        }
    }

    private static Fragment getFragment(Fragmentez fzg) {
        switch (fzg) {
            case INPATIENT:
                return new Inpatient();
            case HAPPENINGFRAME:
                return new HappeningFrame();
            case INSTRUCTION:
                return new Instruction();
            case DASHBOARD:
                return new Dashboard();
            case RECEIVING:
                return new Receiving();
            case THAM_KHAM:
                return new ThamKham();
            case SERVICE_ITEM:
                return new ServiceItem();
            case DRUG_ORDER:
                return new DrugOrder();
            case DRUG_ORDER_OUTSIDE:
                return new DrugOrderOutside();
            case DIAGNOSE:
                return new Diagnose();
            case HAPPENING:
                return new Happening();
            case RESOLVED:
                return new Resolved();
            case RECEIVING_HI:
                return new ReceivingHi();
            case RECEIVING_INFO:
                return new ReceivingInfo();
            case REGISTER:
                return new Register();
            case REGISTER_INFO:
                return new RegisterInfo();
            case REGISTER_RECEIVE:
                return new RegisterReceive();
            case REGISTER_SERVICEITEM:
                return new RegisterServiceItem();
            case PATIENT_LIST:
                return new PatientList();
            case HISTORY:
                return new History();
            case HISTORY_REGISTER:
                return new HistoryRegister();
            case HISTORY_CLINIC:
                return new HistoryClinic();

        }
        return null;
    }

    public static void removeFragment(List<Fragmentoz> lstFragment, FragmentManager fragmentManager) {
        if (fragmentManager != null && lstFragment != null)
            for (Fragmentoz f : lstFragment)
                for (Fragment frg : fragmentManager.getFragments())
                    if (frg == f.getFrg()) {
                        fragmentManager.beginTransaction().remove(frg).commit();
                        break;
                    }
    }

    public static void removeAllFragment(FragmentManager fragmentManager) {
        if (fragmentManager != null)
            for (Fragment frg : fragmentManager.getFragments())
                fragmentManager.beginTransaction().remove(frg).commit();
    }

    public static void clearAllPopBackStack(FragmentManager fragmentManager) {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
    }
}
