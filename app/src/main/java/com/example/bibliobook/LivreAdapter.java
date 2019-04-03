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

        //Log.d("TESTITO","ok" + mDataset.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);

        //Log.d("TESTITO","onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.titre.setText(mDataset.get(i).getTitre());
        viewHolder.auteur.setText(mDataset.get(i).getAuteur());
        viewHolder.genre.setText(mDataset.get(i).getGenre());
        viewHolder.annee.setText(mDataset.get(i).getAnnee());
        Picasso.get().load(mDataset.get(i).getUrl()).into(viewHolder.miniature);
        //Log.d("TESTITO","onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        //Log.d("TESTITO","getItemCount" + mDataset.size());
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titre;
        private TextView auteur;
        private TextView genre;
        private TextView annee;
        private ImageView miniature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("TESTITO","ViewHolder");
            titre = (TextView)itemView.findViewById(R.id.title);
            auteur = (TextView) itemView.findViewById(R.id.auteur);
            genre = (TextView) itemView.findViewById(R.id.genre);
            annee = (TextView) itemView.findViewById(R.id.annee);
            miniature = (ImageView) itemView.findViewById(R.id.couverture);

        }
    }
}
