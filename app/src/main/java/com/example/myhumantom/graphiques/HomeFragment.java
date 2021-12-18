package com.example.myhumantom.graphiques;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhumantom.aliments.Nutriments;
import com.example.myhumantom.joueur.Joueur;
import com.example.myhumantom.R;
import com.example.myhumantom.utilitaires.Constantes;
import com.example.myhumantom.joueur.FichiersJoueur;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private void setOnClick(Button button, Fragment fragment) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.container,
                        fragment).commit();
            }
        });
    }

    private void travailler() {
        if (Joueur.getInstance().peutTravailler()) {
            getParentFragmentManager().beginTransaction().replace(R.id.container,
                    new StonksFragment()).commit();
        } else {
            if (Joueur.getInstance().estMort()) {
                Toast.makeText(getContext(), "Vous n'avez plus assez d'argent, " +
                                "plus rien à manger dans votre inventaire, " +
                                "plus assez d'énergie pour travailler. VOUS AVEZ PERDU.",
                        Toast.LENGTH_LONG).show();
                Joueur.getInstance().reset();
                getParentFragmentManager().beginTransaction().replace(R.id.container,
                        new HomeFragment()).commit();
                try {
                    FichiersJoueur.sauvegardeAll(getContext());
                } catch (IOException ignored) {}
            } else {
                Toast.makeText(getContext(), "Vous n'avez pas assez d'énergie pour " +
                                "aller travailler, veuillez manger quelque chose.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sauvegarder() {
        try {
            FichiersJoueur.sauvegardeAll(getContext());
            Toast.makeText(getContext(),
                    "Vos données ont bien été sauvegardées.",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getContext(),
                    "Il y a eu un problème lors de la sauvegarde des données, veuillez réessayer.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Joueur joueur = Joueur.getInstance();

        TextView argentTV = view.findViewById(R.id.argent_textView);
        TextView momentTV = view.findViewById(R.id.moment_textView);
        TextView niveauTV = view.findViewById(R.id.niveau_textView);

        argentTV.setText("Argent : " + joueur.getArgent() + "$");
        momentTV.setText(joueur.getMomentDuJourString());
        niveauTV.setText("Level : " + joueur.getLevel() + "\n" +
                "Exp : " + joueur.getExp() + "/" + (joueur.getLevel() * Constantes.COEFF_LEVEL));

        setOnClick(view.findViewById(R.id.manger_button), new MangerFragment());
        setOnClick(view.findViewById(R.id.cuisiner_button), new CuisineFragment());
        setOnClick(view.findViewById(R.id.acheter_button), new SupermarcheFragment());

        Button stonksButton = view.findViewById(R.id.stonks_button);
        stonksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travailler();
            }
        });

        Button afficheStatsButton = view.findViewById(R.id.afficheStats_button);
        afficheStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showClassicAlertDialog(getContext(),"Vos statistiques",
                        Joueur.getInstance().toString(), "OK");
            }
        });

        Button sauvegarderButton = view.findViewById(R.id.sauvegarder_button);
        sauvegarderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sauvegarder();
            }
        });

        Button intervallesButton = view.findViewById(R.id.intervalles_button);
        intervallesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showClassicAlertDialog(getContext(),
                        "Intervalles à respecter pour pouvoir travailler dans de bonnes conditions",
                        Nutriments.intervallesToString(), "OK");
            }
        });

        return view;
    }
}
