package com.sdu.online.schoolbus;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class About extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WebView webView = new WebView(this);
		setContentView(webView);
		webView.loadUrl("file:///android_asset/about.html");
	}

}
