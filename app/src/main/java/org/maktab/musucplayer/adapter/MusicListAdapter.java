package org.maktab.musucplayer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.databinding.FragmentMusicListBinding;
import org.maktab.musucplayer.databinding.ListMusicBinding;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.utils.ListUtils;
import org.maktab.musucplayer.view_model.MusicViewModel;

import java.util.List;

public class MusicListAdapter extends Adapter<MusicListAdapter.MusicListHolder> {

    private List<Song> mSongs;
    private ListUtils.Callbacks mCallbacks;
    private ListUtils.States mState;
    //todo make it a line fore showing live

    public static MusicListAdapter newInstance(ListUtils.Callbacks callbacks,
                                               List<Song> songs,
                                               ListUtils.States state) {
        MusicListAdapter musicListAdapter = new MusicListAdapter();
        musicListAdapter.mSongs = songs;
        musicListAdapter.mCallbacks = callbacks;
        musicListAdapter.mState = state;
        return musicListAdapter;
    }

    public void setSongs(List<Song> songs) {
        if (!songs.equals(mSongs)) {
            mSongs = songs;
            notifyDataSetChanged();
        }
    }

    public ListUtils.States getState() {
        return mState;
    }

    public void setState(ListUtils.States state) {
        mState = state;
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
        return mSongs.size();
    }

    class MusicListHolder extends RecyclerView.ViewHolder {

        private ListMusicBinding mBinding;

        public MusicListHolder(@NonNull ListMusicBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Song song) {
            mBinding.setMusicVM(new MusicViewModel(song,mState,mCallbacks));
        }
    }

    public static final int LIMIT_CHARE_IN_VIEW = 9;
    public static final int LIMIT_CHARE_IN_Tiitle = 45;

    public static String limitString(String string, int limit) {
        return string.length() > limit ? string.substring(0, limit) + "..." : string;
    }
}
