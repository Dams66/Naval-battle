
public abstract class Navire {
	
	//Attributs
	
		protected int ident;
		protected TypeNav myType;
		protected Point position;
		protected int numEq;
		protected boolean endommagé;
		protected int portee;
			
	//Constructeur
		
		public Navire(int rang, TypeNav t, int numEquipe,int laPortee){
			ident = rang;
			myType = t;
			numEq = numEquipe;
			endommagé = false;
			position = null;
			portee = laPortee;
		}
			
	//Méthodes
		
		public int getIdent() {
			return this.ident;
		}

		public int getPortee(){
			return this.portee;
		}
		
		public int getnumEq() {
			return this.numEq;
		}
		
		public Point getPos() {
			return this.position;
		}
		
		public void setPos(Point pos) {
			this.position = pos;
		}
		
		public TypeNav getType() {
			return this.myType;
		}
		
		
		public boolean estEndommagé() {
			return this.endommagé;
		}
		
		public void seDeplacer(Point pos) {
			this.position = pos;
		}

		public void setEndommagé() {
			this.endommagé=true;
		}
		
		public abstract String toString();

}
