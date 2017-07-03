package com.example.wkg.part_timejob;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.ContactNotifyEvent;
import cn.jpush.im.android.api.event.MessageBaseEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by Administrator on 2017/7/3.
 */

public class Fragment_new_comunicate  extends Fragment{
    private ImageButton ibtn_sendemoji;
    private ImageButton ibtn_sendInformation;
    private EditText et_themessage;
    private RecyclerView rv_holdmessage;
    private conversationShow_rv_adpter show_rv_adpter;
    private Conversation conversation;
    private UserInfo info;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.dialog_conversation,container,false);
        ibtn_sendemoji= (ImageButton) view.findViewById(R.id.ibtn_sendemoji_dialog);
        ibtn_sendInformation= (ImageButton) view.findViewById(R.id.ibtn_sendMessage);
        et_themessage= (EditText) view.findViewById(R.id.et_conversation_dialog);
        rv_holdmessage= (RecyclerView) view.findViewById(R.id.rv_conversation_dialog);
        rv_holdmessage.setLayoutManager(new LinearLayoutManager(getContext()));
        show_rv_adpter=new conversationShow_rv_adpter(new ArrayList<Message>(),getActivity(),"",getContext());
        rv_holdmessage.setAdapter(show_rv_adpter);
        show_rv_adpter.notifyDataSetChanged();
        rv_holdmessage.smoothScrollToPosition(show_rv_adpter.getItemCount());
        info=((application)getActivity().getApplication()).getInfo();
        JMessageClient.registerEventReceiver(this);
        conversation= Conversation.createSingleConversation(info.getUserName(),null);
        ibtn_sendInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"it is work",Toast.LENGTH_SHORT).show();
                final Message message=conversation.createSendMessage(new TextContent(et_themessage.getText().toString()));
                MessageSendingOptions options=new MessageSendingOptions();
                options.setRetainOffline(false);
                message.setOnSendCompleteCallback(new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if(i==0)
                        {
                            show_rv_adpter.addData(message);
                        }
                    }
                });
                JMessageClient.sendMessage(message);

            }
        });
        return view;
    }
    public void onEvent(ContactNotifyEvent event)
    {
        String reason=event.getReason();
        String fromuser=event.getFromUsername();
        String appkey=event.getfromUserAppKey();
        switch (event.getType())
        {
            case invite_accepted:
                ContactManager.acceptInvitation(fromuser, null, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;
        }
    }
    public void onEvent(MessageBaseEvent event)
    {
        Message msg=event.getMessage();
        switch (msg.getContentType())
        {
            case text:
                show_rv_adpter.addData(msg);
                //文字信息更新到adpater中
                break;
            case image:
                //图片信息
                ImageContent imageContent = (ImageContent) msg.getContent();
                imageContent.getLocalPath();//图片本地地址
                imageContent.getLocalThumbnailPath();//图片对应缩略图的本地地址
                break;
            default:
                break;
        }
    }
}
