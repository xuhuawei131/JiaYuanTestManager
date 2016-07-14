package jiayuan.huawei.com.jiayuantestmanager.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract  class MyBaseRecyclerViewAdapter<T extends RecyclerView.ViewHolder,DataType,ActivityType extends Activity> extends RecyclerView.Adapter<T> {
    protected List<DataType> arrayList;
    private ActivityType context;
    public MyBaseRecyclerViewAdapter(ActivityType context, List<DataType> arrayList){
            this.arrayList=arrayList;
            this.context=context;
    }

    public List<DataType> getArrayList(){
        return  arrayList;
    }
    public ActivityType getActivity(){
        return (ActivityType)context;
    }
    @Override
    public int getItemCount() {
        int length = arrayList == null ? 0 : arrayList.size();
        return length;
    }

    protected View inflateView(int resLayout,ViewGroup parent){
        return LayoutInflater.from(context).inflate(resLayout,parent,false);
    }

}
