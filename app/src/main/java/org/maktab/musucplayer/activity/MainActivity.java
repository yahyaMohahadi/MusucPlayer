package org.maktab.musucplayer.activity;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import org.maktab.musucplayer.R;
import org.maktab.musucplayer.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();

            tr.add(R.id.main_fragment_place, MainFragment.newInstance()).commit();

    }
}