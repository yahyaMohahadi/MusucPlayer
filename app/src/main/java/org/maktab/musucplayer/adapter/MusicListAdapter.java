package org.maktab.musucplayer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.ListMusicBinding;
import org.maktab.musucplayer.data.model.Song;


import java.util.List;

public class MusicListAdapter extends Adapter<MusicListAdapter.MusicListHolder> {

    private List<Song> mSongs;
    //todo make it a line fore showing live

    public static MusicListAdapter newInstance(
            List<Song> songs) {
        MusicListAdapter musicListAdapter = new MusicListAdapter();
        musicListAdapter.mSongs = songs;
        return musicListAdapter;
    }

    public void setSongs(List<Song> songs) {
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
            ;
        }
    }

    public static final int LIMIT_CHARE_IN_VIEW = 9;
    public static final int LIMIT_CHARE_IN_Tiitle = 45;

    public static String limitString(String string, int limit) {
        return string.length() > limit ? string.substring(0, limit) + "..." : string;
    }
}
