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
import org.maktab.musucplayer.model.Song;

import java.util.List;

public class MusicListAdapter extends Adapter<MusicListAdapter.MusicListHolder> {

    private List<Song> mSongs;
    private Context mContext;

    public static MusicListAdapter newInstance(Context context, List<Song> songs) {
        MusicListAdapter musicListAdapter = new MusicListAdapter();
        musicListAdapter.mSongs = songs;
        musicListAdapter.mContext = context.getApplicationContext();
        return musicListAdapter;
    }

    @NonNull
    @Override
    public MusicListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_music, parent, false);
        return new MusicListHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MusicListHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    class MusicListHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewTitle;
        private TextView mTextViewArtist;
        private TextView mTextViewAlbum;

        public MusicListHolder(@NonNull View itemView) {
            super(itemView);
            findView(itemView);

        }

        private void initView(int position) {
            mTextViewTitle.setText(mSongs.get(position).getStringTitle());
            mTextViewArtist.setText(mSongs.get(position).getStringArtist());
            mTextViewAlbum.setText(mSongs.get(position).getStringAlbum());

        }

        private void findView(View view) {
            mTextViewTitle = view.findViewById(R.id.textView_title);
            mTextViewArtist = view.findViewById(R.id.textView_artist);
            mTextViewAlbum = view.findViewById(R.id.textView_album);
        }

        public void bind(int position) {
            initView(position);
        }
    }
}
