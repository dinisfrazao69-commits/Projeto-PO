package erp.core.exception;

public class UnknownClientException  extends Exception{
    
    @java.io.Serial 
    private static final long serialVersionUID = 202608261200L;

    private String _id;

    public UnknownClientException(String id) {
        this._id = id;
    }

    public String getId() {
        return this._id;
    } 
}
