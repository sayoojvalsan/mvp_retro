package services;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

import interfaces.SpotifyApiInterface;
import nomind.retroalbum.MyApplication;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import util.Utils;

/**
 * Created by sayoojvalsan on 12/20/16.
 */
public class RetroFitManager {

    private static final String API_SPOTIFY_COM = "https://api.spotify.com";
    private Retrofit mRetroFit;
    private SpotifyApiInterface mSpotifyApiInterface;

    private static RetroFitManager ourInstance = new RetroFitManager();

    public static RetroFitManager getInstance() {
        return ourInstance;
    }

    private RetroFitManager() {
    }

    public void initRetroFit() {

        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        File httpCacheDirectory = new File(MyApplication.getInstance().getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        //add cache to the client
        client.setCache(cache);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_SPOTIFY_COM)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRetroFit = retrofit;

    }

    public Retrofit getRetroFit() {
        if(mRetroFit == null){
            initRetroFit();
        }
        return mRetroFit;
    }

    public SpotifyApiInterface getSpotifyApiInterface() {
        if(mSpotifyApiInterface == null){
            mSpotifyApiInterface = getRetroFit().create(SpotifyApiInterface.class);
        }
        return mSpotifyApiInterface;
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (Utils.isNetworkAvailable(MyApplication.getInstance())) {
                int maxAge = 60; // read from cache for 1 minute
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }

    };


}
