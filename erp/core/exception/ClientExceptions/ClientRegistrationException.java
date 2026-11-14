
package erp.core.exception.ClientExceptions;


public class ClientRegistrationException extends Exception {
    
    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;
    
    private String _name;
    private String _email;

    public ClientRegistrationException(String name, String email){
        this._name = name;
        this._email = email;
    }

    public String getName(){
        return this._name;
    }

    public String getEmail(){
        return this._email;
    }    
}
