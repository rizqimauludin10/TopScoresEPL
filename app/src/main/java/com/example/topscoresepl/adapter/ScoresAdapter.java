package com.example.topscoresepl.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.topscoresepl.R;
import com.example.topscoresepl.model.ClubLogo;
import com.example.topscoresepl.model.PictPlayer;
import com.example.topscoresepl.model.Scorer;
import com.example.topscoresepl.model.Season;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoresHolder> {
    List<Scorer> allscores;
    private Context context;
    Integer nomor, idClub;

    PictPlayer pictPlayer = new PictPlayer();
    HashMap<Integer, String> foto =  pictPlayer.getFoto();

    ClubLogo clubLogo = new ClubLogo();
    HashMap<Integer, String> club =  clubLogo.getClubLogo();

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

        String item = (String.valueOf(holder.getAdapterPosition() + 1));

        holder.tvNamaPemain.setText(allitemscorers.getPlayer().getName());
        //holder.tvClub.setText(allitemscorers.getTeam().getName());
        holder.tvGoals.setText(Integer.toString(allitemscorers.getNumberOfGoals()));
        //holder.tvNegara.setText(allitemscorers.getPlayer().getNationality());
        holder.tvPosition.setText(item);

        Log.d("Id Pemain", String.valueOf(holder.getAdapterPosition() + 1));
        nomor = (allitemscorers.getPlayer().getId());
        idClub = (allitemscorers.getTeam().getId());

        for (Map.Entry map : foto.entrySet()) {
            if (map.getKey().equals(nomor)) {
                Log.d("Id Pemain", map.getKey() + "+" +nomor);
                Picasso.get().load((String) map.getValue())
                        .into(holder.ivNomor);
                break;
            } else {
                Picasso.get().load(R.drawable.photomissing)
                        .into(holder.ivNomor);
            }
        }

        for (Map.Entry mapc : club.entrySet()) {
            if (mapc.getKey().equals(idClub)) {
                 Picasso.get().load((String) mapc.getValue())
                        .into(holder.ivClubLogo);
                break;
            }
        }


    }

    @Override
    public int getItemCount() {
        return allscores.size();
    }

    public class ScoresHolder extends RecyclerView.ViewHolder {

        public TextView tvNamaPemain;
        public TextView tvClub;
        public TextView tvGoals;
        public TextView tvNegara;
        public TextView tvPosition;
        public TextView tvSeasonOne, tvSeasonTwo, tvMatchday;
        public ImageView ivNomor;
        public ImageView ivClubLogo;

        public ScoresHolder(@NonNull View itemView) {
            super(itemView);
            tvPosition = itemView.findViewById(R.id.position);
            tvNamaPemain = itemView.findViewById(R.id.namapemain);
            //tvClub = itemView.findViewById(R.id.club);
            tvGoals = itemView.findViewById(R.id.goals);
            //tvNegara = itemView.findViewById(R.id.negara);
            ivNomor = itemView.findViewById(R.id.fotopemain);
            tvSeasonOne = itemView.findViewById(R.id.tvSeasonOne);
            tvSeasonTwo = itemView.findViewById(R.id.tvSeasonTwo);
            tvMatchday = itemView.findViewById(R.id.tvMatchday);
            ivClubLogo = itemView.findViewById(R.id.clubLogo);

        }
    }

}
