import java.util.Random;

public class Jeu {
	
	//Attributs
	
		private boolean fini;
		private Plateau lePlateau;
		private JHumain j1;
		private JHumain j2;
		private JIA j3;
		private int nbJoueurs;
		private int numPerdant;
		private int numGagnant1;
		private int numGagnant2;
		private Random alea;
		
	//Constructeur
	
		public Jeu(int taille, int nbPlayers){
			lePlateau = new Plateau(taille);
			alea = new Random();
			fini = false;
			nbJoueurs = nbPlayers;
			numPerdant=-1;
			numGagnant1=-1;
			numGagnant2=-1;
		}
		
	//Méthodes
		
		public void initJoueurs() {
			int res = alea.nextInt(3);
			switch (res) {
				case 0 : 
					this.j1 = new JHumain(1,Statut.NEUTRE);
					this.j2 = new JHumain(2,Statut.MILITAIRE);
					this.j3 = new JIA(3,Statut.MILITAIRE);
					break;
				case 1 : 
					this.j1 = new JHumain(1,Statut.MILITAIRE);
					this.j2 = new JHumain(2,Statut.NEUTRE);
					this.j3 = new JIA(3,Statut.MILITAIRE);
					break;
				case 2 :
					this.j1 = new JHumain(1,Statut.MILITAIRE);
					this.j2 = new JHumain(2,Statut.MILITAIRE);
					this.j3 = new JIA(3,Statut.NEUTRE);
					break;
				default :
					System.out.println("Erreur de l'aléatoire pour la répartition Role joueurs");
					break;
			}
		}

		public int getPerdant(){
			return this.numPerdant;
		}

		public int getGagnant1(){
		return this.numGagnant1;
	}

		public int getGagnant2(){
		return this.numGagnant2;
	}

		public int VerifFini(Joueur j[]) {
			int res = -1;
			for(int cpt = 0; cpt<3 ;cpt++){
				if(j[cpt].eq.listeNavire.isEmpty()) {
					this.fini = true;
					res = cpt+1;
				}
			}
			return res;
		}


		public void afficheFlotteTerrain(Joueur tab[]){
			Navire nav;
			System.out.print("=========================== Voici la position des navires de chaque joueurs ==========================\n");
			System.out.println("||||       Joueur 1        ||||     ||||       Joueur 2        ||||     ||||       Joueur 3(IA)    ||||");
			for(int cpt = 1; cpt<=4 ;cpt++){
				for (Joueur j : tab) {
					nav = j.eq.getNavire(cpt);
					if (j.eq.isNavireInListe(j.eq.getNavire(cpt))) {
						System.out.print("|||| " + nav.toString() + " " + nav.getPos().toString() + " ||||     ");
					} else {
						System.out.print("||||                       ||||     ");
					}
				}
				System.out.print("\n");
			}
		}
		
		public void tourJoueur(Joueur j[], int num) {
			if(j[num].eq.myNature == Nature.IA) {
				JIA ia = (JIA) j[num];
				ia.tirageAleatoire(this.lePlateau);
				//System.out.println(ia.eq.getCommande().toString());
				ia.eq.getCommande().doAction(this.lePlateau,j);
			}
			else {
				JHumain humain = (JHumain) j[num];
				humain.interrogationParClavier(this.lePlateau);
				//System.out.println(humain.eq.getCommande().toString());
				humain.eq.getCommande().doAction(this.lePlateau,j);
			}
		}

		public boolean setGagnantAndIsEgalite(Joueur j[]) {
			int taille =-1,gagnant1=0,gagnant2=0,cpt;
			for (cpt = 0; cpt<3;cpt++) {
				if (j[cpt].eq.getListeNavire().size()>taille) {
					taille = j[cpt].eq.getListeNavire().size();
					gagnant1 = cpt;
				}else if (j[cpt].eq.getListeNavire().size()==taille){
					gagnant2 = cpt;
				}
			}
			this.numGagnant1 = gagnant1+1;
			this.numGagnant2 = gagnant2+1;
			return (j[gagnant1].eq.getListeNavire().size() == j[gagnant2].eq.getListeNavire().size());
		}
		
		public void jouer(){

			System.out.print("\n====== Debut de la partie de Bataille Navale ======\n\n");

			//Initialise chaque case comme vide et avec deux niveaux de profondeurs
			this.lePlateau.initGrille();
			
			//Donne le role aléatoirement aux deux humains et a l'IA
	        this.initJoueurs();
			Joueur ordreTabJoueurs[]= {j1,j2,j3};
	        
	        //Attribution des navires a chaque equipe
	        this.j1.eq.remplirListeNavire(j1.eq.getOrdre());
	        this.j2.eq.remplirListeNavire(j2.eq.getOrdre());
	        this.j3.eq.remplirListeNavire(j3.eq.getOrdre());
	        
	        //Placement des navires sur la grille pour chaque joueurs

			ordreTabJoueurs[0].eq.placerNavire(ordreTabJoueurs[0],lePlateau);
			this.lePlateau.affichage();
			ordreTabJoueurs[0].afficheFlotteJoueur();

			ordreTabJoueurs[1].eq.placerNavire(ordreTabJoueurs[1],lePlateau);
			this.lePlateau.affichage();
			ordreTabJoueurs[1].afficheFlotteJoueur();

			//L'IA place
			ordreTabJoueurs[2].eq.placerNavire(ordreTabJoueurs[2],lePlateau);
			System.out.print("\n====================== Placement des flottes terminé =====================\n");

			// Chacun joue a tour de role tant qu'il n'y a pas de gagnant
			System.out.print("\n============================== Phase de jeu ==============================\n");
			do{
				int cptTour;
				for(cptTour=0; cptTour<nbJoueurs;cptTour++){
					if(this.fini==false) {
						this.lePlateau.affichage();
						this.afficheFlotteTerrain(ordreTabJoueurs);
						System.out.print("============================== Joueur "+ordreTabJoueurs[cptTour].eq.getOrdre()+" à toi de jouer ==============================\n");
						this.tourJoueur(ordreTabJoueurs,cptTour);
						this.numPerdant=this.VerifFini(ordreTabJoueurs);
					}
				}
			}while(this.fini==false);
			boolean egalite = this.setGagnantAndIsEgalite(ordreTabJoueurs);

			this.lePlateau.affichage();
			this.afficheFlotteTerrain(ordreTabJoueurs);

			System.out.print("\n======== Fin de la partie ========\n");
			System.out.print("Le joueur "+this.getPerdant()+" n'a plus de bateaux\n\n");

			if(!egalite) {
				System.out.print("==================================\n");
				System.out.print("====== Victoire du Joueur " + this.getGagnant1() + " ======\n");
				System.out.print("==================================\n");
			}else{
				System.out.print("==================================\n");
				System.out.print("== Victoire des Joueurs " + this.getGagnant1() +" et "+ this.getGagnant2()+" ==\n");
				System.out.print("==================================\n");
			}

		}



	public static void main(String[] args) throws Exception {

		int taille = 10;
		int nbJoueurs = 3;

		//Créer un jeu avec un plateau de taille 10*10
		Jeu jeu = new Jeu(taille, nbJoueurs);

		jeu.jouer();

	}
	
}
