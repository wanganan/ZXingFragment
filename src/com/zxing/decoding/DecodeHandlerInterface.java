package com.zxing.decoding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;

import com.google.zxing.Result;
import com.zxing.view.ViewfinderView;

/***
 * 
 * @author Ryan.Tang & PengJian.Wu
 * 
 */
public interface DecodeHandlerInterface {

	public static final int RESULT_STATE_OK = 0;

	public void drawViewfinder();

	public ViewfinderView getViewfinderView();

	public Handler getHandler();

	public void handleDecode(Result result, Bitmap barcode);

	public void resturnScanResult(int resultCode, Intent data);

	public void launchProductQuary(String url);
}
