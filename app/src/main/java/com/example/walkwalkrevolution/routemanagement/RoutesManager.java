package com.example.walkwalkrevolution.routemanagement;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.walkwalkrevolution.DataKeys;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Iterator;

public class RoutesManager implements IRouteManagement, Serializable, Iterable {

    RoutesData routes;

    public RoutesManager(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = sharedPreferences.getString(DataKeys.ROUTES_DATA_KEY, null);
        if(json == null) {
            routes = new RoutesData();
            prefsEditor.putString(DataKeys.ROUTES_DATA_KEY, gson.toJson(routes));
            prefsEditor.apply();
        } else {
            routes = gson.fromJson(json, RoutesData.class);
        }
    }

    @Override
    public long getRecentSteps(SharedPreferences sharedPreferences) {
        return sharedPreferences.getLong(DataKeys.RECENT_STEPS_KEY, 0);
    }

    @Override
    public double getRecentDistance(SharedPreferences sharedPreferences) {
        return Double.longBitsToDouble(sharedPreferences.getLong(DataKeys.RECENT_DIST_KEY, 0));
    }

    @Override
    public long getRecentTime(SharedPreferences sharedPreferences) {
        return sharedPreferences.getLong(DataKeys.RECENT_TIME_KEY, 0);
    }

    @Override
    public void saveRoute(SharedPreferences sharedPreferences, Route route) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        prefsEditor.putLong(DataKeys.RECENT_STEPS_KEY, route.getSteps());
        prefsEditor.putLong(DataKeys.RECENT_DIST_KEY, Double.doubleToRawLongBits(route.getDistance()));
        prefsEditor.putLong(DataKeys.RECENT_TIME_KEY, route.getTime());
        routes.addRoute(route);
        prefsEditor.putString(DataKeys.ROUTES_DATA_KEY, gson.toJson(routes));
        prefsEditor.apply();
    }

    @NonNull
    @Override
    public Iterator iterator() {
        return routes.iterator();
    }
}
