import java.util.Scanner;

 public class JHumain extends Joueur{
	 
	 private Scanner sc;

     public JHumain(int numPassage,Statut s){
         super(numPassage,Nature.HUMAIN,s);
         sc = new Scanner(System.in);
     }

     public Point placementNavire(Navire nav,Plateau plateau) throws OccupException {
    	 int x,y;
    	 if(nav.getType() == TypeNav.SOUSMARIN) {
			 System.out.print("====== Placement du "+nav.toString()+" ======\n");
    		 do {
    			 
    			 do {
    				 System.out.print("Saisir la coordonée horizontale entre O et "+(plateau.getTaille()-1)+" :\n");
        	         x = sc.nextInt();
					 if(x>=plateau.getTaille() || x < 0 )
						 System.out.print("Veuillez rentrer une valeur correcte\n");
				 }while(x>=plateau.getTaille() || x < 0 );
				 do {
        	         System.out.print("Saisir la coordonée verticale entre O et "+(plateau.getTaille()-1)+" :\n");
        	         y = sc.nextInt();
        	         if(y>=plateau.getTaille() || y<0 )
        	        	 System.out.print("Veuillez rentrer une valeur correcte\n");
    			 }while(y>=plateau.getTaille() || y<0 );
    			 
    			 if (plateau.getCasePlateau(x, y).isOccupeProfondeur())
	            	 throw new OccupException(x,y);
    			 
    		 }while(plateau.getCasePlateau(x, y).isOccupeProfondeur());
    		 plateau.getCasePlateau(x, y).addUnOccupantProfondeur(nav);
    	 }
    	 else {
			 System.out.print("====== Placement du "+nav.toString()+" ======\n");
			 do{
				 do {
					 System.out.print("Saisir la coordonée horizontale entre O et "+(plateau.getTaille()-1)+" :\n");
					 x = sc.nextInt();
					 if(x>=plateau.getTaille() || x < 0 )
						 System.out.print("Veuillez rentrer une valeur correcte\n");
				 }while(x>=plateau.getTaille() || x < 0 );
				 do {
					 System.out.print("Saisir la coordonée verticale entre O et "+(plateau.getTaille()-1)+" :\n");
					 y = sc.nextInt();
					 if(y>=plateau.getTaille() || y<0 )
						 System.out.print("Veuillez rentrer une valeur correcte\n");
				 }while(y>=plateau.getTaille() || y<0 );
				 if (plateau.getCasePlateau(x, y).isOccupeSurface())
					 throw new OccupException(x,y);
    			 
			 }while(plateau.getCasePlateau(x, y).isOccupeSurface());
			 plateau.getCasePlateau(x, y).addUnOccupantSurface(nav);
    	 }
         return new Point(x,y);
     }

	 public void interrogationParClavier(Plateau plateau) {
		 int IdNav;
		 String stra;
		 String strd = "NORD";
		 Point pos = null;
		 boolean hasError;

		 //Choix Navire
		 System.out.println("Veuillez saisir le numéro du navire qui va agir (de 1 a "+(this.eq.getListeNavire().size())+") et 1 étant le premier navire en partant du haut de la liste :");
		 do{
			 IdNav = sc.nextInt();
			 if(IdNav<1 || IdNav >this.eq.getListeNavire().size()){
				 System.out.print("Veuillez rentrer une valeur correcte\n");
			 }
		 }while(IdNav<1 || IdNav >this.eq.getListeNavire().size());
		 IdNav = this.eq.listeNavire.get(IdNav-1).getIdent() ;

		 do{
			 hasError = false;
			 //Choix Action
		 	System.out.print("Quel action voulez-vous faire avec le " + this.eq.getNavire(IdNav).toString());
		 	if (this.eq.getStatut() == Statut.MILITAIRE) {
			 	System.out.print(" (DEPLACEMENT, TIR) :\n");
			 	do{
				 	stra = sc.nextLine();
				 	if(stra.compareTo("DEPLACEMENT")!=0 && stra.compareTo("TIR")!=0){
					 	System.out.print("Veuillez rentrer une commande correcte\n");
				 	}
			 	}while(stra.compareTo("DEPLACEMENT")!=0 && stra.compareTo("TIR")!=0);
		 	}
		 	else {
			 	System.out.print(" (DEPLACEMENT, PECHE) :\n");
			 	do{
				 	stra = sc.nextLine();
				 	if(stra.compareTo("DEPLACEMENT")!=0 && stra.compareTo("PECHE")!=0){
					 	System.out.print("Veuillez rentrer une commande correcte\n");
				 	}
			 	}while(stra.compareTo("DEPLACEMENT")!=0 && stra.compareTo("PECHE")!=0);
		 	}

		 	//Choix Déplacement
		 	if (stra.compareTo("DEPLACEMENT") == 0) {
				if (this.eq.getNavire(IdNav).estEndommagé()== true) {
					hasError= true;
					System.out.println("Impossible de bouger le "+this.eq.getNavire(IdNav).toString()+ " car il est endommagé");
				} else {
					System.out.println("Dans quelle direction voulez-vous faire votre " + stra + " (NORD, SUD, EST, OUEST) :");
					do {
						strd = sc.nextLine();
						if (strd.compareTo("NORD") != 0 && strd.compareTo("SUD") != 0 && strd.compareTo("EST") != 0 && strd.compareTo("OUEST") != 0) {
							System.out.print("Veuillez rentrer une commande correcte\n");
						}
					} while (strd.compareTo("NORD") != 0 && strd.compareTo("SUD") != 0 && strd.compareTo("EST") != 0 && strd.compareTo("OUEST") != 0);
					switch (strd) {
						case "NORD":
							try {
								pos = new Point(this.eq.getNavire(IdNav).getPos().getX(), this.eq.getNavire(IdNav).getPos().getY() - 1);
								if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) {
									throw new LimiteException(this.eq.getNavire(IdNav).getPos().getX(), this.eq.getNavire(IdNav).getPos().getY() - 1);
								} else if (this.eq.getNavire(IdNav).getType() == TypeNav.SOUSMARIN) {
									if (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeProfondeur()) {
										throw new OccupException(pos.getX(), pos.getY());
									}
								} else if (this.eq.getNavire(IdNav).getType() == TypeNav.DESTROYER || this.eq.getNavire(IdNav).getType() == TypeNav.CHALLUTIER) {
									if (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeSurface()) {
										throw new OccupException(pos.getX(), pos.getY());
									}
								}
							} catch (LimiteException | OccupException ex) {
								System.out.println("Erreur: " + ex.getMessage());
								hasError = true;
							}
							break;
						case "SUD":
							try {
								pos = new Point(this.eq.getNavire(IdNav).getPos().getX(), this.eq.getNavire(IdNav).getPos().getY() + 1);
								if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) {
									throw new LimiteException(this.eq.getNavire(IdNav).getPos().getX(), this.eq.getNavire(IdNav).getPos().getY() + 1);
								} else if (this.eq.getNavire(IdNav).getType() == TypeNav.SOUSMARIN) {
									if (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeProfondeur()) {
										throw new OccupException(pos.getX(), pos.getY());
									}
								} else if (this.eq.getNavire(IdNav).getType() == TypeNav.DESTROYER || this.eq.getNavire(IdNav).getType() == TypeNav.CHALLUTIER) {
									if (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeSurface()) {
										throw new OccupException(pos.getX(), pos.getY());
									}
								}
							} catch (LimiteException | OccupException ex) {
								System.out.println("Erreur: " + ex.getMessage());
								hasError = true;
							}
							break;
						case "EST":
							try {
								pos = new Point(this.eq.getNavire(IdNav).getPos().getX() + 1, this.eq.getNavire(IdNav).getPos().getY());
								if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) {
									throw new LimiteException(this.eq.getNavire(IdNav).getPos().getX() + 1, this.eq.getNavire(IdNav).getPos().getY());
								} else if (this.eq.getNavire(IdNav).getType() == TypeNav.SOUSMARIN) {
									if (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeProfondeur()) {
										throw new OccupException(pos.getX(), pos.getY());
									}
								} else if (this.eq.getNavire(IdNav).getType() == TypeNav.DESTROYER || this.eq.getNavire(IdNav).getType() == TypeNav.CHALLUTIER) {
									if (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeSurface()) {
										throw new OccupException(pos.getX(), pos.getY());
									}
								}
							} catch (LimiteException | OccupException ex) {
								System.out.println("Erreur: " + ex.getMessage());
								hasError = true;
							}
							break;
						case "OUEST":
							try {
								pos = new Point(this.eq.getNavire(IdNav).getPos().getX() - 1, this.eq.getNavire(IdNav).getPos().getY());
								if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) {
									throw new LimiteException(this.eq.getNavire(IdNav).getPos().getX() - 1, this.eq.getNavire(IdNav).getPos().getY());
								} else if (this.eq.getNavire(IdNav).getType() == TypeNav.SOUSMARIN) {
									if (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeProfondeur()) {
										throw new OccupException(pos.getX(), pos.getY());
									}
								} else if (this.eq.getNavire(IdNav).getType() == TypeNav.DESTROYER || this.eq.getNavire(IdNav).getType() == TypeNav.CHALLUTIER) {
									if (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeSurface()) {
										throw new OccupException(pos.getX(), pos.getY());
									}
								}
							} catch (LimiteException | OccupException ex) {
								System.out.println("Erreur: " + ex.getMessage());
								hasError = true;
							}
							break;
					}
				}
			}
			 if(stra.compareTo("TIR") == 0){
				 boolean stopPos=false;
				 System.out.println("Dans quelle direction voulez-vous faire votre " + stra + " (NORD, SUD, EST, OUEST) :");
				 do {
					 strd = sc.nextLine();
					 if (strd.compareTo("NORD") != 0 && strd.compareTo("SUD") != 0 && strd.compareTo("EST") != 0 && strd.compareTo("OUEST") != 0) {
						 System.out.print("Veuillez rentrer une commande correcte\n");
					 }
				 } while (strd.compareTo("NORD") != 0 && strd.compareTo("SUD") != 0 && strd.compareTo("EST") != 0 && strd.compareTo("OUEST") != 0);
				 switch (strd) {
					 case "NORD":
						 for(int cpt = 1;cpt<=this.eq.getNavire(IdNav).getPortee();cpt++){
							 if(!stopPos) {
								 try {
									 pos = new Point(this.eq.getNavire(IdNav).getPos().getX(), this.eq.getNavire(IdNav).getPos().getY() -cpt);
									 if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) {
										 throw new LimiteException(pos.getX(), pos.getY());
									 }
								 } catch (LimiteException ex) {
									 System.out.println("Erreur: " + ex.getMessage());

									 hasError = true;
									 stopPos = true;
								 }
								 if(!hasError && (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeProfondeur() || plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeSurface())){
									 stopPos = true;
								 }
							 }
						 }
						 break;
					 case "SUD":
						 for(int cpt = 1;cpt<=this.eq.getNavire(IdNav).getPortee();cpt++){
							 if(!stopPos) {
								 try {
									 pos = new Point(this.eq.getNavire(IdNav).getPos().getX(), this.eq.getNavire(IdNav).getPos().getY() +cpt);
									 if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) {
										 throw new LimiteException(this.eq.getNavire(IdNav).getPos().getX(), this.eq.getNavire(IdNav).getPos().getY() + cpt);
									 }
								 } catch (LimiteException ex) {
									 System.out.println("Erreur: " + ex.getMessage());
									 pos = new Point(this.eq.getNavire(IdNav).getPos().getX(), this.eq.getNavire(IdNav).getPos().getY());
									 hasError = true;
									 stopPos = true;
								 }
								 if(!hasError && (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeProfondeur() || plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeSurface())){
									 stopPos = true;
								 }
							 }
						 }
						 break;
					 case "EST":
						 for(int cpt = 1;cpt<=this.eq.getNavire(IdNav).getPortee();cpt++){
							 if(!stopPos) {
								 try {
									 pos = new Point(this.eq.getNavire(IdNav).getPos().getX()+cpt, this.eq.getNavire(IdNav).getPos().getY());
									 if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) {
										 throw new LimiteException(this.eq.getNavire(IdNav).getPos().getX()+cpt, this.eq.getNavire(IdNav).getPos().getY());
									 }
								 } catch (LimiteException ex) {
									 System.out.println("Erreur: " + ex.getMessage());
									 pos = new Point(this.eq.getNavire(IdNav).getPos().getX(), this.eq.getNavire(IdNav).getPos().getY());
									 hasError = true;
									 stopPos = true;
								 }
								 if(!hasError && (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeProfondeur() || plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeSurface())){
									 stopPos = true;
								 }
							 }
						 }
						 break;
					 case "OUEST":
						 for(int cpt = 1;cpt<=this.eq.getNavire(IdNav).getPortee();cpt++){
							 if(!stopPos) {
								 try {
									 pos = new Point(this.eq.getNavire(IdNav).getPos().getX()-cpt, this.eq.getNavire(IdNav).getPos().getY() );
									 if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) {
										 throw new LimiteException(this.eq.getNavire(IdNav).getPos().getX()-cpt, this.eq.getNavire(IdNav).getPos().getY());
									 }
								 } catch (LimiteException ex) {
									 System.out.println("Erreur: " + ex.getMessage());
									 pos = new Point(this.eq.getNavire(IdNav).getPos().getX(), this.eq.getNavire(IdNav).getPos().getY());
									 hasError = true;
									 stopPos = true;
								 }
								 if(!hasError && (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeProfondeur() || plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeSurface())){
									 stopPos = true;
								 }
							 }
						 }
						 break;
				 }
			 }
		 }while(hasError==true);


		 //Calcul pos pour peche
		 if(stra.compareTo("PECHE") == 0) {
			 pos = new Point(this.eq.getNavire(IdNav).getPos().getX(), this.eq.getNavire(IdNav).getPos().getY());
		 }

		 //Sauvegarde de la commande
		 this.eq.myCommande = new Commande(this.eq,IdNav,stra,strd,pos) ;
	 }
 }
	 