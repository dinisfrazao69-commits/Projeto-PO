package erp.core;

import java.io.Serializable;




public class Client implements Serializable {

  @java.io.Serial
  private static final long serialVersionUID = 202608261200L;




    private String _name;
    private String _email;
    private double _totalSpent;


    public Client(String name, String email){
        this._email = email;
        this._name = name;
        this._totalSpent = 0.0;
    }

    public String getName(){
        return this._name;
    }
    
    public String getEmail(){
        return this._email;
    }

    @Override
    public String toString(){
        return this._email + " - " + this._name + " - " + Format.money(this._totalSpent); 
    }
}






