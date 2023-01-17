
public class CasePlateau {
	
	//Attributs
	
		private boolean surfaceOccupe;
		private boolean profondeurOccupe;
		private Navire occupantSurface;
		private Navire occupantProfondeur;
		
	//Constructeur
	
		public CasePlateau(){
			profondeurOccupe = false;
	        surfaceOccupe = false;
	        occupantSurface = null;
	        occupantProfondeur = null;
		}
		
		
	//Méthodes
		
		public Navire getOccupantProfondeur() {
			return this.occupantProfondeur;
		}
		
		public Navire getOccupantSurface() {
			return this.occupantSurface;
		}

	     public boolean isOccupeProfondeur() {
	         return profondeurOccupe;
	     }

	     public boolean isOccupeSurface() {
	         return surfaceOccupe;
	     }
		
		public void addUnOccupantSurface(Navire nouvelOccupant) {
				occupantSurface = nouvelOccupant;
		        surfaceOccupe = true;
		}
		
		public void addUnOccupantProfondeur(Navire nouvelOccupant) {
			occupantProfondeur = nouvelOccupant;
	        profondeurOccupe = true;
		}
		
		public void removeUnOccupantSurface(Navire nouvelOccupant) {
			occupantSurface = null;
	        surfaceOccupe = false;
		}
	
		public void removeUnOccupantProfondeur(Navire nouvelOccupant) {
			occupantProfondeur = null;
			profondeurOccupe = false;
		}
		
		public void afficheCase() {
			
			if(this.isOccupeSurface()) {
				if(this.getOccupantSurface().getType()==TypeNav.CHALLUTIER) {
					System.out.print("C");
				}
				else {
					System.out.print("D");
				}
			}
			else {
				System.out.print(" ");
			}
			
			System.out.print(" ");
			
			if(this.isOccupeProfondeur()) {
				System.out.print("S");
			}
			else {
				System.out.print(" ");
			}
		}

}
