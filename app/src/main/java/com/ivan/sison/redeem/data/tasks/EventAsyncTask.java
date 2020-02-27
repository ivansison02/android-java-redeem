package com.ivan.sison.redeem.data.tasks;

import android.os.AsyncTask;

import com.ivan.sison.redeem.data.Db;
import com.ivan.sison.redeem.data.entities.Event;
import com.ivan.sison.redeem.data.responses.Response;

import java.util.List;

public class EventAsyncTask {

    public interface EventAsyncTaskDelegate {
        void onAddEvent();
        void onUpdateEvent();
        void onGetEvents(List<Event> events);
    }

    private static EventAsyncTask mInstance = null;
    private static EventAsyncTaskDelegate mDelegate = null;

    private static Db mDb;
    private static Event mEvent;

    public static EventAsyncTask getInstance(Db database, EventAsyncTaskDelegate taskDelegate)
    {
        if (mInstance == null)
            mInstance = new EventAsyncTask();
            mDb = database;
            mDelegate = taskDelegate;

        return mInstance;
    }

    public void getEvents() {
        new GetEvent().execute();
    }

    public void addEvent(Event event) {
        mEvent = event;

        new InsertEvent().execute();
    }

    public static class InsertEvent extends AsyncTask<Void, Void, Response> {

        @Override
        protected Response doInBackground(Void... voids) {
            mDb.eventDao().addEvent(mEvent);

            return null;
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);

            mDelegate.onAddEvent();
        }
    }

    public static class GetEvent extends AsyncTask<Void, Void, List<Event>> {

        @Override
        protected List<Event> doInBackground(Void... voids) {
            return mDb.eventDao().getAllEvents();
        }

        @Override
        protected void onPostExecute(List<Event> events) {
            super.onPostExecute(events);

            mDelegate.onGetEvents(events);
        }
    }
}
