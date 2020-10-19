package org.maktab.musucplayer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.data.model.Artist;
import org.maktab.musucplayer.databinding.ArtistListOpenBinding;
import org.maktab.musucplayer.databinding.ListArtisiBinding;
import org.maktab.musucplayer.databinding.ListMusicBinding;
import org.maktab.musucplayer.ui.Callback;
import org.maktab.musucplayer.ui.lists.artist.ArtistViewModel;
import org.maktab.musucplayer.ui.lists.music.MusicViewModel;

import java.util.List;

public class MusicArtistAdapter extends RecyclerView.Adapter<MusicArtistAdapter.MusicHoldeler> {


    public static final int INT_NORMAL_LIST = 0;
    public static final int INT_OPEN_LIST = 1;
    private List<Artist> mArtists;
    private Artist mArtistOpen;

    private MusicArtistAdapter() {
    }


    public static MusicArtistAdapter newInstance(List<Artist> songs) {

        MusicArtistAdapter musicListAdapter = new MusicArtistAdapter();
        musicListAdapter.mArtists = songs;
        return musicListAdapter;
    }

    public void setSongs(List<Artist> artists) {
        if (artists == null)
            return;
        if (!artists.equals(mArtists)) {
            mArtists = artists;
        }
    }

    private void setArtistOpen(Artist artistOpen) {
        if (artistOpen.equals(mArtistOpen))
            mArtistOpen = null;
        mArtistOpen = artistOpen;
    }

    @Override
    public int getItemViewType(int position) {
        if (mArtistOpen == null) {
            return INT_NORMAL_LIST;
        } else if (mArtists.indexOf(mArtistOpen) == position) {
            return INT_OPEN_LIST;
        } else {
            return INT_NORMAL_LIST;
        }
    }

    @NonNull
    @Override
    public MusicHoldeler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case INT_NORMAL_LIST: {
                ListArtisiBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_artisi, parent, false);
                return new MusicListHolderNormal(binding);
            }
            case INT_OPEN_LIST: {
                ArtistListOpenBinding binding = DataBindingUtil.inflate(inflater, R.layout.artist_list_open, parent, false);
                return new MusicListHolderOpen(binding);
            }
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull MusicHoldeler holder, int position) {
        holder.bind(mArtists.get(position));

    }

    @Override
    public int getItemCount() {
        return mArtists != null ? mArtists.size() : 0;
    }


    abstract class MusicHoldeler extends RecyclerView.ViewHolder {

        public MusicHoldeler(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void bind(final Artist artist);
    }

    class MusicListHolderNormal extends MusicHoldeler {

        private ListArtisiBinding mBinding;

        public MusicListHolderNormal(@NonNull ListArtisiBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void bind(final Artist artist) {
            ArtistViewModel mViewModel;
            mViewModel = new ArtistViewModel(artist);
            mViewModel.setCallback(new Callback<Artist>() {
                @Override
                public void onClick(Artist onCall) {
                    setArtistOpen(onCall);
                    notifyDataSetChanged();
                }
            });
            mBinding.setViewModel(mViewModel);
        }
    }


    class MusicListHolderOpen extends MusicHoldeler {

        private ArtistListOpenBinding mBinding;


        public MusicListHolderOpen(@NonNull ArtistListOpenBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void bind(final Artist artist) {
            ArtistViewModel mViewModel;
            mViewModel = new ArtistViewModel(artist);
            mViewModel.setCallback(new Callback<Artist>() {
                @Override
                public void onClick(Artist onCall) {
                    setArtistOpen(onCall);
                    notifyDataSetChanged();
                }
            });
            mBinding.include.setViewModel(mViewModel);
            mBinding.itemPlace.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(mBinding.itemPlace.getContext());
            for (int i = 0; i <artist.getSongArtist().size() ; i++) {
                ListMusicBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_music, null, false);
                binding.setVievModel(new MusicViewModel(artist.getSongArtist().get(i)));
                mBinding.itemPlace.addView(binding.getRoot(),i);
            }
        }
    }
}