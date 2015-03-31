package com.itparis.b3.meetsik.mains;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itparis.b3.meetsik.beans.Annonce;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.itparis.b3.meetsik.basics.AnnonceAdapter;
import com.itparis.b3.meetsik.R;

/**
 * Created by Bouveti on 11/03/2015.
 */
public class MainFragment extends Fragment {

    ImageView logo;
    TextView date;
    ListView liste;
    AnnonceAdapter adapter;

    @Override
    public void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        adapter = new AnnonceAdapter(getActivity());
        if(savedInstanceState != null){
            ArrayList<Annonce> resultat = (ArrayList<Annonce>)savedInstanceState.getSerializabme("result");
            if(resultat != null)adapter.setAll(resultat);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, null);

        Date today = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String dateToday = f.format(today);

        logo = (ImageView) view.findViewById(R.id.logo);
        date = (TextView) view.findViewById(R.id.date);

        date.setText(dateToday);
        logo.setImageResource(R.drawable.logo);

        liste = (ListView) view.findViewById(R.id.listAnnonce);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
