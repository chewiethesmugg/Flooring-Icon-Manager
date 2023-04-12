package edu.qc.seclass.fim;

import androidx.annotation.Nullable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import java.io.Serializable;


@ParseClassName("Floor")
public class Floor extends ParseObject implements Serializable{
     public static final String KEY_CATEGORY = "category";
     public static final String KEY_COLOR = "color";
     public static final String KEY_SIZE = "size";
     public static final String KEY_NAME = "name";
     public static final String KEY_PRICE = "price";
     public static final String KEY_IMAGE = "image";
     public static final String KEY_TYPE ="type";
     public static final String KEY_STORE = "store";
     public static final String KEY_SPECIES = "Species";
     public static final String KEY_BRAND = "brand";

     public Floor(){
         super();
     }

     public Floor(String category, String color, String size, String name, String price, String type, String store,String species,String brand,ParseFile image){
         super();
        setCategory(category);
        setColor(color);
         setSize(size);
        setName(name);
        setPrice(price);
        setType(type);
        setStore(store);
        setSpecies(species);
        setBrand(brand);
        setImage(image);
     }



    public String getCategory(){
         return getString(KEY_CATEGORY);
    }

    public String getColor(){
        return getString(KEY_COLOR);
    }

    public String getSize(){
        return getString(KEY_SIZE);
    }

    public String getName(){
        return getString(KEY_NAME);
    }

    public String getPrice(){
         return getString(KEY_PRICE);
    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image){put(KEY_IMAGE,image);}

    public String getType(){return getString(KEY_TYPE);}

    public String getStore(){return getString(KEY_STORE);}

    public String getSpecies(){return getString(KEY_SPECIES);}

    public String getBrand(){return getString(KEY_BRAND);}

    public void setCategory(String category){
       put(KEY_CATEGORY, category);
    }

    public void setColor(String color){
        put(KEY_COLOR, color);
    }

    public void setSize(String size){
        put(KEY_SIZE, size);
    }

    public void setName(String name){
         put(KEY_NAME,name);
    }

    public void setPrice(String price){
        put(KEY_PRICE, price);
    }

    public void setType(String type){put(KEY_TYPE,type);}

    public void setStore(String store){put(KEY_STORE,store);}

    public void setSpecies(String species){put(KEY_SPECIES,species);}

    public void setBrand(String brand){put(KEY_BRAND,brand);}
}
