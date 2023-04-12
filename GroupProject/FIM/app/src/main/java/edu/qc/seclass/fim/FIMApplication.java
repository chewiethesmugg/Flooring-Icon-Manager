package edu.qc.seclass.fim;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;


public class FIMApplication extends Application{
    @Override
    public void onCreate(){
        super.onCreate();
        ParseObject.registerSubclass(Floor.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
    }
}