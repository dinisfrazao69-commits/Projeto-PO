package erp.core.exception;

public class UnknownProductException extends Exception {
    
    @java.io.Serial 
    private static final long serialVersionUID = 202608261200L;

    private int _id;

    public UnknownProductException(int id){
        this._id = id;
    }

    public int getId(){
        return this._id;
    }
    
}
