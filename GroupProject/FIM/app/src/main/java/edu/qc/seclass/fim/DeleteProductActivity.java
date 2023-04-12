package edu.qc.seclass.fim;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

//need to pass in a Floor object into this activity via intents
public class DeleteProductActivity extends AppCompatActivity {
    public Floor passedInFloor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        //get passed in data
        Intent intent = getIntent();
        passedInFloor = (Floor) intent.getSerializableExtra("FLOOR_DELETE");
        String deleteName,deleteCategory,deleteType,deleteSpecies,deleteSize,deleteStore,deleteColor,deletePrice,deleteBrand;
        deleteName = passedInFloor.getName();
        deleteCategory = passedInFloor.getCategory();
        deleteType = passedInFloor.getType();
        deleteSpecies = passedInFloor.getSpecies();
        deleteSize = passedInFloor.getSize();
        deleteStore = passedInFloor.getStore();
        deleteColor = passedInFloor.getColor();
        deletePrice = passedInFloor.getPrice();
        deleteBrand = passedInFloor.getBrand();

        //creating UI objects
        Button cancelB = findViewById(R.id.cancelDeleteButton);
        Button confirmB = findViewById(R.id.confirmDeleteButton);

        cancelB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               finish();
            }
        });
        //creating listeners
        confirmB.setOnClickListener(new View.OnClickListener() {
            //queries DB
            //checks for data that matches all values
            //deletes it and creates a toast
            public void onClick(View view) {
                //creating query
                ParseQuery<Floor> queryName = ParseQuery.getQuery("Floor");
                queryName.whereEqualTo("name",deleteName);
                queryName.whereEqualTo("store",deleteStore);
                queryName.whereEqualTo("category",deleteCategory);
                queryName.whereEqualTo("type",deleteType);
                queryName.whereEqualTo("Species",deleteSpecies);
                queryName.whereEqualTo("price",deletePrice);
                queryName.whereEqualTo("brand",deleteBrand);
                queryName.findInBackground(new FindCallback<Floor>() {
                    @Override
                    public void done(List<Floor> objects, ParseException e) {
                        if(e==null){
                            objects.get(0).deleteInBackground(new DeleteCallback() {
                                @Override
                                public void done(ParseException e) {
                                    Log.i("delete: ",e.getMessage());
                                    //GOES BACK
                                    if(e==null){
                                        Toast.makeText(view.getContext(), "Product "+deleteName+" deleted!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                        Toast.makeText(view.getContext(), "Could not delete "+deleteName, Toast.LENGTH_SHORT).show();
                                        finish();
                                    }

                                }
                            });
                        }
                        else {
                            // if expected data isnt in the database
                            Toast.makeText(DeleteProductActivity.this, "Fail to get the object..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}
