package ptt.vn.icaremobileapp.chipview;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;


/**
 * Created by kingpes on 9/21/18.
 */

public abstract class ChipAdapter {

    private ChipView chipView;

    public ArrayList<ChipObject> data = new ArrayList<>();
    public abstract Object getItem(int pos);
    public abstract boolean isSelected(int pos);

    public abstract View createSearchView(Context context, boolean is_checked, int pos, String chr);
    public abstract View createChip(Context context, int pos);

    void setChipView(ChipView chipView){
        this.chipView = chipView;
    }


    void refresh(){
        chipView.notifyDataSetChanged();
    }

    public abstract int getCount();

    public interface OnItemClickListener{
        void onClick(ArrayList<Object> listChips);
    }

}
