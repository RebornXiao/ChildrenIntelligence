package xiao.com.childrenintelligence.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import xiao.com.childrenintelligence.MyApplication;
import xiao.com.childrenintelligence.R;
import xiao.com.childrenintelligence.client.PatientRestClient;
import xiao.com.childrenintelligence.data.ModelDao;
import xiao.com.childrenintelligence.entity.Register;
import xiao.com.childrenintelligence.http.RequestParams;
import xiao.com.childrenintelligence.http.XmlHttpResponseHandler;
import xiao.com.childrenintelligence.tools.CommentWays;
import xiao.com.childrenintelligence.view.TitleView;

public class RegisterActivity extends Activity implements OnClickListener{
	private TitleView titleView;
	private EditText etinputNum;
	private TextView tvcomment;
	private String number;
	private ModelDao model;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.children_register);
		MyApplication.getInstance().addActivity(this);
		init();
	}
	private void init(){
		titleView = (TitleView)findViewById(R.id.mtitleview);
		model = new ModelDao(getApplicationContext());
		etinputNum = (EditText)findViewById(R.id.etinputNum);
		tvcomment = (TextView)findViewById(R.id.tvcomment);
		tvcomment.setOnClickListener(this);
		titleView.setTitle(R.string.register);
		//titleView.setbg(R.drawable.sport_titlebg);
		titleView.setBack("取消", new TitleView.onBackLister() {
			
			@Override
			public void onClick(View button) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	private void json(final String num){		
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
					
					MyApplication.registerFlag = true;
					/*Status status = new Status();
					status.setStaus(1);
					model.insertStatus(status);*/
					ArrayList<Register> registers = model.registerSearth();
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					intent.putExtra("itemId", registers.size() - 1);
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
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tvcomment:
			number = etinputNum.getText().toString();
			ArrayList<Register>  registers = model.registerSearth();
			
			if(number.equals("")){
				MyApplication.getInstance().ways.ToastShow(getApplicationContext(), "请输入电话号码");
				return;
			}
			if(!number.equals("") && registers.size() > 0){
				for(int i = 0; i<registers.size(); i ++){
					if(number.equals(registers.get(i).getNum())){
						MyApplication.getInstance().ways.ToastShow(getApplicationContext(), "电话号码已经注册过");
						return;	
					}
				}
			}
			if(CommentWays.isWifi(getApplicationContext()) == true 
		               || CommentWays.is3G(getApplicationContext()) == true){
				CommentWays.logoProgress(RegisterActivity.this);
				json(number);
				
			}else {
				MyApplication.ways.ToastShow(getApplicationContext(), "请检查网络是否良好");
			}
			
			break;

		default:
			break;
		}
	}
}
