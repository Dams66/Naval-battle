
public class SousMarin extends NavireProfondeur{

	//Constructeur
	
		public SousMarin(int rang, int numEquipe) {
			super(rang, TypeNav.SOUSMARIN, numEquipe,3);
		}
		
	//Méthodes 
		
		public String toString() {
			return "Sous-marin n°"+this.ident;
		}
		
		public void setEndommage() {
			
		}

}
