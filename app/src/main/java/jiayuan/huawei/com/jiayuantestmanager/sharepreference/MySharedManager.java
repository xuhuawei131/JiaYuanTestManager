package jiayuan.huawei.com.jiayuantestmanager.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

import jiayuan.huawei.com.jiayuantestmanager.MyApp;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class MySharedManager {
    private static final String SHARE_NAME = "wcc";

    public static void setLastExcelPath(String filePath) {
        SharedPreferences sp = MyApp.context.getSharedPreferences(
                SHARE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("filePath", filePath);
        editor.commit();
    }

    public static String getLastExcelPath() {
        SharedPreferences sp = MyApp.context.getSharedPreferences(
                SHARE_NAME, Context.MODE_PRIVATE);
        String filePath = sp.getString("filePath", null);
        return filePath;
    }

}
