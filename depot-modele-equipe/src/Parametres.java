/**
 * Paramètres de l'équipe. Remplissez GROUPE et MEMBRES au checkpoint 0,
 * puis ne modifiez plus rien : les autres valeurs sont calculées à partir de GROUPE.
 */
public final class Parametres {
    public static final int GROUPE = 20;                                   // numéro donné par l'enseignant
    public static final String[] MEMBRES = 
        "A : Prénom Nom (matricule)",Ndeye rama Wade
        "B : Prénom Nom (matricule)", Mariama Kane,
        "C : Prénom Nom (matricule)"Adama Kane
    };

    public static final int TAUX_FRAIS = 10 + GROUPE % 6;                 // pour mille (10 = 1 %)
    public static final long PLAFOND_RETRAIT = 150_000 + 10_000 * (GROUPE % 8);   // FCFA
    public static final long SEUIL_PREMIUM = 50_000;                      // FCFA
    public static final int CAPACITE = 20;                                // comptes

    private Parametres() { }
}
