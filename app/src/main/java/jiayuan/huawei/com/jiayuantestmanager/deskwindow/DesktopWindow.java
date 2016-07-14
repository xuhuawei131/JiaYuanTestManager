package jiayuan.huawei.com.jiayuantestmanager.deskwindow;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jiayuan.huawei.com.jiayuantestmanager.MyApp;
import jiayuan.huawei.com.jiayuantestmanager.R;
import jiayuan.huawei.com.jiayuantestmanager.adapters.DesktopMenuAdapter;
import jiayuan.huawei.com.jiayuantestmanager.beans.ExcelBean;
import jiayuan.huawei.com.jiayuantestmanager.beans.MenuBean;
import jiayuan.huawei.com.jiayuantestmanager.constants.Constants;
import jiayuan.huawei.com.jiayuantestmanager.ui.activity.MainActivity;
import jiayuan.huawei.com.jiayuantestmanager.ui.customview.CircularMenu;
import jiayuan.huawei.com.jiayuantestmanager.utils.ClipboardUtil;
import jiayuan.huawei.com.jiayuantestmanager.utils.DensityUtil;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class DesktopWindow implements CircularMenu.OnCircleMenuItemClickListener, View.OnTouchListener {
    private boolean viewAdded = false;
    private WindowManager wm;
    private WindowManager.LayoutParams wmParams;
    private View view = null;
    private Context context;
    private ExcelBean bean;
    private DesktopMenuAdapter adapter;
    private List<String> arrayList;
    private CircularMenu wheelMenuView;
    private int lastX = 0, lastY = 0;
    private int paramX = 0, paramY = 0;

    public DesktopWindow() {
        context = MyApp.context;
        arrayList = new ArrayList<>();
        arrayList.add("账户");
        arrayList.add("密码");
        arrayList.add("UID");
        arrayList.add("开启");
        arrayList.add("关闭");
        adapter = new DesktopMenuAdapter(context, arrayList);
        view = LayoutInflater.from(context).inflate(R.layout.layout_desktop_layout, null);
        wheelMenuView = (CircularMenu) view.findViewById(R.id.circular);
        wheelMenuView.setOnTouchListener(this);
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wmParams = new WindowManager.LayoutParams();
        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT; // 系统提示类型,重要
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; // 不能抢占聚焦点
        wmParams.flags = wmParams.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        wmParams.flags = wmParams.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; // 排版不受限制
        wmParams.alpha = 1.0f;
        wmParams.gravity = Gravity.LEFT | Gravity.TOP; // 调整悬浮窗口至左上角
        wmParams.x = (Constants.WIN_WIDTH - Constants.DESKTOP_WIDTH) / 2;
        wmParams.y = (Constants.WIN_HEIGHT - Constants.DESKTOP_HEIGHT) / 2;
        wmParams.width = Constants.DESKTOP_WIDTH;
        wmParams.height = Constants.DESKTOP_HEIGHT;
//        if (view != null) {
//            wm.removeView(view);
//        }
    }

    public void showDesktopView(ExcelBean bean) {
        this.bean = bean;
        wheelMenuView.setAdapter(arrayList);
        wheelMenuView.setOnCircleMenuItemClickListener(this);
        if (viewAdded) {
            wm.updateViewLayout(view, wmParams);
        } else {
            wm.addView(view, wmParams);
            viewAdded = true;
        }
    }

    public void closeDesktopView() {
        wm.removeView(view);
        viewAdded = false;
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0://账号
                ClipboardUtil.copy(bean.userName, context);
                Toast.makeText(context, "账号已经复制", Toast.LENGTH_LONG).show();
                break;
            case 1://密码
                ClipboardUtil.copy(bean.password, context);
                Toast.makeText(context, "密码已经复制", Toast.LENGTH_LONG).show();
                break;
            case 2://uid
                ClipboardUtil.copy(bean.uid, context);
                Toast.makeText(context, "UID已经复制", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case 4:
                closeDesktopView();
                break;
        }

    }

    @Override
    public void onCenterClick() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                paramX = wmParams.x;
                paramY = wmParams.y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                wmParams.x = paramX + dx;
                wmParams.y = paramY + dy;
                // 更新悬浮窗位置
                wm.updateViewLayout(view, wmParams);
                return true;
        }
        return false;
    }
}
