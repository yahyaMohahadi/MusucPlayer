package org.maktab.musucplayer.adapter;

import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.data.model.Song;
import org.maktab.musucplayer.databinding.ListMusicBinding;
import org.maktab.musucplayer.ui.Callback;
import org.maktab.musucplayer.ui.lists.music.MusicViewModel;

import java.util.ArrayList;
import java.util.List;

public class MusicListAdapter extends Adapter<MusicListAdapter.MusicListHolder> {

    private List<Song> mSongs = new ArrayList<>();
    private Callback<Song> mSongCallback;


    public MusicListAdapter(List<Song> songs, Callback<Song> songCallback) {
        mSongCallback = songCallback;
        mSongs = songs;

    }


    public void setSongs(@NonNull List<Song> songs) {
        if (!songs.equals(mSongs)) {
            mSongs = songs;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public MusicListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListMusicBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_music, parent, false);
        return new MusicListHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull MusicListHolder holder, int position) {
        holder.bind(mSongs.get(position));

    }

    @Override
    public int getItemCount() {
        return mSongs == null ? 0 : mSongs.size();
    }

    class MusicListHolder extends RecyclerView.ViewHolder {

        private ListMusicBinding mBinding;

        public MusicListHolder(@NonNull ListMusicBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Song song) {
            MusicViewModel viewModel = new MusicViewModel(song, new Callback<Song>() {
                @Override
                public void onClick(Song onCall, boolean inAllSongCalled) {
                    mSongCallback.onClick(onCall,true);
                }
            });
            mBinding.setVievModel(viewModel);
            mBinding.executePendingBindings();
        }
    }
}
