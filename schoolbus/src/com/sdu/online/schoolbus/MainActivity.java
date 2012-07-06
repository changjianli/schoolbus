package com.sdu.online.schoolbus;

import java.util.List;

import com.sdu.online.schoolbus.model.BusInfo;
import com.sdu.online.schoolbus.model.SchoolBusModel;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	private Spinner startSpinner,endSpinner;
	private Button searchButton;
	private RadioGroup radioGroup;
	private LinearLayout showLayout;
	private ListView listView;
	
	private static final String TAG = MainActivity.class.getSimpleName();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        findViews();
        setListeners();
    }
    
    private void findViews(){
    	startSpinner = (Spinner)findViewById(R.id.main_layout_spinner_start);
    	endSpinner = (Spinner)findViewById(R.id.main_layout_spinner_end);
    	searchButton = (Button)findViewById(R.id.bus_search_button);
    	radioGroup = (RadioGroup)findViewById(R.id.time_group);
    	showLayout = (LinearLayout)findViewById(R.id.main_layout_linearlayout_result);
    	listView = (ListView)findViewById(R.id.main_layout_listview);
    	
    }
    
    private void setListeners(){
    	searchButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				search();
			}
		});
    }
    
    private void search(){
    	SchoolBusModel model = SchoolBusModel.getInstance();
    	
    	List<BusInfo> busInfo = model.getBus(this, 
    	    	startSpinner.getSelectedItem().toString(),
    	    	endSpinner.getSelectedItem().toString(), 
    	    	1);
    	CellAdapter adapter = new CellAdapter(this, busInfo);
    	listView.setAdapter(adapter);
    	if(showLayout.getVisibility() != View.VISIBLE){
    		showLayout.setVisibility(View.VISIBLE);
    	}
    	
    	Log.v(TAG, busInfo.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
