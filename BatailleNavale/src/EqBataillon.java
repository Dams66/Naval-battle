
public class EqBataillon extends Equipe{
	
	public EqBataillon(Nature n, int numPassage)  {
		super(n,Statut.MILITAIRE,numPassage);
	}
	
	public void remplirListeNavire(int numPassageEquipe) {
		NavireSurface destroyer1 = new Destroyer(1,numPassageEquipe);
		NavireSurface destroyer2 = new Destroyer(2,numPassageEquipe);
		NavireProfondeur sousmarin1 = new SousMarin(3,numPassageEquipe);
		NavireProfondeur sousmarin2 = new SousMarin(4,numPassageEquipe);
		this.addNavire(destroyer1);
		this.addNavire(destroyer2);
		this.addNavire(sousmarin1);
		this.addNavire(sousmarin2);
	}

}
