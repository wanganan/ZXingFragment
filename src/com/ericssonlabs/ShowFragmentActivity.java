package com.ericssonlabs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.zxing.fragment.CaptureFragment;

public class ShowFragmentActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_fragment);

		CaptureFragment fragment = new CaptureFragment();
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		fragmentTransaction.replace(R.id.container, fragment, "");
		fragmentTransaction.commit();

		MyBrodcast brodcast = new MyBrodcast();
		IntentFilter intentFilter = new IntentFilter(
				CaptureFragment.SCAN_RESULT_ACTION);
		registerReceiver(brodcast, intentFilter);
	}

	public class MyBrodcast extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			if (intent.getAction().equals(CaptureFragment.SCAN_RESULT_ACTION)) {

				Toast.makeText(context, intent.getExtras().getString("result"),
						0).show();
			}
		}
	}
}
