package services;


import javax.inject.Inject;

import interfaces.FetchAlbumServiceInterface;
import interfaces.SpotifyApiInterface;
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
    private final SpotifyApiInterface mSpotifyApiInterface;


    @Inject
    public FetchAlbumService(SpotifyApiInterface spotifyApiInterface) {
        mSpotifyApiInterface = spotifyApiInterface;
    }

    @Override
    public void fetchAlbum(final String albumName, final Callback<AlbumResponse> callback) {


        final Call<AlbumResponse> albumsCall = mSpotifyApiInterface.fetchAlbum(albumName);

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
