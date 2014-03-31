package com.homework.gamersbook.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.homework.gamersbook.R;
import com.homework.gamersbook.utils.Constants;
import com.homework.gamersbook.view.player.StreamPlayer;

public class MainActivity extends Activity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		final EditText channelTxt = (EditText) findViewById(R.id.channelName);
		final Button watchStreamBtn = (Button) findViewById(R.id.watchStreamBtn);

		watchStreamBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!channelTxt.getText().toString().equals("")) {
					Intent i = new Intent(MainActivity.this, StreamPlayer.class);
					i.putExtra(Constants.INTENT_KEY_CHANNEL_NAME, channelTxt
							.getText().toString().replaceAll("\\s+", ""));
					startActivity(i);
				}
			}
		});
	}
}
