package erp.core;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
/**
 * A client's shopping basket at one company: what it intends to buy, and how it
 * wants the purchase shipped.
 *
 * <p>The basket menu is opened <em>over</em> a basket, so this class is the
 * receiver of every command in {@code erp.app.basket}. Its name and package are
 * fixed by that code; its content is yours to define.</p>
 */
public class Basket implements Serializable {

  @java.io.Serial
  private static final long serialVersionUID = 202608261200L;

  private Client _client;
  private Company _company;
  private Map<Integer, Basket> _baskets;

  public Basket(Client client, Company company){
    this._client = client;
    this._company = company;
    this._baskets = new TreeMap<>();
  }

  public Client getClient(){
    return this._client;
  }

  public Company getCompany(){
    return this._company;
  }

  
}
