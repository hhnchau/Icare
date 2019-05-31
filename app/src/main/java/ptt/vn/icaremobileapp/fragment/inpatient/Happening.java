package ptt.vn.icaremobileapp.fragment.inpatient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.alert.MyAlert;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.utils.Constant;
import ptt.vn.icaremobileapp.dialog.DialogNewHappening;
import ptt.vn.icaremobileapp.fragmentutils.Directionez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;
import ptt.vn.icaremobileapp.adapter.HappeningAdapter;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;
import ptt.vn.icaremobileapp.utils.Utils;

public class Happening extends BaseFragment {
    private View view;
    private InpatientDomain inpatient;
    private HappeningAdapter adapter;
    private List<HappeningDomain> lstHappening;
    private int offset = 0;
    private int limit = 1000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.happening, container, false);

        if (getArguments() != null)
            inpatient = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT);
        if (inpatient != null) {
            initFloatButton();

            setupExpandableRecyclerView();

            getHappening(offset, limit, inpatient.getIdlink());
        }

        return view;
    }


    private void initFloatButton() {
        view.findViewById(R.id.fabAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogNewHappening.getInstance().show(getActivity(), null, new DialogNewHappening.OnClickListener() {
                    @Override
                    public void onClickListener(HappeningDomain happening) {
                        /*
                         * NEW
                         **/
                        happening.setActive(Constant.ACTIVE);
                        happening.setSiterf(Constant.SITERF);
                        happening.setIdline(Utils.newGuid());
                        happening.setIdlinedepartinfolog(Utils.newGuid());
                        happening.setIdlink(inpatient.getIdlink());
                        saveHappening(happening);
                    }
                });
            }
        });
    }

    private void setupExpandableRecyclerView() {
        RecyclerView rcv = view.findViewById(R.id.rcv);
        lstHappening = new ArrayList<>();
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HappeningAdapter(lstHappening);
        rcv.setAdapter(adapter);

        adapter.setOnItemClick(new HappeningAdapter.OnItemClick() {
            @Override
            public void onItem(HappeningDomain happening) {

                gotoInstruction(happening);
            }

            @Override
            public void onCopy(HappeningDomain happening) {
                DialogNewHappening.getInstance().show(getActivity(), happening, new DialogNewHappening.OnClickListener() {
                    @Override
                    public void onClickListener(HappeningDomain happening) {
                        /*
                         * COPY
                         **/
                        happening.setIdline(Utils.newGuid());
                        happening.setIdlinedepartinfolog(Utils.newGuid());
                        saveHappening(happening);
                    }
                });
            }

            @Override
            public void onEdit(HappeningDomain happening) {
                DialogNewHappening.getInstance().show(getActivity(), happening, new DialogNewHappening.OnClickListener() {
                    @Override
                    public void onClickListener(HappeningDomain happening) {
                        /*
                         * EDIT
                         **/
                        saveHappening(happening);
                    }
                });
            }

            @Override
            public void onDelete(final HappeningDomain happening) {
                MyAlert.getInstance().show(getActivity(), getString(R.string.txt_delete_happening), getString(R.string.btn_delete), MyAlert.REB, getString(R.string.btn_cancel), MyAlert.WHITE, false, new MyAlert.OnAlertClickListener() {
                    @Override
                    public void onYes() {
                        /*
                         * DELETE
                         **/
                        happening.setActive(Constant.DELETE);
                        deleteHappening(happening);
                    }

                    @Override
                    public void onNo() {

                    }
                });
            }
        });
    }

    private void gotoInstruction(HappeningDomain happening) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Fragmentuz.BUNDLE_KEY_HAPPENING, happening);
        bundle.putParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT, inpatient);
        if (getActivity() != null)
            //Fragmentuz.replaceFragment(getActivity().getSupportFragmentManager(), bundle, Fragmentez.INSTRUCTION, R.id.mainFrame, Directionez.NEXT);
            Fragmentuz.replaceFragment(getActivity().getSupportFragmentManager(), Fragmentez.INSTRUCTION, true, R.id.mainFrame, bundle, Directionez.NEXT);
    }

    private void getHappening(int _offset, int _limit, @NonNull String _idLink) {
        ApiController.getInstance().getHappening(getActivity(), _offset, _limit, _idLink, new ACallback<HappeningDomain>() {
            @Override
            public void response(List<HappeningDomain> listHappening) {
                lstHappening = listHappening;

                adapter.setItems(lstHappening);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void saveHappening(HappeningDomain happening) {
        ApiController.getInstance().saveHappening(getActivity(), happening, new Callback<HappeningDomain>() {
            @Override
            public void response(HappeningDomain happening) {
                boolean isNew = true;
                for (int i = 0; i < lstHappening.size(); i++)
                    if (lstHappening.get(i).getIdline().equals(happening.getIdline())) {
                        lstHappening.set(i, happening);
                        adapter.notifyDataSetChanged();
                        isNew = false;
                        break;
                    }

                if (isNew) {
                    lstHappening.add(happening);
                    adapter.setItems(lstHappening);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void deleteHappening(HappeningDomain happening) {
        ApiController.getInstance().deleteHappening(getActivity(), happening, new Callback<HappeningDomain>() {
            @Override
            public void response(HappeningDomain happening) {
                for (int i = 0; i < lstHappening.size(); i++)
                    if (lstHappening.get(i).getIdline().equals(happening.getIdline())) {
                        adapter.removeItem(i);
                        break;
                    }
            }
        });
    }

    @Override
    public void toolbarListener() {

    }
}
