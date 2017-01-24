package com.ce.android.retrofitdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Chaker on 1/24/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
    private List<ResultAPI> resultAPIsList;
    private RecyclerViewHolder recyclerViewHolder;
    private LayoutInflater inflater;
    private Context mContext;

    public RecyclerAdapter(Context context, List<ResultAPI> result){

        this.resultAPIsList = result;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        recyclerViewHolder = new RecyclerViewHolder(itemView);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ResultAPI resultAPI = resultAPIsList.get(position);
        //     holder.id.setText(String.valueOf(mRecipe.getId()));
        holder.username.setText(resultAPI.getName());
        holder.urlString.setText(resultAPI.getContentsUrl());
        holder.mCardView.setTag(position);
        holder.setData(resultAPI, position);
    }

    @Override
    public int getItemCount() {
        return resultAPIsList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView username;
        TextView urlString;
        LinearLayout rv;
        int position;
        public View mCardView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.user_name);
            urlString = (TextView) itemView.findViewById(R.id.url_txt);
            mCardView = (CardView) itemView.findViewById(R.id.card_v);
         //   rv = (LinearLayout) itemView.findViewById(R.id.recycler_view);
            mCardView.setOnClickListener(this);
        }

        public void setData(ResultAPI current, int position) {

            this.username.setText(current.getName());
            this.urlString.setText(current.getCloneUrl());

            this.position = position;
            //   this.current = current;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.card_v:
                    int pos = (int) v.getTag();
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("userInfo", resultAPIsList.get(pos));
                    mContext.startActivity(intent);
            }
        }
    }
}
