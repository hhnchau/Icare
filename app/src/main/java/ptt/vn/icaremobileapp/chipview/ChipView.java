package ptt.vn.icaremobileapp.chipview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.flexbox.FlexboxLayout;

import ptt.vn.icaremobileapp.R;

/**
 * Created by kingpes on 9/21/18.
 *
 * How to use
 *
 * xml:    <ptt.vn.icaremobileapp.chipview.ChipView
 *         android:id="@+id/cvTag"
 *         android:layout_width="match_parent"
 *         android:layout_height="match_parent"/>
 *
 * activity:
 *
 */

public class ChipView extends RelativeLayout {

    private FlexboxLayout flChips;
    private EditText etSearch;
    private ListView lvList;
    private ChipAdapter adapter;
    private ChipSearchAdapter simpleSearchAdapter;

    public ChipView(Context context) {
        super(context);
        init();
    }

    public ChipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        View view = inflate(getContext(), R.layout.chip_view,this);
        flChips = view.findViewById(R.id.flChips);
        etSearch = view.findViewById(R.id.etSearch);
        lvList = view.findViewById(R.id.lvList);
    }

    public void setAdapter(ChipAdapter adapter){
        this.adapter = adapter;
        adapter.setChipView(this);
        simpleSearchAdapter = new ChipSearchAdapter(getContext(),adapter);
        lvList.setAdapter(simpleSearchAdapter);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                simpleSearchAdapter.getFilter().filter(editable.toString());
            }
        });
    }

    public void notifyDataSetChanged(){
        refreshFlexBox();
        simpleSearchAdapter.notifyDataSetChanged();
    }

    private void refreshFlexBox(){
        for(int i = flChips.getChildCount() - 1; i >= 0;i--){
            if(flChips.indexOfChild(etSearch) != i){
                flChips.removeViewAt(i);
            }
        }
        for(int i = 0;i < adapter.getCount();i++){
            if(adapter.isSelected(i)) {
                View v = adapter.createChip(getContext(), i);
                flChips.addView(v,0);
            }
        }
    }
}
