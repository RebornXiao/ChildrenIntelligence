package xiao.com.childrenintelligence.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import xiao.com.childrenintelligence.entity.HealthScan;
import xiao.com.childrenintelligence.entity.Register;
import xiao.com.childrenintelligence.entity.ScanFlag;
import xiao.com.childrenintelligence.entity.Status;

public class ModelDao {
	private DBOpenHelper helper;
	public ModelDao(Context context){
		helper = new DBOpenHelper(context);
	}

	public long insertRegister(Register register){
		long rowId = -1;
		ContentValues values = new ContentValues();
		values.put(DBOpenHelper.name, register.getName());
		values.put(DBOpenHelper.phoneNum, register.getNum());
		values.put(DBOpenHelper.age, register.getAge());
		values.put(DBOpenHelper.sex, register.getSex());
		values.put(DBOpenHelper.height, register.getHeight());
		values.put(DBOpenHelper.weight, register.getWeight());
		values.put(DBOpenHelper.uId, register.getuId());
		SQLiteDatabase db = helper.getWritableDatabase();
		rowId = db.insert(DBOpenHelper.registerTal, DBOpenHelper.registerId, values);
		db.close();
		return rowId;
	}
	
	public long insertStatus(Status status){
		long rowId = -1;
		ContentValues values = new ContentValues();
		values.put(DBOpenHelper.stutas, status.getStaus());
		SQLiteDatabase db = helper.getWritableDatabase();
		rowId = db.insert(DBOpenHelper.statsTal, DBOpenHelper.statusId, values);
		db.close();
		return rowId;
	}
	
	
	public long insertScanFlag(ScanFlag flag){
		long rowId = -1;
		ContentValues values = new ContentValues();
		values.put(DBOpenHelper.date, flag.getDate());
		values.put(DBOpenHelper.scanNum, flag.getNum());
		SQLiteDatabase db = helper.getWritableDatabase();
		rowId = db.insert(DBOpenHelper.scanFlagTal, DBOpenHelper.scanFlagId, values);
		db.close();
		return rowId;
	}
	
	public long deleteScanFlag(){
		long rowId = -1;
		SQLiteDatabase db = helper.getWritableDatabase();
		rowId = db.delete(DBOpenHelper.scanFlagTal, null, null);
		db.close();
		return rowId;
	}
	
	public long deleteScanFlagId(int id){
		long rowId = -1;
		SQLiteDatabase db = helper.getWritableDatabase();
		rowId = db.delete(DBOpenHelper.scanFlagTal, "_id="+id, null);
		db.close();
		return rowId;
	}
	
	public long deleteUserItem(int id){
		long rowId = -1;
		SQLiteDatabase db = helper.getWritableDatabase();
		rowId = db.delete(DBOpenHelper.registerTal, "_id="+id, null);
		db.close();
		return rowId;
	}
	
	public long deleteHealthScanItem(int id){
		long rowId = -1;
		SQLiteDatabase db = helper.getWritableDatabase();
		rowId = db.delete(DBOpenHelper.healthScanTal, "_id="+id, null);
		db.close();
		return rowId;
	}
	
