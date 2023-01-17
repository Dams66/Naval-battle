import java.util.Scanner;

public class Commande {
	
	//Attributs
	
		protected int idNavire;
		protected Action actionChoisie;
		protected Direction directionChoisie;
		protected Equipe equipe;
		protected Point toPos;
		private Scanner sc;
		
	//Constructeur
	
		public Commande(Equipe eq, int id, String a, String d, Point pos){
			equipe = eq;
			idNavire = id;
			setActionChoisie(a);
			setDirectionChoisie(d);
			toPos = pos;
			sc = new Scanner(System.in);
		}
		
	//Méthodes

		public void setActionChoisie(String stra){
			if(stra.compareTo("DEPLACEMENT")==0) {
				actionChoisie = Action.DEPLACEMENT;
			}else if (stra.compareTo("TIR")==0) {
				actionChoisie = Action.TIR;
			}else if(stra.compareTo("PECHE")==0) {
				actionChoisie = Action.PECHE;
			}
		}

		public void setDirectionChoisie(String strd){
			if(strd.compareTo("NORD")==0) {
				directionChoisie = Direction.NORD;
			}else if(strd.compareTo("SUD")==0) {
				directionChoisie = Direction.SUD;
			}else if(strd.compareTo("EST")==0) {
				directionChoisie = Direction.EST;
			}else if(strd.compareTo("OUEST")==0) {
				directionChoisie = Direction.OUEST;
			}
		}

		public Equipe getEquipe() {
			return this.equipe;
		}

		public Point getToPos(){
			return this.toPos;
		}
		
		public int getIdNavire() {
			return this.idNavire;
		}
		
		public Action getActionChoisie() {
			return this.actionChoisie;
		}
		
		public Direction getDirectionChoisie() {
			return this.directionChoisie;
		}

