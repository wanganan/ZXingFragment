package com.ericssonlabs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.zxing.activity.CaptureActivity;
import com.zxing.encoding.EncodingHandler;

/**
 * 二维码、条形码扫描——使用Google ZXing - Ryan's zone - 博客频道 - CSDN.NET
 * http://blog.csdn.net/ryantang03/article/details/7831826
 * 
 * Zxing 在Fragment中实现二维码扫描 - aishang5wpj的专栏 - 博客频道 - CSDN.NET
 * http://blog.csdn.net/aishang5wpj/article/details/46987031
 * 
 * @author Administrator
 * 
 */
public class BarCodeTestActivity extends Activity {
	/** Called when the activity is first created. */
	private TextView resultTextView;
	private EditText qrStrEditText;
	private ImageView qrImgImageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
		qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
		qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);

		Button startBarCodeFragmentBtn = (Button) findViewById(R.id.btn_scan_barcode_fragment);
		startBarCodeFragmentBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(BarCodeTestActivity.this,
						ShowFragmentActivity.class));
			}
		});

		Button scanBarCodeButton = (Button) this
				.findViewById(R.id.btn_scan_barcode);
		scanBarCodeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 打开扫描界面扫描条形码或二维码
				Intent openCameraIntent = new Intent(BarCodeTestActivity.this,
						CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});

		Button generateQRCodeButton = (Button) this
				.findViewById(R.id.btn_add_qrcode);
		generateQRCodeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					String contentString = qrStrEditText.getText().toString();
					if (!contentString.equals("")) {
						// 根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
						Bitmap qrCodeBitmap = EncodingHandler.createQRCode(
								contentString, 350);
						qrImgImageView.setImageBitmap(qrCodeBitmap);
					} else {
						Toast.makeText(BarCodeTestActivity.this,
								"Text can not be empty", Toast.LENGTH_SHORT)
								.show();
					}

				} catch (WriterException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 处理扫描结果（在界面上显示）
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			resultTextView.setText(scanResult);
		}
	}
}