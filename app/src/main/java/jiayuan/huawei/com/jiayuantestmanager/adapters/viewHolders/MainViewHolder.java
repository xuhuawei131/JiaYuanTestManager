package jiayuan.huawei.com.jiayuantestmanager.adapters.viewHolders;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import jiayuan.huawei.com.jiayuantestmanager.R;
import jiayuan.huawei.com.jiayuantestmanager.adapters.MyBaseRecyclerViewAdapter;
import jiayuan.huawei.com.jiayuantestmanager.beans.ExcelBean;
import jiayuan.huawei.com.jiayuantestmanager.constants.Constants;
import jiayuan.huawei.com.jiayuantestmanager.deskwindow.DesktopWindow;
import jiayuan.huawei.com.jiayuantestmanager.ui.activity.SelectFileActivity;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class MainViewHolder extends BaseRViewHolder<ExcelBean> {
    private TextView text_name;
    private TextView text_code;
    private TextView text_uid;
    private ImageView image_sex;
    private TextView text_sex;
    private TextView btn_copy;
    public MainViewHolder(MyBaseRecyclerViewAdapter adapter, View itemView) {
        super(adapter, itemView);
    }

    @Override
    protected void findViewByIds() {
        text_name = (TextView) findViewById(R.id.text_name);
        text_code = (TextView) findViewById(R.id.text_code);
        text_uid = (TextView) findViewById(R.id.text_uid);
        text_sex= (TextView) findViewById(R.id.text_sex);
        image_sex=(ImageView)findViewById(R.id.image_sex);
        btn_copy=(TextView)findViewById(R.id.btn_copy);
    }

    @Override
    protected void setItemData() {
        text_name.setText("账户:" + bean.userName);
        text_code.setText("密码:" + bean.password);
        text_uid.setText("UID:" + bean.uid);
        text_sex.setText("性别:" + bean.sex);

        if (bean.sex.equals(Constants.SEX_MAN)) {
            image_sex.setImageResource(R.drawable.default_avatar_m);
        } else if (bean.sex.equals(Constants.SEX_WOMAN)) {
            image_sex.setImageResource(R.drawable.default_avatar_f);
        } else {
            image_sex.setImageResource(R.drawable.default_photo);
        }
        image_sex.setOnClickListener(this);
        btn_copy.setOnClickListener(this);
    }

    @Override
    protected void onItemClick(int position, ExcelBean bean, View view) {
        Intent intent=new Intent(adapter.getActivity(), SelectFileActivity.class);
        adapter.getActivity().startActivity(intent);
    }
    @Override
    public void onClick(View view) {
        super.onClick(view);
        if(image_sex==view){

        }else if(btn_copy==view){
            DesktopWindow window=new DesktopWindow();
            window.showDesktopView(bean);
        }
    }
}
