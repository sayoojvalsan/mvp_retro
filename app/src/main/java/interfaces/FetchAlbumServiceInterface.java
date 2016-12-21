package interfaces;

import model.AlbumResponse;

/**
 * Created by sayoojvalsan on 12/19/16.
 */

public interface FetchAlbumServiceInterface {

    void fetchAlbum(String albumName, Callback<AlbumResponse> callback);
}
