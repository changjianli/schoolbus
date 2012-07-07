package com.sdu.online.schoolbus;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class SettingsActivity extends PreferenceActivity {

	private CheckBoxPreference autoUpdate,autoUpdateWifi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);
		
		findKeys();
		
		listen();
	}
	
	private void findKeys(){
		autoUpdate = (CheckBoxPreference) findPreference("auto_update");
		autoUpdateWifi = (CheckBoxPreference) findPreference("update_only_wifi");
	}
	
	private void listen(){
		if(autoUpdate.isChecked()){
			autoUpdateWifi.setEnabled(true);
		}else autoUpdateWifi.setEnabled(false);
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		if(preference.getKey().equals("share")){
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "学生在线推出了一款移动校车查询助手啦！很棒，只有android智能机才有哦~~亲们快去下吧~~!");
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
		}
		listen();
		return super.onPreferenceTreeClick(preferenceScreen, preference);
		
	}
	
	

}
