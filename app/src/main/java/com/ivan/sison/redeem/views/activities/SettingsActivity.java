package com.ivan.sison.redeem.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.ivan.sison.redeem.R;
import com.ivan.sison.redeem.data.Db;
import com.ivan.sison.redeem.data.entities.Event;
import com.ivan.sison.redeem.data.tasks.EventAsyncTask;
import com.ivan.sison.redeem.utils.ConstantUtil;
import com.ivan.sison.redeem.views.adapters.EventsAdapter;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    public static Db mDb;

    private EventAsyncTask.EventAsyncTaskDelegate mDelegate = new EventAsyncTask.EventAsyncTaskDelegate() {
        @Override
        public void onAddEvent() {
            Log.e(ConstantUtil.TAG, "Successfully added");
        }

        @Override
        public void onUpdateEvent() {
            Log.e(ConstantUtil.TAG, "Successfully updated");            }

        @Override
        public void onGetEvents(List<Event> events) {
            if (events.size() > 0) {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_events);
                LinearLayoutManager layoutManager = new LinearLayoutManager(SettingsActivity.this, LinearLayoutManager.VERTICAL, false);
                EventsAdapter adapter = new EventsAdapter(SettingsActivity.this, events);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }
            else {
                Log.e(ConstantUtil.TAG, "No events found");
            }
        }
    };

    private List<Event> mEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mDb = Room.databaseBuilder(this, Db.class, "ticket_db")
                .build();

        EventAsyncTask.getInstance(mDb, mDelegate).getEvents();
    }
}
