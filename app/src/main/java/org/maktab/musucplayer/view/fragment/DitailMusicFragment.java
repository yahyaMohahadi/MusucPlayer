package org.maktab.musucplayer.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.adapter.MusicListAdapter;
import org.maktab.musucplayer.databinding.FragmentMusicDitailBinding;
import org.maktab.musucplayer.model.Song;
import org.maktab.musucplayer.repository.Music;


public class DitailMusicFragment extends Fragment {

    private FragmentMusicDitailBinding mBinding;
    private TextView mTextViewTittle;
    private TextView mTextViewArtist;
    private ImageView mImageViewSongDitails;
    private Song mSongCurent;

    public static DitailMusicFragment newInstance() {
        DitailMusicFragment fragment = new DitailMusicFragment();
        Bundle args = new Bundle();
        try {
            fragment.mSongCurent = Music.newInstance().getCurentSong();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_music_ditail,container,false);
        findView(mBinding.getRoot());
        initView();
        return mBinding.getRoot();
    }

    public void updateList() {
        try {
            mSongCurent = Music.newInstance().getCurentSong();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        if (mSongCurent == null) {
            mTextViewArtist.setText("artist");
            mTextViewTittle.setText("tittle");
        } else {
            mTextViewArtist.setText(MusicListAdapter.limitString(mSongCurent.getStringArtist(), MusicListAdapter.LIMIT_CHARE_IN_Tiitle));
            mTextViewTittle.setText(MusicListAdapter.limitString(mSongCurent.getStringTitle(), 25));
            mImageViewSongDitails.setImageBitmap(
                    mSongCurent.getImageSong(getActivity())
            );
            //todo text image
        }
    }

    private void findView(View view) {
        mImageViewSongDitails = view.findViewById(R.id.imageView_detail_cover);
        mTextViewArtist = view.findViewById(R.id.textView_ditail_artist);
        mTextViewTittle = view.findViewById(R.id.textView_ditail_tittle);
    }
}