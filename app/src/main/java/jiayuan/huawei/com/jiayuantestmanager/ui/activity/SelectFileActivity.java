package jiayuan.huawei.com.jiayuantestmanager.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import jiayuan.huawei.com.jiayuantestmanager.R;
import jiayuan.huawei.com.jiayuantestmanager.adapters.DesktopMenuAdapter;
import jiayuan.huawei.com.jiayuantestmanager.ui.customview.CircularMenu;

public class SelectFileActivity extends BaseActivity implements CircularMenu.OnCircleMenuItemClickListener {
    @Bind(R.id.circular)
    CircularMenu wheelMenuView;

    private List<String> arrayList;
    @Override
    protected void initData() {
        arrayList=new ArrayList<>();
        arrayList.add("账户");
        arrayList.add("密码");
        arrayList.add("UID");
    }

    @Override
    protected int getActivityContentLayout() {
        return R.layout.layout_desktop_layout;
    }

    @Override
    protected void findViewByIds() {
        wheelMenuView.setAdapter(arrayList);
        wheelMenuView.setOnCircleMenuItemClickListener(this);
    }

    @Override
    protected void requestService() {

    }

    @Override
    protected void onMyDestory() {

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onCenterClick() {

    }
}
