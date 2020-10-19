package org.maktab.musucplayer.ui.lists.artist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.data.model.Artist;
import org.maktab.musucplayer.databinding.FragmentArtistListBinding;

import java.util.List;


public class MusicListArtistFragment extends Fragment {

    private ArtistListViewModel mViewModel;
    private FragmentArtistListBinding mBinding;

    public static MusicListArtistFragment newInstance() {
        MusicListArtistFragment fragment = new MusicListArtistFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_artist_list, container, false);
        mViewModel = new ViewModelProvider(this).get(ArtistListViewModel.class);
        mViewModel.setupRecyclerView(mBinding.recyclerviewArtist);
        mViewModel.fetchLiveData(getContext());
        mViewModel.getListMutableLiveData().observe(this, new Observer<List<Artist>>() {
            @Override
            public void onChanged(List<Artist> artists) {
                mViewModel.setupRecyclerView(mBinding.recyclerviewArtist);
            }
        });
        return mBinding.getRoot();
    }
}