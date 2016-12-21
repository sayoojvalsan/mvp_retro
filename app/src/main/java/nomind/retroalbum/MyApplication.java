package nomind.retroalbum;

import android.app.Application;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

import interfaces.SpotifyApiInterface;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import util.Utils;

/**
 * Created by sayoojvalsan on 12/20/16.
 */

public class MyApplication  extends Application {

    private static MyApplication sInstance;

    public static MyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }


}
