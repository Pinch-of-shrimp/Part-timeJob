package com.example.wkg.part_timejob;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/29.
 */

public class conversationShow_rv_adpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Message>list;
    private Intent intent;
    private Context context;
    private Activity activity;
    private String avator;

    public conversationShow_rv_adpter(ArrayList<Message>data,Activity activity,String avator,Context context)
    {
        list=data;
        this.activity=activity;
        this.avator=avator;
        this.context=context;
    }
    public void addData(Message message)
    {
        list.add(list.size(),message);
        notifyItemInserted(list.size());
        notifyDataSetChanged();
    }
    @Override
    public int getItemViewType(int position)
    {
        Message message=list.get(position);
        if (message.getDirect()==MessageDirect.send)
        {
            if (message.getContentType()==ContentType.text)
            {
                return Constants.SENDTEXT;
            }
            else if(message.getContentType()==ContentType.image) {
            return Constants.SENDIMAGE;
        }
        }
        else if (message.getDirect() == MessageDirect.receive)
        {
            if(message.getContentType()==ContentType.text)
            {
                return Constants.RECIVIRETEXT;
            }
            else if(message.getContentType()==ContentType.image)
                return Constants.RECIVERIMAGE;
        }
        return super.getItemViewType(position);

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        switch (viewType)
        {
            case Constants.SENDTEXT:
                view= LayoutInflater.from(context).inflate(R.layout.rv_conversation_sendertext_layout,parent,false);
                return new SendTextViewHolder(view);
            case Constants.SENDIMAGE:
                view=LayoutInflater.from(context).inflate(R.layout.rv_conversation_senderimage_layout,parent,false);
                return new SendImageViewHolder(view);
            case Constants.RECIVIRETEXT:
                view=LayoutInflater.from(context).inflate(R.layout.rv_conversation_receivertext_layout,parent,false);
                return new ReciveTextViewHolder(view);
            case Constants.RECIVERIMAGE:
                view=LayoutInflater.from(context).inflate(R.layout.rv_conversation_receiverimage_layout,parent,false);
                return new ReciveImageViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SendTextViewHolder)
        {
            Message message=list.get(position);
            String str=((TextContent)message.getContent()).getText();
            //((SendTextViewHolder)holder).tv_item_send_txt.setText(message.getContent().getStringExtra("text"));
            ((SendTextViewHolder)holder).tv_send_msg_date.setText("the time"+message.getCreateTime());
            ((SendTextViewHolder)holder).tv_item_send_txt.setText(""+str);
        }
        else if(holder instanceof SendImageViewHolder)
        {

        }
        else if (holder instanceof ReciveTextViewHolder)
        {
            Message message=list.get(position);
            //((ReciveTextViewHolder)holder).tv_item_from_txt.setText("21321321");
            String str=((TextContent)message.getContent()).getText();
            ((ReciveTextViewHolder)holder).tv_item_from_txt.setText(str);
            ((ReciveTextViewHolder)holder).tv_from_msg_date.setText("the time"+message.getCreateTime());
        }
        else
        {

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class SendTextViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_item_send_txt;
        public TextView tv_send_msg_date;
        public SendTextViewHolder(View itemView) {
            super(itemView);
            tv_item_send_txt = (TextView) itemView.findViewById(R.id.tv_send_item);
            tv_send_msg_date = (TextView) itemView.findViewById(R.id.tv_send_msg_date);
        }
    }
    class SendImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_item_send_image;
        public SendImageViewHolder(View itemView) {
            super(itemView);
            iv_item_send_image = (ImageView) itemView.findViewById(R.id.iv_send_image);
        }
    }
    class ReciveTextViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tv_item_from_txt;
        public ImageView from_person_avator;
        public TextView tv_from_msg_date;

        public ReciveTextViewHolder(View view) {
            super(view);
            tv_item_from_txt = (TextView) view.findViewById(R.id.tv_item_from_txt);
            from_person_avator = (ImageView) view.findViewById(R.id.from_person_avator);
            tv_from_msg_date = (TextView) view.findViewById(R.id.tv_from_msg_date);
        }
    }
    class ReciveImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_item_from_image;
        public ImageView from_person_avator;
        public ReciveImageViewHolder(View itemView) {
            super(itemView);
            iv_item_from_image = (ImageView) itemView.findViewById(R.id.iv_from_image);
            from_person_avator = (ImageView) itemView.findViewById(R.id.from_person_avator);
        }
    }
}
