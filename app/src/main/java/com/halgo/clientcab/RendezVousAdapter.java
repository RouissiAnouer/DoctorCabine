package com.halgo.clientcab;

/**
 * Created by Halgo on 28/11/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RendezVousAdapter extends RecyclerView.Adapter<RendezVousAdapter.MyViewHolder> {

    private List<Example> RdvList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView etat, date, heure;

        public MyViewHolder(View view) {
            super(view);
            etat= (TextView) view.findViewById(R.id.etat);
            date = (TextView) view.findViewById(R.id.date);
            heure = (TextView) view.findViewById(R.id.heure);
        }
    }


    public RendezVousAdapter(List<Example> RdvList) {
        this.RdvList = RdvList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Example Rdv = RdvList.get(position);
        holder.etat.setText(Rdv.getEtat());
        holder.date.setText(Rdv.getDate());
        holder.heure.setText(Rdv.getHeure());
    }

    @Override
    public int getItemCount() {
        return RdvList.size();
    }
}
