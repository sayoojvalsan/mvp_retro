package presenter;

import android.util.Log;

import java.lang.ref.WeakReference;

import interfaces.AlbumPresenterInterface;
import interfaces.AlbumViewInterface;
import model.AlbumResponse;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
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
            albumViewInterface.showProgress();
            mFetchAlbumService.fetchAlbum(albumName, this);
        }
    }



    @Override
    public void onResponse(Response<AlbumResponse> response, Retrofit retrofit) {
        final AlbumViewInterface albumViewInterface = mAlbumViewInterface.get();
        if(albumViewInterface != null) {
            if(response != null && response.body() != null) {
                albumViewInterface.hideProgress();
                albumViewInterface.onAlbumLoaded(response.body().getAlbums());
            }
            else onFailure(new NullPointerException());

        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(t != null){
            Log.e(TAG, "onFailure " + t.getLocalizedMessage());
        }
        final AlbumViewInterface albumViewInterface = mAlbumViewInterface.get();
        if(albumViewInterface != null) {
            albumViewInterface.hideProgress();
            albumViewInterface.onFailure(t);
        }
    }
}
