package org.maktab.musucplayer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.ListArtisiBinding;
import org.maktab.musucplayer.model.Artist;
import org.maktab.musucplayer.utils.ListUtils;
import org.maktab.musucplayer.view_model.itemViewModel.ArtistViewModel;

import java.util.List;

public class MusicArtistAdapter extends RecyclerView.Adapter<MusicArtistAdapter.MusicListHolder> {


    private List<Artist> mArtists;

    private MusicArtistAdapter() {
    }


    public static MusicArtistAdapter newInstance(List<Artist> songs) {

        MusicArtistAdapter musicListAdapter = new MusicArtistAdapter();
        musicListAdapter.mArtists = songs;
        return musicListAdapter;
    }

    public void setSongs(List<Artist> albums) {
        if (!mArtists.equals(albums)) {
            mArtists = albums;
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public MusicArtistAdapter.MusicListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListArtisiBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_artisi, parent, false);
        return new MusicArtistAdapter.MusicListHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull MusicArtistAdapter.MusicListHolder holder, int position) {
        holder.bind(mArtists.get(position));

    }

    @Override
    public int getItemCount() {
        return mArtists != null ? mArtists.size() : 0;
    }


    class MusicListHolder extends RecyclerView.ViewHolder {

        private ListArtisiBinding mBinding;

        public MusicListHolder(@NonNull ListArtisiBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;

        }

        public void bind(final Artist artist) {
            //todo dont use image view to set
            mBinding.executePendingBindings();
        }
    }
}