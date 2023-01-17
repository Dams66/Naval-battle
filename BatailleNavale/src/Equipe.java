import java.util.ArrayList;

public abstract class Equipe {

	//Attributs
	
		protected ArrayList<Navire> listeNavire;
		protected Commande myCommande;
		protected Statut myStatut;
		protected Nature myNature;
		protected int numPassage;
		
		//Constructeur
		public Equipe(Nature n, Statut s, int numPass){
			myNature = n;
			myCommande = null;
			myStatut = s;
			numPassage = numPass;
			listeNavire = new ArrayList<Navire>();
		}

		//Méthodes
		
		public Statut getStatut() {
			return this.myStatut;
		}
		
		public Commande getCommande() {
			return this.myCommande;
		}

		public String toString() {
			return "Equipe "+numPassage+" (" + myStatut + "," + myNature + "), avec " + listeNavire;
		}
	
		public void addNavire(Navire nav) {
			listeNavire.add(nav) ;
		}

		public void removeNavire(Navire nav){
			if(isNavireInListe(nav)){
				this.getListeNavire().remove(nav);
			}
		}
		
		public void placerNavire(Joueur j,Plateau plateau) {
			System.out.print("====== Joueur "+j.eq.getOrdre()+" place ses navires ======\n");
			for (Navire nav : j.eq.listeNavire) {
				boolean hasError;
				do {
					hasError = false;
					try {
						Point point = j.placementNavire(nav, plateau);
						nav.setPos(point);
					} catch (OccupException | LimiteException e) {
						hasError = true;
						System.out.println("Erreur: " + e.getMessage());
					}
				} while (hasError);
			}
		}
		
		protected  abstract void remplirListeNavire(int NumPassageEquipe);
		
		public ArrayList<Navire> getListeNavire(){
			return listeNavire;
		}

		public Navire getNavire(int idNavire){
			for (Navire nav : this.getListeNavire()){
				if(nav.getIdent() == idNavire) {
					return nav;
				}
			}
			return null ;
		}

		public boolean isNavireInListe(Navire nav){
			return this.getListeNavire().contains(nav);
		}
		
		public int getOrdre() {
			return numPassage;
		}
}
