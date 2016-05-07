package xiao.com.childrenintelligence.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
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
import xiao.com.childrenintelligence.entity.ScanFlag;
import xiao.com.childrenintelligence.http.RequestParams;
import xiao.com.childrenintelligence.http.XmlHttpResponseHandler;

public class MainDataView extends LinearLayout {

	//private int positionId ;
	private ArrayList<Register> registers;
//	private ArrayList<HealthScan> scans;
	private ModelDao model;
	private TextView date;
//	private Intent intent;
	private TextView  score,steps,stepsPercent;//scorePercent
	private TextView  sportTime,sportTimePercent,kaloria,kaloriaPercent;
	private TextView heartRate,heartRatePercent,deepSleep,deepSleepPercent;
	private TextView tvpressure,tvpressureLow,tvsugar,tvsugarAfter;
	private ImageView iv1,iv2,iv3,iv4,iv5;
	private TextView tvhealthScanTips;
	public MainDataView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		model = new ModelDao(context);
		init();
	}
	
	public void init(){
		View view = LayoutInflater.from(getContext()).inflate(R.layout.mainview, null);
		registers = model.registerSearth();
		//scorePercent = (TextView)findViewById(R.id.tvscorePercent);
		score = (TextView)view.findViewById(R.id.tvscore);
		steps = (TextView)view.findViewById(R.id.tvsteps);
		stepsPercent = (TextView)view.findViewById(R.id.tvstepsPercent);
		sportTime = (TextView)view.findViewById(R.id.tvsportTime);
		sportTimePercent = (TextView)view.findViewById(R.id.tvsportTimePercent);
		kaloria = (TextView)view.findViewById(R.id.tvkaloria);
		kaloriaPercent = (TextView)view.findViewById(R.id.tvkaloriaPercent);
		heartRate = (TextView)view.findViewById(R.id.tvheartRate);
		heartRatePercent = (TextView)view.findViewById(R.id.tvheartRatePercent);
		deepSleep = (TextView)view.findViewById(R.id.tvdeepSleep);
		deepSleepPercent = (TextView)view.findViewById(R.id.tvdeepSleepPercent);
		date = (TextView)view.findViewById(R.id.tvdate);
		
		tvhealthScanTips = (TextView)view.findViewById(R.id.tvhealthScanTips);
		iv1 = (ImageView)view.findViewById(R.id.iv1);
		iv2 = (ImageView)view.findViewById(R.id.iv2);
		iv3 = (ImageView)view.findViewById(R.id.iv3);
		iv4 = (ImageView)view.findViewById(R.id.iv4);
		iv5 = (ImageView)view.findViewById(R.id.iv5);
		
		tvpressure = (TextView)view.findViewById(R.id.tvbloodPressure);
		tvsugar = (TextView)view.findViewById(R.id.tvbloodSugar);
		tvpressureLow = (TextView)view.findViewById(R.id.tvweight);
		tvsugarAfter = (TextView)view.findViewById(R.id.tvbloodSugarAfter);
		addView(view);
	}
	//可能要传一个时间参数,日期要做处理
	public void dealData(int i){
		steps.setText(MyApplication.curUserscans.get(i).getHSsteps());
		heartRate.setText(MyApplication.curUserscans.get(i).getHSheart());
		kaloria.setText(MyApplication.curUserscans.get(i).getHSkaloria());
		deepSleep.setText(MyApplication.curUserscans.get(i).getHSsleepDeep());
		sportTime.setText(MyApplication.curUserscans.get(i).getHSstepTime());
		score.setText(MyApplication.curUserscans.get(i).getHShealthScore());
		deepSleepPercent.setText(MyApplication.curUserscans.get(i).getHSdeepPer());
		heartRatePercent.setText(MyApplication.curUserscans.get(i).getHSratePer());
		stepsPercent.setText(MyApplication.curUserscans.get(i).getHSstepsPer());
		sportTimePercent.setText(MyApplication.curUserscans.get(i).getHSstepsPer());
		kaloriaPercent.setText(MyApplication.curUserscans.get(i).getHSstepsPer());
		date.setText(MyApplication.curUserscans.get(i).getHShealthDate());
		int hsScore = 0;
		String nscore = MyApplication.curUserscans.get(i).getHShealthScore();
		if(nscore != null && !nscore.equals("") && !nscore.equals("差")){
		   hsScore = Integer.parseInt(nscore);
			}
		if(hsScore > 60 && hsScore < 80){
		   iv1.setBackgroundResource(R.drawable.bright);
		   iv2.setBackgroundResource(R.drawable.bright);
		   iv3.setBackgroundResource(R.drawable.bright);
		   iv4.setBackgroundResource(R.drawable.gray);
		   iv5.setBackgroundResource(R.drawable.gray);
		   tvhealthScanTips.setText("您昨日运动指标良好，请继续保持!              ");
			}else if(hsScore > 80 && hsScore < 90){
		   iv1.setBackgroundResource(R.drawable.bright);
		   iv2.setBackgroundResource(R.drawable.bright);
		   iv3.setBackgroundResource(R.drawable.bright);
		   iv4.setBackgroundResource(R.drawable.bright);
		   iv5.setBackgroundResource(R.drawable.gray);
		   tvhealthScanTips.setText("您昨日运动指标良好，请继续保持!              ");
			}else if(hsScore > 90 ){
		   iv1.setBackgroundResource(R.drawable.bright);
		   iv2.setBackgroundResource(R.drawable.bright);
		   iv3.setBackgroundResource(R.drawable.bright);
		   iv4.setBackgroundResource(R.drawable.bright);
		   iv5.setBackgroundResource(R.drawable.bright);
		   tvhealthScanTips.setText("您昨日运动指标良好，请继续保持!              ");
		   }else if(hsScore == 0){
			tvhealthScanTips.setText("1.您昨日的运动指标不好,请加强身体锻炼\n2.可能是您使用方法不当.");
		   }
	}
	
	
	public void json(final int id){		
		String op = "10005";
		//final ArrayList<Register> registers = model.registerSearth();
		if(registers.size() == 0) return;
		final String uId = registers.get(id).getuId();//"2558128";
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("op", op);
		map2.put("uId", uId);
		RequestParams params = new RequestParams(map2);
		
		PatientRestClient.post("getSport", params, new XmlHttpResponseHandler(){
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				super.onFinish();
				Log.i("info", "onFinish");
			}
			@Override
			public void onSuccess(JSONObject response) {//回复6代表什么???
				// TODO Auto-generated method stub
				super.onSuccess(response);
				try {
					
				String ack = response.getString("ack");
				String op = response.getString("op");
				if(ack.equals("0") && op.equals("10005")){
					
					MyApplication.getInstance().ways.ToastShow(getContext(), "成功");
					String info = response.getString("info");
					JSONArray array = null;
					if(info != null && !info.equals("{}")){
						//删除之前的数据
						if(MyApplication.curUserscans.size() > 0){
						   for(int i = 0; i < MyApplication.curUserscans.size(); i++){
							   model.deleteHealthScanItem(MyApplication.curUserscans.get(i).getId());
						   }
						}
					   MyApplication.curUserscans = new ArrayList<HealthScan>();
					   array = new JSONArray(info);
					   HealthScan scan = null;
						for(int i = 0 ; i< array.length(); i++){
							JSONObject object = (JSONObject) array.get(i); 
							scan = new HealthScan();
							scan.setHSsteps(object.getString("steps"));
							scan.setHSstepTime(object.getString("sportTime")+"分钟");
							scan.setHSkaloria(object.getString("calories"));
							scan.setHSsleepDeep(object.getString("deepTime")+"小时");
							scan.setHShealthDate( object.getString("time"));
							scan.setHSheart(object.getString("heartRate"));
							scan.setAfterSugar(object.getString("afterBlood"));
							scan.setBefSugar(object.getString("befmeaBlood"));
							scan.setHigPre(object.getString("higPressure"));
							scan.setLowPre(object.getString("lowPressure"));
							scan.setHSdeepPer(object.getString("deepTimePer"));
							scan.setHSstepsPer(object.getString("stepsPer"));
							scan.setHSratePer(object.getString("heartRatePer"));
							
							String nscore = object.getString("score");
							if(nscore.equals("0")) scan.setHShealthScore("差");
							else scan.setHShealthScore(nscore);
							
							//保存每日取数据的标志，一天只能取一次,电话号码和日期作为标志
							if(registers.size() > 0) {
								for(int n = 0; n < registers.size(); n++){
									if(registers.get(n).getuId().equals(uId)){
										scan.setPhoneNum(registers.get(n).getNum());
										break;
									}
								}
							}
							
							model.insertHealthScan(scan);
							//数据添加到临时的数组里面
							MyApplication.curUserscans.add(scan);
						}
						//显示第一条数据
						if(MyApplication.curUserscans.size() > 0 ){
							dealData(0);
						}
						
						ScanFlag scanFlag = new ScanFlag();
						scanFlag.setDate(MyApplication.ways.getnDate());
						scanFlag.setNum(registers.get(id).getNum());
						model.insertScanFlag(scanFlag);
					}
					
					
					
					
					/*String name = response.getString("info");
					JSONObject object2 = new JSONObject(name);  
				    String nsportTime = object2.getString("sportTime");
				    String ncaloria = object2.getString("calories");
					String ndeepTime = object2.getString("deepTime");
					String nheartRate = object2.getString("heartRate");
					String nsteps = object2.getString("steps");
					String ntime = object2.getString("time");
					

					String nstepPer = object2.getString("stepsPer");
					String ndeepTimePer = object2.getString("deepTimePer");
					String nheartRatePer = object2.getString("heartRatePer");
					String nscore = object2.getString("score");
					

					String nhigPressure = object2.getString("higPressure");
					String nlowPressure = object2.getString("lowPressure");
					String nbefSugar = object2.getString("befmeaBlood");
					String nafterSugar = object2.getString("afterBlood");
					
					
					Log.i("info", "nscooo="+nscore);
					steps.setText(nsteps);
					stepsPercent.setText(nstepPer+"%");
					sportTimePercent.setText(nstepPer+"%");
					kaloriaPercent.setText(nstepPer+"%");
					heartRatePercent.setText(nheartRatePer+"%");
					deepSleepPercent.setText(ndeepTimePer+"%");
					if(score.equals("0"))
					score.setText("差");
					else score.setText(nscore);
					sportTime.setText(nsportTime+"分钟");
					kaloria.setText(ncaloria);
					deepSleep.setText(ndeepTime+"小时");
					heartRate.setText(nheartRate+"分/次数");
					date.setText(ntime);
					
					tvpressure.setText(nhigPressure);
					tvpressureLow.setText(nlowPressure);
					tvsugar.setText(nbefSugar);
					tvsugarAfter.setText(nafterSugar);
					
					int hsScore = 0 ;
					if(nscore != null && !nscore.equals("") && !nscore.equals("差")){
						hsScore = Integer.parseInt(nscore);
					}
					if(hsScore > 60 && hsScore < 80){
						   iv1.setBackgroundResource(R.drawable.bright);
						   iv2.setBackgroundResource(R.drawable.bright);
						   iv3.setBackgroundResource(R.drawable.bright);
						   iv4.setBackgroundResource(R.drawable.gray);
						   iv5.setBackgroundResource(R.drawable.gray);
						   tvhealthScanTips.setText("您昨日运动指标良好，请继续保持!");
						}else if(hsScore > 80 && hsScore < 90){
							   iv1.setBackgroundResource(R.drawable.bright);
							   iv2.setBackgroundResource(R.drawable.bright);
							   iv3.setBackgroundResource(R.drawable.bright);
							   iv4.setBackgroundResource(R.drawable.bright);
							   iv5.setBackgroundResource(R.drawable.gray);
							   tvhealthScanTips.setText("您昨日运动指标良好，请继续保持!");
						}else if(hsScore > 90 ){
							   iv1.setBackgroundResource(R.drawable.bright);
							   iv2.setBackgroundResource(R.drawable.bright);
							   iv3.setBackgroundResource(R.drawable.bright);
							   iv4.setBackgroundResource(R.drawable.bright);
							   iv5.setBackgroundResource(R.drawable.bright);
							   tvhealthScanTips.setText("您昨日运动指标良好，请继续保持!");
						}else if(hsScore == 0){
							tvhealthScanTips.setText("1.您昨日的运动指标不好,请加强身体锻炼\n2.可能是您使用方法不当.");
						}
					
					*/
					/*//保存健康浏览数据
					HealthScan scan = new HealthScan();
					scan.setHShealthDate(ntime);
					scan.setHSheart(nheartRate);
					scan.setHSkaloria(ncaloria);
					scan.setHSsleepDeep(ndeepTime);
					scan.setHSsteps(nsteps);
					scan.setHSstepTime(nsportTime);
					scan.setHSstepsPer(nstepPer+"%");
					scan.setHSdeepPer(ndeepTimePer+"%");
					scan.setHSratePer(nheartRatePer+"%");
					if(nscore.equals("0")) scan.setHShealthScore("差");
					else scan.setHShealthScore(nscore);*/
					
				}else if(ack.equals("1")){
					//MyApplication.getInstance().ways.ToastShow(getApplicationContext(), "用户不存在");
					
				}else if(ack.equals("2")){
					//MyApplication.getInstance().ways.ToastShow(getApplicationContext(), "失败");
				  }else if(ack.equals("3")){
					//MyApplication.getInstance().ways.ToastShow(getApplicationContext(), "没有运动信息");
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
}
