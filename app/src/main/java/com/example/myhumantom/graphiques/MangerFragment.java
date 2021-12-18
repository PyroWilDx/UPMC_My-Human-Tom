package com.example.myhumantom.graphiques;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.myhumantom.aliments.Nourriture;
import com.example.myhumantom.joueur.Joueur;
import com.example.myhumantom.R;

public class MangerFragment extends Fragment {

    private ListView mangerLV;

    private void refreshLV() {
        ListViewAdapterImageText adapter = new ListViewAdapterImageText(getContext(),
                Joueur.getInstance().getInventaire().getTabImage(),
                Joueur.getInstance().getInventaire().getTabNom());
        mangerLV.setAdapter(adapter);
    }

    private void manger(int position) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        Nourriture nourriture = Joueur.getInstance().getInventaire().getNourritureFromPosition(position);
        alertDialog.setTitle(nourriture.getNom());
        alertDialog.setMessage(nourriture.getNutriments().toString());
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Manger",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Joueur.getInstance().manger(position);
                        Toast.makeText(getContext(), "Miam", Toast.LENGTH_SHORT).show();
                        refreshLV();
                    }
                });
        alertDialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listview_norritures_simple, container, false);

        ConstraintLayout background = view.findViewById(R.id.background);
        background.setBackgroundResource(R.drawable.manger);

        mangerLV = view.findViewById(R.id.nourritures_listView);
        refreshLV();

        mangerLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                manger(position);
            }
        });

        view.findViewById(R.id.home_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.container,
                        new HomeFragment()).commit();
            }
        });

        return view;
    }
}
