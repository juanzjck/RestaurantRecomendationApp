package com.example.restaurantapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyLocationAdapter extends RecyclerView.Adapter<MyLocationAdapter.ViewHolder> {

    private List<MyFavoriteLocations> listLocation;
    public void setListener(LocationListener listener) {

        this.listener = listener;
    }
    protected View.OnClickListener onClickListener;

    public MyLocationAdapter(List<MyFavoriteLocations> listLocation) {
        this.listLocation = listLocation;
    }

    private LocationListener listener;

    public interface LocationListener {
        void onLocationListener(int position);

        void onLocationDelete(int position);
    }

    public void setListaContactos(List<MyFavoriteLocations> listaContactos) {
        this.listLocation = listaContactos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout root;
        private ImageView imageViewRowC;
        private TextView textViewRowCTtitle;
        private TextView textViewRowCAdress;
        private TextView textViewRowCBuget;
        private Button deleteButton;


        public ViewHolder(View v) {
            super(v);
            root = (LinearLayout) v.findViewById(R.id.contactoRoot);

            textViewRowCTtitle = (TextView) v.findViewById(R.id.title);
            textViewRowCAdress = (TextView) v.findViewById(R.id.address);
            textViewRowCBuget = (TextView) v.findViewById(R.id.budget);
            deleteButton = (Button) v.findViewById(R.id.delete);


        }
    }


    @Override
    public void onBindViewHolder(@NonNull MyLocationAdapter.ViewHolder holder, int position) {
        listLocation.get(position);
        String title = listLocation.get(position).getMyFavoriteLocations().getTitle();
        String address = listLocation.get(position).getMyFavoriteLocations().getAddress();
        String budget ="$"+listLocation.get(position).getMyFavoriteLocations().getBudget();

        holder.textViewRowCTtitle.setText(title);
        holder.textViewRowCAdress.setText(address);
        holder.textViewRowCBuget.setText(budget);


        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLocationListener(position);
                }
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLocationDelete(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    @Override
    public int getItemCount() {
        return listLocation.size();
    }
}
