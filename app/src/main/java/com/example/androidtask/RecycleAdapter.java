package com.example.androidtask;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    List<NotesDetails> detail;
    Context context;
    static String title;
    static String image;
    static String description;

    public RecycleAdapter(List<NotesDetails> detail, Context context) {
        this.detail = detail;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_str,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(detail.get(position).getTitle());
        holder.decription.setText(detail.get(position).getDescription());
        Picasso.with(context)
                .load(detail.get(position).getImageurl())
                .into(holder.img);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                final  NotesDetails data= detail.get(position);
                title=data.getTitle();
                image=data.getImageurl();
                description=data.getDescription();
                Intent intent=new Intent(context,DetailsScreen.class);
                intent.putExtra("title",title);
                intent.putExtra("descri",description);
                intent.putExtra("imgurl",image);
                context.startActivity(intent);




            }
        });


    }

    @Override
    public int getItemCount() {
        return detail.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        CardView card;
        TextView title,decription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageView);
            card=(CardView) itemView.findViewById(R.id.cardView);
            title=(TextView)itemView.findViewById(R.id.title_text);
            decription=(TextView)itemView.findViewById(R.id.decri_text);
        }
    }
}
