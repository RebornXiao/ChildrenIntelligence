package xiao.com.childrenintelligence.tools;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.Gravity;
import android.widget.Toast;

import org.apache.http.util.EncodingUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class CommentWays {

    private Calendar ca;
    private int year, month, day;
    private static ProgressDialog dialogLogo;
    static String rootPath = Environment.getExternalStorageDirectory().getPath();  //��ȡSD���ĸ�Ŀ¼

    private Toast toast;

    public void ToastShow(Context context, String msg) {
        if (toast == null)
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 10, 50);
        toast.setText(msg);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    /*public static void NetWork(){
          String strVer=GetVersion.GetSystemVersion();
          strVer=strVer.substring(0,3).trim();
          float fv=Float.valueOf(strVer);
          if(fv>2.3){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
           .detectDiskReads().detectDiskWrites().detectNetwork() .penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
            penaltyLog().penaltyDeath().build());
           }
     }*/
    public int getnDate() {
        ca = Calendar.getInstance();
        year = ca.get(Calendar.YEAR);
        month = ca.get(Calendar.MONTH);
        day = ca.get(Calendar.DATE);
        return year * 10000 + (month + 1) * 100 + (day - 1);
    }

    /**
     * 判断当前网络是否是wifi网络
     * if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) { //判断3G�?
     *
     * @param context
     * @return boolean
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    public static boolean is3G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    public static String Write(String name) {
        String path1 = rootPath + "/" + "user" + "/";
        File file = new File(path1);
        if (!file.exists()) {
            file.mkdirs();
        }
        String path2 = path1 + "UserName.txt";
        try {
            File files = new File(path2);
            if (!files.exists()) {
                files.createNewFile();
            }
            FileOutputStream os = new FileOutputStream(files);
            byte[] buf = name.getBytes();
            os.write(buf);
            os.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;

    }

    //������ȡ�û�������
    @SuppressLint("NewApi")
    public static String read() {
        //String path = rootPath +"/"+"user"+"/";
        String path = rootPath + "/" + "user" + "/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String name = "";
        String path1 = path + "UserName.txt";
        try {
            File files = new File(path1);
            if (!files.exists()) {
                files.createNewFile();
            }
            FileInputStream fin = new FileInputStream(files);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            name = EncodingUtils.getString(buffer, "uft");//EncodingUtils
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (name == null || name.isEmpty() == true)
            name = "0";

        return name;
    }

    public static void logoProgress(Context context) {
        // TODO Auto-generated constructor stub
        dialogLogo = new ProgressDialog(context);
        dialogLogo.setMessage("登录中，请稍候..");
        dialogLogo.setCancelable(true);
        dialogLogo.setIndeterminate(false);
        dialogLogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialogLogo.setMax(100);
        dialogLogo.show();
    }

    public static void logoCancel() {
        dialogLogo.cancel();
        dialogLogo = null;
    }
}
