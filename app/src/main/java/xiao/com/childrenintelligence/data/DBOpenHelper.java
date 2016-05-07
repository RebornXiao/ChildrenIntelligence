package xiao.com.childrenintelligence.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {
	private static final String DBNAME = "children.db";
	
	/**
	 *注册表
	 */
	public static final String registerTal = "registerTal";
	public static String registerId = "_id";
	public static String name = "name";
	public static String phoneNum = "num";
	public static String age = "age";
	public static String sex = "sex";
	public static String height = "height";
	public static String weight = "weight";
	public static String uId = "uId";
	
	

	/**
	 *保存状态表 
	 */

	public static final String statsTal = "statsTal";
	public static String stutas = "stutas";
	public static String statusId = "_id";
	
	
	
	/**
	 *健康扫描表 
	 */
	public static final String healthScanTal = "healthScanTal";
	public static String healthId = "_id";
	public static String healthSteps = "healthSteps";
	public static String healthHeart = "healthHeart";
	public static String healthKaloria = "healthKaloria";
	public static String healthSleepDeep = "healthSleepDeep";
	public static String healthStepTime = "healthStepTime";
	public static String healthScore = "healthScore";
	public static String healthDate = "healthDate";
	public static String num = "num";
	public static String healthStepsPer = "healthStepsPer";
	public static String healthRatePer = "healthRatePer";
	public static String healthDeepPer = "healthDeepPer";
	

	public static String higPre = "higPre";
	public static String lowPre = "lowPre";
	public static String befSugar = "befSugar";
	public static String afterSugar = "afterSugar";
 	
	/**
	 * 健康扫描标识表,如果健康扫描已向手环取昨天的数据，标志为1，否则为0
	 */
	public static final String scanFlagTal = "scanFlagTal";
	public static String scanFlagId = "_id";
	public static String date = "date";
	public static String scanNum = "scanNum";
	
	
	
	public DBOpenHelper(Context context){
		super(context,DBNAME,null,3);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub 
		//可以修改表名，添加字段，不能修改字段，删除字段.向表中添加一个字段,ALTER TABLE `table1` ADD `AAAA` VARCHAR(10) NOT NULL ;
		String registerSql = "create table if not exists " + registerTal + "(" +
				"_id integer primary key autoincrement,name text,num text,"+
				"age text,sex text,height text,weight text,uId text"+
				")";
		db.execSQL(registerSql);
		
		
		String scanFlagSql = "create table if not exists " + scanFlagTal + "(" +
				"_id integer primary key autoincrement,date integer,scanNum text"+
				")";
		db.execSQL(scanFlagSql);
		
		String statsSql = "create table if not exists " + statsTal + "(" +
				"_id integer primary key autoincrement,stutas integer"+
				")";
		db.execSQL(statsSql);
		
		
		String healthSql = "create table if not exists " + healthScanTal + "(" +
				"_id integer primary key autoincrement,healthDate text,healthSteps text,"+
				"healthHeart text,healthKaloria text,healthSleepDeep text,healthStepTime text,"+
				"healthScore text,num text,healthStepsPer text,healthRatePer text,healthDeepPer text," +
				"higPre text,lowPre text,befSugar text,afterSugar text"+
				")";
		db.execSQL(healthSql);
		
		//测试添加一列
	//	db.execSQL("alter table  " + healthScanTal + " add column " + "test" +  "text" + " ;"); 
	}
	
	//定义升级函数
	private void upgradeDatabaseToVersion1(SQLiteDatabase db) {   
		// Add 'new' column to mytable table.        
		//db.execSQL("ALTER TABLE healthScanTal ADD COLUMN higPre afterSugar TEXT");    
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 switch (oldVersion) {        
		 case 2:            
		  if (newVersion <= 1) {                
			  return;            
			  }            
			 db.beginTransaction();            
			 try {               
				 upgradeDatabaseToVersion1(db);                
				 db.setTransactionSuccessful();           
				 } catch (Throwable ex) {               
				   Log.e("info", ex.getMessage(), ex);              
				   break;           
				 } finally {               
				   db.endTransaction();            
					}	   
			  break;  
			 }   
		 Log.e("info", "Destroying all old data.");   
	//	 dropAll(db);    删除所有的表
	//	 onCreate(db);
	}

}
