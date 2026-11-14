package erp.core.exception;

public class ProductRegistrationException  extends Exception{
    
    @java.io.Serial 
    private static final long serialVersionUID = 202608261200L;

    private String _name;

    public ProductRegistrationException(String name){
        this._name = name;
    }

    public String getId(){
        return this._name;
    }
}
