package com.sdu.online.schoolbus.util;

import com.sdu.online.schoolbus.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;

public class DialogUtils {

	public static AlertDialog showProcessDialog(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		LayoutInflater mInflater = LayoutInflater.from(context);
		View v = mInflater.inflate(R.layout.waiting, null);
		return builder.setView(v).show();
	}
	
	public static void showNoUpdateDialog(Context context,String message){
		Looper.prepare();
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message)
		.setTitle("检查更新")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setPositiveButton("确定", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).show();
		Looper.loop();
	}
}
