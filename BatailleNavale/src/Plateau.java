
public class Plateau {
	
	//Attributs
	
		private int myTaille;
		private CasePlateau matrice[][];
		
	//Constructeur
	
		public Plateau(int taille){
			myTaille = taille;
			matrice = new CasePlateau[taille][taille];
		}
		
		
	//Méthodes
		
		public void initGrille() {
			int cptL, cptC ;
			for(cptL = 0 ;cptL < this.myTaille; cptL ++) {
				for(cptC = 0 ;cptC < this.myTaille; cptC ++) {
					this.matrice[cptL][cptC]= new CasePlateau();
				}
			}
		}
		
		public void affichage() {
			int cptL, cptC ;
			System.out.print("============ Plateau de jeu =============\n\n");
			System.out.print(" ");
			for(cptL = 0 ;cptL < this.myTaille; cptL ++) {
				System.out.print("  "+cptL+" ");
			}
			System.out.print(" \n");
			for(cptL = 0 ;cptL < this.myTaille; cptL ++) {
				System.out.print(cptL+"|");
				for(cptC = 0 ;cptC < this.myTaille; cptC ++) {
					this.matrice[cptL][cptC].afficheCase();
					System.out.print("|");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
		
		public int getTaille() {
			return this.myTaille;
		}
		
		public CasePlateau getCasePlateau(int nl, int nc) {
			return this.matrice[nc][nl];
		}
		
		public String toString() {
			return "";
		}
	
}
