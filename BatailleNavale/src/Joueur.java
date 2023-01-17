
public abstract class Joueur {
	
	protected Equipe eq;
    
    public Joueur(int passage,Nature n, Statut s){
        if(s == Statut.MILITAIRE) {
        	eq = new EqBataillon(n, passage);
        }
        else {
        	eq = new EqPecheur(n, passage);
        }
    }

    protected abstract Point placementNavire(Navire nav,Plateau plateau) throws OccupException, LimiteException;

    public void afficheFlotteJoueur(){
        System.out.print("=== Voici la position des navires de la flotte du Joueur "+this.eq.getOrdre()+" ===\n");
        for(Navire nav : this.eq.getListeNavire()){
            System.out.print("|||| "+nav.toString()+" "+nav.getPos().toString()+" ||||\n");
        }
        System.out.print("\n");
    }
}