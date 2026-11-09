public abstract class compte {
    private String numero;
    private String titulaire;
    private long solde=0;

    public compte(  String numero, String titulaire,long solde){
    
         this.numero= numero;
          this.titulaire=titulaire;
         this.solde=solde;

    }


    }

    public String getNumero() {
        return numero;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public long getSolde() {
        return solde;
    }

    }

    public abstract long frais(long montant);
    public abstract long plafond();
    public abstract String type();

    public boolean deposer(long montant) {
        if (montant <= 0) {
            return false;
        }
        solde += montant;
        return true;
    }

    public boolean retirer(long montant) {
        if (montant <= 0 || montant > plafond() || (montant + frais(montant)) > solde) {
            return false;
        }
        solde -= (montant + frais(montant));
        return true;
    }

    @Override
    public String toString() {
        return "[" + type() + "] " + numero + " " + titulaire + " " + solde + " FCFA";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Compte autre = (Compte) obj;
        return numero != null && numero.equals(autre.numero);
    }

    @Override
    public int hashCode() {
        return numero != null ? numero.hashCode() : 0;
    }
}
    
} 

    

