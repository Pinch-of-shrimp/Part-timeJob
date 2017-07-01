package com.example.wkg.part_timejob;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.wkg.part_timejob.R.id.tv_theconversation;

/**
 * Created by Administrator on 2017/6/26.
 */

public class conversation_rv_adapter extends RecyclerView.Adapter<conversation_rv_adapter.MyViewHolder> {
    ArrayList<Conversation_Data>arrayList;
    Context context;
    MyItemClickListener myItemClickListener;
    public conversation_rv_adapter(Context context)
    {
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_conversation_layout,parent,false),arrayList,myItemClickListener);
        return holder;
    }
    public void setArrayList(ArrayList<Conversation_Data>datas)
    {
        arrayList=datas;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.init(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_name,tv_data,tv_conversation;
        ImageView iv_photo;
        ArrayList<Conversation_Data> data;
        MyItemClickListener mListnener;
        public MyViewHolder(View itemView,ArrayList<Conversation_Data> data,MyItemClickListener myItemClickListener) {
            super(itemView);
            tv_name= (TextView) itemView.findViewById(R.id.tv_thename);
            tv_conversation= (TextView) itemView.findViewById(R.id.tv_theconversation);
            tv_data= (TextView) itemView.findViewById(R.id.tv_thedata);
            iv_photo= (ImageView) itemView.findViewById(R.id.iv_photo);
            this.mListnener=myItemClickListener;
            itemView.setOnClickListener(this);
            this.data=data;
        }
        public void init(int position)
        {
            tv_name.setText(data.get(position).getTheName());
            tv_data.setText(data.get(position).getTheData());
            tv_conversation.setText(data.get(position).getTheConversation());
            iv_photo.setImageResource(R.mipmap.user);

        }

        @Override
        public void onClick(View v) {
            if(mListnener!=null)
            {
                mListnener.onItemClick(v,getPosition());
            }
        }
    }
    public interface MyItemClickListener
    {
        void onItemClick(View view,int position);
    }
    public void addData(Conversation_Data n)
    {
        arrayList.add(n);
        notifyItemInserted(arrayList.size());
    }
    public void setItemClickListener(MyItemClickListener myItemClickListener)
    {
        this.myItemClickListener=myItemClickListener;
    }
}
