package org.maktab.musucplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.database.SongRepository;
import org.maktab.musucplayer.fragment.lists.ListFragment;
import org.maktab.musucplayer.model.Album;
import org.maktab.musucplayer.model.Artist;
import org.maktab.musucplayer.model.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicListAdapter extends Adapter<MusicListAdapter.MusicListHolder> {

    private List<Song> mSongs;
    private List<Album> mAlbums;
    private List<Artist> mArtists;
    private Context mContext;
    private ListFragment.Callbacks mCallbacks;
    private ListFragment.States mState;


    public static MusicListAdapter newInstance(Context context,
                                               List<Song> songs,
                                               ListFragment.States state,
                                               ListFragment.Callbacks callbacks) {
        MusicListAdapter musicListAdapter = new MusicListAdapter();
        musicListAdapter.mSongs = songs;
        musicListAdapter.mContext = context.getApplicationContext();
        musicListAdapter.mCallbacks = callbacks;
        musicListAdapter.mState = state;
        musicListAdapter.mAlbums = SongRepository.getAlbumFromSongs((ArrayList<Song>) songs);
        musicListAdapter.mArtists = SongRepository.getArtistsFromSongs((ArrayList<Song>) songs);
        return musicListAdapter;
    }

    public void setSongs(List<Song> songs) {
        mSongs = songs;
        mAlbums = SongRepository.getAlbumFromSongs((ArrayList<Song>) songs);
        mArtists = SongRepository.getArtistsFromSongs((ArrayList<Song>) songs);
    }

    @NonNull
    @Override
    public MusicListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = null;
        switch (mState) {
            case ALBUMS: {
                view = inflater.inflate(R.layout.list_album, parent, false);
                break;
            }
            case ARTISTS: {
                view = inflater.inflate(R.layout.list_artisi, parent, false);
                break;
            }
            case MUSICS: {
                view = inflater.inflate(R.layout.list_music, parent, false);
                break;
            }
        }
        return new MusicListHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MusicListHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        switch (mState) {
            case ALBUMS: {
                return mAlbums.size();

            }
            case ARTISTS: {
                return mArtists.size();

            }
            case MUSICS: {
                return mSongs.size();

            }
        }
        return 0;
    }

    class MusicListHolder extends RecyclerView.ViewHolder {

        //music
        private TextView mTextViewTitle;
        private TextView mTextViewArtist;
        private TextView mTextViewAlbum;

        //artisit
        private TextView mTextViewArtistName;
        private TextView mTextViewArtistNumber;

        //album
        private TextView mTextViewAlbumtName;
        private TextView mTextViewAlbumNumber;

        public MusicListHolder(@NonNull View itemView) {
            super(itemView);
            findViewState(itemView);


        }

        private void findViewState(View view) {
            switch (mState) {
                case ALBUMS: {
                    mTextViewAlbumNumber = view.findViewById(R.id.textView_album_number);
                    mTextViewAlbumtName = view.findViewById(R.id.textView_list_album_name);
                    break;
                }
                case ARTISTS: {
                    mTextViewArtistName = view.findViewById(R.id.textView_artist_artist_name);
                    mTextViewArtistNumber = view.findViewById(R.id.textView_list_artisi_number);
                    break;
                }
                case MUSICS: {
                    mTextViewTitle = view.findViewById(R.id.textView_title);
                    mTextViewArtist = view.findViewById(R.id.textView_tittle);
                    mTextViewAlbum = view.findViewById(R.id.textView_album);
                    break;
                }
            }
        }

        public void bind(final int position) {
            switch (mState) {
                case ALBUMS: {
                    initItemViewAlbum(position);
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mCallbacks.itemCalled(mState, mAlbums.get(position).getStringAlbumName());
                        }
                    });
                    break;
                }
                case ARTISTS: {
                    initItemViewArtisi(position);
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mCallbacks.itemCalled(mState, mArtists.get(position).getStringArtistName());
                        }
                    });
                    break;
                }
                case MUSICS: {
                    initItemViewMusic(position);
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mCallbacks.itemCalled(mState, String.valueOf(mSongs.get(position).getIntId()));
                        }
                    });
                    break;
                }
            }


        }


        private void initItemViewMusic(final int position) {

            mTextViewTitle.setText(mSongs.get(position).getStringTitle());
            mTextViewArtist.setText(mSongs.get(position).getStringArtist());
            mTextViewAlbum.setText(mSongs.get(position).getStringAlbum());


        }

        private void initItemViewAlbum(int position) {
            mTextViewAlbumtName.setText(mAlbums.get(position).getStringAlbumName());
            mTextViewAlbumNumber.setText(String.valueOf(mAlbums.get(position).getSongAlbum().size()));
        }

        private void initItemViewArtisi(int position) {
            mTextViewArtistName.setText(mArtists.get(position).getStringArtistName());
            mTextViewArtistNumber.setText(String.valueOf(mArtists.get(position).getSongArtist().size()));
        }
    }

}
