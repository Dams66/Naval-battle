import java.util.Random;

public class JIA extends Joueur{
	 
     private Random rd;

     public JIA(int numPassage,Statut s){
         super(numPassage,Nature.IA,s);
         rd = new Random();
     }

     public Point placementNavire(Navire nav, Plateau plateau) {
    	 int x,y;
    	 if(nav.getType() == TypeNav.SOUSMARIN) {
    		 do {
    			 x = rd.nextInt(plateau.getTaille());
    			 y = rd.nextInt(plateau.getTaille());
    		 }while(plateau.getCasePlateau(x, y).isOccupeProfondeur());
			 plateau.getCasePlateau(x, y).addUnOccupantProfondeur(nav);
    	 }
    	 else {
    		 do {
    			 x = rd.nextInt(plateau.getTaille());
    			 y = rd.nextInt(plateau.getTaille());
    		 }while(plateau.getCasePlateau(x, y).isOccupeSurface());
			 plateau.getCasePlateau(x, y).addUnOccupantSurface(nav);
    	 }
         return new Point(x,y);
     }
     
     public void tirageAleatoire(Plateau plateau){
    	 
    	 String stra = "";
    	 String strd = "NORD";
		 Point pos = null;
		 boolean directionIsOk;
		 boolean hasError;

		 int idDirection;
    	 int idNav = rd.nextInt(this.eq.listeNavire.size()) ;
    	 idNav = this.eq.listeNavire.get(idNav).getIdent() ;
    	 
    	 if(this.eq.getStatut() == Statut.NEUTRE) {
			 if(plateau.getCasePlateau(this.eq.getNavire(idNav).getPos().getX(),this.eq.getNavire(idNav).getPos().getY()).isOccupeProfondeur()){
				 stra = "PECHE";
				 pos = new Point(this.eq.getNavire(idNav).getPos().getX(),this.eq.getNavire(idNav).getPos().getY());
			 }else{
				 stra = "DEPLACEMENT";
				 do{
					 directionIsOk = false;
					 idDirection = rd.nextInt(4);
					 switch (idDirection) {
						 case 0:
							 strd = "NORD";
							 pos = new Point(this.eq.getNavire(idNav).getPos().getX(),this.eq.getNavire(idNav).getPos().getY()-1);
							 if(pos.getX()>=0 && pos.getX()<10 && pos.getY()>=0 && pos.getY()<10 ){
								 directionIsOk = true;
							 }
							 break;
						 case 1:
							 strd = "SUD";
							 pos = new Point(this.eq.getNavire(idNav).getPos().getX(),this.eq.getNavire(idNav).getPos().getY()+1);
							 if(pos.getX()>=0 && pos.getX()<10 && pos.getY()>=0 && pos.getY()<10 ){
								 directionIsOk = true;
							 }
							 break;
						 case 2:
							 strd = "EST";
							 pos = new Point(this.eq.getNavire(idNav).getPos().getX()+1,this.eq.getNavire(idNav).getPos().getY());
							 if(pos.getX()>=0 && pos.getX()<10 && pos.getY()>=0 && pos.getY()<10 ){
								 directionIsOk = true;
							 }
							 break;
						 case 3:
							 strd = "OUEST";
							 pos = new Point(this.eq.getNavire(idNav).getPos().getX()-1,this.eq.getNavire(idNav).getPos().getY());
							 if(pos.getX()>=0 && pos.getX()<10 && pos.getY()>=0 && pos.getY()<10 ){
								 directionIsOk = true;
							 }
							 break;
					 }
				 }while(directionIsOk == false);
			 }

    	 }
    	 else {
			 do {
				 hasError = true;
				 directionIsOk = false;
				 int idAction = rd.nextInt(2);
				 switch (idAction) {
					 case 0:
						 stra = "DEPLACEMENT";
						 break;
					 case 1:
						 stra = "TIR";
						 break;
				 }
				 if (stra.compareTo("DEPLACEMENT") == 0) {
					 if (this.eq.getNavire(idNav).estEndommagé() == true) {
						 System.out.println("Impossible de bouger le " + this.eq.getNavire(idNav).toString() + " car il est endommagé");
					 } else {
						 idDirection = rd.nextInt(4);
						 switch (idDirection) {
							 case 0:
								 strd = "NORD";
								 pos = new Point(this.eq.getNavire(idNav).getPos().getX(), this.eq.getNavire(idNav).getPos().getY() + 1);
								 if (pos.getX() >= 0 && pos.getX() < 10 && pos.getY() >= 0 && pos.getY() < 10) {
									 directionIsOk = true;
								 }
								 break;
							 case 1:
								 strd = "SUD";
								 pos = new Point(this.eq.getNavire(idNav).getPos().getX(), this.eq.getNavire(idNav).getPos().getY() - 1);
								 if (pos.getX() >= 0 && pos.getX() < 10 && pos.getY() >= 0 && pos.getY() < 10) {
									 directionIsOk = true;
								 }
								 break;
							 case 2:
								 strd = "EST";
								 pos = new Point(this.eq.getNavire(idNav).getPos().getX() + 1, this.eq.getNavire(idNav).getPos().getY());
								 if (pos.getX() >= 0 && pos.getX() < 10 && pos.getY() >= 0 && pos.getY() < 10) {
									 directionIsOk = true;
								 }
								 break;
							 case 3:
								 strd = "OUEST";
								 pos = new Point(this.eq.getNavire(idNav).getPos().getX() - 1, this.eq.getNavire(idNav).getPos().getY());
								 if (pos.getX() >= 0 && pos.getX() < 10 && pos.getY() >= 0 && pos.getY() < 10) {
									 directionIsOk = true;
								 }
								 break;
						 }
					 }
				 }else if(stra.compareTo("TIR") == 0){
					 hasError = false;
					 directionIsOk =true;
					 boolean stopPos=false;
					 idDirection = rd.nextInt(4);
					 switch (idDirection) {
						 case 0:
							 strd = "NORD";
							 for(int cpt = 1;cpt<=this.eq.getNavire(idNav).getPortee();cpt++){
								 if(!stopPos) {
									 try {
										 pos = new Point(this.eq.getNavire(idNav).getPos().getX(), this.eq.getNavire(idNav).getPos().getY() -cpt);
										 if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) {
											 throw new LimiteException(pos.getX(), pos.getY());
										 }
									 } catch (LimiteException ex) {
										 pos = new Point(this.eq.getNavire(idNav).getPos().getX(), this.eq.getNavire(idNav).getPos().getY());
										 hasError = true;
										 stopPos = true;
									 }
									 if(!hasError && (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeProfondeur() || plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeSurface())){
										 stopPos = true;
									 }
								 }
							 }
							 break;
						 case 1:
							 strd = "SUD";
							 for(int cpt = 1;cpt<=this.eq.getNavire(idNav).getPortee();cpt++){
								 if(!stopPos) {
									 try {
										 pos = new Point(this.eq.getNavire(idNav).getPos().getX(), this.eq.getNavire(idNav).getPos().getY() +cpt);
										 if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) {
											 throw new LimiteException(this.eq.getNavire(idNav).getPos().getX(), this.eq.getNavire(idNav).getPos().getY() + cpt);
										 }
									 } catch (LimiteException ex) {
										 pos = new Point(this.eq.getNavire(idNav).getPos().getX(), this.eq.getNavire(idNav).getPos().getY());
										 hasError = true;
										 stopPos = true;
									 }
									 if(!hasError && (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeProfondeur() || plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeSurface())){
										 stopPos = true;
									 }
								 }
							 }
							 break;
						 case 2:
							 strd = "EST";
							 for(int cpt = 1;cpt<=this.eq.getNavire(idNav).getPortee();cpt++){
								 if(!stopPos) {
									 try {
										 pos = new Point(this.eq.getNavire(idNav).getPos().getX()+cpt, this.eq.getNavire(idNav).getPos().getY());
										 if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) {
											 throw new LimiteException(this.eq.getNavire(idNav).getPos().getX()+cpt, this.eq.getNavire(idNav).getPos().getY());
										 }
									 } catch (LimiteException ex) {
										 pos = new Point(this.eq.getNavire(idNav).getPos().getX(), this.eq.getNavire(idNav).getPos().getY());
										 hasError = true;
										 stopPos = true;
									 }
									 if(!hasError && (plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeProfondeur() || plateau.getCasePlateau(pos.getX(), pos.getY()).isOccupeSurface())){
										 stopPos = true;
									 }
								 }
							 }
							 break;
						 case 3:
							 strd = "OUEST";
							 for(int cpt = 1;cpt<=this.eq.getNavire(idNav).getPortee();cpt++){
								 if(!stopPos) {
									 try {
										 pos = new Point(this.eq.getNavire(idNav).getPos().getX()-cpt, this.eq.getNavire(idNav).getPos().getY() );
										 if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) {
											 throw new LimiteException(this.eq.getNavire(idNav).getPos().getX()-cpt, this.eq.getNavire(idNav).getPos().getY());
										 }
									 } catch (LimiteException ex) {
										 pos = new Point(this.eq.getNavire(idNav).getPos().getX(), this.eq.getNavire(idNav).getPos().getY());
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
			 }while (directionIsOk == false || hasError == true) ;
		 }
    	 this.eq.myCommande = new Commande(this.eq,idNav,stra,strd,pos) ;
    }


 }