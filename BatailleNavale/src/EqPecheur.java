
public class EqPecheur extends Equipe{

	public EqPecheur(Nature n, int numPassage) {
		super(n,Statut.NEUTRE,numPassage);
	}
	
	public void remplirListeNavire(int numPassageEquipe) {
		NavireSurface chalutier1 = new Chalutier(1,numPassageEquipe);
		NavireSurface chalutier2 = new Chalutier(2,numPassageEquipe);
		NavireSurface chalutier3 = new Chalutier(3,numPassageEquipe);
		NavireSurface chalutier4 = new Chalutier(4,numPassageEquipe);
		this.addNavire(chalutier1);
		this.addNavire(chalutier2);
		this.addNavire(chalutier3);
		this.addNavire(chalutier4);
	}
}
