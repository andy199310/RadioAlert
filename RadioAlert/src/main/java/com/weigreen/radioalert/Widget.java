package com.weigreen.radioalert;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by wind on 2013/5/28.
 */
public class Widget extends AppWidgetProvider
{
    private final String LOG_TAG = "Widget";

    @Override
    public void onDeleted(Context context, int[] widgetIds)
    {
        super.onDeleted(context, widgetIds);
        Log.d(LOG_TAG, "onDeleted()");
    }

    @Override
    public void onDisabled(Context context)
    {
        super.onDisabled(context);
        Log.d(LOG_TAG, "onDisabled()");
    }

    @Override
    public void onEnabled(Context context)
    {
        super.onEnabled(context);
        Log.d(LOG_TAG, "onEnabled()");
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        super.onReceive(context, intent);
        Log.d(LOG_TAG, "onReceive()");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] widgetIds)
    {
        super.onUpdate(context, appWidgetManager, widgetIds);
        Log.d(LOG_TAG, "onUpdate()");
    }


}
