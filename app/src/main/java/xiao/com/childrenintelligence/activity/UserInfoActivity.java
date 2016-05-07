package xiao.com.childrenintelligence.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import java.util.ArrayList;

import xiao.com.childrenintelligence.MyApplication;
import xiao.com.childrenintelligence.R;
import xiao.com.childrenintelligence.adapter.UserInfoAdapter;
import xiao.com.childrenintelligence.data.ModelDao;
import xiao.com.childrenintelligence.entity.HealthScan;
import xiao.com.childrenintelligence.entity.Register;
import xiao.com.childrenintelligence.entity.ScanFlag;
import xiao.com.childrenintelligence.view.TitleView;

public class UserInfoActivity extends Activity {
	private TitleView titleView;
	private UserInfoAdapter adapter;
	private ListView listview;
	private ModelDao model;
	private AlertDialog dialog;
	private ArrayList<Register> registers;
	private ArrayList<HealthScan> scans;
	private ArrayList<ScanFlag> flags;
	private boolean  longClick = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.children_user);
		MyApplication.getInstance().addActivity(this);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private void init(){
		titleView = (TitleView)findViewById(R.id.mtitleview);
		listview = (ListView)findViewById(R.id.Lvlist);
		model = new ModelDao(getApplicationContext());
		registers = model.registerSearth();
		scans = model.HealthScanSearth();
		flags = model.scanFlagSearth();
		adapter = new UserInfoAdapter(getApplicationContext(), registers);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(longClick == false){
				   MyApplication.curUserscans = new ArrayList<HealthScan>();
				   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				   intent.putExtra("itemId", position);
				   startActivity(intent);
				}else {
					longClick = false;
				}
			}
		});
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {
        
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position,  long id) {
				// TODO Auto-generated method stub
				registers = model.registerSearth();
				longClick = true;
				Builder builder = new Builder(UserInfoActivity.this);				
				dialog = builder.setTitle("                     删除").setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
//						longClick = false;
						
						model.deleteUserItem(registers.get(position).getId());
						String nNum = registers.get(position).getNum();
						if(scans.size() > 0){
						  for(int i = 0 ;i < scans.size(); i++){
							  if(scans.get(i).getPhoneNum().equals(nNum)){
								  model.deleteHealthScanItem(scans.get(i).getId());
								  break;
							  }
						  }
						}
						if(flags.size() > 0){
						   for(int i = 0; i<flags.size(); i++){
							   if(flags.get(i).getNum().equals(nNum)){
								  model.deleteScanFlagId(flags.get(i).getId());
								  break;
								}
							}
						}
						adapter.setBreakRules(model.registerSearth());
						adapter.notifyDataSetChanged();
						MyApplication.deleteFlag = true;
						dialog.dismiss();
					}
				}).setNegativeButton("取消", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				}).create();
				dialog.show();
				return false;
			}
		});
		
		titleView.setTitle(R.string.userChoice);
		titleView.setBack("取消", new TitleView.onBackLister() {
			
			@Override
			public void onClick(View button) {
				// TODO Auto-generated method stub
				if(MyApplication.deleteFlag == true){
				   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				   startActivity(intent);
				}else finish();
			}
		});
		titleView.Set("添加用户", new TitleView.onSetLister() {
			
			@Override
			public void onClick(View button) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(intent);
			}
		});
	}
	
}
