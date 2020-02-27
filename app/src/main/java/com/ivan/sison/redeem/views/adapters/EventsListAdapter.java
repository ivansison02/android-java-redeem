package com.ivan.sison.redeem.views.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ivan.sison.redeem.R;
import com.ivan.sison.redeem.data.entities.Event;
import com.ivan.sison.redeem.utils.ConstantUtil;

import java.util.ArrayList;

public class EventsListAdapter  extends ArrayAdapter<Event> {

    private ArrayList<Event> mItems, mSuggestions = new ArrayList<>();

    private Context mContext;
    private int mLayoutResource;

    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            mSuggestions.clear();

            if (constraint == null || constraint.length() == 0) {
                mSuggestions.addAll(mItems);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Event event : mItems) {
                    if (event.getTitle().toLowerCase().contains(filterPattern)) {
                        mSuggestions.add(event);
                    }
                }
            }

            results.values = mSuggestions;
            results.count = mSuggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Event> events = (ArrayList<Event>) results.values;

            if (events != null && events.size() > 0) {
                clear();

                addAll(events);

                notifyDataSetChanged();
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Event) resultValue).getTitle();
        }
    };

    public EventsListAdapter(@NonNull Context context, int resource, ArrayList<Event> mItems) {
        super(context, resource);

        this.mContext = context;
        this.mLayoutResource = resource;
        this.mItems = mItems;
        this.mSuggestions.addAll(mItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        try {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(mLayoutResource, parent, false);
            }

            TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
            txtTitle.setText(mSuggestions.get(position).getTitle());
        }
        catch (Exception e) {
            Log.e(ConstantUtil.TAG, e.toString());
        }

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public void onUpdateData(ArrayList<Event> items) {
        this.mItems = items;

        notifyDataSetChanged();
    }
}
