package jiayuan.huawei.com.jiayuantestmanager.adapters;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jiayuan.huawei.com.jiayuantestmanager.R;
import jiayuan.huawei.com.jiayuantestmanager.adapters.viewHolders.MainViewHolder;
import jiayuan.huawei.com.jiayuantestmanager.beans.ExcelBean;
import jiayuan.huawei.com.jiayuantestmanager.ui.activity.MainActivity;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class MainAdapter extends MyBaseRecyclerViewAdapter<MainViewHolder,ExcelBean,MainActivity> {

    public MainAdapter(MainActivity context, List<ExcelBean> arrayList) {
        super(context, arrayList);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflateView(R.layout.adapter_layout_excel,parent);
        return new MainViewHolder(this,view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.setData(position,arrayList.get(position));
    }
}
