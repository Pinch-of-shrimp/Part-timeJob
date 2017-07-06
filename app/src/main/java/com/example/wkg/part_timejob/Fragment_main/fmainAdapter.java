package com.example.wkg.part_timejob.Fragment_main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wkg.part_timejob.Job;
import com.example.wkg.part_timejob.R;
import com.example.wkg.part_timejob.conversation_rv_adapter;

import java.util.List;

/**
 * Created by WKG on 2017/7/2.
 */

public class fmainAdapter extends RecyclerView.Adapter<fmainAdapter.ViewHolder> {
    private List<fmain> fmainList;
    MyItemClickListener mItemClickListener;
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /*
        需要用到的变量名
         */
        View fmainView;//view 名字
        TextView jobname;
        TextView jobplace;
        TextView jobtime;
        TextView jobsalary;
        TextView jobgettype;
        private MyItemClickListener myItemClickListener;
        public ViewHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            fmainView=itemView;
            jobname=(TextView)itemView.findViewById(R.id.jobname);
            jobplace=(TextView)itemView.findViewById(R.id.jobplace);
            jobtime=(TextView)itemView.findViewById(R.id.jobtime);
            jobsalary=(TextView)itemView.findViewById(R.id.jobsalary);
            jobgettype=(TextView)itemView.findViewById(R.id.jobgettype);
            myItemClickListener=listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(myItemClickListener!=null)
            {
                myItemClickListener.onItemClick(v,getPosition());
            }
        }
    }
    public interface MyItemClickListener {
        public void onItemClick(View view,int postion);
    }
    public fmainAdapter(List<fmain> List)
    {
        fmainList=List;//得到重写链表的内容
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mainpage_jobitem,parent
                ,false);
        final ViewHolder holder=new ViewHolder(view,mItemClickListener);
        /*
        Create by wkg
        7.1
         */
        //添加点击事件
         /*
        holder.fmainView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int posotion=holder.getAdapterPosition();
                fmain fmain_item=fmainList.get(posotion);
                Toast.makeText(v.getContext(),"你点击了这里"+fmain_item.GetJobname(),Toast.LENGTH_SHORT).show();
            }
        });*/
        return holder;
    }

    public void setmItemClickListener(MyItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        fmain main_item=fmainList.get(position);//得到所在 位置
        holder.jobname.setText(main_item.GetJobname());
        holder.jobplace.setText(main_item.GetJobplace());
        holder.jobtime.setText(main_item.GetJobtime());
        holder.jobsalary.setText(main_item.GetJobsalry());
        holder.jobgettype.setText(main_item.GetJobgettye());
    }

    @Override
    public int getItemCount() {
        return fmainList.size();
    }
    public void addData(Job job)
    {
        fmain data;
        if(job.getStartdate()!=null) {
            data = new fmain(job.getJob(), job.getCity(), job.getStartdate() + "~" + job.getEnddate(), job.getSalary() + "元", job.getSalarytype());
        }
        else
        {
            data = new fmain(job.getTitle(), job.getCity(), job.getPlace(), job.getSalary() + "元", job.getType());
        }
        fmainList.add(data);
        notifyItemInserted(fmainList.size());
    }


}
