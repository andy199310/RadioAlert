package com.weigreen.radioalert;

import android.provider.BaseColumns;

public class FeedReaderContract {

	public static abstract class FeedEntry implements BaseColumns {
		public static final String TABLE_NAME = "pinewave";
		public static final String COLUMN_NAME_ENTRY_ID = "programid";
		public static final String COLUMN_NAME_DATE = "date";
		public static final String COLUMN_NAME_TIME = "time";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_DJ = "dj";
		public static final String COLUMN_NAME_NULLABLE = null;

	}

	private FeedReaderContract() {}
}
