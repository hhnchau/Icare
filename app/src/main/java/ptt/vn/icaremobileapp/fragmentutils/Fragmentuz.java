package ptt.vn.icaremobileapp.fragmentutils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import ptt.vn.icaremobileapp.R;
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

    public static void replaceFrame(FragmentManager fragmentManager, Bundle bundle, Fragmentez fzg, int frame, Directionez direction) {

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

    public static void addFrame(List<Fragmentoz> lstFragment, FragmentManager fragmentManager, Fragmentez fzg, int frame, Directionez direction) {
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
                transaction
                        .setCustomAnimations(enter, exit)
                        .add(frame, frg)
                        .commit();
                lstFragment.add(new Fragmentoz(fzg, frg));
            }
        }
    }

    private static Fragment getFragment(Fragmentez fzg) {
        switch (fzg) {
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
        }
        return null;
    }

    public static void clearPopBackStack(FragmentManager fragmentManager) {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
    }
}
