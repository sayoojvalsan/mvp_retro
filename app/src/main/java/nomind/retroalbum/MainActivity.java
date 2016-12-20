package nomind.retroalbum;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import presenter.AlbumPresenter;
import services.FetchAlbumService;
import view.AlbumView;

public class MainActivity extends AppCompatActivity {

    private static final String QUERY = "QUERY";

    @BindView(R.id.album_view)
    AlbumView mAlbumView;
    private AlbumPresenter mAlbumPresenter;
    private String mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        final FetchAlbumService fetchAlbumService = new FetchAlbumService();
        mAlbumPresenter = new AlbumPresenter(mAlbumView, fetchAlbumService);

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
