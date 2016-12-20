package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Albums;
import nomind.retroalbum.R;

/**
 * Created by sayoojvalsan on 12/19/16.
 */

public class AlbumViewAdapter extends RecyclerView.Adapter<AlbumViewAdapter.CustomViewHolder> {

    private Albums mAlbums;

    private Context mContext;


    public AlbumViewAdapter(Albums albums, Context context) {
        mAlbums = albums;
        mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if(mAlbums == null || mAlbums.getItems() == null) return;
        final String url = mAlbums.getItems()[position].getImages()[0].getUrl();
        Picasso.with(mContext).load(url).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        if(mAlbums.getItems() == null) return 0;

        return mAlbums.getItems().length;
    }

    public void loadAlbums(Albums album) {
        mAlbums = album;
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.title) TextView title;

        public CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
