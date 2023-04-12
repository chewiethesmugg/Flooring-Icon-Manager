package edu.qc.seclass.fim;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 2022;
    public Bitmap imageBitmap;
    public ImageView iv_selectedImage;
    public Button  bt_selectImage;
    public File file;
    public ParseFile parseFile;
    public String productName,productCategory,productType,productSpecies,productStore,productColor,productSize,productPrice,productBrand;
    //used to ensure that productSpecies can only be modified for the correct category
    public Boolean isWood = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        //species is the only nullable field in the database
        productSpecies = "null";

        //creating objects for the UI elements
        Button addButton = findViewById(R.id.addButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        Spinner typeSelector = findViewById(R.id.typeSelect);
        Spinner categorySelector = findViewById(R.id.categorySelect);
        Spinner speciesSelector = findViewById(R.id.speciesSelect);
        Spinner storeSelector =  findViewById(R.id.storeSelect);

        EditText et_productColor = findViewById(R.id.colorInput);
        EditText et_productPrice  = findViewById(R.id.priceInput);
        EditText et_productName = findViewById(R.id.nameInput);
        EditText et_productSize = findViewById(R.id.sizeInput);
        EditText et_productBrand = findViewById(R.id.brandInput);


        iv_selectedImage = findViewById(R.id.iv_selectedImage);
        bt_selectImage = findViewById(R.id.bt_selectImage);


        //creating array adapters
        //setting default values for all the spinners
        //the default type adapter is stone, it will be updated when category is selected
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.stone_type_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> speciesAdapter = ArrayAdapter.createFromResource(this,
                R.array.wood_species_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> storeAdapter = ArrayAdapter.createFromResource(this,
                R.array.store_array, android.R.layout.simple_spinner_item);

        //setting default adapters for the spinners
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSelector.setAdapter(typeAdapter);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySelector.setAdapter(categoryAdapter);
        speciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speciesSelector.setAdapter(speciesAdapter);
        storeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        storeSelector.setAdapter(storeAdapter);


        bt_selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });


                //adding listeners to the UI elements
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //used to check whether inputs are valid and can be added to DB
                        boolean goodInputs = true;
                        productColor = et_productColor.getText().toString();
                        productPrice = et_productPrice.getText().toString();
                        productSize = et_productSize.getText().toString();
                        productName = et_productName.getText().toString();
                        productBrand = et_productBrand.getText().toString();


                        ParseObject object = ParseObject.create("Floor");
                        object.put("name",productName);
                        object.put("price",productPrice);
                        object.put("size",productSize);
                        object.put("brand", productBrand);
                        object.put("color",productColor);
                        object.put("category", productCategory);
                        object.put("type",productType);
                        object.put("Species",productSpecies);
                        object.put("image",new ParseFile(file));
                        object.put("store",productStore);

                        object.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e==null){
                                    Toast.makeText(view.getContext(),"saved product",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(view.getContext(),"failed to save",Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                }
                            }
                        });

                       // Toast.makeText(view.getContext(),"add clicked",Toast.LENGTH_LONG).show();
                        //checking inputs
                        //check name
                        if (productName.length() == 0) {
                            Toast.makeText(view.getContext(), "Cannot have empty name!", Toast.LENGTH_LONG).show();
                            goodInputs = false;
                        }
                        //check color
                        else if (productColor.length() == 0) {
                            Toast.makeText(view.getContext(), "Cannot have empty color!", Toast.LENGTH_LONG).show();
                            goodInputs = false;
                        }
                        //check price
                        //check size
                        //check brand
                        else if (productBrand.length() == 0) {
                            Toast.makeText(view.getContext(), "Cannot have empty color!", Toast.LENGTH_LONG).show();
                            goodInputs = false;
                        }

                        //check species , this works
                        else if (isWood) {
                            if (productSpecies == "null") {
                                Toast.makeText(view.getContext(), "Cannot have null species!", Toast.LENGTH_LONG).show();
                                goodInputs = false;
                            }
                        }



                        //only handle input data if its good
                        if (goodInputs) {

//                            floor.saveInBackground(new SaveCallback() {
//                                public void done(ParseException e) {
//                                    if (e == null) {
//                                        // Success
//                                        Toast.makeText(view.getContext(), "Added new product: " + productName, Toast.LENGTH_LONG).show();
////                                      Intent intent = new Intent(AddProductActivity.this,EmployeeMainActivity.class);
////                                      startActivity(intent);
//                                    } else {
//                                            Toast.makeText(view.getContext(), "Couldnt add new product: " + e.getMessage(), Toast.LENGTH_LONG).show();
//                                            Log.e("parse error", e.getMessage().toString());
//
//                                            }
//                                }
//                            });
                        }
                    }
                });
        //
        cancelButton.setOnClickListener(new View.OnClickListener() {
            //GO BACK TO EMPLOYEE HOMEPAGE
            public void onClick(View view) {

                Intent i = new Intent(AddProductActivity.this, EmployeeMainActivity.class);
                startActivity(i);
            }
        });


        //the category spinner listener changes the default values of the other spinners
        categorySelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedCategory = adapterView.getItemAtPosition(i).toString();
                //getting category information
                productCategory = selectedCategory;

                //setting the text options for other spinners
                switch(selectedCategory){
                    case "Tile":
                        //setting type spinner
                        typeSelector.setAdapter(ArrayAdapter.createFromResource(adapterView.getContext(),
                                R.array.tile_type_array, android.R.layout.simple_spinner_item));
                        break;
                    case "Stone":
                        //setting type spinner
                        typeSelector.setAdapter(ArrayAdapter.createFromResource(adapterView.getContext(),
                                R.array.stone_type_array, android.R.layout.simple_spinner_item));
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
                        break;
                    case "Vinyl":
                        //setting type spinner
                        typeSelector.setAdapter(ArrayAdapter.createFromResource(adapterView.getContext(),
                                R.array.vinyl_type_array, android.R.layout.simple_spinner_item));
                        break;
                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        //getting type information
        typeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                productType = adapterView.getItemAtPosition(i).toString();
            }
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        //getting species information
        speciesSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(isWood) {
                    productSpecies = adapterView.getItemAtPosition(i).toString();
                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        //getting store information
        storeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                productStore = adapterView.getItemAtPosition(i).toString();
            }
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//            imageData = data.getData();
//
//            if (imageData != null) {
//                iv_selectedImage.setImageURI(imageData);
//
//            }
//        }
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            iv_selectedImage.setImageBitmap(imageBitmap);

            Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);
            file = new File(getRealPathFromURI(tempUri));
            Toast.makeText(getApplicationContext(),file.getAbsolutePath(),Toast.LENGTH_LONG).show();
        }
    }

    private String getRealPathFromURI(Uri tempUri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(tempUri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    private Uri getImageUri(Context applicationContext, Bitmap imageBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(applicationContext.getContentResolver(), imageBitmap, "Title", null);
        return Uri.parse(path);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
            e.printStackTrace();
        }
    }
}
