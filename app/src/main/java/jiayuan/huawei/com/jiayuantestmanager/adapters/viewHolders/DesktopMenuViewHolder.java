package jiayuan.huawei.com.jiayuantestmanager.adapters.viewHolders;

import android.view.View;
import android.widget.TextView;

import jiayuan.huawei.com.jiayuantestmanager.R;
import jiayuan.huawei.com.jiayuantestmanager.adapters.MyBaseComm1Adapter;
import jiayuan.huawei.com.jiayuantestmanager.beans.DesktopBean;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class DesktopMenuViewHolder extends BaseCommViewHolder<String> {

    private TextView text_desktop;
    public DesktopMenuViewHolder(MyBaseComm1Adapter adapter, View itemView) {
        super(adapter, itemView);
    }

    @Override
    protected void findViewByIds() {
        text_desktop=(TextView)findViewById(R.id.text_desktop);
    }

    @Override
    protected void setItemData() {
        text_desktop.setText(bean);
    }

    @Override
    protected void onItemClick(int position, String bean, View view) {

    }
}
