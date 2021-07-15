package com.example.restaurantapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ViewHolder> {

    private List<Contacto> listaContactos;

    protected View.OnClickListener onClickListener;

    public ContactosAdapter(List<Contacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row, parent, false);
        v.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String image  = listaContactos.get(position).getImage();
        String name = listaContactos.get(position).getName();
        String phone = listaContactos.get(position).getPhone();
        String address = listaContactos.get(position).getAddress();

        holder.imageViewRowPicture.setImageBitmap(BitmapFactory.decodeFile(image));
        holder.textViewRowName.setText(name);
        holder.textViewRowPhone.setText(phone);
        holder.txtViewRCAddress.setText(address);

        holder.imageViewContactosDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Contacto contacto = listaContactos.get(position);
                listaContactos.remove(contacto);
                //contacto.delete();
                notifyDataSetChanged();
            }
        });

        holder.imageViewRowMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Contacto contacto = listaContactos.get(position);
                Bundle bundle= new Bundle();
                bundle.putSerializable("contacto",contacto);
                Intent intent = new Intent(view.getContext(),MapsActivity.class) ;
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewRowPicture;
        private TextView textViewRowName;
        private TextView textViewRowPhone;
        private TextView txtViewRCAddress;
        private ImageView imageViewContactosDelete;
        private ImageView imageViewRowMap;

        public ViewHolder(View v) {
            super(v);
            imageViewRowPicture = (ImageView) v.findViewById(R.id.imgViewContacto);
            textViewRowName = (TextView) v.findViewById(R.id.txtViewRCName);
            textViewRowPhone = (TextView) v.findViewById(R.id.txtViewRCPhone);
            txtViewRCAddress = (TextView) v.findViewById(R.id.txtViewRCAddress);
            imageViewContactosDelete=(ImageView) v.findViewById(R.id.imageViewEliminarContacto);
            imageViewRowMap        = (ImageView) v.findViewById(R.id.imageViewRowMap);

        }
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
