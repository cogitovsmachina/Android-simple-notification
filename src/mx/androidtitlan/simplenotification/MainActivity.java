package mx.androidtitlan.simplenotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Intent mServiceIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mServiceIntent = new Intent(getApplicationContext(), PingService.class);
	}

	public void createNotification(View v) {
		int seconds;
		String message = "This is my awesome text for notification!";
		mServiceIntent.putExtra(CommonConstants.EXTRA_MESSAGE, message);
		mServiceIntent.setAction(CommonConstants.ACTION_NOTIFY);
		Toast.makeText(this, R.string.timer_start, Toast.LENGTH_SHORT).show();

		EditText editText = (EditText) findViewById(R.id.edit_seconds);
		String input = editText.getText().toString();

		if (input == null || input.trim().equals("")) {
			seconds = R.string.seconds_default;
		} else {
			seconds = Integer.parseInt(input);
		}
		int milliseconds = (seconds * 1000);
		mServiceIntent.putExtra(CommonConstants.EXTRA_TIMER, milliseconds);
		startService(mServiceIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
