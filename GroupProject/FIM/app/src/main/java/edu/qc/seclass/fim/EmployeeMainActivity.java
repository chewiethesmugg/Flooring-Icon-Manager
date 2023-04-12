package edu.qc.seclass.fim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMainActivity extends AppCompatActivity{

    FloorAdapter adapter;
    RecyclerView floorRecyclerView;
    ArrayList<Floor> floorInFlushing;
    ArrayList<Floor> floorInManhattan;
    ArrayList<Floor> floorInHicksville;
    ArrayList<Floor> floorinMorrisPark;

    /*onStarted use since we're extending another activity
    protected void onStarted() {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);
    }
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);
        floorRecyclerView = findViewById(R.id.rvFloorEmployee);
        floorInFlushing=new ArrayList<Floor>();
        queryFloor(floorInFlushing,"Flushing");

        adapter = new FloorAdapter(this,floorInFlushing,"employee");
        floorRecyclerView.setAdapter(adapter);
        floorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void queryFloor(ArrayList<Floor> floorList, String storeName) {
        // Specify which class to query
        ParseQuery<Floor> query = ParseQuery.getQuery(Floor.class);
        // Find all Post objects
        // query.include(Floor.KEY_CATEGORY);
        query.whereContains(Floor.KEY_STORE,storeName);
        query.findInBackground(new FindCallback<Floor>() {
            @Override
            public void done(List<Floor> objects, ParseException e) {
                if(e == null){
                    if(objects != null){
                        floorList.addAll(objects);
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.employee_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.employee_item_flushing:
                adapter.clear();
                floorInFlushing = new ArrayList<Floor>();
                queryFloor(floorInFlushing, "Flushing");
                adapter = new FloorAdapter(this,floorInFlushing,"employee");
                floorRecyclerView.setAdapter(adapter);
                floorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                return true;

            case R.id.employee_item_manhattan:
                adapter.clear();
                floorInManhattan = new ArrayList<Floor>();
                queryFloor(floorInManhattan,"Manhattan");
                adapter = new FloorAdapter(this,floorInManhattan,"employee");
                floorRecyclerView.setAdapter(adapter);
                floorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                return true;

            case R.id.employee_item_hicksville:
                adapter.clear();
                floorInHicksville = new ArrayList<Floor>();
                queryFloor(floorInHicksville,"Hicksville");
                adapter = new FloorAdapter(this,floorInHicksville,"employee");
                floorRecyclerView.setAdapter(adapter);
                floorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                return true;

            case R.id.employee_item_morris_park:
                adapter.clear();
                floorinMorrisPark = new ArrayList<Floor>();
                queryFloor(floorinMorrisPark,"Morris Park");
                adapter = new FloorAdapter(this,floorinMorrisPark,"employee");
                floorRecyclerView.setAdapter(adapter);
                floorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                return true;

            case R.id.employee_add:
                    gotoAddProductActivity();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void gotoAddProductActivity(){
        Intent intent = new Intent(EmployeeMainActivity.this,AddProductActivity.class);
        startActivity(intent);
    }
}