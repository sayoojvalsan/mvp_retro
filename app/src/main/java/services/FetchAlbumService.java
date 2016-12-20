package services;

import interfaces.FetchAlbumServiceInterface;
import interfaces.SpotifyApiInterface;
import model.AlbumResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by sayoojvalsan on 12/19/16.
 */

public class FetchAlbumService implements FetchAlbumServiceInterface {


    private static final String TAG = FetchAlbumService.class.getSimpleName();
    public static final String API_SPOTIFY_COM = "https://api.spotify.com";

    @Override
    public void fetchAlbum(final String albumName, Callback<AlbumResponse> callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_SPOTIFY_COM)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final SpotifyApiInterface spotifyApiInterface = retrofit.create(SpotifyApiInterface.class);

        final Call<AlbumResponse> albumsCall = spotifyApiInterface.fetchAlbum(albumName);

        albumsCall.enqueue(callback);

    }

}