	public boolean queryScanFlag(int date,String num){
		boolean flag = false;
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c =db.rawQuery("select * from "+DBOpenHelper.scanFlagTal +" "+ 
				"where date = ? AND scanNum = ?", new String[]{date+"",num});
		ScanFlag scanFlag = null;
		if(c!=null && c.getCount()>0){
			String[] colNames = c.getColumnNames();
			while(c.moveToNext()){
				scanFlag = new ScanFlag();
			  for(String columnName : colNames){//columnName字段的值
				if(columnName.equals(DBOpenHelper.date))
					scanFlag.setDate(c.getInt(c.getColumnIndex(columnName)));
				if(columnName.equals(DBOpenHelper.scanNum))
					scanFlag.setNum(c.getString(c.getColumnIndex(columnName)));
				flag = true;
				}
			}
		}
		c.close();
		db.close();
		return flag;
	}
	
	
	public long insertHealthScan(HealthScan scan){
		long rowId = -1;
		ContentValues values = new ContentValues();
		values.put(DBOpenHelper.healthDate, scan.getHShealthDate());
		values.put(DBOpenHelper.healthSteps, scan.getHSsteps());
		values.put(DBOpenHelper.healthHeart, scan.getHSheart());
		values.put(DBOpenHelper.healthKaloria, scan.getHSkaloria());
		values.put(DBOpenHelper.healthSleepDeep, scan.getHSsleepDeep());
		values.put(DBOpenHelper.healthStepTime, scan.getHSstepTime());
		values.put(DBOpenHelper.healthScore, scan.getHShealthScore());
		values.put(DBOpenHelper.num, scan.getPhoneNum());
		values.put(DBOpenHelper.healthStepsPer, scan.getHSstepsPer());
		values.put(DBOpenHelper.healthRatePer, scan.getHSratePer());
		values.put(DBOpenHelper.healthDeepPer, scan.getHSdeepPer());
		

		values.put(DBOpenHelper.higPre, scan.getHigPre());
		values.put(DBOpenHelper.lowPre, scan.getLowPre());
		values.put(DBOpenHelper.befSugar, scan.getBefSugar());
		values.put(DBOpenHelper.afterSugar, scan.getAfterSugar());
		
		SQLiteDatabase db = helper.getWritableDatabase();
		rowId = db.insert(DBOpenHelper.healthScanTal, DBOpenHelper.healthId, values);
		db.close();
		return rowId;
	}


	public HealthScan queryHealthyScan(String date){
		boolean flag = false;
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c =db.rawQuery("select * from "+DBOpenHelper.healthScanTal +" "+ 
				"where healthDate = ?", new String[]{date});
		HealthScan healthScan = null;
		if(c!=null && c.getCount()>0){
			String[] colNames = c.getColumnNames();
			while(c.moveToNext()){
				healthScan = new HealthScan();
			  for(String columnName : colNames){//columnName字段的值
				if(columnName.equals(DBOpenHelper.healthSteps))
					healthScan.setHSsteps(c.getString(c.getColumnIndex(columnName)));
				if(columnName.equals(DBOpenHelper.healthHeart))
					healthScan.setHSheart(c.getString(c.getColumnIndex(columnName)));

				if(columnName.equals(DBOpenHelper.healthKaloria))
					healthScan.setHSkaloria(c.getString(c.getColumnIndex(columnName)));
				if(columnName.equals(DBOpenHelper.healthSleepDeep))
					healthScan.setHSsleepDeep(c.getString(c.getColumnIndex(columnName)));

				if(columnName.equals(DBOpenHelper.healthStepTime))
					healthScan.setHSstepTime(c.getString(c.getColumnIndex(columnName)));
				if(columnName.equals(DBOpenHelper.healthScore))
					healthScan.setHShealthScore(c.getString(c.getColumnIndex(columnName)));
				if(columnName.equals(DBOpenHelper.healthDate))
					healthScan.setHShealthDate(c.getString(c.getColumnIndex(columnName)));
				flag = true;
				}
			}
		}
		c.close();
		db.close();
		return healthScan;
	}

	// 修改表--添加一列  
	 // @table 需要修改的table名  
	 // @column 添加的列的名称  
	 // @type 列的类型,如text,varchar等  
	 public boolean alterTableAddColumn(String table,  
	 String column, String type) {  
	 try {  
		 SQLiteDatabase db = helper.getWritableDatabase();
		 db.execSQL("alter table  " + table + " add column " + column + type + " ;");  
	   
	 } catch (SQLException e) {  
		 Log.i("info", "添加列失败");
	 return false;  
	 }  
	   
	 return true;  
	 } 
	
	
	public ArrayList<Status> statusSearth(){
 		ArrayList<Status> status = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from "+DBOpenHelper.statsTal,null); 
		status = new ArrayList<Status>();
		if(c!=null && c.getCount()>0){
			while(c.moveToNext()){
				Status status2 = new Status();
				status2.setStaus(c.getInt(c.getColumnIndex(DBOpenHelper.stutas)));
				status.add(status2);
			}
		} 
		db.close();
		c.close();
		return status;
	}
	
