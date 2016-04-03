package com.udacitynanodegree.rajeefmk.popularmovies;

import android.text.TextUtils;

import com.activeandroid.app.Application;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class PopularMoviesApplication extends Application {
    private RequestQueue mRequestQueue;
    public static final String TAG = PopularMoviesApplication.class.getSimpleName();
    private static PopularMoviesApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public static synchronized PopularMoviesApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
