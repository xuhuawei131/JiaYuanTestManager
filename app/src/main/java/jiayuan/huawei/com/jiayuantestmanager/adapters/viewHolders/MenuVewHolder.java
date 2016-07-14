package jiayuan.huawei.com.jiayuantestmanager.adapters.viewHolders;

import android.view.View;
import android.widget.TextView;

import org.simple.eventbus.EventBus;

import jiayuan.huawei.com.jiayuantestmanager.R;
import jiayuan.huawei.com.jiayuantestmanager.adapters.MyBaseRecyclerViewAdapter;
import jiayuan.huawei.com.jiayuantestmanager.beans.MenuBean;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class MenuVewHolder extends BaseRViewHolder<MenuBean> {
    private TextView text_menu;
    private TextView text_num;

    public MenuVewHolder(MyBaseRecyclerViewAdapter adapter, View itemView) {
        super(adapter, itemView);
    }

    @Override
    protected void findViewByIds() {
        text_menu = (TextView) findViewById(R.id.text_menu);
        text_num = (TextView) findViewById(R.id.text_num);
    }

    @Override
    protected void setItemData() {
        text_menu.setText(bean.itemName);
        text_num.setText("(" + bean.num + ")");
        if (bean.isSelected) {
            itemView.setBackgroundResource(R.color.colorPrimaryDark);
        } else {
            itemView.setBackgroundResource(R.color.colorPrimary);
        }
    }

    @Override
    protected void onItemClick(int position, MenuBean bean, View view) {
        EventBus.getDefault().post(bean, "onMenuItemClick");
    }
}
