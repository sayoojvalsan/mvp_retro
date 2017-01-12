package di;

import android.app.Application;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import interfaces.SpotifyApiInterface;
import nomind.retroalbum.MyApplication;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import util.Utils;

/**
 * Created by sayoojvalsan on 1/11/17.
 */

@Module
public class NetModule {

    String mBaseUrl;

    public NetModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }





    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        File httpCacheDirectory = new File(MyApplication.getInstance().getCacheDir(), "responses");
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        return cache;
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        client.setCache(cache);
        return client;
    }

    @Provides
    @Singleton
    Retrofit provieRetroFit(OkHttpClient client){

        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Provides
    @Singleton
    SpotifyApiInterface provieSpotifyInterface(Retrofit retrofit){

        return retrofit.create(SpotifyApiInterface.class);
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
