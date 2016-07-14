package jiayuan.huawei.com.jiayuantestmanager.adapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

import jiayuan.huawei.com.jiayuantestmanager.adapters.viewHolders.BaseRViewHolder;

public abstract class MyBaseCommAdapter<VH extends BaseRViewHolder,DataType,ActivityType extends Activity> extends BaseAdapter {
	protected List<DataType> arrayList;
	private  LayoutInflater inflater;
	private ActivityType context;

	protected abstract VH createViewHolder(int position,DataType bean,ViewGroup viewgroup);
	protected abstract void bindViewHolder(int position,DataType bean,VH holder);
	
	public MyBaseCommAdapter(ActivityType context, List<DataType> arrayList){
		this.context=context;
		this.inflater=LayoutInflater.from(context);
		this.arrayList=arrayList;
	}

	public List<DataType> getArrayList(){
		return  arrayList;
	}
	public ActivityType getActivity(){
		return (ActivityType)context;
	}


	@Override
	public int getCount() {
		return arrayList==null?0:arrayList.size();
	}

	@Override
	public DataType getItem(int arg0) {
		return arrayList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	protected View inflateView(int resId){
		return inflater.inflate(resId, null);
	}
	@Override
	public View getView(int position, View converView, ViewGroup viewgroup) {
		VH holder;
		DataType bean=arrayList.get(position);
		if(converView==null){
			holder=createViewHolder(position,bean,viewgroup);
			converView=holder.itemView;
			converView.setTag(holder);
		}else{
			holder=(VH)converView.getTag();
		}
		bindViewHolder(position,bean,holder);
		return converView;
	}
	
}