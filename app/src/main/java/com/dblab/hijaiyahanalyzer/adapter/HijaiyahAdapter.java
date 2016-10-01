package com.dblab.hijaiyahanalyzer.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dblab.hijaiyahanalyzer.R;
import com.dblab.hijaiyahanalyzer.model.Hijaiyah;

import java.util.ArrayList;

/**
 * Created by dblab on 01/09/16.
 */
public class HijaiyahAdapter extends RecyclerView.Adapter<HijaiyahAdapter.ViewHolder>{

    private ArrayList<Hijaiyah> hijaiyahList;
    private OnItemClickListener clickListener;
    private Context context;

    public HijaiyahAdapter(Context context, ArrayList<Hijaiyah> hijaiyahList) {
        this.context = context;
        this.hijaiyahList = hijaiyahList;
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_huruf, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HijaiyahAdapter.ViewHolder holder, int position) {
        holder.imgHuruf.setImageDrawable(ContextCompat.getDrawable(context, hijaiyahList.get(position).getDrawableId()));
        holder.bind(hijaiyahList.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return hijaiyahList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgHuruf;

        public ViewHolder(final View itemView) {
            super(itemView);
            imgHuruf = (ImageView) itemView.findViewById(R.id.img_huruf);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = getAdapterPosition();
//                    Toast.makeText(itemView.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
//                }
//            });
        }

        public void bind(final Hijaiyah item, final OnItemClickListener clickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Hijaiyah item);
    }
}
