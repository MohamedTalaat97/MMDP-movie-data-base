package com.example.mmdp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Media> {



    private int resourceLayout;
    private Context mContext;

    public ListAdapter(Context context, int resource, List<Media> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Media p = getItem(position);

        if (p != null) {
            TextView title = (TextView) v.findViewById(R.id.item_title);
            //ImageView  poster = (ImageView) v.findViewById(R.id.item_poster);

            if (title != null) {
                title.setText(p.getTitle());
            }

        }

        return v;
    }

}
