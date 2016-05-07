package xiao.com.childrenintelligence;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import xiao.com.childrenintelligence.entity.HealthScan;
import xiao.com.childrenintelligence.tools.CommentWays;

public class MyApplication extends Application {
    /**
     * final表示终态,不可以改变变量本身.static 表示类变量，值可以被改变
     */
    private static MyApplication instance;
    public static CommentWays ways;
    public boolean CONNETET = false;
    public byte[] arr = new byte[4];
    private List<Activity> activityList = new LinkedList<Activity>();
    public static Context mcontext = null;
    public static int activityStuts = 0;
    // public static EBbraceletcom eBbraceletcom;
    public static int style = 0;
    public static String uId;
    public static boolean registerFlag = false;
    public static boolean deleteFlag = false;
    public static ArrayList<HealthScan> curUserscans = new ArrayList<HealthScan>();

    private MyApplication() {
        getWays();
    }

    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    public static void getWays() {
        if (ways == null)
            ways = new CommentWays();
    }

    /**
     * 退出程序使用
     */
    //添加Activity 到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }
    //遍历所有Activity 并finish

    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
}
