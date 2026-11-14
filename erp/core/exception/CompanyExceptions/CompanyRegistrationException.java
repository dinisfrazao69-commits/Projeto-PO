package erp.core.exception.CompanyExceptions;


public class CompanyRegistrationException extends Exception{

    @java.io.Serial 
    private static final long serialVersionUID = 202608261200L;

    private String _name;

    public CompanyRegistrationException(String name){
        this._name = name;
    }
    
    public String getName(){
        return this._name;
    }

}
