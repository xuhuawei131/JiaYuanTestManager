package jiayuan.huawei.com.jiayuantestmanager.ui.activity;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import jiayuan.huawei.com.jiayuantestmanager.R;
import jiayuan.huawei.com.jiayuantestmanager.adapters.MainAdapter;
import jiayuan.huawei.com.jiayuantestmanager.adapters.MenuAdapter;
import jiayuan.huawei.com.jiayuantestmanager.adapters.itemdecorations.SpacesItemDecoration;
import jiayuan.huawei.com.jiayuantestmanager.beans.ExcelBean;
import jiayuan.huawei.com.jiayuantestmanager.beans.MenuBean;
import jiayuan.huawei.com.jiayuantestmanager.sharepreference.MySharedManager;
import jiayuan.huawei.com.jiayuantestmanager.utils.ExcelUtils;
import jiayuan.huawei.com.jiayuantestmanager.utils.Utils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.mainRecycler)
    RecyclerView mainRecycler;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.match_title)
    TextView match_title;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.menuRecycler)
    RecyclerView menuRecycler;

    private List<MenuBean> menuList;
    private List<ExcelBean> mainList;

    private MenuAdapter menuAdapter;
    private MainAdapter mainAdapter;
    private String filePath;
    private String fileName;
    private Map<MenuBean, List<ExcelBean>> excelMap;

    @Override
    protected void initData() {
        Log.v("xhw","initData ");
        initIntent();
        menuList = new ArrayList<>();
        mainList = new ArrayList<>();

        menuAdapter = new MenuAdapter(this, menuList);
        mainAdapter = new MainAdapter(this, mainList);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Log.v("xhw","onNewIntent");
        initIntent();
        setExcelFilePath(filePath);
    }

    private void initIntent() {
        filePath = this.getIntent().getStringExtra("filePath");
        Log.v("xhw","initIntent filePath "+filePath);
        if(TextUtils.isEmpty(filePath)){
            filePath=MySharedManager.getLastExcelPath();
        }
        fileName = Utils.getFileName(filePath);
    }

    @Override
    protected int getActivityContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViewByIds() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);//如果调用toolbar的话  不会起作用


        match_title.setText(fileName);

        //toggle 开关 http://www.it165.net/pro/html/201507/46841.html
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.addDrawerListener(mDrawerToggle);


        LinearLayoutManager menuManager = new LinearLayoutManager(this);
        menuManager.setOrientation(LinearLayoutManager.VERTICAL);
        SpacesItemDecoration decoration = new SpacesItemDecoration(this, LinearLayoutManager.VERTICAL);

        menuRecycler.setLayoutManager(menuManager);
        menuRecycler.addItemDecoration(decoration);
        menuRecycler.setAdapter(menuAdapter);

        LinearLayoutManager excelManager = new LinearLayoutManager(this);
        excelManager.setOrientation(LinearLayoutManager.VERTICAL);

        mainRecycler.setLayoutManager(excelManager);
        mainRecycler.addItemDecoration(decoration);
        mainRecycler.setAdapter(mainAdapter);
    }

    @Override
    protected void requestService() {
        Log.v("xhw","requestService");
        setExcelFilePath(filePath);
    }

    private void setExcelFilePath(String filePath) {
        Observable.just(filePath)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String path) {
                        excelMap = ExcelUtils.getExcelMapByFile(path);
                        menuList.clear();
                        for (Map.Entry<MenuBean, List<ExcelBean>> entry : excelMap.entrySet()) {
                            MenuBean bean = entry.getKey();//entry.getValue();
                            menuList.add(bean);
                        }
                        return path;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        MenuBean menuBean = menuList.get(0);
                        setTestTitle(menuBean);
                        menuBean.isSelected = true;
                        mainList.clear();
                        mainList.addAll(excelMap.get(menuBean));
                        menuAdapter.notifyDataSetChanged();
                        mainAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void onMyDestory() {

    }

    @Subscriber(tag = "onMenuItemClick")
    public void onMenuItemClick(MenuBean bean) {
        for (MenuBean item : menuList) {
            if (item.equals(bean)) {
                item.isSelected = true;
            } else {
                item.isSelected = false;
            }
        }
        mainList.clear();
        setTestTitle(bean);
        mainList.addAll(excelMap.get(bean));
        menuAdapter.notifyDataSetChanged();
        mainAdapter.notifyDataSetChanged();
        mDrawerLayout.closeDrawers();
    }

    private void setTestTitle(MenuBean bean) {
        match_title.setText(fileName + "---" + bean.itemName);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}
