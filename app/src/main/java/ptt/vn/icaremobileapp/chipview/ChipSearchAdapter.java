package ptt.vn.icaremobileapp.chipview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;


/**
 * Created by kingpes on 9/21/18.
 */
public class ChipSearchAdapter extends ArrayAdapter<String> {

    private ChipAdapter adapter;
    private ArrayList<ChipObject> data;
    private String chr = "";

    ChipSearchAdapter(Context context, ChipAdapter adapter){
        super(context,-1);
        this.adapter = adapter;
        this.data = adapter.data;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                if(charSequence == null || charSequence.length() == 0){
                    results.values = adapter.data;
                    results.count = adapter.data.size();
                    chr="";
                }else{
                    ArrayList<Object> tmp = new ArrayList<>();
                    for(int i = 0;i < adapter.data.size();i++){
                        chr = charSequence.toString().toLowerCase();
                        if(adapter.data.get(i).getName().toLowerCase().contains(chr)){
                            tmp.add(adapter.data.get(i));
                        }
                    }
                    results.values = tmp;
                    results.count = tmp.size();
                }
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data = (ArrayList<ChipObject>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        int pos = adapter.data.indexOf(data.get(position));
        if(pos != -1) {
            return adapter.createSearchView(getContext(), adapter.isSelected(pos), pos, chr);
        }else {
            return null;
        }
    }
}
