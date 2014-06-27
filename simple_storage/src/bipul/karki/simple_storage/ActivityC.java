package bipul.karki.simple_storage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityC extends Activity {
	EditText msg;
	Button cancelDB, saveDB;
	MessageOpenHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_c);

		msg = (EditText) findViewById(R.id.EditTextMessageInput_c);
		cancelDB = (Button) findViewById(R.id.cancelSQLite);
		saveDB = (Button) findViewById(R.id.saveSQLite);
		

	}// onCreate

	@Override
	protected void onStart() {
		super.onStart();
		msg.setText("");

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

	public void cancelSQliteActivity(View v) {
		ActivityC.this.finish();
	}

	public void saveMessage(View v) {
		dbHelper = new MessageOpenHelper(this);
		dbHelper.insertMessage(msg.getText().toString());
		
		ActivityC.this.finish();
	}
}
