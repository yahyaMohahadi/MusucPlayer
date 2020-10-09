package org.maktab.musucplayer.view.fragment.lists;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.databinding.FragmentMusicListBinding;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.utils.ListUtils;
import org.maktab.musucplayer.view_model.Music;

import java.util.List;

public class MusicListFragment extends Fragment {
    private FragmentMusicListBinding mBinding;
    RecyclerView mRecyclerViewSongs;
    private ListUtils.Callbacks mCallbacks;
    private List<Song> mSongsToShow;
    private MusicListAdapter mMusicListAdapter;
    private ListUtils.States mStates;

    public static MusicListFragment newInstance(ListUtils.Callbacks callbacks, ListUtils.States states) {
        if (states == ListUtils.States.MUSIC_ALBUM || states == ListUtils.States.MUSICS || states == ListUtils.States.MUSIC_ARTIST) {
            MusicListFragment fragment = new MusicListFragment();
            fragment.mCallbacks = callbacks;
            fragment.mStates = states;
            try {
                fragment.mSongsToShow = Music.newInstance().getSongList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return fragment;
        }
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_music_list,container,false);
        findView(mBinding.getRoot());
        intRecyclerVive();
        mRecyclerViewSongs.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateList();
       /* mRecyclerViewSongs.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL));*/
        return mBinding.getRoot();
    }

    public void findView(View view) {
        mRecyclerViewSongs = view.findViewById(R.id.recyclerview_songs);
    }

    private void intRecyclerVive() {
        mRecyclerViewSongs.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        mMusicListAdapter = MusicListAdapter.newInstance(
                getActivity(), mSongsToShow, mStates, mCallbacks);
        mRecyclerViewSongs.setAdapter(mMusicListAdapter);

    }

    public void updateList() {
        try {
            mMusicListAdapter.setSongs(Music.newInstance().getSongList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mSongsToShow == null) {
            mSongsToShow = SongRepository.newInstance(getActivity()).getSongs();
        }
    }

    public ListUtils.States getStates() {
        return mStates;
    }

    public void setStates(Context context, ListUtils.States states) {
        //todo why adapter is null fix
        mStates = states;
        if (mMusicListAdapter == null) {
            mMusicListAdapter = MusicListAdapter.newInstance(
                    context, mSongsToShow, mStates, mCallbacks);
            return;
        }

        mMusicListAdapter.setState(states);
    }

    public void changeCurentSong(Song song) {
        if (mMusicListAdapter != null)
            mMusicListAdapter.setIntCurentSong(mSongsToShow.indexOf(song));
    }
}