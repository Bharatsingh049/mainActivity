package com.nickelfox.mvp_test.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nickelfox.mvp_test.R;
import com.nickelfox.mvp_test.data.model.Article;

import java.util.List;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.ListViewHolder>{

    private List<Article> list;
    private Context MyContext;

    HorizontalRecyclerViewAdapter(List<Article> list){
        this.list=list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyContext=viewGroup.getContext();
        LayoutInflater inflater=LayoutInflater.from(MyContext);
        View view=inflater.inflate(R.layout.horizontalcardlayout,viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, final int i) {
        Glide.with(MyContext)
                .load(list.get(i).getUrlToImage())
        .crossFade()
        .into(listViewHolder.Image);
        listViewHolder.Title.setText(list.get(i).getTitle());
        listViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent=new Intent(MyContext,Separate_News_Item.class);
                intent.putExtra("data",(Serializable) list.get(i));
                MyContext.startActivity(intent);*/
            }
        });
    }

    public  void setList(List<Article> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if(list.size()<7){
            return list.size();
        }else {
            return 7;
        }
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
     TextView Title;
     ImageView Image;
    public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView=itemView.findViewById(R.id.News_card);
        Title=itemView.findViewById(R.id.News_Title);
        Image=itemView.findViewById(R.id.News_Image);
    }
}
}
