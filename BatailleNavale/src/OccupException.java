
public class OccupException extends Exception {

	public OccupException(String m) {
		super(m);
	}
	
	public OccupException(int abs, int ord) {
		super("La case [" +abs+ " , " +ord+ " ] est déja occupée !");
	}
}