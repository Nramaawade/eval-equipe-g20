public class Plateforme {
    private static final int CAPACITE = 100;
    private Compte[] comptes;
    private int nombreComptes;
    private String[] journal;
    private int nombreJournal;
    private long totalFrais;
    private int compteurTransaction;

    public Plateforme() {
        this.comptes = new Compte[CAPACITE];
        this.nombreComptes = 0;
        this.journal = new String[100];
        this.nombreJournal = 0;
        this.totalFrais = 0;
        this.compteurTransaction = 1;
    }

    public int getNombre() {
        return this.nombreComptes;
    }

    public long getTotalFrais() {
        return this.totalFrais;
    }

    public Compte rechercher(String numero) {
        for (int i = 0; i < this.nombreComptes; i++) {
            if (this.comptes[i].getNumero().equals(numero)) {
                return this.comptes[i];
            }
        }
        return null;
    }

    public boolean ajouter(Compte c) {
        if (c == null || this.nombreComptes >= CAPACITE) {
            return false;
        }
        if (this.rechercher(c.getNumero()) != null) {
            return false;
        }
        this.comptes[this.nombreComptes++] = c;
        return true;
    }

    public boolean transferer(String de, String vers, long montant) {
        if (montant <= 0 || de.equals(vers)) {
            return false;
        }
        Compte source = this.rechercher(de);
        Compte destinataire = this.rechercher(vers);
        if (source == null || destinataire == null) {
            return false;
        }

        long frais = source.calculerFrais(montant);
        long totalDebit = montant + frais;

        if (source.getSolde() < totalDebit) {
            return false;
        }

        source.retirer(totalDebit);
        destinataire.deposer(montant);
        this.totalFrais += frais;

        if (this.nombreJournal < this.journal.length) {
            String numTrans = String.format("TX%04d", this.compteurTransaction++);
            this.journal[this.nombreJournal++] = numTrans + " " + de + " -> " + vers + " " + montant + " FCFA (frais " + frais + ")";
        }

        return true;
    }

    public String[] journal() {
        String[] copie = new String[this.nombreJournal];
        for (int i = 0; i < this.nombreJournal; i++) {
            copie[i] = this.journal[i];
        }
        return copie;
    }

    public int[] compterParType() {
        int standard = 0;
        int premium = 0;
        int marchand = 0;
        for (int i = 0; i < this.nombreComptes; i++) {
            Compte c = this.comptes[i];
            if (c instanceof CompteMarchand) {
                marchand++;
            } else if (c instanceof ComptePremium) {
                premium++;
            } else if (c instanceof Compte) {
                standard++;
            }
        }
        return new int[]{standard, premium, marchand};
    }

    public void trierParSolde() {
        for (int i = 1; i < this.nombreComptes; i++) {
            Compte key = this.comptes[i];
            int j = i - 1;
            while (j >= 0 && (this.comptes[j].getSolde() < key.getSolde() || 
                  (this.comptes[j].getSolde() == key.getSolde() && 
                   this.comptes[j].getNumero().compareTo(key.getNumero()) > 0))) {
                this.comptes[j + 1] = this.comptes[j];
                j = j - 1;
            }
            this.comptes[j + 1] = key;
        }
    }

    public Compte[] comptes() {
        Compte[] result = new Compte[this.nombreComptes];
        for (int i = 0; i < this.nombreComptes; i++) {
            result[i] = this.comptes[i];
        }
        return result;
    }

    public Compte[] comptesAuDessus(long seuil) {
        int count = 0;
        for (int i = 0; i < this.nombreComptes; i++) {
            if (this.comptes[i].getSolde() >= seuil) {
                count++;
            }
        }
        Compte[] result = new Compte[count];
        int index = 0;
        for (int i = 0; i < this.nombreComptes; i++) {
            if (this.comptes[i].getSolde() >= seuil) {
                result[index++] = this.comptes[i];
            }
        }
        return result;
    }
}
