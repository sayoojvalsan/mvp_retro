package interfaces;


import model.Albums;

/**
 * Created by sayoojvalsan on 12/19/16.
 */

public interface AlbumViewInterface {

     void onAlbumLoaded(Albums album);

     void showProgress();

     void hideProgress();

     void onFailure(Throwable t);

     void hideMessage();

     void showMessage();

     void clear();
}
