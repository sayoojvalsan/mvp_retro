package services;


import interfaces.FetchAlbumServiceInterface;
import model.AlbumResponse;
import retrofit.Call;
import retrofit.Callback;

/**
 * Created by sayoojvalsan on 12/19/16.
 */

public class FetchAlbumService implements FetchAlbumServiceInterface {


    private static final String TAG = FetchAlbumService.class.getSimpleName();

    @Override
    public void fetchAlbum(final String albumName, Callback<AlbumResponse> callback) {


        final Call<AlbumResponse> albumsCall = RetroFitManager.getInstance().getSpotifyApiInterface().fetchAlbum(albumName);

        albumsCall.enqueue(callback);

    }



}
