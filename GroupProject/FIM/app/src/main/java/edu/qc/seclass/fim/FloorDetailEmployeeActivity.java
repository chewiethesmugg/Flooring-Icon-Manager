package edu.qc.seclass.fim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.Serializable;

public class FloorDetailEmployeeActivity extends AppCompatActivity implements Serializable{
    public Floor passedInFloor;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //super.onStart();
        setContentView(R.layout.activity_floor_detail_employee);

        //creating UI objects
        TextView tv_name,tv_category,tv_type,tv_species,tv_color,tv_size,tv_price,tv_brand;
        ImageView productImage;
        Button backB,editB,deleteB;

        //setting the objects
        tv_name = findViewById(R.id.nameDisplay);
        tv_category = findViewById(R.id.categoryDisplay);
        tv_type = findViewById(R.id.typeDisplay);
        tv_species = findViewById(R.id.speciesDisplay);
        tv_color = findViewById(R.id.colorDisplay);
        tv_size = findViewById(R.id.sizeDisplay);
        tv_price = findViewById(R.id.priceDisplay);
        tv_brand = findViewById(R.id.brandDisplay);
        productImage = findViewById(R.id.employeeDetailImage);
        backB = findViewById(R.id.homeButton);
        editB = findViewById(R.id.editButton);
        deleteB = findViewById(R.id.deleteButton);

        //getting the data passed via the intent
        Intent intent = getIntent();
        passedInFloor = (Floor) intent.getSerializableExtra("FLOOR_EXTRA");

        Glide.with(this.getApplicationContext()).load(passedInFloor.getImage().getUrl()).into(productImage);

        //setting output
        tv_name.setText(passedInFloor.getName());
        tv_category.setText(passedInFloor.getCategory());
        tv_type.setText(passedInFloor.getType());
        tv_species.setText(passedInFloor.getSpecies());
        tv_color.setText(passedInFloor.getColor());
        tv_size.setText(passedInFloor.getSize());
        tv_price.setText(passedInFloor.getPrice());
        tv_brand.setText(passedInFloor.getBrand());

        //HOME BUTTON
        //go back to employee main activity
        backB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(),EmployeeMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //DELETE BUTTON
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),DeleteProductActivity.class);
                intent.putExtra("FLOOR_DELETE",(Serializable) passedInFloor);
                startActivity(intent);
            }
        });

        //EDIT BUTTON
        editB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),EditProductActivity.class);
                //Bundle newTemp = new Bundle();
                //onSaveInstanceState(newTemp);
                intent.putExtra("FLOOR_EDIT",(Serializable) passedInFloor);
                //intent.putExtras(newTemp);
                startActivity(intent);
            }
        });
    }
}
