package com.itparis.b3.meetsik.mains;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itparis.b3.meetsik.beans.Annonce;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.itparis.b3.meetsik.basics.AnnonceAdapter;
import com.itparis.b3.meetsik.R;
import com.itparis.b3.meetsik.beans.Auteur;
import com.itparis.b3.meetsik.services.GetAllAnnonceService;

/**
 * Created by Bouveti on 11/03/2015.
 */
public class MainFragment extends Fragment {

    ImageView logo;
    TextView date;
    ListView liste;
    AnnonceAdapter adapter;
    GetAllAnnonceService service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, null);

        Date today = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String dateToday = f.format(today);

        Intent i = new Intent(getActivity(), GetAllAnnonceService.class);
        getActivity().startService(i);


        logo = (ImageView) view.findViewById(R.id.logo);
        date = (TextView) view.findViewById(R.id.date);

        date.setText(dateToday);
        logo.setImageResource(R.drawable.logo);

        liste = (ListView) view.findViewById(R.id.listAnnonce);

        service = new GetAllAnnonceService();


        adapter = new AnnonceAdapter(getActivity(),0);

        if(service.getIsDataLoaded())adapter.setAll((java.util.Collection<Annonce>) service.getAllAnnonces());
        else {

            ArrayList<Annonce> saved = new ArrayList<Annonce>();
            Annonce a1 = new Annonce();
            Annonce a2 = new Annonce();

            Auteur durand = new Auteur();
            durand.seteMail("durand@ece.fr");

            a1.setId(4);
            a1.setNom("Gratte");
            a1.setPrix(2);
            a1.setDate("2014-10-11T15:15:00+0200");
            a1.setCategorie("Vente");
            a1.setAuteur(durand);

            a2.setId(1);
            a2.setNom("Guitare Fender");
            a2.setPrix(60);
            a2.setDate("2015-02-03T11:29:12+0100");
            a2.setCategorie("Vente");
            a2.setAuteur(durand);

            saved.add(a1);
            saved.add(a2);
            adapter.setAll(saved);

        }

        if(adapter != null)liste.setAdapter(adapter);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object annonce = liste.getItemAtPosition(position);

                DetailFragment secondFrag = new DetailFragment();
                Bundle arg = new Bundle();
                arg.putSerializable("annonce", (Annonce) annonce);
                secondFrag.setArguments(arg);

                ((MainActivity) getActivity()).showFragment(secondFrag);


            }
        });

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
