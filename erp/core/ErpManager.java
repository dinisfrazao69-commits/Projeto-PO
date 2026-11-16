package erp.core;

import erp.core.exception.*;
import erp.core.exception.ClientExceptions.ClientRegistrationException;
import erp.core.exception.ClientExceptions.UnknownClientException;
import erp.core.exception.CompanyExceptions.CompanyRegistrationException;
import erp.core.exception.CompanyExceptions.UnknownCompanyException;

import java.io.*;
import java.util.List;

/**
 * The façade of the ERP core: the single entry point through which the
 * application interacts with the domain.
 *
 * <p><b>Façade</b> pattern.</p>
 */
public class ErpManager {

  /** The object doing all the actual work. */
  private Erp _erp = new Erp();
  private boolean _modified = false;

  public List<Client> listClients(){
    return _erp.listClients();
  }
  public void registerClient(String name, String email) throws ClientRegistrationException {
    _erp.registerClient(name, email);
    _modified = true;
  }

  public Client searchClient(String email) throws UnknownClientException {
    return _erp.searchClient(email);
  }

  public int registerNationalProduct(String name, int basePrice, String category, int baseDeliveryTime) 
    throws ProductRegistrationException {
      int id = _erp.registerNationalProduct(name, basePrice, category, baseDeliveryTime);
      _modified = true;
      return id;
    } 

  public int registerImportedProduct(String name, int basePrice, String category, int baseDeliveryTime, String country, double tax)
    throws ProductRegistrationException {
      int id = _erp.registerImportedProduct(name, basePrice, category, baseDeliveryTime, country, tax);
      _modified = true;
      return id;
    }

  public List<Product> listProducts() {
    return _erp.listProducts();
  }

  public Product searchProduct(int id) throws UnknownProductException {
    return _erp.searchProduct(id);
  }


  public String[] productTypes() {
  return _erp.productTypes();
}

  public String[] productCategories() {
    return _erp.productCategories();
  }

  public String[] searchCriteria(){
    return _erp.searchCriteria();
  }

  public List<Product> searchProducts(String criterio, String objetivo){
    return _erp.searchProducts(criterio, objetivo);
   }




  public int registerCompany(String name) throws CompanyRegistrationException{
    int id = _erp.registerCompany(name);
    _modified = true;
    return id;
  }

  public Company searchCompany(int id) throws UnknownCompanyException{
    return _erp.searchCompany(id);
  }

  public List<Company> listCompanies(){
    return _erp.listCompanies();
  }

  public boolean changeProductInventory(int productId, int companyId, int quantity, double factor) throws UnknownCompanyException, UnknownProductException{
    boolean valor = _erp.changeProductInventory(productId, companyId, quantity, factor);
    if (valor)
      _modified = true;
    return valor;
  }

  public List<SuppliedProduct> listCompanyProducts(int companyID) throws UnknownCompanyException{
    return _erp.listCompanyProducts(companyID);
  }

  public int getDateAtual() {
    return _erp.getDateAtual();
  }

  public void advanceDate(int days) {
    _erp.advanceDate(days);
    _modified = true;
  }


  // --- persistence -------------------------------------------------------

  /**
   * Saves the serialized application's state into the file associated to the current Erp
   * application
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current library does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws MissingFileAssociationException, IOException {
    //FIXME implement method
  }

  /**
   * Saves the serialized application's state into the specified file. The current Erp
   * application is associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current library does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void saveAs(String filename) throws MissingFileAssociationException, IOException {
    //FIXME implement method
  }

  /**
   * Loads the previously serialized application's state as set it as the current Erp
   * application.
   *
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    //FIXME implement method
  }

  /**
   * Read text input file and initializes the current application (which should be empty)
   * with the domain entities representeed in the import file.
   *
   * @param datafile name of the text input file
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    try {
      if (filename != null && !filename.isEmpty()) {
        _erp.importFile(filename);
        _modified = true;
      }
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(filename, e);
    }
  }
  
  //FIXME implement other methods
}
