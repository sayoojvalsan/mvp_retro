package nomind.retroalbum;

import android.app.Application;

import di.AppModule;
import di.DaggerNetComponent;
import di.NetComponent;
import di.NetModule;

/**
 * Created by sayoojvalsan on 12/20/16.
 */

public class MyApplication  extends Application {

    private static MyApplication sInstance;
    private NetComponent mNetComponent;

    public static MyApplication getInstance() {
        return sInstance;
    }
    private static final String API_SPOTIFY_COM = "https://api.spotify.com";

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule(API_SPOTIFY_COM))
                .build();

    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }



}
