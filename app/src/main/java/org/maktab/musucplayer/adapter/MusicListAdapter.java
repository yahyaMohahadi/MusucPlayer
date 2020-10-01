package org.maktab.musucplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    //todo make it a line fore showing live
    public static final int LIMIT_CHARE_IN_VIEW = 9;
    public static final int LIMIT_CHARE_IN_Tiitle = 45;


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
        private ImageView mImageViewMusic;

        //artisit
        private TextView mTextViewArtistName;
        private TextView mTextViewArtistNumber;
        private ImageView mImageViewArtist;

        //album
        private TextView mTextViewAlbumtName;
        private TextView mTextViewAlbumNumber;
        private TextView mTextViewAlbumArtist;
        private ImageView mImageViewAlbum;

        public MusicListHolder(@NonNull View itemView) {
            super(itemView);
            findViewState(itemView);


        }

        private void findViewState(View view) {
            switch (mState) {
                case ALBUMS: {
                    mTextViewAlbumNumber = view.findViewById(R.id.textView_album_music_number);
                    mTextViewAlbumtName = view.findViewById(R.id.textView_list_album_name);
                    mTextViewAlbumArtist = view.findViewById(R.id.textView_list_album_artist_name);
                    mImageViewAlbum = view.findViewById(R.id.imageView_list_album);
                    break;
                }
                case ARTISTS: {
                    mTextViewArtistName = view.findViewById(R.id.textView_artist_artist_name);
                    mTextViewArtistNumber = view.findViewById(R.id.textView_list_artisi_number);
                    mImageViewArtist = view.findViewById(R.id.imageView_list_artist);
                    break;
                }
                case MUSICS: {
                    mTextViewTitle = view.findViewById(R.id.textView_list_title);
                    mTextViewArtist = view.findViewById(R.id.textView_list_artist);
                    mImageViewMusic = view.findViewById(R.id.imageView_list_music_image);
                    break;
                }
            }
        }

        public void bind(final int position) {
            switch (mState) {
                case ALBUMS: {
                    initItemViewAlbum(position);
                    itemView.setOnClickListener(
                            new View.OnClickListener() {
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
            mTextViewTitle.setText(limitString(mSongs.get(position).getStringTitle(),LIMIT_CHARE_IN_Tiitle));
            mTextViewArtist.setText(mSongs.get(position).getStringArtist());
            mImageViewMusic.setImageBitmap(mSongs.get(position).getImageMusicSize(mContext));
        }


        private void initItemViewAlbum(int position) {
            mTextViewAlbumtName.setText(limitString(mAlbums.get(position).getStringAlbumName(),LIMIT_CHARE_IN_VIEW));
            mTextViewAlbumNumber.setText(limitString(String.valueOf(mAlbums.get(position).getSongAlbum().size()),LIMIT_CHARE_IN_VIEW));
            mTextViewAlbumArtist.setText(limitString(mArtists.get(position).getStringArtistName(),LIMIT_CHARE_IN_VIEW));
            mImageViewAlbum.setImageBitmap(mAlbums.get(position).getSongAlbum().get(0).getImageSong(mContext));
        }

        private void initItemViewArtisi(int position) {
            mTextViewArtistName.setText(limitString(mArtists.get(position).getStringArtistName(),LIMIT_CHARE_IN_VIEW));
            mTextViewArtistNumber.setText(limitString(String.valueOf(mArtists.get(position).getSongArtist().size()),LIMIT_CHARE_IN_VIEW));
            mImageViewArtist.setImageBitmap(mArtists.get(position).getSongArtist().get(0).getImageSong(mContext));
        }
    }

    public static String limitString(String string,int limit) {
        return string.length() > limit ? string.substring(0, limit)+"..." : string;
    }

}
