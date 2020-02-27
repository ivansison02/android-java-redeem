package com.ivan.sison.redeem.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ivan.sison.redeem.R;
import com.ivan.sison.redeem.data.entities.Event;
import com.ivan.sison.redeem.views.activities.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<Event> mItems;
    private SettingsActivity mView;

    public EventsAdapter(SettingsActivity mView, List<Event> mItems) {
        this.mView = mView;
        this.mItems = mItems;
    }

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_event, parent, false);
        return new EventsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventsAdapter.ViewHolder holder, final int position) {
        final Event event = mItems.get(position);

        holder.mLbl.setText(event.getTitle());
        holder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRemoveItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void onUpdateData(ArrayList<Event> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void onAddItem(Event event) {
        mItems.add(event);
        notifyItemInserted(getItemCount());

        //mView.onUpdatedSavedEventsList(mItems.size(), true);
    }

    public void onRemoveItem(int index) {
        mItems.remove(index);
        notifyDataSetChanged();

        //mView.onUpdatedSavedEventsList(mItems.size(), true);
    }

    public List<Event> getItems() {
        return mItems;
    }

    // MARK: - View
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mLbl;
        private ImageView mImg;

        public ViewHolder(View view) {
            super(view);

            mLbl = (TextView) view.findViewById(R.id.txt_title);
            mImg = (ImageView) view.findViewById(R.id.img_remove);
        }
    }
}
