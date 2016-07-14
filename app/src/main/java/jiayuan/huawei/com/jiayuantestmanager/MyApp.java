package jiayuan.huawei.com.jiayuantestmanager;

import android.app.Application;
import android.content.Context;

import jiayuan.huawei.com.jiayuantestmanager.constants.Constants;
import jiayuan.huawei.com.jiayuantestmanager.utils.DensityUtil;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class MyApp extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        Constants.WIN_WIDTH =MyApp.context.getResources()
                .getDisplayMetrics().widthPixels;
        Constants.WIN_HEIGHT=MyApp.context.getResources()
                .getDisplayMetrics().heightPixels;

        Constants.DESKTOP_WIDTH= DensityUtil.dip2px(125);
        Constants.DESKTOP_HEIGHT= DensityUtil.dip2px(125);
    }
}
