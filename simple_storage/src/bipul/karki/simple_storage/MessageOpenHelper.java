package bipul.karki.simple_storage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MessageOpenHelper extends SQLiteOpenHelper {
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "MessageReader.db";
	public static final String TABLE_NAME = "message";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_MESSAGE = "entry";
	public static final String TIME_STAMP = "ts";
	private static final String DATABASE_CREATE = "create table message (id integer primary key autoincrement, entry text, ts text)";
	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;

	public MessageOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);

		Log.d("SQliteLog", "Database created");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This database is only a cache for online data, so its upgrade policy
		// is
		// to simply to discard the data and start over
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}

	@SuppressLint("SimpleDateFormat")
	public void insertMessage(String msg) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy-hh:mm a");
		String strDate = sdf.format(c.getTime());
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(COLUMN_MESSAGE, msg);
		values.put(TIME_STAMP, strDate);
		db.insert(TABLE_NAME, null, values);
		db.close();
		Log.d("SQliteLog", "Message inserted");

	}

	public String readDisplay() {
		int id = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		String[] columns = { COLUMN_ID, COLUMN_MESSAGE, TIME_STAMP };
		Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null,
				null);
		StringBuffer buffer = new StringBuffer();
		while (cursor.moveToNext()) {
			id = cursor.getInt(0);
			String msg = cursor.getString(1);
			String t = cursor.getString(2);
			buffer.append("SQlite" + Integer.toString(id) + " " + t + "\n");
			Log.d("SQliteLog", msg);
		}
		Log.d("SQliteLog", Integer.toString(id));

		return buffer.toString();

	}
}
