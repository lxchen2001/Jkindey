package com.liji.jkidney.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.model.M_ChcekInfo;
import com.liji.jkidney.model.M_Info;
import com.liji.jkidney.utils.XCallbackListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查项目adapters
 * Created by liji on 2016/4/10.
 */
public class InfoFragmentAdapter extends RecyclerView.Adapter<InfoFragmentAdapter.ItemTag> {

    private Context mContext;
    private XCallbackListener listener;
    private LayoutInflater mInflater;
    List<M_Info> infos = new ArrayList<>();


    public InfoFragmentAdapter(Context context, List<M_Info> infoList, XCallbackListener listener) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.infos = infoList;
        this.listener = listener;
    }


    @Override
    public ItemTag onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_info, parent, false);
        ItemTag viewHolder = new ItemTag(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemTag holder, final int position) {
        M_Info info = infos.get(position);
        holder.mCheckImg.setBackgroundResource(info.getmNewsTypePic());
        holder.mCheckTxt.setText(info.getmNewsType());
        holder.mCheckContent.setText("" + info.getmNewsTypeContent());
        holder.mCheckLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.call(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return infos.size();
    }


    public static class ItemTag extends RecyclerView.ViewHolder {
        RelativeLayout mCheckLayout;
        ImageView mCheckImg;
        TextView mCheckTxt;
        TextView mCheckContent;

        public ItemTag(View convertView) {
            super(convertView);
            this.mCheckLayout = (RelativeLayout) convertView.findViewById(R.id.item_rlayout);
            this.mCheckImg = (ImageView) convertView.findViewById(R.id.item_img);
            this.mCheckTxt = (TextView) convertView.findViewById(R.id.item_txt);
            this.mCheckContent = (TextView) convertView.findViewById(R.id.item_content);

        }
    }
}
