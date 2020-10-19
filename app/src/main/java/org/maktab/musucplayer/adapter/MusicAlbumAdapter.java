package org.maktab.musucplayer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.data.model.Album;
import org.maktab.musucplayer.databinding.ListAlbumBinding;
import org.maktab.musucplayer.ui.lists.album.AlbumViewModel;

import java.util.List;


public class MusicAlbumAdapter extends RecyclerView.Adapter<MusicAlbumAdapter.MusicListHolder> {


    private List<Album> mAlbums;

    private MusicAlbumAdapter() {
    }

    public static MusicAlbumAdapter newInstance(List<Album> albums) {
        MusicAlbumAdapter musicListAdapter = new MusicAlbumAdapter();
        musicListAdapter.mAlbums = albums;
        return musicListAdapter;
    }

    public void setSongs(List<Album> albums) {
        if (albums==null)
            return;
        if (!albums.equals(mAlbums)) {
            mAlbums = albums;
        }
    }


    @NonNull
    @Override
    public MusicAlbumAdapter.MusicListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListAlbumBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_album, parent, false);
        return new MusicAlbumAdapter.MusicListHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull MusicAlbumAdapter.MusicListHolder holder, int position) {
        holder.bind(mAlbums.get(position));

    }

    @Override
    public int getItemCount() {
        return mAlbums != null ? mAlbums.size() : 0;
    }


    class MusicListHolder extends RecyclerView.ViewHolder {

        private ListAlbumBinding mBinding;

        public MusicListHolder(@NonNull ListAlbumBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;

        }

        public void bind(final Album album) {
            mBinding.executePendingBindings();
            mBinding.setViewModel(new AlbumViewModel(album));
        }
    }
}