package com.example.mmdp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Fixture> {



    private int resourceLayout;
    private Context mContext;

    public ListAdapter(Context context, int resource, List<Fixture> items) {
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

        Fixture p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.home_name);
            TextView tt2 = (TextView) v.findViewById(R.id.date);
            TextView tt3 = (TextView) v.findViewById(R.id.away_name);

            if (tt1 != null) {
                tt1.setText(p.GetHome());
            }

            if (tt2 != null) {
                tt2.setText(p.GetDate());
            }

            if (tt3 != null) {
                tt3.setText(p.GetAway());
            }
        }

        return v;
    }

}