package view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import adapter.AlbumViewAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import interfaces.AlbumViewInterface;
import model.Albums;
import nomind.retroalbum.R;
import util.ErrorUtil;

/**
 * Created by sayoojvalsan on 12/19/16.
 */

public class AlbumView extends FrameLayout implements AlbumViewInterface{

    private static final String TAG = AlbumView.class.getSimpleName();

    @BindView(R.id.album_recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress)
    CircularProgressBar mProgress;

    @BindView(R.id.search_text)
    TextView mSearchText;

    private AlbumViewAdapter mMyRecyclerViewAdapter;




    public AlbumView(Context context) {
        super(context);
        init(context);

    }

    public AlbumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public AlbumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AlbumView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }



    public void init(Context context){

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View inflate = inflater.inflate(R.layout.ablum_view, this);
        ButterKnife.bind(this, inflate);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mMyRecyclerViewAdapter = new AlbumViewAdapter(new Albums(), context);
        mRecyclerView.setAdapter(mMyRecyclerViewAdapter);
        mProgress.setVisibility(View.GONE);
        mSearchText.setVisibility(View.VISIBLE);
        setMessage(getResources().getString(R.string.default_message));
    }


    public void setMessage(String message){
        mSearchText.setText(message);
    }

    @Override
    public void onAlbumLoaded(Albums albums) {
        mMyRecyclerViewAdapter.loadAlbums(albums);
        hideMessage();
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
        hideMessage();
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);

    }

    @Override
    public void onFailure(Throwable t) {
        setMessage(ErrorUtil.getErrorMessage(getContext(), t));
        showMessage();
    }

    @Override
    public void hideMessage() {
        mSearchText.setVisibility(View.GONE);
    }

    @Override
    public void showMessage() {
        mSearchText.setVisibility(View.VISIBLE);

    }
}
