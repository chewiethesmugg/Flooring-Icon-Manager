package edu.qc.seclass.fim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloorAdapter adapter;
    RecyclerView floorRecyclerView;
    ArrayList<Floor> floorInFlushing;
    ArrayList<Floor> floorInManhattan;
    ArrayList<Floor> floorInHicksville;
    ArrayList<Floor> floorinMorrisPark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floorRecyclerView = findViewById(R.id.rvFloor);
        floorInFlushing = new ArrayList<Floor>();
        queryFloor(floorInFlushing,"Flushing");

        adapter = new FloorAdapter(this,floorInFlushing);
        floorRecyclerView.setAdapter(adapter);
        floorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.store_menu, menu);

        MenuItem item = menu.getItem(0);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Search your floor here...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_flushing:
                adapter.clear();
                floorInFlushing = new ArrayList<Floor>();
                queryFloor(floorInFlushing, "Flushing");
                adapter = new FloorAdapter(this,floorInFlushing);
                floorRecyclerView.setAdapter(adapter);
                floorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                return true;

            case R.id.item_manhattan:
                adapter.clear();
                floorInManhattan = new ArrayList<Floor>();
                queryFloor(floorInManhattan,"Manhattan");
                adapter = new FloorAdapter(this,floorInManhattan);
                floorRecyclerView.setAdapter(adapter);
                floorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                return true;

            case R.id.item_hicksville:
                adapter.clear();
                floorInHicksville = new ArrayList<Floor>();
                queryFloor(floorInHicksville,"Hicksville");
                adapter = new FloorAdapter(this,floorInHicksville);
                floorRecyclerView.setAdapter(adapter);
                floorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                return true;

            case R.id.item_morris_park:
                adapter.clear();
                floorinMorrisPark = new ArrayList<Floor>();
                queryFloor(floorinMorrisPark,"Morris Park");
                adapter = new FloorAdapter(this,floorinMorrisPark);
                floorRecyclerView.setAdapter(adapter);
                floorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
}