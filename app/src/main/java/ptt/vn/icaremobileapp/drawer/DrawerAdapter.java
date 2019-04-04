package ptt.vn.icaremobileapp.drawer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ptt.vn.icaremobileapp.R;

/**
 * Created by kingpes on 9/5/18.
 */

public class DrawerAdapter extends BaseAdapter {
    private Context context;
    private List<DrawerModel> lists;

    public DrawerAdapter(Context context, List<DrawerModel> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.drawer_item, parent, false);
        }

        TextView drawerTitle = convertView.findViewById(R.id.drawerTitle);
        drawerTitle.setText(lists.get(position).getTitle());
        ImageView drawerIcon = convertView.findViewById(R.id.drawerIcon);
        drawerIcon.setImageResource(lists.get(position).getIcon());

        return convertView;
    }
}

