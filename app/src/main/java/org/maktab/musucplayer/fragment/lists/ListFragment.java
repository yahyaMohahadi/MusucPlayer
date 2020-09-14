package org.maktab.musucplayer.fragment.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.musucplayer.adapter.MusicListAdapter;

public abstract class ListFragment extends Fragment {

    RecyclerView mRecyclerViewSongs;

    public abstract MusicListAdapter getMusicAdapter();

    public abstract int getFragmtView();

    public abstract int getRecyclerViewId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmtView(), container, false);
        findView(view);
        intRecyclerVive();
        mRecyclerViewSongs.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public void findView(View view) {
        mRecyclerViewSongs = view.findViewById(getRecyclerViewId());
    }

    private void intRecyclerVive() {
        mRecyclerViewSongs.setAdapter(getMusicAdapter());

    }
}
