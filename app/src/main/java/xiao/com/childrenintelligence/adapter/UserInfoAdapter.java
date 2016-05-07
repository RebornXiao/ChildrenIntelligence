package xiao.com.childrenintelligence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import xiao.com.childrenintelligence.R;
import xiao.com.childrenintelligence.entity.Register;

public class UserInfoAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private ArrayList<Register> registers;
	private Context context;
	//int test = 0 ;

	public void setBreakRules(ArrayList<Register> registers) {
		if(registers != null)
			this.registers = registers;
		else 
			registers = new ArrayList<Register>();
	}
	public UserInfoAdapter(Context context ,ArrayList<Register> registers) {
		// TODO Auto-generated constructor stub
		this.inflater = LayoutInflater.from(context);
		this.setBreakRules(registers);
		this.context = context;
	}
	public void changeData(ArrayList<Register> registers){
		this.setBreakRules(registers);
		this.notifyDataSetChanged();
		this.notifyDataSetInvalidated();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return registers.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return registers.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.children_user_item, null);
			holder.tvname = (TextView) convertView.findViewById(R.id.tvname);
			holder.tvage = (TextView) convertView.findViewById(R.id.tvage);
			holder.tvsex = (TextView) convertView.findViewById(R.id.tvsex);
			holder.ivmale = (ImageView) convertView.findViewById(R.id.ivmale);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Register register = registers.get(position);
		holder.tvname.setText(register.getName());
		holder.tvage.setText(register.getAge());
		holder.tvsex.setText(register.getSex());
		holder.ivmale.setBackgroundResource(R.drawable.male);
		
		return convertView;
	}
	class ViewHolder {
		TextView tvname;
		TextView tvage;
		TextView tvsex;
		ImageView ivmale;
	}
}
