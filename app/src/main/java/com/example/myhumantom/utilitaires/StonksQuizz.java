package com.example.myhumantom.utilitaires;

import com.example.myhumantom.joueur.Joueur;

public class StonksQuizz {
    public static final int MAX_CAGNOTTE = 195;
    public static final int GAIN_BONNE_REP = 13;
    public static final int TEMPS_MAX = 16; //en secondes

    private static String[] questionEnCours = new String[10];

    public static final String[][] QUESTIONS_4REPONSES = {
    //Format : {Question, BonneRep, FausseRep, FausseRep, FausseRep
            //{"Question", "Rep1 (Bonne)", "Rep2", "Rep3", "Rep4"}

            {"Parmi ces quatre aliments, lequel fait partie des produits laitiers ?", "Les yaourts", "Les oeufs", "Les pâtes", "Le poisson"},
            {"Quel est le petit-déjeuner le plus équilibré pour être en forme toute la journée ?", "Du pain, un verre de lait, une orange", "Un croissant, une barre de chocolat, un verre de jus d'orange", "Un bol de chocolat chaud et des céréales", "Peu importe, le petit-déjeuner n'est pas important"},
            {"Parmi ces quatre aliments, lesquels apportent des protéines animales ?", "Le fromage", "Le riz", "La pomme", "Les céréales"},
            {"Si possible, combien de temps avant l'effort le dernier repas complet doit-il être pris ?", "3 heures", "Juste avant l'effort", "6 heures", "1 heure"},
            {"Quel repas est le plus important de la journée ?", "Le petit-déjeuner", "Le déjeuner", "Le dîner", "Le goûter"},
            {"Lequel de ces menus est le plus équilibré ?", "Carottes rapées + Steak pâtes + fromage + mousse chocolat", "Salade de pommes de terre + spaghettis bolognaise + gâteau de semoule", "Saucisson + Pizza + glace", "Soupe de légumes + gratin de courgettes + compote de poires"},
            {"Combien de fois dans la journée doit-on manger des aliments protéiques ? (type viande, poisson ... )", "1 à 2 fois", "3 fois", "4 à 5 fois", "On ne doit pas en manger du tout"},
            {"Dans quel aliment ne trouve-t-on pas de vitamines ?", "Les bonbons", "Les pâtes", "Les légumes", "Le poisson"},
            {"Quel aliment doit-on retrouver au moins une fois à chaque repas (midi et soir) ?", "Une crudité (fruit ou légume)", "Un gâteau", "De la viande", "Des oeufs"},
            {"Combien faut-il prendre de repas par jour ?", "4", "2", "3", "5"},
            {"De quelle famille d'aliments fait partie le pain?", "Les féculents", "Les fruits et légumes", "Les produits laitiers", "Viandes"},
            {"Pour avoir une alimentation équilibrée, il faut...", "Manger de tout et en assez grande quantité", "Consommer uniquement des fruits et des produits laitiers", "Manger uniquement de la viande", "Manger le moins possible"},
            {"Boire de l'eau est...", "Indispensable", "Inutile", "Facultatif", "Mauvais pour la santé"},
            {"Les pâtes et le riz sont...", "Des féculents", "De la viande", "Des produits laitiers", "Des sucreries",},
            {"Les éléments gras sont indispensables pour une bonne santé", "Vrai,mais il ne faut pas en consommer de grandes quantités", "Vrai, et on doit en consommer beaucoup", "Vrai, mais on ne doit pas en manger tous les jours pour ne pas grossir", "Faux"},
            {"Les pommes de terres font partie du groupe des...", "Féculents", "Fruits", "Légumes", "Produits laitiers"},
            {"Manger des fraises en hiver...", "Non, car c’est meilleur pour l’environnement de manger des fruits et légumes de saison", "Bah… si elles viennent d’un pays lointain dans lequel c’est la saison des fraises…", "Oui, car il est important de manger varié toute l’année", "/"},
            {"Combien faut-il manger de fruits et légumes par jour ?", "5", "6", "3", "4"},
            {"Passer à une alimentation biologique permet...", "De réduire les impacts négatifs sur la santé", "De ne manger que des graines", "De manger uniquement local", "/"},
            {"Un aliment ultra-transformé...", "Est une formulation industrielle contenant de nombreux ingrédients", "Contient peu d’ingrédients", "Est synonyme d’un aliment hyper sain", "/"},
            {"Manger des protéines animales est :", "Une pratique alimentaire qui a un fort impact sur l’environnement", "Sans impact sur l’environnement !", "La seule solution car on ne trouve pas de protéines ailleurs", "/"},
            {"Réfléchir aux modes de cuisson...", "Indispensable pour maintenir la qualité des aliments !", "Pourquoi ? Cela n’affecte en rien la qualité des produits.", "De toute façon, on n’a pas le choix, tout passe au micro-ondes…", "/"},
            {"Le gaspillage alimentaire...", "Un vrai travail d’anticipation sur les commandes et les achats !", "Peu importe la quantité de déchets car de toute façon on trie et on a des poules…", "Un gaspillage inévitable quand on doit gérer de nombreux enfants…", "/"},
            {"L’utilisation de contenants en plastique...", "Surtout pas ! Ce matériau a des effets négatifs sur l’environnement et sur la santé !", "A petites doses… si c’est pour stocker OK, mais pas pour réchauffer…", "C’est pratique, sain et irremplaçable !", "/"},
            {"Il est conseillé de manger 5 fruits et légumes par jour, mais en vrai on peut en consommer à volonté...", "Faux", "Vrai", "/", "/"},
            {"Il est recommandé de boire 1,5 l d’eau par jour, avant, pendant ou après le repas.", "Vrai", "Faux", "/", "/"},
            {"L’huile d’olive est moins calorique que l’huile de colza.", "Faux", "Vrai", "/", "/"},
            {"Il est conseillé de manger des féculents le soir, même si on veut perdre du poids.", "Vrai", "Faux", "/", "/"},
            {"Le blanc d’œuf contient du cholestérol contrairement au jaune d’œuf.", "Faux", "Vrai", "/", "/"},
            {"Combien de fois faut-il manger du poisson par semaine ?", "2", "3", "4", "1"},
            {"Les produits complets sont plus riches en fibres et vitamines que les produits non complets.", "Vrai", "Faux", "/", "/"},
            {"Les noix sont bonnes pour la santé.", "Vrai", "Faux", "/", "/"},
            {"Peu importe l’âge, il suffit de bouger 30 minutes par jour !", "Faux", "Vrai", "/", "/"},
            {"Pour ne pas prendre de poids, il vaut mieux manger un biscuit bio qu’un biscuit non bio.", "Faux", "Vrai", "/", "/"},
            {"Au bar, il y a autant d’alcool dans un verre de vin que dans un verre de whisky.", "Vrai", "Faux", "/", "/"},
            {"Il vaut mieux boire de l’eau en bouteille que de l’eau du robinet.", "Faux", "Vrai", "/", "/"},
            {"Il y a autant de sucre dans 1 verre de coca que dans un verre de jus de fruit.", "Vrai", "Faux", "/", "/"},
            {"Combien de fois faut-il consommer de légumes secs par semaine ?", "2", "4", "5", "0"},
            {"Sur les étiquettes alimentaires, KJ signifie kilo par Jour.", "Faux", "Vrai", "/", "/"},
            {"Consommez 500g de viande rouge par semaine peut causer le cancer du pancréas.", "Faux", "Vrai", "/", "/"}
    };

    private StonksQuizz() {}

    public static void ajouterArgentJoueur(int valeur) {
        Joueur joueur = Joueur.getInstance();
        joueur.setArgent(joueur.getArgent() + valeur);
    }

    private static boolean estDansTab(int val, int[] tab) {
        for (int v : tab) {
            if (val == v) {
                return true;
            }
        }
        return false;
    }

    private static String[] getQuestionMelange4Reps(int n) {
        String[] res = new String[QUESTIONS_4REPONSES[n].length];
        questionEnCours = QUESTIONS_4REPONSES[n].clone();
        res[0] = questionEnCours[0];

        int rand;
        int[] done = new int[res.length - 1];

        for (int i = 1; i < res.length; i++) {
            do {
                rand = (int) (Math.random() * (res.length - 1)) + 1;
            } while (estDansTab(rand, done));

            done[i-1] = rand;
            res[i] = questionEnCours[rand];
        }
        return res;
    }

    public static String[] getRandomQuestion4Reps() {
        int rand = (int) (Math.random() * StonksQuizz.QUESTIONS_4REPONSES.length);
        return getQuestionMelange4Reps(rand);
    }


    public static boolean verif4Reps(String rep) {
        return questionEnCours[1].equals(rep);
    }

}
