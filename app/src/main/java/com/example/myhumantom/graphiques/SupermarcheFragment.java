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

import com.example.myhumantom.R;
import com.example.myhumantom.aliments.Nourriture;
import com.example.myhumantom.joueur.Inventaire;
import com.example.myhumantom.utilitaires.Supermarche;

public class SupermarcheFragment extends Fragment {

    private void acheter(int position) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        Nourriture nourriture = Supermarche.STOCK[position];
        alertDialog.setTitle(nourriture.getNom() + ", " + nourriture.getPrix() + "$");
        alertDialog.setMessage(nourriture.getNutriments().toString());
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Acheter",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        achat(position);
                    }
                });
        alertDialog.show();
    }

    private void achat(int position) {
        int res = Supermarche.vendre(position);
        if (res == 1) {
                Toast.makeText(getContext(),
                        "Vous avez bien acheté cet aliment !",
                        Toast.LENGTH_SHORT).show();
        } else if (res == -1) {
            Toast.makeText(getContext(),
                    "Impossible, votre inventaire est rempli (" + Inventaire.TAILLE_MAX_INV + " emplacements max).",
                    Toast.LENGTH_SHORT).show();
        } else if (res == 0) {
            Toast.makeText(getContext(),
                    "Vous n'avez pas assez d'argent.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listview_norritures_simple, container, false);

        ConstraintLayout background = view.findViewById(R.id.background);
        background.setBackgroundResource(R.drawable.supermarche);

        ListView supermarcheLV = view.findViewById(R.id.nourritures_listView);
        ListViewAdapterImageText adapter = new ListViewAdapterImageText(getContext(),
                Supermarche.getTabStockImage(),
                Supermarche.getTabStockNomPrix());
        supermarcheLV.setAdapter(adapter);

        supermarcheLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                acheter(position);
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
