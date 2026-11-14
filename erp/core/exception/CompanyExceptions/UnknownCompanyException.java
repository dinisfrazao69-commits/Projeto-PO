package erp.core.exception.CompanyExceptions;

public class UnknownCompanyException extends Exception{

    @java.io.Serial 
    private static final long serialVersionUID = 202608261200L;

    private int _companyId;

    public UnknownCompanyException(int id){
        this._companyId = id;
    }

    public int getId(){
        return this._companyId;
    }




}
