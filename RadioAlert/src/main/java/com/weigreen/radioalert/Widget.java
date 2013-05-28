package com.weigreen.radioalert;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by wind on 2013/5/28.
 */
public class Widget extends AppWidgetProvider
{
    private final String LOG_TAG = "Widget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] widgetIds){
        super.onUpdate(context, appWidgetManager, widgetIds);
        Log.d(LOG_TAG, "onUpdate()");

        ComponentName componentName = new ComponentName(context.getPackageName(), Widget.class.getName());

        Intent start = new Intent(context, RadioService.class);
        start.putExtra(RadioService.SERVICE_TAG, RadioService.SERVICE_START);
        PendingIntent startPendingIntent = PendingIntent.getService(context, 0, start, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent stop = new Intent(context, RadioService.class);
        stop.putExtra(RadioService.SERVICE_TAG, RadioService.SERVICE_STOP);
        PendingIntent stopPendingIntent = PendingIntent.getService(context, 1, stop, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        views.setOnClickPendingIntent(R.id.widget_start, startPendingIntent);
        views.setOnClickPendingIntent(R.id.widget_stop, stopPendingIntent);

        appWidgetManager.updateAppWidget(componentName, views);



    }


}
