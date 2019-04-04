package ptt.vn.icaremobileapp.chipview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.utils.Utils;


/**
 * Created by kingpes on 9/21/18.
 */

public class ChipViewAdapter extends ChipAdapter{

    private ArrayList<ChipObject> search_data;
    private ArrayList<Object> chips = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public ChipViewAdapter(ArrayList<ChipObject> search_data, OnItemClickListener onItemClickListener){
        this.search_data = search_data;
        this.data = search_data;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public Object getItem(int pos) {
        return search_data.get(pos);
    }

    @Override
    public boolean isSelected(int pos) {
        return chips.contains(search_data.get(pos));
    }

    @Override
    public View createSearchView(Context context, final boolean is_checked, final int pos, String chr) {
        View view = View.inflate(context,R.layout.chip_search_item,null);

        ChipObject data = search_data.get(pos);
        if (data != null) {

            final ImageView cb = view.findViewById(R.id.tvSn);

            if (is_checked) {
                cb.setImageResource(R.drawable.ic_checked);
            } else {
                cb.setImageResource(R.drawable.ic_uncheck);
            }

            TextView tvService = view.findViewById(R.id.tvName);
            tvService.setText(Utils.spannable(data.getName(), chr));

            TextView tvUnit = view.findViewById(R.id.tvAddress);
            tvUnit.setText(data.getUnit());

            TextView tvPrice = view.findViewById(R.id.tvGender);
            tvPrice.setText(Utils.formatCurrency(data.getPrice()));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!is_checked) {
                        chips.add(search_data.get(pos));
                        refresh();
                    } else {
                        chips.remove(search_data.get(pos));
                        refresh();
                    }
                }
            });
        }

        return view;
    }

    @Override
    public View createChip(final Context context, final int pos) {
        View view = View.inflate(context, R.layout.chip_item, null);

        ChipObject data = search_data.get(pos);
        if (data!= null) {

            TextView tvChip = view.findViewById(R.id.tvChip);
            tvChip.setText(data.getName());

            if (onItemClickListener != null)
                onItemClickListener.onClick(chips);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chips.remove(search_data.get(pos));
                    refresh();
                    if (onItemClickListener != null)
                        onItemClickListener.onClick(chips);
                }
            });
        }
        return view;
    }

    @Override
    public int getCount() {
        return search_data.size();
    }

}
