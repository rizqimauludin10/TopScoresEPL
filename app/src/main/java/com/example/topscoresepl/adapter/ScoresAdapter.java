package com.example.topscoresepl.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topscoresepl.DetailActivity;
import com.example.topscoresepl.R;
import com.example.topscoresepl.model.ClubLogo;
import com.example.topscoresepl.model.PictPlayer;
import com.example.topscoresepl.model.Scorer;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoresHolder> {
    List<Scorer> allscores;
    private Context context;
    Integer nomor, idClub;
    Integer idPL;
    String UrlPictPlayers,clubName;

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
        holder.tvClub.setText(allitemscorers.getTeam().getName());
        holder.tvGoals.setText(Integer.toString(allitemscorers.getNumberOfGoals()));
        //holder.tvNegara.setText(allitemscorers.getPlayer().getNationality());
        holder.tvPosition.setText(item);

        Log.d("Id Pemain", String.valueOf(holder.getAdapterPosition() + 1));
        nomor = (allitemscorers.getPlayer().getId());
        idClub = (allitemscorers.getTeam().getId());


        for (Map.Entry map : foto.entrySet()) {
            if (map.getKey().equals(nomor)) {
                Log.d("Id Pemain", map.getKey() + "+" + nomor + map.getValue());
                Picasso.get().load((String) map.getValue())
                        .into(holder.ivNomor);
                break;
            } else {
                Picasso.get().load(R.drawable.photomissing)
                        .into(holder.ivNomor);
            }
        }

        /*for (Map.Entry mapc : club.entrySet()) {
            if (mapc.getKey().equals(idClub)) {
                 Picasso.get().load((String) mapc.getValue())
                        .into(holder.ivClubLogo);
                break;
            }
        }*/

        holder.itemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                idPL = (allitemscorers.getPlayer().getId());
                clubName = (allitemscorers.getTeam().getName());
                intent.putExtra("idPlayers", idPL);
                intent.putExtra("clubName", clubName);


                for (Map.Entry map : foto.entrySet()) {
                    if (map.getKey().equals(idPL)) {
                        Log.d("Id Players Detail =>", map.getKey() + "+" + map.getValue());
                        intent.putExtra("urlPict", (String) map.getValue());
                        break;
                    } else {

                    }
                }

                view.getContext().startActivity(intent);
                System.out.println("Id Players Detail " + idPL);
            }
        });
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
        public LinearLayout itemClick;

        public ScoresHolder(@NonNull View itemView) {
            super(itemView);
            tvPosition = itemView.findViewById(R.id.position);
            tvNamaPemain = itemView.findViewById(R.id.namapemain);
            tvClub = itemView.findViewById(R.id.club);
            tvGoals = itemView.findViewById(R.id.goals);
            //tvNegara = itemView.findViewById(R.id.negara);
            ivNomor = itemView.findViewById(R.id.fotopemain);
            tvSeasonOne = itemView.findViewById(R.id.tvSeasonOne);
            tvSeasonTwo = itemView.findViewById(R.id.tvSeasonTwo);
            tvMatchday = itemView.findViewById(R.id.tvMatchday);
            //ivClubLogo = itemView.findViewById(R.id.clubLogo);
            itemClick = itemView.findViewById(R.id.myLinear);

        }
    }

}
