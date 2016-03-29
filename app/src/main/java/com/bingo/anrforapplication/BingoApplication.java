package com.bingo.anrforapplication;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

public class BingoApplication extends Application {

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		if (isStartWaitProcess())
			return;
		try {
			File startWaitTempFile = getStartWaitTempFile();
			if (!startWaitTempFile.exists())
				startWaitTempFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Intent intent = new Intent(this, StartLoadingActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		MultiDex.install(this);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		if (isStartWaitProcess())
			return;
		SystemClock.sleep(5 * 1000l);
		getStartWaitTempFile().delete();
	}

	public File getStartWaitTempFile() {
		return new File(getFilesDir(), "startWaitTemp");
	}

	protected boolean isStartWaitProcess() {
		String curProcessName = getCurProcessName(this);
		return !TextUtils.isEmpty(curProcessName) && curProcessName.endsWith("appStarting");
	}

	protected static String curProcessName = null;

	public static String getCurProcessName(Context context) {
		if (curProcessName == null) {
			int pid = android.os.Process.myPid();
			ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
				if (appProcess.pid == pid) {
					curProcessName = appProcess.processName;
					break;
				}
			}
		}
		return curProcessName;
	}

}