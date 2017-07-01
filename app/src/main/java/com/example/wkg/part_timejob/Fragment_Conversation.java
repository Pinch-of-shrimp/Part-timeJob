package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
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
 * Created by Administrator on 2017/6/26.
 */

public class Fragment_Conversation extends Fragment {
    private RecyclerView rv_conversation;
    private Toolbar tb_conversation;
    private AlertDialog dia_addFriend;
    private AlertDialog dia_conversation;
    private ArrayList<Conversation_Data>datas;
    private ArrayList<Message>data_show_rv;
    private EditText et_addfriend;
    private Button btn_addfriend;
    private  conversation_rv_adapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_conversation,container,false);
        rv_conversation= (RecyclerView) view.findViewById(R.id.rv_conversation);
        tb_conversation= (Toolbar) view.findViewById(R.id.tb_conversation);
        setHasOptionsMenu(true);
        datas=new ArrayList<>();
        data_show_rv=new ArrayList<Message>();
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_conversation);
        adapter=new conversation_rv_adapter(getContext());
        adapter.setArrayList(datas);
        show_rv_adpter=new conversationShow_rv_adpter(data_show_rv,getActivity(),null);
        JMessageClient.registerEventReceiver(this);
        //ContactManager.acceptInvitation();
        overseeTheFriend();
        rv_conversation.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_conversation.setAdapter(adapter);
        ContactManager.getFriendList(new GetUserInfoListCallback() {
            @Override
            public void gotResult(int i, String s, List<UserInfo> list) {
                if(i==0)
                {
                    for (int j=0;i<list.size();i++) {
                        Conversation_Data data = new Conversation_Data();
                        data.setInfo(list.get(j));
                        data.setTheName(list.get(j).getUserName());
                        adapter.addData(data);
                    }
                }
            }
        });
        adapter.setItemClickListener(new conversation_rv_adapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(getContext(),"点击了:"+position,Toast.LENGTH_SHORT).show();
                showConversationdia(datas.get(position).getInfo());
            }
        });
        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        JMessageClient.unRegisterEventReceiver(this);
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
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_conversation_toolbar_menu,menu);
    }

    public void showAddFriend()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_addfriend,null);
        et_addfriend= (EditText) view.findViewById(R.id.et_friendEmail);
        btn_addfriend= (Button) view.findViewById(R.id.btn_addFriend);
        btn_addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactManager.sendInvitationRequest(et_addfriend.getText().toString(), null, "hello", new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if(i==0)
                            Toast.makeText(getContext(),"成功发送请求",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getContext(),"发送失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        builder.setView(view);
        builder.setTitle("添加好友");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dia_addFriend=builder.create();
        dia_addFriend.show();
    }
    public void overseeTheFriend()
    {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mn_add:
                showAddFriend();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //dialog component
    private ImageButton ibtn_sendemoji;
    private ImageButton ibtn_sendInformation;
    private EditText et_themessage;
    private RecyclerView rv_holdmessage;
    private conversationShow_rv_adpter show_rv_adpter;
    private Conversation conversation;
    public void showConversationdia(final UserInfo info)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_conversation,null);
        ibtn_sendemoji= (ImageButton) view.findViewById(R.id.ibtn_sendemoji_dialog);
        ibtn_sendInformation= (ImageButton) view.findViewById(R.id.ibtn_sendMessage);
        et_themessage= (EditText) view.findViewById(R.id.et_conversation_dialog);
        rv_holdmessage= (RecyclerView) view.findViewById(R.id.rv_conversation_dialog);
        rv_holdmessage.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_holdmessage.setAdapter(show_rv_adpter);
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

        builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                JMessageClient.deleteSingleConversation(info.getUserName());

            }
        });
        builder.setView(view);
        dia_conversation=builder.create();
        dia_conversation.show();

    }
}
