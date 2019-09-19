package com.example.topscoresepl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topscoresepl.R;
import com.example.topscoresepl.model.Player;
import com.example.topscoresepl.model.Scorer;
import com.example.topscoresepl.model.Value;

import java.util.List;

import retrofit2.Callback;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoresHolder> {
    List<Scorer> allscores;
    Context context;
    String nomor;

    public ScoresAdapter(Context context, List<Scorer> scoreslist) {
        this.context = context;
        this.allscores = scoreslist;
    }

    @NonNull
    @Override
    public ScoresAdapter.ScoresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topscorerslist, parent, false);
        final ScoresHolder scoresHolder = new ScoresHolder(view);
        return scoresHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoresAdapter.ScoresHolder holder, int position) {
        final Scorer allitemscorers = allscores.get(position);
        holder.tvNamaPemain.setText(allitemscorers.getPlayer().getName());
        holder.tvClub.setText(allitemscorers.getTeam().getName());
        holder.tvGoals.setText(Integer.toString(allitemscorers.getNumberOfGoals()));

        //holder.tvGoals.setText(allitemscorers.getPlayer().getNationality());


    }

    @Override
    public int getItemCount() {
        return allscores.size();
    }

    public class ScoresHolder extends RecyclerView.ViewHolder {

        public TextView tvNamaPemain;
        public TextView tvClub;
        public TextView tvGoals;
        public ImageView ivNomor;

        public ScoresHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaPemain = itemView.findViewById(R.id.namapemain);
            tvClub = itemView.findViewById(R.id.club);
            tvGoals = itemView.findViewById(R.id.goals);
            ivNomor = itemView.findViewById(R.id.fotopemain);

        }
    }

}
