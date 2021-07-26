package com.example.kelas5_belajar_api.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.kelas5_belajar_api.R;
import com.example.kelas5_belajar_api.model.ModelPost;

import java.util.ArrayList;
import java.util.List;

public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.HolderData> {

    private List<ModelPost> modelposts ;
    Context context;

    public ApiAdapter(Context context, List<ModelPost> modelposts){
        this.context = context;
        this.modelposts = modelposts;

    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_api_client,parent,false);
        HolderData holderData = new HolderData(view);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        holder.tvID.setText(modelposts.get(position).getId().toString());
        holder.tvTitle.setText(modelposts.get(position).getTitle());
    }

    @Override
    public int getItemCount(){return modelposts.size(); }

    public class  HolderData extends RecyclerView.ViewHolder{
        TextView tvID, tvTitle;
        public HolderData(View itemView){
            super(itemView);
            tvID = itemView.findViewById(R.id.tv_resultID);
            tvTitle = itemView.findViewById(R.id.tv_resultTitle);
        }
    }
}
