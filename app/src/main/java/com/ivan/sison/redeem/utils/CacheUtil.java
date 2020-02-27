package com.ivan.sison.redeem.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.ivan.sison.redeem.data.entities.User;
import com.ivan.sison.redeem.data.objects.SavedEvents;

public class CacheUtil {

    private Context mContext;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;

    private Gson mGson;

    private final String PREF_NAME = "prefs";
    private final String KEY_USER = "user";
    private final String KEY_EVENTS_SAVED = "events_saved";

    public CacheUtil(Context mContext) {
        this.mContext = mContext;

        mShared = getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mShared.edit();

        mGson = new Gson();
    }

    public Activity getActivity() {
        return (Activity) mContext;
    }

    public Boolean hasActiveUser() {
        if (getUser() == null) {
            return false;
        }
        return true;
    }

    public Boolean hasSavedEvents() {
        if (getSavedEvents() == null) {
            return false;
        }
        return true;
    }

    public User getUser() {
        String account = mShared.getString(KEY_USER, "");
        return mGson.fromJson(account, User.class);
    }

    public SavedEvents getSavedEvents() {
        String log = mShared.getString(KEY_EVENTS_SAVED, "");
        return mGson.fromJson(log, SavedEvents.class);
    }

    public void onUpdateCache(User user) {
        String json = mGson.toJson(user);

        mEditor.putString(KEY_USER, json);
        mEditor.apply();
    }

    public void onUpdateCache(SavedEvents savedEvents) {
        String json = mGson.toJson(savedEvents);

        mEditor.putString(KEY_EVENTS_SAVED, json);
        mEditor.apply();
    }
}
