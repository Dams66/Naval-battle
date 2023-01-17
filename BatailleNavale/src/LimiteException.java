public class LimiteException extends Exception{

    public LimiteException (String m){
        super(m);
    }

    public LimiteException(int abs, int ord){
        super("La case [" +abs+ " , " +ord+ " ] est en dehors du plateau !");
    }
}
