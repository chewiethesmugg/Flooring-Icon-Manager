package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FloorDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_detail);

        TextView tv_detailName, tv_detailCategory, tv_detailType, tv_detailColor, tv_detailSize, tv_detailPrice;
        ImageView iv_detailView;
        Button bt_home;

        tv_detailName = findViewById(R.id.tv_detailName);
        tv_detailCategory = findViewById(R.id.tv_detailCategory);
        tv_detailType = findViewById(R.id.tv_detailType);
        tv_detailColor = findViewById(R.id.tv_detailColor);
        tv_detailSize = findViewById(R.id.tv_detailSize);
        tv_detailPrice = findViewById(R.id.tv_detailPrice);

        iv_detailView = findViewById(R.id.iv_detailView);
        bt_home = findViewById(R.id.bt_home);

        Intent intent = getIntent();
        Floor floor = (Floor) intent.getSerializableExtra("FLOOR_EXTRA");

        Log.i("FloorDetail", floor.getCategory()+floor.getType()+floor.getName());

        Glide.with(this.getApplicationContext()).load(floor.getImage().getUrl()).into(iv_detailView);

        tv_detailName.setText(floor.getName());
        tv_detailCategory.setText(floor.getCategory());
        tv_detailType.setText(floor.getType());
        tv_detailColor.setText(floor.getColor());
        tv_detailSize.setText(floor.getSize());
        tv_detailPrice.setText(floor.getPrice());

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}