package bipul.karki.simple_storage;  //Bipul karki, CMPE277, Spring 2014, SJSU (Assignment 3)

import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class ActivityA extends Activity {
	public static final String PREFS_NAME = "BOOK_PREFERENCE";
	SharedPreferences sharedPreferences;
	Button prefBtn, sqlBtn, closeBtn;
	ScrollView sv;
	TextView tv;
	MessageOpenHelper msgDb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);

		prefBtn = (Button) findViewById(R.id.buttonPrefs_a);
		sqlBtn = (Button) findViewById(R.id.buttonSQLite_a);
		closeBtn = (Button) findViewById(R.id.buttonClose_a);
		sv = (ScrollView)findViewById(R.id.textDisplay_a);
		tv = new TextView(ActivityA.this);
		sv.addView(tv);
		msgDb = new MessageOpenHelper(this);
	}

	
	@Override
	protected void onStart() {
		super.onStart();
		String s = msgDb.readDisplay();
		tv.setText("");
		tv.setText(s);
		sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
		Map<String, ?> keys = sharedPreferences.getAll();
		for (Map.Entry<String, ?> entry : keys.entrySet()) {
				Log.d("Preferences", entry.getKey() + ":"
						+ entry.getValue().toString());
				if (entry.getKey().contains("Save")) {
					tv.append(entry.getKey() + ", "
							+ entry.getValue().toString() + "\n");	
				}
			}
		}

	@Override
	protected void onResume() {
		super.onResume();
		// The activity has become visible (it is now "resumed").
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Another activity is taking focus (this activity is about to be
		// "paused").
	}

	@Override
	protected void onStop() {
		super.onStop();
		// The activity is no longer visible (it is now "stopped")
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// The activity is about to be destroyed.
	}

	public void startPreferenceActivity_a(View v) {
		// Intent intent = new Intent(getApplicationContext(),ActivityB.class);
		// startActivity(intent);
		Intent intent = new Intent(getApplicationContext(), ActivityB.class);
		startActivity(intent);
		//ActivityC.this.finish();
	}

	public void closeActivity_a(View v) {
		ActivityA.this.finish();
		
	}

	public void startSQLiteActivity_a(View v) {
		Intent intent = new Intent(getApplicationContext(), ActivityC.class);
		startActivity(intent);

	}
}