		public void doAction(Plateau plateau, Joueur tab[]){
			boolean isCibleTirProfondeur = false;
			boolean isCibleTirSurface = false;
			String choix;
			if (this.getActionChoisie() == Action.DEPLACEMENT){
				if(this.getEquipe().getNavire(this.getIdNavire()).getType()==TypeNav.SOUSMARIN){
					plateau.getCasePlateau(this.getEquipe().getNavire(this.getIdNavire()).getPos().getX(),this.getEquipe().getNavire(this.getIdNavire()).getPos().getY()).removeUnOccupantProfondeur(this.getEquipe().getNavire(this.getIdNavire()));
					this.getEquipe().getNavire(this.getIdNavire()).seDeplacer(this.getToPos());
					plateau.getCasePlateau(this.getToPos().getX(),this.getToPos().getY()).addUnOccupantProfondeur(this.getEquipe().getNavire(this.getIdNavire()));
				}else{
					plateau.getCasePlateau(this.getEquipe().getNavire(this.getIdNavire()).getPos().getX(),this.getEquipe().getNavire(this.getIdNavire()).getPos().getY()).removeUnOccupantSurface(this.getEquipe().getNavire(this.getIdNavire()));
					this.getEquipe().getNavire(this.getIdNavire()).seDeplacer(this.getToPos());
					plateau.getCasePlateau(this.getToPos().getX(),this.getToPos().getY()).addUnOccupantSurface(this.getEquipe().getNavire(this.getIdNavire()));
				}
				System.out.println("Le "+this.getEquipe().getNavire(this.getIdNavire()).toString()+" se déplace dans la direction "+this.getDirectionChoisie());

			}else if (this.getActionChoisie() == Action.TIR){

				if (plateau.getCasePlateau(this.getToPos().getX(),this.getToPos().getY()).isOccupeSurface()){
					if( !this.getEquipe().isNavireInListe(plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantSurface())){
						isCibleTirSurface = true;
					}
				}
				if (plateau.getCasePlateau(this.getToPos().getX(),this.getToPos().getY()).isOccupeProfondeur()){
					if( !this.getEquipe().isNavireInListe(plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantProfondeur())){
						isCibleTirProfondeur = true;
					}
				}
				if (isCibleTirProfondeur == true && isCibleTirSurface == true) {
					System.out.println("Voulez-vous tirer en surface pour toucher le " + plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantSurface().toString() + " ou en profondeur pour toucher le" + plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantProfondeur().toString() + " (PROFONDEUR, SURFACE) ?");
					do {
						choix = sc.nextLine();
						if (choix.compareTo("SURFACE") != 0 && choix.compareTo("PROFONDEUR") != 0) {
							System.out.println("Veuillez rentrer une commande correcte");
						}
					} while (choix.compareTo("SURFACE") != 0 && choix.compareTo("PROFONDEUR") != 0);

					switch (choix) {
						case "SURFACE":
							System.out.println("Tir de barrage sur le " + plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantSurface().toString());
							//remove de la liste et du plateau
							for (int cpt = 0; cpt < tab.length; cpt++) {
								tab[cpt].eq.removeNavire(plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantSurface());
							}
							plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).removeUnOccupantSurface(plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantSurface());
							break;
						case "PROFONDEUR":
							System.out.println("Tir de barrage sur le " + plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantProfondeur().toString());
							//remove de la liste et du plateau
							for (int cpt = 0; cpt < tab.length; cpt++) {
								tab[cpt].eq.removeNavire(plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantProfondeur());
							}
							plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).removeUnOccupantProfondeur(plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantProfondeur());
							break;
					}

				}else if(isCibleTirProfondeur == true && isCibleTirSurface == false){
					System.out.println("Tir de barrage sur le "+plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantProfondeur().toString());
					//remove de la liste et du plateau
					for (int cpt=0; cpt<tab.length;cpt++){
						tab[cpt].eq.removeNavire(plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantProfondeur());
					}
					plateau.getCasePlateau(this.getToPos().getX(),this.getToPos().getY()).removeUnOccupantProfondeur(plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantProfondeur());
				}else if (isCibleTirSurface == true && isCibleTirProfondeur == false){
					System.out.println("Tir de barrage sur le "+plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantSurface().toString());
					//remove de la liste et du plateau
					for (int cpt=0; cpt<tab.length;cpt++){
						tab[cpt].eq.removeNavire(plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantSurface());
					}
					plateau.getCasePlateau(this.getToPos().getX(),this.getToPos().getY()).removeUnOccupantSurface(plateau.getCasePlateau(this.getToPos().getX(), this.getToPos().getY()).getOccupantSurface());
				}else{
					System.out.println("Le bombardement n'a pas fait mouche");
				}


			}else if(this.getActionChoisie() == Action.PECHE){//Peche
				System.out.print("Le "+this.getEquipe().getNavire(this.getIdNavire()).toString()+" déploie son filet de peche");
				if(plateau.getCasePlateau(this.getEquipe().getNavire(this.getIdNavire()).getPos().getX(),this.getEquipe().getNavire(this.getIdNavire()).getPos().getY()).isOccupeProfondeur()){
					plateau.getCasePlateau(this.getEquipe().getNavire(this.getIdNavire()).getPos().getX(),this.getEquipe().getNavire(this.getIdNavire()).getPos().getY()).getOccupantProfondeur().setEndommagé();
					System.out.println( " et touche le "+plateau.getCasePlateau(this.getEquipe().getNavire(this.getIdNavire()).getPos().getX(),this.getEquipe().getNavire(this.getIdNavire()).getPos().getY()).getOccupantProfondeur().toString()+" aux coordonnées "+plateau.getCasePlateau(this.getEquipe().getNavire(this.getIdNavire()).getPos().getX(),this.getEquipe().getNavire(this.getIdNavire()).getPos().getY()).getOccupantProfondeur().getPos().toString());
				}
				else{
					System.out.println(" mais la peche n'est pas fructueuse : aucun sous-marin endommagé");
				}

			}
		}
		
		public String toString() {
			return "La commande est Equipe :"+this.getEquipe().getOrdre()+" navire : numéro "+this.getIdNavire()+ " action : "+this.getActionChoisie()+" direction : "+this.getDirectionChoisie()+" position : "+this.getToPos().toString();
		}
	
}
