
public class Chalutier extends NavireSurface{

	public Chalutier(int rang, int numPassageEquipe) {
		super(rang, TypeNav.CHALLUTIER, numPassageEquipe,0);
	}
	
	public String toString() {
		return "Chalutier n°"+this.ident;
	}

}
