package jiayuan.huawei.com.jiayuantestmanager.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jiayuan.huawei.com.jiayuantestmanager.R;
import jiayuan.huawei.com.jiayuantestmanager.adapters.viewHolders.MenuVewHolder;
import jiayuan.huawei.com.jiayuantestmanager.beans.MenuBean;
import jiayuan.huawei.com.jiayuantestmanager.ui.activity.MainActivity;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class MenuAdapter extends MyBaseRecyclerViewAdapter<MenuVewHolder,MenuBean,MainActivity>{

    public MenuAdapter(MainActivity context, List<MenuBean> arrayList) {
        super(context, arrayList);
    }

    @Override
    public MenuVewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflateView(R.layout.adapter_layout_menu,parent);
        return new MenuVewHolder(this,view);
    }

    @Override
    public void onBindViewHolder(MenuVewHolder holder, int position) {
        holder.setData(position,arrayList.get(position));
    }
}
