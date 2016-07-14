package jiayuan.huawei.com.jiayuantestmanager.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import jiayuan.huawei.com.jiayuantestmanager.R;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected abstract void initData();
    protected abstract int getActivityContentLayout();
    protected abstract void findViewByIds();
    protected abstract void requestService();
    protected abstract void onMyDestory();
    protected BaseActivity context;
    private FrameLayout layout_main_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        initData();
        setContentView(R.layout.activity_base);
        layout_main_content=(FrameLayout)super.findViewById(R.id.layout_main_content);
        int layoutID=getActivityContentLayout();
        if(layoutID!=0){
            View view= LayoutInflater.from(this).inflate(layoutID,null);
            layout_main_content.addView(view);
        }
        ButterKnife.bind(this);
        findViewByIds();
        requestService();

    }

    @Override
    public View findViewById(int id) {
        return layout_main_content.findViewById(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onMyDestory();
    }

}

