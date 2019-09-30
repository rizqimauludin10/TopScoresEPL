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

import com.example.topscoresepl.R;
import com.example.topscoresepl.model.PictPlayer;
import com.example.topscoresepl.model.Scorer;
import com.example.topscoresepl.model.Season;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Callback;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoresHolder> {
    List<Scorer> allscores;
    Season seasons;
    Context context;
    Integer nomor, id, image;
    private ArrayList<PictPlayer> pictPlayers;
    HashMap<Integer, String> foto =  new HashMap<Integer, String>();

    public ScoresAdapter(Context context, List<Scorer> scoreslist) {
        this.context = context;
        this.allscores = scoreslist;
    }

    public HashMap<Integer, String> getFoto() {
        foto.put(7891, "https://i.imgur.com/eNZpurw.png");
        foto.put(7985, "https://i.imgur.com/pA1zFrE.png");
        foto.put(7801, "https://i.imgur.com/1iTySnu.png");
        foto.put(24121, "https://i.imgur.com/kVHOw4L.png");
        foto.put(3329, "https://i.imgur.com/68uBIiL.png");
        foto.put(3754, "https://i.imgur.com/HHNsxZm.png");
        foto.put(3626, "https://i.imgur.com/0dr3QtV.png");
        foto.put(8054, "https://i.imgur.com/FfJOEQS.png");
        foto.put(3331, "https://i.imgur.com/IRI2UgH.png");
        foto.put(8004, "https://i.imgur.com/KYnnQ4G.png");
        foto.put(3254, "https://i.imgur.com/SPdzrlR.png");
        foto.put(3196, "https://i.imgur.com/ptJYlW3.png");
        foto.put(3236, "https://i.imgur.com/7lKOyDg.png");
        foto.put(3184, "https://i.imgur.com/zBDshSm.png");
        foto.put(7599, "https://i.imgur.com/O53e0w1.png");
        foto.put(3371, "https://i.imgur.com/EqW5x6g.png");
        foto.put(3233, "https://i.imgur.com/QcFyhO8.png");
        foto.put(7974, "https://i.imgur.com/YzrzwsI.png");
        foto.put(3366, "https://i.imgur.com/1BEmfIt.png");
        foto.put(3372, "https://i.imgur.com/RybEX5I.png");
        foto.put(3324, "https://i.imgur.com/nKkgxWr.png");
        foto.put(3343, "https://i.imgur.com/TO1xfzF.png");
        foto.put(8003, "https://i.imgur.com/GgBTFtJ.png");
        foto.put(3330, "https://i.imgur.com/ZgtnjwA.png");
        foto.put(8251, "https://i.imgur.com/AGmPnbU.png");

        return foto;
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

       // final Season season = sea

        String item = (String.valueOf(holder.getAdapterPosition() + 1));

        /*holder.tvSeasonOne.setText(season.getStartDate());
        holder.tvSeasonTwo.setText(season.getEndDate());*/
        //holder.tvMatchday.setText(Integer.toString(seasons.getCurrentMatchday()));

        holder.tvNamaPemain.setText(allitemscorers.getPlayer().getName());
        holder.tvClub.setText(allitemscorers.getTeam().getName());
        holder.tvGoals.setText(Integer.toString(allitemscorers.getNumberOfGoals()));
        holder.tvNegara.setText(allitemscorers.getPlayer().getNationality());
        holder.tvPosition.setText(item);

        Log.d("Id Pemain", String.valueOf(holder.getAdapterPosition() + 1));
        nomor = (allitemscorers.getPlayer().getId());

        for (Map.Entry map : getFoto().entrySet()) {
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

        public ScoresHolder(@NonNull View itemView) {
            super(itemView);
            tvPosition = itemView.findViewById(R.id.position);
            tvNamaPemain = itemView.findViewById(R.id.namapemain);
            tvClub = itemView.findViewById(R.id.club);
            tvGoals = itemView.findViewById(R.id.goals);
            tvNegara = itemView.findViewById(R.id.negara);
            ivNomor = itemView.findViewById(R.id.fotopemain);
            tvSeasonOne = itemView.findViewById(R.id.tvSeasonOne);
            tvSeasonTwo = itemView.findViewById(R.id.tvSeasonTwo);
            tvMatchday = itemView.findViewById(R.id.tvMatchday);


        }
    }

}
