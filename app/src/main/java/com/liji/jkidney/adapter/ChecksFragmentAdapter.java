package com.liji.jkidney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liji.jkidney.R;

/**
 * 检查项目adapters
 * Created by liji on 2016/4/10.
 */
public class ChecksFragmentAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;


    private String[] mChecksNames = {"肝功能", "肾功能", "尿蛋白", "尿常规", "尿四项", "体重", "血糖", "血压"};
    private int[] mChecksResId = {R.drawable.ic_xueye, R.drawable.ic_shengongneng,
            R.drawable.ic_niaodanbai, R.drawable.ic_niaochanggui,
            R.drawable.ic_niaosixiang, R.drawable.ic_tizhong,
            R.drawable.ic_xuetang, R.drawable.ic_xueya};

    public ChecksFragmentAdapter(Context context) {

        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mChecksNames.length;
    }

    @Override
    public Object getItem(int position) {
        return mChecksNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ItemTag viewTag;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_checks_fragment_gridview, null);
            viewTag = new ItemTag((LinearLayout) convertView.findViewById(R.id.item_llayout), (ImageView) convertView.findViewById(R.id.item_img), (TextView) convertView.findViewById(R.id.item_txt));
            convertView.setTag(viewTag);
        } else {
            viewTag = (ItemTag) convertView.getTag();
        }

        viewTag.mCheckImg.setBackgroundResource(mChecksResId[position]);
        viewTag.mCheckTxt.setText(mChecksNames[position]);
        viewTag.mCheckLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "" + mChecksNames[position], Toast.LENGTH_LONG).show();
            }
        });


        return convertView;
    }

    private class ItemTag {
        LinearLayout mCheckLayout;
        ImageView mCheckImg;
        TextView mCheckTxt;

        public ItemTag(LinearLayout mCheckLayout, ImageView mCheckImg, TextView mCheckTxt) {
            this.mCheckLayout = mCheckLayout;
            this.mCheckImg = mCheckImg;
            this.mCheckTxt = mCheckTxt;

        }
    }
}
