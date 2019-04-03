package com.example.bibliobook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LivreAdapter extends RecyclerView.Adapter<LivreAdapter.ViewHolder> {

    private ArrayList<Livre> mDataset;

    public LivreAdapter(ArrayList<Livre> mDataset) {
        this.mDataset = mDataset;

        Log.d("TESTITO","ok" + mDataset.size());
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        Log.d("TESTITO","onCreateViewHolder" + viewGroup.toString());
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.titre.setText(mDataset.get(i).getTitre());
        viewHolder.auteur.setText(mDataset.get(i).getAuteur());
        viewHolder.genre.setText(mDataset.get(i).getGenre());
        viewHolder.annee.setText(mDataset.get(i).getAnnee());
        Picasso.get().load(mDataset.get(i).getUrl()).into(viewHolder.miniature);
        Log.d("TESTITO","onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        Log.d("TESTITO","getItemCount" + mDataset.size());
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titre = null;
        private TextView auteur = null;
        private TextView genre = null;
        private TextView annee = null;
        private ImageView miniature = null;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (titre == null){
                Log.d("TESTITO","TITRE EST NUL AU DEBUT");
            }
            this.titre = itemView.findViewById(R.id.title);
            this.auteur = itemView.findViewById(R.id.auteur);
            this.genre = itemView.findViewById(R.id.genre);
            this.annee = itemView.findViewById(R.id.annee);
            this.miniature = itemView.findViewById(R.id.couverture);

            if (titre == null){
                Log.d("TESTITO","TITRE EST NUL A LA FIN");
            }

        }
    }
}
