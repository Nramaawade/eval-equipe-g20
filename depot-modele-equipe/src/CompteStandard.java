public class CompteStandard extends compte {

    
    public CompteStandard(String numero, String titulaire) {
        super(numero, titulaire);
    }

    public long frais(long montant) {
        return (montant * Parametres.TAUX_FRAIS) / 1000;
    }

    public long plafond() {
        return Parametres.PLAFOND_RETRAIT;
    }

    public String type() {
        return "STD";
    }
}
