package services;


import interfaces.FetchAlbumServiceInterface;
import model.AlbumResponse;
import interfaces.Callback;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sayoojvalsan on 12/19/16.
 */

public class FetchAlbumService implements FetchAlbumServiceInterface {


    private static final String TAG = FetchAlbumService.class.getSimpleName();

    @Override
    public void fetchAlbum(final String albumName, final Callback<AlbumResponse> callback) {


        final Call<AlbumResponse> albumsCall = RetroFitManager.getInstance().getSpotifyApiInterface().fetchAlbum(albumName);

        albumsCall.enqueue(new retrofit.Callback<AlbumResponse>() {
            @Override
            public void onResponse(Response<AlbumResponse> response, Retrofit retrofit) {
                callback.onResponse(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);

            }
        });

    }



}
