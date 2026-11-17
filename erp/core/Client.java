package erp.core;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;




public class Client implements Serializable {

  @java.io.Serial
  private static final long serialVersionUID = 202608261200L;




    private String _name;
    private String _email;
    private double _totalSpent;
    private Map<Integer, Basket> _baskets;


    public Client(String name, String email){
        this._email = email;
        this._name = name;
        this._totalSpent = 0.0;
        this._baskets = new TreeMap<>();
    }

    public String getName(){
        return this._name;
    }
    
    public String getEmail(){
        return this._email;
    }


    public Basket getBasket(Company company){
        int id = company.getId();
        if (this._baskets.get(id) == null){
            _baskets.put(id, new Basket(this, company));
        }
        return this._baskets.get(id);
    }

    @Override
    public String toString(){
        return this._email + " - " + this._name + " - " + Format.money(this._totalSpent); 
    }
}






