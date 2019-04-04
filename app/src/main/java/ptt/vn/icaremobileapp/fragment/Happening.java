package ptt.vn.icaremobileapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.InpatientListAdapter;
import ptt.vn.icaremobileapp.dialog.DialogNewHappening;
import ptt.vn.icaremobileapp.enums.Directionez;
import ptt.vn.icaremobileapp.enums.Fragmentez;
import ptt.vn.icaremobileapp.expandcardview.ExpandableCardView;
import ptt.vn.icaremobileapp.expandrecyclerview.DetailModel;
import ptt.vn.icaremobileapp.expandrecyclerview.ExpandableAdapter;
import ptt.vn.icaremobileapp.expandrecyclerview.HeaderModel;
import ptt.vn.icaremobileapp.utils.Fragmentuz;

public class Happening extends BaseFragment {
    private View view;
    private FloatingActionButton fabAdd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.happening, container, false);

        fabAdd = view.findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogNewHappening.getInstance().show(getActivity(), null);
            }
        });


        setupExpandableCardView();
        setupExpandableRecyclerView();


        return view;
    }


    private void setupExpandableCardView() {
        final ExpandableCardView expandableCardView = view.findViewById(R.id.epCardInfo);
        //expandableCardView.setChildrenView("Demo");

        expandableCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandableCardView.isExpanded())
                    expandableCardView.collapse();
                else expandableCardView.expand();
            }
        });
    }

    private void setupExpandableRecyclerView() {
        List<DetailModel> lstDetail = new ArrayList<>();
        lstDetail.add(new DetailModel(getString(R.string.txt_happening)));

        List<DetailModel> lstDetail1 = new ArrayList<>();
        lstDetail1.add(new DetailModel("Hoan"));

        List<DetailModel> lstDetail2 = new ArrayList<>();
        lstDetail2.add(new DetailModel("Tri"));

        List<DetailModel> lstDetail3 = new ArrayList<>();
        lstDetail3.add(new DetailModel("Dai"));

        List<HeaderModel> lst = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lst.add(new HeaderModel(getString(R.string.txt_happening), lstDetail));
            lst.add(new HeaderModel("Hoan", lstDetail1));
            lst.add(new HeaderModel("Tri", lstDetail2));
            lst.add(new HeaderModel("Dai", lstDetail3));
        }

        RecyclerView rcv = view.findViewById(R.id.rcv);
        ExpandableAdapter adapter = new ExpandableAdapter(getActivity(), lst);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(adapter);

        adapter.setOnItemClick(new ExpandableAdapter.OnItemClick() {
            @Override
            public void onHeaderClick(int p) {
                if (getActivity() != null)
                    Fragmentuz.addMainFrame(getActivity().getSupportFragmentManager(), Fragmentez.INSTRUCTION, R.id.mainFrame, Directionez.NEXT);
                }

        });

    }
}
