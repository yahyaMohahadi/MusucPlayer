package org.maktab.musucplayer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.data.model.Album;
import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.databinding.AlbumListOpenBinding;
import org.maktab.musucplayer.databinding.ListAlbumBinding;
import org.maktab.musucplayer.databinding.ListMusicBinding;
import org.maktab.musucplayer.ui.Callback;
import org.maktab.musucplayer.ui.lists.album.AlbumViewModel;
import org.maktab.musucplayer.ui.lists.music.MusicViewModel;

import java.util.List;


public class MusicAlbumAdapter extends RecyclerView.Adapter<MusicAlbumAdapter.MusicHoldeler> {


    public static final int INT_NORMAL_LIST = 0;
    public static final int INT_OPEN_LIST = 1;
    private List<Album> mAlbums;
    private Album mAlbumOpen;
    private Callback<Album> mCallback;
    private Callback<Song> mSongCallback;

    private MusicAlbumAdapter() {
    }


    public static MusicAlbumAdapter newInstance(List<Album> songs, Callback<Album> albumCallback, Callback<Song> songCallback) {
        MusicAlbumAdapter musicListAdapter = new MusicAlbumAdapter();
        musicListAdapter.mCallback = albumCallback;
        musicListAdapter.mSongCallback = songCallback;
        musicListAdapter.mAlbums = songs;
        return musicListAdapter;
    }

    public void setSongs(List<Album> albums) {
        if (albums.equals(mAlbums)) {
            mAlbums = null;
        } else {
            mAlbums = albums;
        }
    }

    private void setAlbumOpen(Album albumOpen) {
        mAlbumOpen = albumOpen;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mAlbumOpen == null) {
            return INT_NORMAL_LIST;
        } else if (mAlbums.indexOf(mAlbumOpen) == position) {
            return INT_OPEN_LIST;
        } else {
            return INT_NORMAL_LIST;
        }
    }

    @NonNull
    @Override
    public MusicAlbumAdapter.MusicHoldeler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case INT_NORMAL_LIST: {
                ListAlbumBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_album, parent, false);
                return new MusicAlbumAdapter.MusicListHolderNormal(binding);
            }
            case INT_OPEN_LIST: {
                AlbumListOpenBinding binding = DataBindingUtil.inflate(inflater, R.layout.album_list_open, parent, false);
                return new MusicAlbumAdapter.MusicListHolderOpen(binding);
            }
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull MusicAlbumAdapter.MusicHoldeler holder, int position) {
        holder.bind(mAlbums.get(position));

    }

    @Override
    public int getItemCount() {
        return mAlbums != null ? mAlbums.size() : 0;
    }


    abstract class MusicHoldeler extends RecyclerView.ViewHolder {

        public MusicHoldeler(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void bind(final Album album);
    }

    class MusicListHolderNormal extends MusicAlbumAdapter.MusicHoldeler {

        private ListAlbumBinding mBinding;

        public MusicListHolderNormal(@NonNull ListAlbumBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void bind(final Album album) {
            AlbumViewModel mViewModel;
            mViewModel = new AlbumViewModel(album);
            mViewModel.setCallback(new Callback<Album>() {
                @Override
                public void onClick(Album onCall, boolean allSong) {
                    setAlbumOpen(onCall);
                    notifyDataSetChanged();
                    mCallback.onClick(onCall, false);
                }
            });
            mBinding.setViewModel(mViewModel);
        }
    }


    class MusicListHolderOpen extends MusicAlbumAdapter.MusicHoldeler {

        private AlbumListOpenBinding mBinding;


        public MusicListHolderOpen(@NonNull AlbumListOpenBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void bind(final Album album) {
            AlbumViewModel mViewModel;
            mViewModel = new AlbumViewModel(album);
            mViewModel.setCallback(new Callback<Album>() {
                @Override
                public void onClick(Album onCall, boolean allCall) {
                    if (onCall.equals(mAlbumOpen)) {
                        setAlbumOpen(null);
                        return;
                    }
                    mCallback.onClick(album, false);
                    setAlbumOpen(onCall);

                }
            });
            mBinding.include.setViewModel(mViewModel);
            mBinding.itemPlace.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(mBinding.itemPlace.getContext());
            for (int i = 0; i < album.getSongAlbum().size(); i++) {
                ListMusicBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_music, null, false);
                binding.setVievModel(new MusicViewModel(album.getSongAlbum().get(i), mSongCallback));
                mBinding.itemPlace.addView(binding.getRoot(), i);
            }
        }
    }
}