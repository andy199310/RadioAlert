package com.weigreen.radioalert;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLBridge {
	public static void updatePinewave(final Context context) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
					SQLiteDatabase db = mDbHelper.getWritableDatabase();
					db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, null, null);
					
					InputStream is = new URL("http://140.115.189.175/java/get.php").openStream();
					BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
					StringBuilder sb = new StringBuilder();
					int cp;
					while ((cp = rd.read()) != -1) {
						sb.append((char) cp);
					}
					JSONObject json = new JSONObject(sb.toString());
					int lenth = json.length();
					for (int i=0; i<lenth; i++){
						JSONObject under = json.getJSONObject(String.valueOf(i));
						
						ContentValues values = new ContentValues();
						values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, under.getString("id"));
						values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, under.getString("date"));
						values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TIME, under.getString("time"));
						values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME, under.getString("name"));
						values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DJ, under.getString("dj"));
						
						db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, FeedReaderContract.FeedEntry.COLUMN_NAME_NULLABLE, values);
						
						Log.d("update name:", under.getString("name"));

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public static String[][] getPinewave(final Context context){
		String[][] data = null;
		
		FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		String[] projection = {
			FeedReaderContract.FeedEntry._ID,
			FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID,
			FeedReaderContract.FeedEntry.COLUMN_NAME_DATE,
			FeedReaderContract.FeedEntry.COLUMN_NAME_TIME,
			FeedReaderContract.FeedEntry.COLUMN_NAME_NAME,
			FeedReaderContract.FeedEntry.COLUMN_NAME_DJ};
		
		Cursor c = db.query(FeedReaderContract.FeedEntry.TABLE_NAME, projection, null, null, null, null, null);
		int count = c.getCount();
		int columnCount = c.getColumnCount();
		data = new String[count][columnCount];
		Log.d("row:", String.valueOf(count));
		Log.d("column", String.valueOf(columnCount));
		for(int i=0; i<count; i++){
			c.moveToNext();
			Log.d("get name", c.getString(c.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME)));
			for (int j=0; j<columnCount; j++){
				data[i][j] = c.getString(j);
			}
		}
		return data;
	}
	
	public static String[][] getPinewaveFuture(final Context context, Date now){
		String[][] data = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		String[] projection = {
			FeedReaderContract.FeedEntry._ID,
			FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID,
			FeedReaderContract.FeedEntry.COLUMN_NAME_DATE,
			FeedReaderContract.FeedEntry.COLUMN_NAME_TIME,
			FeedReaderContract.FeedEntry.COLUMN_NAME_NAME,
			FeedReaderContract.FeedEntry.COLUMN_NAME_DJ};
		
		Cursor c = db.query(FeedReaderContract.FeedEntry.TABLE_NAME, projection, null, null, null, null, null);
		int tmpCount = c.getCount();
		int count = tmpCount;
		int columnCount = c.getColumnCount();
		data = new String[count][columnCount];
		Log.d("row:", String.valueOf(count));
		Log.d("column", String.valueOf(columnCount));
		List<String[]> tmpData = new ArrayList<String[]>();
		for(int i=0; i<count; i++){
			try {
				c.moveToNext();
				Log.d("get name", c.getString(c.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME)));
				Date chooseDate = simpleDateFormat.parse(c.getString(c.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)));
				if (chooseDate.after(now)){
					Log.d("new", chooseDate.toString() + "___" + now.toString());
					String[] tmp = new String[columnCount];
					for (int j=0; j<columnCount; j++){
						tmp[j] = c.getString(j);
					}
					tmpData.add(tmp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		String[][] array = tmpData.toArray(new String[tmpData.size()][columnCount]);
		return array;
	}
	
	public static String[][] getPinewavePast(final Context context, Date now){
		String[][] data = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		String[] projection = {
			FeedReaderContract.FeedEntry._ID,
			FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID,
			FeedReaderContract.FeedEntry.COLUMN_NAME_DATE,
			FeedReaderContract.FeedEntry.COLUMN_NAME_TIME,
			FeedReaderContract.FeedEntry.COLUMN_NAME_NAME,
			FeedReaderContract.FeedEntry.COLUMN_NAME_DJ};
		
		Cursor c = db.query(FeedReaderContract.FeedEntry.TABLE_NAME, projection, null, null, null, null, null);
		int tmpCount = c.getCount();
		int count = tmpCount;
		int columnCount = c.getColumnCount();
		data = new String[count][columnCount];
		Log.d("row:", String.valueOf(count));
		Log.d("column", String.valueOf(columnCount));
		List<String[]> tmpData = new ArrayList<String[]>();
		for(int i=0; i<count; i++){
			try {
				c.moveToNext();
				Log.d("get name", c.getString(c.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME)));
				Date chooseDate = simpleDateFormat.parse(c.getString(c.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE)));
				if (chooseDate.before(now)){
					Log.d("new", chooseDate.toString() + "___" + now.toString());
					String[] tmp = new String[columnCount];
					for (int j=0; j<columnCount; j++){
						tmp[j] = c.getString(j);
					}
					tmpData.add(tmp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		String[][] array = tmpData.toArray(new String[tmpData.size()][columnCount]);
		return array;
	}
}
