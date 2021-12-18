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

import com.example.myhumantom.joueur.Joueur;
import com.example.myhumantom.R;
import com.example.myhumantom.exceptions.DejaCuitException;
import com.example.myhumantom.exceptions.NonComposableException;
import com.example.myhumantom.exceptions.NonCuisinableException;

public class CuisineFragment extends Fragment {

    private ListView cuisineLV;
    private int lastPosition = -1;

    private void refreshLV() {
        ListViewAdapterImageText adapter = new ListViewAdapterImageText(getContext(),
                Joueur.getInstance().getInventaire().getTabImage(),
                Joueur.getInstance().getInventaire().getTabNom());
        cuisineLV.setAdapter(adapter);
    }

    private void choixComposer(int position) {
        lastPosition = position;
        Toast.makeText(getContext(), "Choisissez un autre aliment pour le composer avec celui-là",
                Toast.LENGTH_SHORT).show();
    }

    private void composer(int position) {
        if (position == lastPosition) {
            Toast.makeText(getContext(), "Impossible de composer un aliment avec lui-même.",
                    Toast.LENGTH_SHORT).show();
        } else {
            try {
                Joueur.getInstance().composer(lastPosition, position);
                Toast.makeText(getContext(), "Vous avez composé ces aliments avec merveille !",
                        Toast.LENGTH_SHORT).show();
                refreshLV();
            } catch (NonComposableException e) {
                Toast.makeText(getContext(), e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            } finally {
                lastPosition = -1;
            }
        }
    }

    private void cuisiner(int position) {
        try {
            Joueur.getInstance().cuisiner(position);
            Toast.makeText(getContext(), "Vous avez cuisiné cet aliment avec merveille !",
                    Toast.LENGTH_SHORT).show();
            refreshLV();
        } catch (NonCuisinableException | DejaCuitException e) {
            Toast.makeText(getContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void cuisinerOuComposer(int position) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Voulez-vous cuisiner ou composer cet alilment ?");
        alertDialog.setMessage("Ces deux procédés augmentent les nutriments que donnent vos aliments !");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cuisiner",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        cuisiner(position);
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Composer",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choixComposer(position);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listview_norritures_simple, container, false);

        ConstraintLayout background = view.findViewById(R.id.background);
        background.setBackgroundResource(R.drawable.cuisine);

        cuisineLV = view.findViewById(R.id.nourritures_listView);
        refreshLV();

        cuisineLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (lastPosition == -1) {
                    cuisinerOuComposer(position);
                } else {
                    composer(position);
                }
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
