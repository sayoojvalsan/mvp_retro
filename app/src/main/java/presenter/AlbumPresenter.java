package presenter;

import android.util.Log;

import java.lang.ref.WeakReference;

import interfaces.AlbumPresenterInterface;
import interfaces.AlbumViewInterface;
import interfaces.Callback;
import model.AlbumResponse;

import services.FetchAlbumService;

/**
 * Created by sayoojvalsan on 12/19/16.
 * Using MVP pattern
 */

public class AlbumPresenter implements AlbumPresenterInterface, Callback<AlbumResponse> {

    private static final String TAG = AlbumPresenter.class.getSimpleName();
    private WeakReference<AlbumViewInterface> mAlbumViewInterface;
    private FetchAlbumService mFetchAlbumService;


    public AlbumPresenter(AlbumViewInterface albumViewInterface, FetchAlbumService fetchAlbumService) {
        mAlbumViewInterface = new WeakReference<>(albumViewInterface);
        mFetchAlbumService = fetchAlbumService;
    }


    @Override
    public void fetchAlbum(final String albumName) {
        final AlbumViewInterface albumViewInterface = mAlbumViewInterface.get();
        if(albumViewInterface != null) {
            albumViewInterface.clear();
            albumViewInterface.showProgress();
            mFetchAlbumService.fetchAlbum(albumName, this);
        }
    }



    @Override
    public void onResponse(AlbumResponse response) {

        final AlbumViewInterface albumViewInterface = mAlbumViewInterface.get();
        if(albumViewInterface != null) {
                albumViewInterface.hideProgress();
            if(response != null) albumViewInterface.onAlbumLoaded(response.getAlbums());
            else onFailure(new NullPointerException());

        }

    }

    @Override
    public void onFailure(Throwable t) {
        final AlbumViewInterface albumViewInterface = mAlbumViewInterface.get();
        if(albumViewInterface != null) {
            albumViewInterface.hideProgress();
            albumViewInterface.onFailure(t);
        }
    }
}
