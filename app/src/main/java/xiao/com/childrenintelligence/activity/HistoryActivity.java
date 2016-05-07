package xiao.com.childrenintelligence.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import xiao.com.childrenintelligence.MyApplication;
import xiao.com.childrenintelligence.R;
import xiao.com.childrenintelligence.view.MainDataView;
import xiao.com.childrenintelligence.view.TitleView;


public class HistoryActivity extends Activity {
	private TitleView titleView;
	private MainDataView view1,view2,view3;
	private MainDataView view4,view5,view6;
	private MainDataView view7,view8,view9;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.children_history);
		init();
	}
	
	private void init(){
		titleView = (TitleView)findViewById(R.id.titleview);
		view1 = (MainDataView)findViewById(R.id.view1);
		view2 = (MainDataView)findViewById(R.id.view2);
		view3 = (MainDataView)findViewById(R.id.view3);
		view4 = (MainDataView)findViewById(R.id.view4);
		view5 = (MainDataView)findViewById(R.id.view5);
		view6 = (MainDataView)findViewById(R.id.view6);
		view7 = (MainDataView)findViewById(R.id.view7);
		view8 = (MainDataView)findViewById(R.id.view8);
		view9 = (MainDataView)findViewById(R.id.view9);
		titleView.setTitle(R.string.hostory_record);
		titleView.setBack("返回", new TitleView.onBackLister() {
			
			@Override
			public void onClick(View button) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		Log.i("info", "MyApplication.curUserscans.size()="+ MyApplication.curUserscans.size());
		//处理数据,最多10条，9条历史显示倒数2,3,4，5,6.。。
				if(MyApplication.curUserscans.size() == 10){
					view1.dealData(1);
					view2.dealData(2);
					view3.dealData(3);
					view4.dealData(4);
					view5.dealData(5);
					view6.dealData(6);
					view7.dealData(7);
					view8.dealData(8);
					view9.dealData(9);
				}else if(MyApplication.curUserscans.size() == 9){

					view1.dealData(1);
					view2.dealData(2);
					view3.dealData(3);
					view4.dealData(4);
					view5.dealData(5);
					view6.dealData(6);
					view7.dealData(7);
					view8.dealData(8);
					view9.setVisibility(View.GONE);
				}else if(MyApplication.curUserscans.size() == 8){

					view1.dealData(1);
					view2.dealData(2);
					view3.dealData(3);
					view4.dealData(4);
					view5.dealData(5);
					view6.dealData(6);
					view7.dealData(7);
					view8.setVisibility(View.GONE);
					view9.setVisibility(View.GONE);
				}else if(MyApplication.curUserscans.size() == 7){

					view1.dealData(1);
					view2.dealData(2);
					view3.dealData(3);
					view4.dealData(4);
					view5.dealData(5);
					view6.dealData(6);
					view7.setVisibility(View.GONE);
					view8.setVisibility(View.GONE);
					view9.setVisibility(View.GONE);
				}else if(MyApplication.curUserscans.size() == 6){

					view1.dealData(1);
					view2.dealData(2);
					view3.dealData(3);
					view4.dealData(4);
					view5.dealData(5);
					view6.setVisibility(View.GONE);
					view7.setVisibility(View.GONE);
					view8.setVisibility(View.GONE);
					view9.setVisibility(View.GONE);
				}else if(MyApplication.curUserscans.size() == 5){
					view1.dealData(1);
					view2.dealData(2);
					view3.dealData(3);
					view4.dealData(4);
					view5.setVisibility(View.GONE);
					view6.setVisibility(View.GONE);
					view7.setVisibility(View.GONE);
					view8.setVisibility(View.GONE);
					view9.setVisibility(View.GONE);
				}else if(MyApplication.curUserscans.size() == 4){
					view1.dealData(1);
					view2.dealData(2);
					view3.dealData(3);
					view4.setVisibility(View.GONE);
					view5.setVisibility(View.GONE);
					view6.setVisibility(View.GONE);
					view7.setVisibility(View.GONE);
					view8.setVisibility(View.GONE);
					view9.setVisibility(View.GONE);
				}else if(MyApplication.curUserscans.size() == 3){
					view1.dealData(1);
					view2.dealData(2);
					view3.setVisibility(View.GONE);
					view4.setVisibility(View.GONE);
					view5.setVisibility(View.GONE);
					view6.setVisibility(View.GONE);
					view7.setVisibility(View.GONE);
					view8.setVisibility(View.GONE);
					view9.setVisibility(View.GONE);
				}else if(MyApplication.curUserscans.size() == 2){
					view1.dealData(1);
					view2.setVisibility(View.GONE);
					view3.setVisibility(View.GONE);
					view4.setVisibility(View.GONE);
					view5.setVisibility(View.GONE);
					view6.setVisibility(View.GONE);
					view7.setVisibility(View.GONE);
					view8.setVisibility(View.GONE);
					view9.setVisibility(View.GONE);
				}else if(MyApplication.curUserscans.size() == 1 || MyApplication.curUserscans.size() == 0){
					view1.setVisibility(View.GONE);
					view2.setVisibility(View.GONE);
					view3.setVisibility(View.GONE);
					view4.setVisibility(View.GONE);
					view5.setVisibility(View.GONE);
					view6.setVisibility(View.GONE);
					view7.setVisibility(View.GONE);
					view8.setVisibility(View.GONE);
					view9.setVisibility(View.GONE);
				}
			}
	}

