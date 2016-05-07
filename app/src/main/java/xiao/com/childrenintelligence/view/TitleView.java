package xiao.com.childrenintelligence.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import xiao.com.childrenintelligence.R;


public class TitleView extends LinearLayout implements OnClickListener{
	private LinearLayout layout_top_bg;
	private TextView ivback;
	//private ImageView ivshare,ivset;
	private TextView ivset;
	private TextView mTitle;
	private onBackLister monClickListener;
	private onShareLister mShareonClickListener;
	private onSetLister monSetClickListener;
	public interface onBackLister {
		public void onClick(View button);
	}

	public interface onShareLister {
		public void onClick(View button);
	}
	
	public interface onSetLister {
		public void onClick(View button);
	}
	
	public void setbg(int res){
		layout_top_bg.setBackgroundResource(res);
	}
	
	public void setBack(String res, onBackLister listener) {
		ivback.setText(res);
		ivback.setVisibility(View.VISIBLE);
		monClickListener = listener;
	}
	
	public void HiddenBackImg() {
		ivback.setVisibility(View.INVISIBLE);
	}
	
	public void showBack() {
		ivback.setVisibility(View.VISIBLE);
	}
	

	/*public void Share(int res,onShareLister listener)  {
		ivshare.setVisibility(View.VISIBLE);
		ivshare.setBackgroundResource(res);
		mShareonClickListener = listener;
		
	}
	public void HiddenShareImg() {
		ivshare.setVisibility(View.INVISIBLE);
	}
	
	public void ShowShareImg() {
		ivshare.setVisibility(View.VISIBLE);
	}*/
	
	
	
	public void Set( String resid,onSetLister listener) {
		ivset.setText(resid);
		ivset.setVisibility(View.VISIBLE);
		monSetClickListener = listener;
	}
	public void HiddenSetImg() {
		ivset.setVisibility(View.INVISIBLE);
	}
	
	public void showSetImg() {
		ivset.setVisibility(View.VISIBLE);
	}

	public TitleView(Context context) {
		this(context, null);
	}

	public TitleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	@SuppressLint("NewApi")
	public TitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.children_title_view, this, true);
		layout_top_bg = (LinearLayout)findViewById(R.id.title_bg);
		mTitle = (TextView)findViewById(R.id.tvTitle);
		ivback = (TextView)findViewById(R.id.ivback);
	//	ivshare = (ImageView)findViewById(R.id.ivshare);
		ivset = (TextView)findViewById(R.id.ivset);
		ivback.setOnClickListener(this);
		ivset.setOnClickListener(this);
	//	ivshare.setOnClickListener(this);
	}
	public void setTitle(int stringID) {
		mTitle.setVisibility(View.VISIBLE);
		mTitle.setText(stringID);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivback:
			monClickListener.onClick(v);
			break;

		/*case R.id.ivshare:
			mShareonClickListener.onClick(v);
			break;*/
		case R.id.ivset:

			monSetClickListener.onClick(v);
			break;
		}
	}

}
