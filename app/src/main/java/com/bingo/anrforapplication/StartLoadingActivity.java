package com.bingo.anrforapplication;

import android.app.Activity;
import android.os.Bundle;

import java.io.File;

public class StartLoadingActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_loading_activity);
		waitThread.start();
	}

	protected Thread waitThread = new Thread() {
		@Override
		public void run() {
			super.run();
			File startWaitTempFile = ((BingoApplication) getApplication()).getStartWaitTempFile();
			try {
				while (startWaitTempFile.exists()) {
					Thread.sleep(50);
				}
				finish();
				Thread.sleep(500);//防止黑屏
				System.exit(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
}