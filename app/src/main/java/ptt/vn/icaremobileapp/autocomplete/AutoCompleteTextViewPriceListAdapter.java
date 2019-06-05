package ptt.vn.icaremobileapp.autocomplete;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.model.serviceitem.MapPriceServiceItemHDomain;
import ptt.vn.icaremobileapp.utils.Utils;

public class AutoCompleteTextViewPriceListAdapter extends ArrayAdapter<MapPriceServiceItemHDomain> {
    private List<MapPriceServiceItemHDomain> lists;
    private String chr = "";

    public AutoCompleteTextViewPriceListAdapter(@NonNull Context context, @NonNull List<MapPriceServiceItemHDomain> lists) {
        super(context, 0, lists);
        this.lists = new ArrayList<>(lists);
    }


    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.autocompleteitem, parent, false);
        }

        TextView txt = convertView.findViewById(R.id.tvName);

        final MapPriceServiceItemHDomain complete = getItem(position);

        if (complete != null) {
            txt.setText(Utils.spannable(complete.getName(), chr));
        }

        return convertView;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<MapPriceServiceItemHDomain> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(lists);
            } else {
                chr = constraint.toString().toLowerCase();

                for (MapPriceServiceItemHDomain item : lists) {
                    if (item.getName().toLowerCase().contains(chr)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                clear();
                addAll((List) results.values);
                notifyDataSetChanged();
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((MapPriceServiceItemHDomain) resultValue).getName();
        }
    };

    public void setItems(List<MapPriceServiceItemHDomain> lists) {
        this.lists = lists;
    }
}
