package com.weigreen.radioalert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedReaderDbHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 2;
	public static final String DATABASA_NAME = "FeedReader.db";

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ FeedReaderContract.FeedEntry.TABLE_NAME + " ("
			+ FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY,"
			+ FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP
			+ FeedReaderContract.FeedEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP
			+ FeedReaderContract.FeedEntry.COLUMN_NAME_TIME + TEXT_TYPE + COMMA_SEP
			+ FeedReaderContract.FeedEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP
			+ FeedReaderContract.FeedEntry.COLUMN_NAME_DJ + TEXT_TYPE
			+ ")";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+  FeedReaderContract.FeedEntry.TABLE_NAME;

	public FeedReaderDbHelper(Context context) {
		super(context, DATABASA_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);

	}

}
