package edu.qc.seclass.fim;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


public class FloorAdapter extends RecyclerView.Adapter<FloorAdapter.MyViewHolder>{

    public Context context;
    public ArrayList<Floor> listOfFloors;
    //used to determine which layout to use
    public String parentName;

    public FloorAdapter(Context context, ArrayList<Floor> listOfFloors){
        this.context =context;
        this.listOfFloors = listOfFloors;
        this.parentName="customer";
    }

    //new constructor to handle employee activities
    public FloorAdapter(Context context,ArrayList<Floor> listOfFloors,String pName){
        this.context =context;
        this.listOfFloors = listOfFloors;
        this.parentName=pName;
    }

    @NonNull
    @Override
    public FloorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_floor,parent,false);
        return new FloorAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FloorAdapter.MyViewHolder holder, int position) {
        Floor floor = listOfFloors.get(position);
        holder.bind(floor);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Floor floorClicked = listOfFloors.get(holder.getAdapterPosition());
                //create the right intent depending on customer or employee calling
                if (parentName == "customer") {
                    Intent intent = new Intent(view.getContext(), FloorDetailActivity.class);
                    intent.putExtra("FLOOR_EXTRA", (Serializable) floorClicked);
                    view.getContext().startActivity(intent);
                    //if the item is clicked from the employee view
                }else if(parentName=="employee"){
                    Intent intent = new Intent(view.getContext(), FloorDetailEmployeeActivity.class);
                    intent.putExtra("FLOOR_EXTRA", (Serializable) floorClicked);
                    view.getContext().startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfFloors.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv_category, tv_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.imageView);
            tv_category = itemView.findViewById(R.id.tv_category);
            tv_name = itemView.findViewById((R.id.tv_name));

        }
        public void bind(Floor floor){
            tv_category.setText(floor.getCategory());
            tv_name.setText(floor.getName());

            Glide.with(itemView.getContext()).load(floor.getImage().getUrl()).into(iv);

        }

    }

    public void clear(){
        listOfFloors.clear();
        notifyDataSetChanged();
    }

}
