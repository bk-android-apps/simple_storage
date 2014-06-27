package bipul.karki.simple_storage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
//import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityB extends Activity {
	String book_name, author_name, description;
	EditText bookname, authorname, desc;
	Button cancelPref, savePref;
	SharedPreferences sharedPreferences;
	int i = 1;

	public static final String PREFS_NAME = "BOOK_PREFERENCE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_b);

		bookname = (EditText) findViewById(R.id.EditTextBookInput);
		authorname = (EditText) findViewById(R.id.EditTextAuthorInput);
		desc = (EditText) findViewById(R.id.EditTextDescriptionInput);
		cancelPref = (Button) findViewById(R.id.cancelPreferences);
		savePref = (Button) findViewById(R.id.savePreferences);

	}// onCreate

	@Override
	protected void onStart() {
		super.onStart();
		bookname.setText("");
		authorname.setText("");
		desc.setText("");
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

	public void cancelPreferences(View v) {
		ActivityB.this.finish();
	}

	@SuppressLint("SimpleDateFormat")
	public void savePreferences(View v) {
		sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		book_name = bookname.getText().toString();
		author_name = authorname.getText().toString();
		description = desc.getText().toString();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy-hh:mm a");
		String strDate = sdf.format(c.getTime());
		i = sharedPreferences.getInt("Counter", 1);	
		editor.putString("Save Preference" + Integer.toString(i), strDate);
		editor.putString("Book Name", book_name);
		editor.putString("Book Author", author_name);
		editor.putString("Description", description);
		editor.putInt("Counter", i+1);
		
		editor.commit();
		ActivityB.this.finish();
		/*
		Intent intent = new Intent(getApplicationContext(),
				ActivityA.class);
		startActivity(intent);
		*/
	}
}
