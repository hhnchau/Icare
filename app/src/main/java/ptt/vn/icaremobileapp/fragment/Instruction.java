package ptt.vn.icaremobileapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.enums.Directionez;
import ptt.vn.icaremobileapp.enums.Fragmentez;
import ptt.vn.icaremobileapp.expandcardview.ExpandableCardView;
import ptt.vn.icaremobileapp.togglebutton.MyTabButton;
import ptt.vn.icaremobileapp.utils.Fragmentuz;

public class Instruction extends BaseFragment {
    private View view;
    private FragmentManager fragmentManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.instruction, container, false);
        if (getActivity() != null)
            fragmentManager = getActivity().getSupportFragmentManager();

        setupExpandableCardView();

        MyTabButton myTabButton = view.findViewById(R.id.toggle);
        myTabButton.setOnToggleSelectedListener(new MyTabButton.OnToggledListener() {
            @Override
            public void onToggled(Fragmentez fzg) {
                if (getActivity() != null) {
                    switch (fzg) {
                        case THAM_KHAM:
                            Fragmentuz.addFrame(fragmentManager, Fragmentez.THAM_KHAM, R.id.frame, Directionez.NEXT);
                            break;
                        case SERVICE_ITEM:
                            Fragmentuz.addFrame(fragmentManager, Fragmentez.SERVICE_ITEM, R.id.frame, Directionez.NEXT);
                            break;
                        case DRUG_ORDER:
                            Fragmentuz.addFrame(fragmentManager, Fragmentez.DRUG_ORDER, R.id.frame, Directionez.NEXT);
                            break;
                        case DRUG_ORDER_OUTSIDE:
                            Fragmentuz.addFrame(fragmentManager, Fragmentez.DRUG_ORDER_OUTSIDE, R.id.frame, Directionez.NEXT);
                            break;
                        case DIAGNOSE:
                            Fragmentuz.addFrame(fragmentManager, Fragmentez.DIAGNOSE, R.id.frame, Directionez.NEXT);
                            break;
                    }
                }
            }
        });


        return view;
    }

    private void setupExpandableCardView() {
        final ExpandableCardView expandableCardViewInfo = view.findViewById(R.id.epCardInfo);
        //expandableCardViewInfo.setChildrenView("Demo");

        expandableCardViewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandableCardViewInfo.isExpanded())
                    expandableCardViewInfo.collapse();
                else expandableCardViewInfo.expand();
            }
        });

    }
}
