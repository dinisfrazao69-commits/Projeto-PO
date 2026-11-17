package erp.core;

import erp.core.exception.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
// FIXME maybe import classes

/**
 * The whole ERP system: holds all the state and the domain logic.
 */
class Erp implements Serializable {

  @java.io.Serial
  private static final long serialVersionUID = 202608261200L;

  //FIXME maybe define attributes
  //FIXME maybe implement constructor



  //FIXME maybe implement methods
  private int _companiesId = 1;
  private int _productsId = 1;
  private int _dateAtual = 1;
  private Map<Integer, Company> _companies= new TreeMap<>();
  private Map<String, Client> _clients = new TreeMap<>(); 
  private Map<Integer, Product> _products = new TreeMap<>();



  List <Client> listClients() {
    List<Client> clients = new ArrayList<>(_clients.values());

    clients.sort(Comparator.comparing(Client::getName, 
      String.CASE_INSENSITIVE_ORDER).thenComparing(Client::getEmail, String.CASE_INSENSITIVE_ORDER));
    //o string.case_insensitive é para comparar os textos sem distinguir maisuculas d eminusculas
    //ou seja, primeiro compara os nomes e depois compara os emails
    return clients;
  }

  //o modified vai ficar no erpManager e não aqui
  
  void registerClient(String name, String email) throws ClientRegistrationException {
    if (name.isBlank() || email.isBlank() || _clients.containsKey(email)) {
      throw new ClientRegistrationException(name, email);
        }
          
        Client client = new Client(name, email);
        _clients.put(email, client);
  }


  Client searchClient(String email) throws UnknownClientException {
    if (!_clients.containsKey(email)) {
      throw new UnknownClientException(email);
    }
    return _clients.get(email);
  }



  Product searchProduct(int id) throws UnknownProductException{
    if(!_products.containsKey(id)){
      throw new UnknownProductException(id);
    }
    return _products.get(id);
  }

  List <Product> listProducts() {
    List<Product> products = new ArrayList<>();

    for (Product p: this._products.values()){
      products.add(p);
    }
    return products;
  }

  int registerNationalProduct(String name, int basePrice, String category, int baseDeliveryTime)
      throws ProductRegistrationException {
        if (name.isBlank() || basePrice <= 0) {
          throw new ProductRegistrationException(name);
        }

        ProductCategory productCategory;

        try {
          productCategory = ProductCategory.valueOf(category); //basicamente verifica se a categoria existe 
        } catch (IllegalArgumentException e) {
             throw new ProductRegistrationException(name);
          }
        NationalProduct product = new NationalProduct(_productsId, name, productCategory, basePrice, baseDeliveryTime);

        _products.put(product.getId(), product);
        return this._productsId++;
      }

  int registerImportedProduct(String name, int basePrice, String category, int baseDeliveryTime, String country, double tax)
      throws ProductRegistrationException {
        if (name.isBlank() || basePrice <= 0 || country.isBlank()) {
          throw new ProductRegistrationException(name);
        }
        ProductCategory productCategory;

        try {
          productCategory = ProductCategory.valueOf(category);
        } catch (IllegalArgumentException e) {
            throw new  ProductRegistrationException(name);
        }
        ImportedProduct product = new ImportedProduct(_productsId, name, productCategory, basePrice, baseDeliveryTime, tax, country);

        _products.put(product.getId(), product);
        return this._productsId++;
      }
  


  String[] productTypes() {
    return new String[] { "NACIONAL", "IMPORTADO" };
  }

  String[] searchCriteria(){
    return new String[] { "NOME", "CATEGORIA", "ORIGEM"};
  }

  String[] productCategories() {
    ProductCategory[] values = ProductCategory.values();
    String[] names = new String[values.length];
    for (int i = 0; i < values.length; i++)
      names[i] = values[i].name();
    return names;
  }
      

      
  List<Product> searchProducts(String criterio, String objetivo){
    List<Product> procura = new ArrayList<>();
    for (Product p: _products.values()){
      switch (criterio){
        case "NOME":
          if (p.getName().equalsIgnoreCase(objetivo)){
            procura.add(p);
          }
          break;
        case "CATEGORIA":
          if(p.getCategory().name().equalsIgnoreCase(objetivo)){
            procura.add(p);
          }
          break;
        case "ORIGEM":
          if(p.hasOrigin(objetivo)){
            procura.add(p);
          }
          break;
      }
    }
    return procura;
  }



  int registerCompany(String name) throws CompanyRegistrationException{
    if (name.isBlank()){
      throw new CompanyRegistrationException(name);
    }
    Company company = new Company(this._companiesId, name);
    _companies.put(company.getId(), company);
    return this._companiesId++;
  }

  Company searchCompany(int id) throws UnknownCompanyException{
    if(!_companies.containsKey(id)){
      throw new UnknownCompanyException(id);
    }
    return _companies.get(id);
  }

  List<Company> listCompanies(){
    List<Company> companies = new ArrayList<>();

    for (Company p: this._companies.values()){
      companies.add(p);
    }
    return companies;
  }



  boolean changeProductInventory(int productId, int companyId, int quantity, double factor) throws UnknownCompanyException, UnknownProductException{
    Company c = searchCompany(companyId);
    Product p = searchProduct(productId);
    SuppliedProduct stock = c.getProduct(productId);
    if (stock != null){
      return stock.update(quantity,factor);//O update atualiza os dados daquele produto naquela empresa
    }
    if (quantity <=0 || factor <= 0){
      return false;
    }
    SuppliedProduct novo = new SuppliedProduct(c, p, quantity, factor);
    c.addProduct(novo);
    p.addSupply(novo);
    return true;
    }

  
  List<SuppliedProduct> listCompanyProducts(int companyID) throws UnknownCompanyException{
    Company company = searchCompany(companyID);
    return company.listProducts();
    
  }



  Basket getBasket(String clientId, int companyId) throws UnknownClientException, UnknownCompanyException{
    Client client = searchClient(clientId);
    Company company = searchCompany(companyId);
    return client.getBasket(company);
  }

  int getDateAtual() {
    return _dateAtual;
  } 

  void advanceDate(int days) {
    for (int i = 0; i < days; i++) {
      _dateAtual++;
    }
  }


  /**
   * Read text input file at the beginning of the program and populates the
   * the state of this application with the domain entities represented in the text file.
   * 
   * @param filename name of the text input file to process
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException
                       /* FIXME maybe other exceptions */ {
    new Parser(this).parseFile(filename);
    
  }
}
