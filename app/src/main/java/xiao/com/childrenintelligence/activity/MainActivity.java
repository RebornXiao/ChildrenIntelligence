package xiao.com.childrenintelligence.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import xiao.com.childrenintelligence.MyApplication;
import xiao.com.childrenintelligence.R;
import xiao.com.childrenintelligence.client.PatientRestClient;
import xiao.com.childrenintelligence.data.ModelDao;
import xiao.com.childrenintelligence.entity.HealthScan;
import xiao.com.childrenintelligence.entity.Register;
import xiao.com.childrenintelligence.entity.Status;
import xiao.com.childrenintelligence.http.RequestParams;
import xiao.com.childrenintelligence.http.XmlHttpResponseHandler;
import xiao.com.childrenintelligence.tools.CommentWays;
import xiao.com.childrenintelligence.view.MainDataView;
import xiao.com.childrenintelligence.view.TitleView;

public class MainActivity extends Activity implements OnClickListener{
	private TitleView titleView;
	private TextView name,sex,age,date;
	private TextView tvhistory;
	private Intent intent;
	private ModelDao model;
	private int positionId ;
	private ArrayList<Register> registers;
	private EditText etinputNum;
	private TextView tvcomment;
	private String number;
	private MainDataView view1;;
	private ArrayList<HealthScan> scans;
	/**
	 * 每次最多取10条数据，当前页面显示昨天的数据，假如没有，则显示0或者空
	 * 而 历史则显示9天数据
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		model = new ModelDao(getApplicationContext());
		registers = model.registerSearth();
		scans = model.HealthScanSearth();
		//ArrayList<Status> status = model.statusSearth();
		if(MyApplication.deleteFlag == true){
			setContentView(R.layout.activity_main);
			MyApplication.getInstance().addActivity(this);
			init();
			MyApplication.deleteFlag = false;
		}else {
			if(registers.size() > 0){
				setContentView(R.layout.activity_main);
				MyApplication.getInstance().addActivity(this);
				init();
				//判断昨天的日期是否取过数据
				boolean flag = model.queryScanFlag(MyApplication.ways.
						 getnDate(),registers.get(positionId).getNum());
				if(flag == true) return;
				
				if(CommentWays.isWifi(getApplicationContext()) == true
			       || CommentWays.is3G(getApplicationContext()) == true){
					
					view1.json(positionId);
					
				}else {
					MyApplication.ways.ToastShow(getApplicationContext(), "请检查网络是否良好");
				}
			}else {
				setContentView(R.layout.children_register);
				MyApplication.getInstance().addActivity(this);
				initRegister();
			}
		}
	}
	
	private void initRegister(){
		titleView = (TitleView)findViewById(R.id.mtitleview);
		model = new ModelDao(getApplicationContext());
		etinputNum = (EditText)findViewById(R.id.etinputNum);
		tvcomment = (TextView)findViewById(R.id.tvcomment);
		tvcomment.setOnClickListener(this);
		titleView.setTitle(R.string.register);
		titleView.setBack("取消", new TitleView.onBackLister() {
			
			@Override
			public void onClick(View button) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	private void jsonRegister(final String num){		
		String op = "10004";
		final JSONObject object = new JSONObject();
		try {
			object.put("phone", num);
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("op", op);
		map2.put("info", object.toString());
		RequestParams params = new RequestParams(map2);
		
		PatientRestClient.post("login", params, new XmlHttpResponseHandler(){
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				super.onFinish();
				Log.i("info", "onFinish");
			}
			@Override
			public void onSuccess(JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(response);
				try {
				CommentWays.logoCancel();
				String ack = response.getString("ack");
				String op = response.getString("op");
				if(ack.equals("0") && op.equals("10004")){
					 MyApplication.getInstance().ways.ToastShow(getApplicationContext(), "成功");

					String name = response.getString("info");
					JSONObject object2 = new JSONObject(name);  
					String name2 = object2.getString("name");
				    String sex = object2.getString("sex");
				    String age = object2.getString("age");
				//	String err = response.getString("err");
					//String info = response.getString("info");
					String uId = response.getString("uId");
					if(uId != null) MyApplication.uId = uId;
					Register register = new Register();
					register.setName(name2);
					if(sex.equals("1"))
					   register.setSex("男");
					else if(sex.equals("2"))
					   register.setSex("女");
					register.setAge(age);
					register.setuId(uId);
					register.setNum(num);
					model.insertRegister(register);
					
					Status status = new Status();
					status.setStaus(1);
					model.insertStatus(status);
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(intent);
				}else if(ack.equals("1")){
					MyApplication.getInstance().ways.ToastShow(getApplicationContext(), "电话不存在");
					
				}else if(ack.equals("2")){
					MyApplication.getInstance().ways.ToastShow(getApplicationContext(), "失败");
				  }
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void onFailure(Throwable error, String content) {
				// TODO Auto-generated method stub
				super.onFailure(error, content);
				Log.i("info", "fale");
			}
		});
	}
	private void init(){
		model = new ModelDao(getApplicationContext());
		titleView = (TitleView)findViewById(R.id.titleview);
		name = (TextView)findViewById(R.id.tvname);
		sex = (TextView)findViewById(R.id.tvsex);
		age = (TextView)findViewById(R.id.tvage);
		tvhistory = (TextView)findViewById(R.id.tvhistory);
		//给10个view初始化
		view1 = (MainDataView)findViewById(R.id.view1);
		titleView.setTitle(R.string.mainActivity );
		intent = getIntent();
		positionId = intent.getIntExtra("itemId", 0);
		//当前用户的数据，放到一个临时数组.
		titleView.Set("用户选择", new TitleView.onSetLister() {
			
			@Override
			public void onClick(View button) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
				startActivity(intent);
			}
		});
		if(registers.size() == 0)return;
		for(int i = 0; i<scans.size(); i++){
		  if(registers.get(positionId).getNum().equals(scans.get(i).getPhoneNum())){
			 MyApplication.curUserscans.add(scans.get(i));
			  }
			}
		if(MyApplication.curUserscans.size() > 0){
		   view1.dealData(0);
		}
		
		
	//	registers = model.registerSearth();
		if(registers.size() > 0) {
			name.setText(registers.get(positionId).getName());
			age.setText(registers.get(positionId).getAge());
			sex.setText(registers.get(positionId).getSex());
		}
		tvhistory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "历史", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
				startActivity(intent);
			}
		});
		
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch (v.getId()) {
	case  R.id.tvcomment:
		number = etinputNum.getText().toString();
		if(number.equals("")){
			MyApplication.getInstance().ways.ToastShow(getApplicationContext(), "请输入电话号码");
			return;
		}
		if(CommentWays.isWifi(getApplicationContext()) == true 
	               || CommentWays.is3G(getApplicationContext()) == true){
			CommentWays.logoProgress(MainActivity.this);
			jsonRegister(number);
		}else {
			MyApplication.ways.ToastShow(getApplicationContext(), "请检查网络是否良好");
		}
		
		break;

	default:
		break;
	  }
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			MyApplication.getInstance().exit();
		}
		return super.onKeyDown(keyCode, event);
	}
}
