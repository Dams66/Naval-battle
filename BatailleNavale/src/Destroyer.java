
public class Destroyer extends NavireSurface{

	public Destroyer(int rang, int numPassageEquipe) {
		super(rang,TypeNav.DESTROYER, numPassageEquipe, 2);
	}
	
	public String toString() {
		return "Destroyer n°"+this.ident;
	}

}
