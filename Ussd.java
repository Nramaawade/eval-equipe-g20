public class Ussd {


   
    public static boolean numeroValide(String n) {

        if (n == null || n.length() != 9) {
            return false;
        }

        if (n.charAt(0) != '7') {
            return false;
        }

        char deuxieme = n.charAt(1);

        if (deuxieme != '0' && deuxieme != '5'
                && deuxieme != '6' && deuxieme != '7'
                && deuxieme != '8') {
            return false;
        }

        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);

            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }


    
    public static String normaliser(String s) {

        if (s == null) {
            return null;
        }

        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c != ' ' && c != '.' && c != '-') {
                resultat.append(c);
            }
        }

        String numero = resultat.toString();

        if (numero.startsWith("+221")) {
            numero = numero.substring(4);
        } else if (numero.startsWith("00221")) {
            numero = numero.substring(5);
        }

        if (!numeroValide(numero)) {
            return null;
        }

        return numero;
    }


    public static String masquer(String n) {

        if (!numeroValide(n)) {
            return null;
        }

        return n.substring(0, 2) + "*****" + n.substring(7);
    }


   
    public static long lireMontant(String s) {

        if (s == null || s.length() < 1 || s.length() > 9) {
            return -1;
        }

        long montant = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c < '0' || c > '9') {
                return -1;
            }

            montant = montant * 10 + (c - '0');
        }

        return montant;
    }


    
    public static String formaterMontant(long m) {

        String nombre = String.valueOf(m);
        StringBuilder resultat = new StringBuilder();

        int debut = 0;

        if (nombre.charAt(0) == '-') {
            resultat.append('-');
            debut = 1;
        }

        for (int i = debut; i < nombre.length(); i++) {

            if (i > debut && (nombre.length() - i) % 3 == 0) {
                resultat.append(' ');
            }

            resultat.append(nombre.charAt(i));
        }

        return resultat.toString();
    }



    public static String[] decouper(String code) {

        if (code == null || code.length() < 4) {
            return null;
        }

        if (code.charAt(0) != '*'
                || code.charAt(code.length() - 1) != '#') {
            return null;
        }

        int nombreChamps = 1;

        for (int i = 1; i < code.length() - 1; i++) {
            if (code.charAt(i) == '*') {
                nombreChamps++;
            }
        }

        String[] champs = new String[nombreChamps];

        int debut = 1;
        int index = 0;

        for (int i = 1; i < code.length(); i++) {

            if (code.charAt(i) == '*' || code.charAt(i) == '#') {

                if (i == debut) {
                    return null;
                }

                champs[index] = code.substring(debut, i);

                index++;
                debut = i + 1;
            }
        }

        return champs;
    }



    public static String executer(Plateforme p, String appelant, String code) {

        String[] champs = decouper(code);

        if (champs == null) {
            return "Code invalide";
        }

        if (!champs[0].equals("144")) {
            return "Service inconnu";
        }

        if (p == null || p.rechercher(appelant) == null) {
            return "Compte inconnu";
        }

        if (champs.length < 2) {
            return "Code invalide";
        }

        if (!champs[1].equals("1") && !champs[1].equals("2")) {
            return "Service inconnu";
        }

       
        if (champs[1].equals("2")) {

            if (champs.length != 2) {
                return "Code invalide";
            }

            long solde = p.rechercher(appelant).getSolde();

            return "Solde : " + formaterMontant(solde) + " FCFA";
        }

        // Transfert
        if (champs.length != 4) {
            return "Code invalide";
        }

        String destinataire = normaliser(champs[2]);

        if (destinataire == null) {
            return "Numero invalide";
        }

        long montant = lireMontant(champs[3]);

        if (montant <= 0) {
            return "Montant invalide";
        }

        long fraisAvant = p.getTotalFrais();

        boolean resultat = p.transferer(appelant, destinataire, montant);

        if (!resultat) {
            return "Transfert refuse";
        }

        long frais = p.getTotalFrais() - fraisAvant;

        long solde = p.rechercher(appelant).getSolde();

        return "Transfert de " + formaterMontant(montant)
                + " FCFA vers " + masquer(destinataire)
                + " effectue. Frais : " + formaterMontant(frais)
                + " FCFA Solde : " + formaterMontant(solde)
                + " FCFA";
    }
}

