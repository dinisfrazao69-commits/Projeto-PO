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
  private Map<String, Client> _clients = new TreeMap<>(); 

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
    //FIXME implement method
  }
}
