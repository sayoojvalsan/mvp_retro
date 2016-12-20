package interfaces;

import model.AlbumResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by sayoojvalsan on 12/19/16.
 */

public interface SpotifyApiInterface {
    @GET("/v1/search?type=album")
    Call<AlbumResponse> fetchAlbum(@Query("q") String albumName);

}