	public ArrayList<Register> registerSearth(){
 		ArrayList<Register> registers = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from "+DBOpenHelper.registerTal,null); 
		registers = new ArrayList<Register>();
		if(c!=null && c.getCount()>0){
			while(c.moveToNext()){
				Register register = new Register();
				register.setId(c.getInt(c.getColumnIndex(DBOpenHelper.registerId)));
				register.setName(c.getString(c.getColumnIndex(DBOpenHelper.name)));
				register.setNum(c.getString(c.getColumnIndex(DBOpenHelper.phoneNum)));
				register.setAge(c.getString(c.getColumnIndex(DBOpenHelper.age)));
				register.setSex(c.getString(c.getColumnIndex(DBOpenHelper.sex)));
				register.setHeight(c.getString(c.getColumnIndex(DBOpenHelper.height)));
				register.setWeight(c.getString(c.getColumnIndex(DBOpenHelper.weight)));
				register.setuId(c.getString(c.getColumnIndex(DBOpenHelper.uId)));
				registers.add(register);
			}
		} 
		db.close();
		c.close();
		return registers;
	}
	
	
	public ArrayList<HealthScan> HealthScanSearth(){
 		ArrayList<HealthScan> scans = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from "+DBOpenHelper.healthScanTal,null); 
		scans = new ArrayList<HealthScan>();
		if(c!=null && c.getCount()>0){
			while(c.moveToNext()){
				HealthScan scan = new HealthScan();
				scan.setId(c.getInt(c.getColumnIndex(DBOpenHelper.healthId)));
				scan.setHSsteps(c.getString(c.getColumnIndex(DBOpenHelper.healthSteps)));
				scan.setHSheart(c.getString(c.getColumnIndex(DBOpenHelper.healthHeart)));
				scan.setHSkaloria(c.getString(c.getColumnIndex(DBOpenHelper.healthKaloria)));
				scan.setHSsleepDeep(c.getString(c.getColumnIndex(DBOpenHelper.healthSleepDeep)));
				scan.setHSstepTime(c.getString(c.getColumnIndex(DBOpenHelper.healthStepTime)));
				scan.setHShealthScore(c.getString(c.getColumnIndex(DBOpenHelper.healthScore)));
				scan.setHShealthDate(c.getString(c.getColumnIndex(DBOpenHelper.healthDate)));
				scan.setPhoneNum(c.getString(c.getColumnIndex(DBOpenHelper.num)));
				scan.setHSstepsPer(c.getString(c.getColumnIndex(DBOpenHelper.healthStepsPer)));
				scan.setHSratePer(c.getString(c.getColumnIndex(DBOpenHelper.healthRatePer)));
				scan.setHSdeepPer(c.getString(c.getColumnIndex(DBOpenHelper.healthDeepPer)));
				

				scan.setHigPre(c.getString(c.getColumnIndex(DBOpenHelper.higPre)));
				scan.setLowPre(c.getString(c.getColumnIndex(DBOpenHelper.lowPre)));
				scan.setBefSugar(c.getString(c.getColumnIndex(DBOpenHelper.befSugar)));
				scan.setAfterSugar(c.getString(c.getColumnIndex(DBOpenHelper.afterSugar)));
				scans.add(scan);
			}
		} 
		db.close();
		c.close();
		return scans;
	}
	
	public ArrayList<ScanFlag> scanFlagSearth(){
 		ArrayList<ScanFlag> scanFlags = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from "+DBOpenHelper.scanFlagTal,null); 
		scanFlags = new ArrayList<ScanFlag>();
		if(c!=null && c.getCount()>0){
			while(c.moveToNext()){
				ScanFlag flag = new ScanFlag();
				flag.setId(c.getInt(c.getColumnIndex(DBOpenHelper.scanFlagId)));
				flag.setDate(c.getInt(c.getColumnIndex(DBOpenHelper.date)));
				flag.setNum(c.getString(c.getColumnIndex(DBOpenHelper.scanNum)));
				scanFlags.add(flag);
			}
		} 
		db.close();
		c.close();
		return scanFlags;
	}
	
	
}
