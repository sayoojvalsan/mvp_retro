package nomind.retroalbum;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import interfaces.SpotifyApiInterface;
import presenter.AlbumPresenter;
import services.FetchAlbumService;
import view.AlbumView;

public class MainActivity extends AppCompatActivity {

    private static final String QUERY = "QUERY";

    @BindView(R.id.album_view)
    AlbumView mAlbumView;
    private String mQuery;

    @Inject
    SpotifyApiInterface mSpotifyApiInterface;

    @Inject
    FetchAlbumService mFetchAlbumService;


    AlbumPresenter mAlbumPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getNetComponent().inject(this);


        mAlbumPresenter = new AlbumPresenter(mAlbumView, mFetchAlbumService);

        //restore query on orientation change
        if(savedInstanceState != null && savedInstanceState.containsKey(QUERY)){
            mQuery = (String) savedInstanceState.get(QUERY);
            mAlbumPresenter.fetchAlbum(mQuery);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mQuery = query;
            //use the query to fetchAlbum
            mAlbumPresenter.fetchAlbum(query);
        }

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mQuery != null) {
            outState.putString(QUERY, mQuery);
        }
    }


}
