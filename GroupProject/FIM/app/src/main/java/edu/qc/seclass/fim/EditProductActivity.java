package edu.qc.seclass.fim;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class EditProductActivity extends AppCompatActivity {
    String newProductName,newProductCategory,newProductType,newProductSpecies,newProductStore,newProductColor,newProductSize,newProductPrice,newProductBrand;
    Floor passedInFloor;
    //used to check for species
    boolean isWood = false;
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(intent.getExtras());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        //get passed in data this is the default unless changed
        Intent intent = getIntent();
        passedInFloor = (Floor) intent.getSerializableExtra("FLOOR_EDIT");
        newProductName = passedInFloor.getName();
        newProductCategory = passedInFloor.getCategory();
        newProductType = passedInFloor.getType();
        newProductSpecies = passedInFloor.getSpecies();
        newProductSize = passedInFloor.getSize();
        newProductStore = passedInFloor.getStore();
        newProductColor = passedInFloor.getColor();
        newProductPrice = passedInFloor.getPrice();
        newProductBrand = passedInFloor.getBrand();
        //saving original data
        String ProductName,ProductCategory,ProductType,ProductSpecies,ProductStore,ProductColor,ProductSize,ProductPrice,ProductBrand;
        ProductName = newProductName;
        ProductCategory=newProductCategory;
        ProductType=newProductType;
        ProductSpecies=newProductSpecies;
        ProductSize=newProductSize;
        ProductStore=newProductStore;
        ProductColor=newProductColor;
        ProductPrice=newProductPrice;
        ProductBrand = newProductBrand;

        //strip price and size of other characters
        String formattedPrice = newProductPrice.substring(1);
        String formattedSize = newProductSize.substring(0, 4);


        //creating editText objects and setting them
        EditText nameInput = (EditText) findViewById(R.id.editProductNameInput);
        EditText priceInput = (EditText) findViewById(R.id.editProductPriceInput);
        EditText sizeInput = (EditText) findViewById(R.id.editProductSizeInput);
        EditText brandInput = (EditText) findViewById(R.id.editProductBrandInput);
        EditText colorInput = (EditText) findViewById(R.id.editProductColorInput);
        //set editTexts to default values
        nameInput.setText(newProductName);
        priceInput.setText(formattedPrice);
        sizeInput.setText(formattedSize);
        brandInput.setText(newProductBrand);
        colorInput.setText(newProductColor);

        //creating spinners
        Spinner typeSelector = (Spinner) findViewById(R.id.editProductTypeSelector);
        Spinner categorySelector = (Spinner) findViewById(R.id.editProductCategorySelector);
        Spinner speciesSelector = (Spinner) findViewById(R.id.editProductSpeciesSelector);
        Spinner storeSelector = (Spinner) findViewById(R.id.editProductStoreSelector);

        //setting default values for all the spinners
        //newProductCategory is used to determine type
        String selectedCategory = newProductCategory;
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> speciesAdapter = ArrayAdapter.createFromResource(this,
                R.array.wood_species_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> storeAdapter = ArrayAdapter.createFromResource(this,
                R.array.store_array, android.R.layout.simple_spinner_item);
        categorySelector.setAdapter(categoryAdapter);
        //setting the text options for other spinners
        switch(selectedCategory) {
            case "Tile":
                //setting type spinner
                typeSelector.setAdapter(ArrayAdapter.createFromResource(this,
                        R.array.tile_type_array, android.R.layout.simple_spinner_item));
                //set default category value
                categorySelector.setSelection(1);
                break;
            case "Stone":
                //setting type spinner
                typeSelector.setAdapter(ArrayAdapter.createFromResource(this,
                        R.array.stone_type_array, android.R.layout.simple_spinner_item));
                categorySelector.setSelection(2);
                break;
            case "Wood":
                //setting type spinner
                typeSelector.setAdapter(ArrayAdapter.createFromResource(this,
                        R.array.wood_type_array, android.R.layout.simple_spinner_item));
                isWood = true;
                categorySelector.setSelection(3);
                break;
            case "Laminate":
                //setting type spinner
                typeSelector.setAdapter(ArrayAdapter.createFromResource(this,
                        R.array.laminate_type_array, android.R.layout.simple_spinner_item));
                categorySelector.setSelection(4);
                break;
            case "Vinyl":
                //setting type spinner
                typeSelector.setAdapter(ArrayAdapter.createFromResource(this,
                        R.array.vinyl_type_array, android.R.layout.simple_spinner_item));
                categorySelector.setSelection(5);
                break;
        }

        //setting the other spinners
        speciesSelector.setAdapter(speciesAdapter);
        if(isWood){
            switch(newProductSpecies){
                case "Oak":
                    speciesSelector.setSelection(1);
                    break;
                case "Hickory":
                    speciesSelector.setSelection(2);
                    break;
                case "Maple":
                    speciesSelector.setSelection(3);
                    break;
            }
        }
        storeSelector.setAdapter(storeAdapter);
        switch(newProductStore){
            case "Flushing":
                storeSelector.setSelection(1);
                break;
            case "Manhattan":
                storeSelector.setSelection(2);
                break;
            case "Morris Hill":
                storeSelector.setSelection(3);
                break;
            case  "Hicksville":
                storeSelector.setSelection(4);
                break;
        }

        //setting spinner listeners, this will update default values
        //the category spinner listener changes the default values of the other spinners
        categorySelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedCategory = adapterView.getItemAtPosition(i).toString();
                newProductCategory = selectedCategory;
                //setting the text options for other spinners
                switch(selectedCategory){
                    case "Tile":
                        //setting type spinner
                        typeSelector.setAdapter(ArrayAdapter.createFromResource(adapterView.getContext(),
                                R.array.tile_type_array, android.R.layout.simple_spinner_item));
                        //set species spinner
                        speciesSelector.setSelection(0);
                        newProductSpecies="null";
                        break;
                    case "Stone":
                        //setting type spinner
                        typeSelector.setAdapter(ArrayAdapter.createFromResource(adapterView.getContext(),
                                R.array.stone_type_array, android.R.layout.simple_spinner_item));
                        //set species spinner
                        speciesSelector.setSelection(0);
                        newProductSpecies="null";
                        break;
                    case "Wood":
                        //setting type spinner
                        typeSelector.setAdapter(ArrayAdapter.createFromResource(adapterView.getContext(),
                                R.array.wood_type_array, android.R.layout.simple_spinner_item));
                        isWood = true;
                        break;
                    case "Laminate":
                        //setting type spinner
                        typeSelector.setAdapter(ArrayAdapter.createFromResource(adapterView.getContext(),
                                R.array.laminate_type_array, android.R.layout.simple_spinner_item));
                        //set species spinner
                        speciesSelector.setSelection(0);
                        newProductSpecies="null";
                        break;
                    case "Vinyl":
                        //setting type spinner
                        typeSelector.setAdapter(ArrayAdapter.createFromResource(adapterView.getContext(),
                                R.array.vinyl_type_array, android.R.layout.simple_spinner_item));
                        //set species spinner
                        speciesSelector.setSelection(0);
                        newProductSpecies="null";
                        break;
                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        //getting type information
        typeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newProductType = adapterView.getItemAtPosition(i).toString();
                Log.e("newtype: ",newProductType+" "+i);
            }
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        //getting species information
        speciesSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(isWood) {
                    newProductSpecies = adapterView.getItemAtPosition(i).toString();
                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        //getting store information
        storeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newProductStore = adapterView.getItemAtPosition(i).toString();
            }
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        //creating buttons
        Button confirmB = findViewById(R.id.editProductConfirmButton);
        Button backB = findViewById(R.id.editProductCancelButton);

        confirmB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean goodInput = true;
                //get inputted data
                newProductName = nameInput.getText().toString();
                newProductPrice = priceInput.getText().toString();
                newProductSize = sizeInput.getText().toString();
                newProductBrand = brandInput.getText().toString();
                newProductColor = colorInput.getText().toString();
                //check name is not empty
                if(newProductName.length()==0){
                    Toast.makeText(view.getContext(), "Can't have an empty product name!", Toast.LENGTH_SHORT).show();
                    goodInput = false;
                }
                //check price is not empty
                else if(newProductPrice.length()==0){
                    Toast.makeText(view.getContext(), "Can't have an empty price!", Toast.LENGTH_SHORT).show();
                    goodInput=false;
                }
                //check size is not empty
                else if(newProductSize.length()==0){
                    Toast.makeText(view.getContext(), "Can't have an empty size!", Toast.LENGTH_SHORT).show();
                    goodInput=false;
                }
                //check brand
                else if(newProductBrand.length()==0){
                    Toast.makeText(view.getContext(), "Can't have an empty brand!", Toast.LENGTH_SHORT).show();
                    goodInput=false;
                }
                else if(newProductColor.length()==0){
                    Toast.makeText(view.getContext(), "Can't have an empty color!", Toast.LENGTH_SHORT).show();
                    goodInput=false;
                }
                //no category was selected
                else if(categorySelector.getSelectedItemPosition()==0){
                    Toast.makeText(view.getContext(), "Please select a category!", Toast.LENGTH_SHORT).show();
                    goodInput = false;
                }
                //no type was selected
                else if(typeSelector.getSelectedItemPosition()==0){
                    Toast.makeText(view.getContext(), "Please select a type!", Toast.LENGTH_SHORT).show();
                    goodInput = false;

                }
                //no store was selected
                else if(storeSelector.getSelectedItemPosition()==0){
                    Toast.makeText(view.getContext(), "Please select a store!", Toast.LENGTH_SHORT).show();
                    goodInput = false;
                }
                //check that species is null if false isWood
                else if(isWood){
                    if(speciesSelector.getSelectedItemPosition()==0){
                        Toast.makeText(view.getContext(), "Please select a species!", Toast.LENGTH_SHORT).show();
                        goodInput = false;
                    }
                }

                //FORMATTING DATA TO FIT DB
                newProductPrice = "$"+newProductPrice;
                newProductSize = newProductSize+" sq ft/case";
                Log.e("product: ",newProductName+" "+newProductPrice+" "+newProductSpecies+" "+newProductCategory+" "+newProductBrand+" "+newProductType+" "+newProductStore);
                //if all data is correct
                if(goodInput){
                    //query DB for item
                    ParseQuery<Floor> queryName = ParseQuery.getQuery("Floor");
                    queryName.whereEqualTo("name",ProductName);
                    queryName.whereEqualTo("category",ProductCategory);
                    queryName.whereEqualTo("type",ProductType);
                    queryName.whereEqualTo("Species",ProductSpecies);
                    queryName.whereEqualTo("store",ProductStore);
                    queryName.whereEqualTo("price",ProductPrice);
                    queryName.whereEqualTo("brand",ProductBrand);
                    queryName.whereEqualTo("size",ProductSize);
                    queryName.whereEqualTo("color",ProductColor);
                    //modifing the data
                    queryName.findInBackground(new FindCallback<Floor>() {
                        @Override
                        public void done(List<Floor> objects, ParseException e) {
                            if(e==null){
                                ParseObject currentFloor = objects.get(0);
                                currentFloor.put("category",newProductCategory);
                                currentFloor.put("type",newProductType);
                                currentFloor.put("store",newProductStore);
                                currentFloor.put("size",newProductSize);
                                currentFloor.put("price",newProductPrice);
                                currentFloor.put("color",newProductColor);
                                currentFloor.put("name",newProductName);
                                currentFloor.put("Species",newProductSpecies);
                                currentFloor.put("brand",newProductBrand);
                                currentFloor.put("image",passedInFloor.getImage());
                                currentFloor.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if(e==null){
                                            Toast.makeText(view.getContext(), "Edited Product!", Toast.LENGTH_LONG).show();
                                        }else{
                                            Toast.makeText(view.getContext(), "Could not not edit product!"+e.getMessage().toString(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }else{
                                // if expected data isnt in the database
                                Toast.makeText(EditProductActivity.this, "Fail to get the object..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        //goes back to employee detailed view
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
