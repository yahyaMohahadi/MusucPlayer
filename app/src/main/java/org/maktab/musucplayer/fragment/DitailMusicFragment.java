package org.maktab.musucplayer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.model.Song;


public class DitailMusicFragment extends Fragment {

    private TextView mTextViewTittle;
    private TextView mTextViewArtist;
    private ImageView mImageViewSongDitails;
    private Song mSongCurent;

    public static DitailMusicFragment newInstance(Song song) {
        DitailMusicFragment fragment = new DitailMusicFragment();
        Bundle args = new Bundle();
        fragment.mSongCurent = song;
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
        View view = inflater.inflate(R.layout.fragment_music_ditail, container, false);
        findView(view);
        initView();
        return view;
    }

    public void updateSong(Song songCurent) {
        mSongCurent = songCurent;

    }

    private void initView() {
        if (mSongCurent == null) {
            mTextViewArtist.setText("artist");
            mTextViewTittle.setText("tittle");
        } else {
            mTextViewArtist.setText(mSongCurent.getStringArtist());
            mTextViewTittle.setText(mSongCurent.getStringTitle());
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