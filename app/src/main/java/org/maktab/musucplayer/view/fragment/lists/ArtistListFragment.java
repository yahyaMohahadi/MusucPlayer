package org.maktab.musucplayer.view.fragment.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.databinding.FragmentAlbumListBinding;
import org.maktab.musucplayer.databinding.FragmentArtistListBinding;
import org.maktab.musucplayer.repository.SongRepository;
import org.maktab.musucplayer.utils.ListUtils;
import org.maktab.musucplayer.view_model.ArtistListViewModel;
import org.maktab.musucplayer.view_model.ArtistViewModel;
import org.maktab.musucplayer.view_model.Music;


public class ArtistListFragment extends Fragment {
    private static ListUtils.Callbacks mCallbacks;
    private FragmentArtistListBinding mBinding;
    private ArtistListViewModel mViewModel;

    public static ArtistListFragment newInstance(ListUtils.Callbacks callbacksLists) {
        ArtistListFragment fragment = new ArtistListFragment();
        mCallbacks= callbacksLists;
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ArtistListViewModel(getContext(),mCallbacks);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_artist_list,container,false);
        mViewModel.setupRecyclerView(mBinding.recyclerviewArtist);
        return mBinding.getRoot();
    }
}