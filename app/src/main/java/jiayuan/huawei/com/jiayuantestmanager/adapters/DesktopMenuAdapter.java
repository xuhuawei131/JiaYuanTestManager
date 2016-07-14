package jiayuan.huawei.com.jiayuantestmanager.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jiayuan.huawei.com.jiayuantestmanager.R;
import jiayuan.huawei.com.jiayuantestmanager.adapters.viewHolders.DesktopMenuViewHolder;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class DesktopMenuAdapter extends MyBaseComm1Adapter<DesktopMenuViewHolder,String> {
    public DesktopMenuAdapter(Context context, List<String> arrayList) {
        super(context, arrayList);
    }
    @Override
    protected DesktopMenuViewHolder createViewHolder(int position, String bean, ViewGroup viewgroup) {
        View view=inflateView(R.layout.adapter_layout_desktop,viewgroup);
        DesktopMenuViewHolder holder=new DesktopMenuViewHolder(this,view);
        return holder;
    }

    @Override
    protected void bindViewHolder(int position, String bean, DesktopMenuViewHolder holder) {
        holder.setData(position,bean);
    }
}
