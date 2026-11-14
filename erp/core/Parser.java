package erp.core;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Reader;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import erp.core.exception.UnrecognizedEntryException;
import erp.core.exception.ProductRegistrationException;
import erp.core.exception.UnknownProductException;
import erp.core.exception.ClientExceptions.ClientRegistrationException;
import erp.core.exception.CompanyExceptions.CompanyRegistrationException;
import erp.core.exception.CompanyExceptions.UnknownCompanyException;

class Parser {

  private ErpManager _manager;

  Parser(ErpManager manager) {
    _manager = manager;
  }

  void parseFile(String fileName) throws IOException, UnrecognizedEntryException {
    try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
      String line;

      while ((line = in.readLine()) != null)
        parseLine(line);
    }
  }

  private void parseLine(String line) throws UnrecognizedEntryException {
    String[] components = line.split(":");

    try {
      switch(components[0]) {
      case "COMPANY":
        parseCompany(components, line);
        break;
      case "IMPORTED":
        parseImported(components, line);
        break;
      case "NATIONAL":
        parseNational(components, line);
        break;
      case "CLIENT":
        parseClient(components, line);
        break;
      case "SUPPLY":
        parseSupply(components, line);
        break;
      case "RATE":
        parseRate(components, line);
        break;
      default:
        throw new UnrecognizedEntryException("Entrada inválida: " + line);
      }
    } catch (CompanyRegistrationException | ClientRegistrationException | ProductRegistrationException
             | UnknownCompanyException | UnknownProductException e) {
      throw new UnrecognizedEntryException("Erro a processar linha: " + line + " Erro = " + e.getMessage());
    }
  }

  private void checkCorrectNumberOfFields(String[] components, int expectedSize, String line)
    throws UnrecognizedEntryException {
    if (components.length != expectedSize)
      throw new UnrecognizedEntryException("Número inválido de campos (" + components.length + ") na linha: " + line);
  }

  // COMPANY:nome
  private void parseCompany(String[] components, String line)
    throws UnrecognizedEntryException, CompanyRegistrationException {
    checkCorrectNumberOfFields(components, 2, line);
    _manager.registerCompany(components[1]);
  }

  // IMPORTED:nome:preçoBase:categoria:prazo:paisOrigem:taxa
  private void parseImported(String[] components, String line)
    throws UnrecognizedEntryException, ProductRegistrationException {
    checkCorrectNumberOfFields(components, 7, line);
    _manager.registerImportedProduct(components[1], Integer.valueOf(components[2]), components[3],
                                     Integer.valueOf(components[4]), components[5], Double.valueOf(components[6]));
  }

  // NATIONAL:nome:preçoBase:categoria:prazo
  private void parseNational(String[] components, String line)
    throws UnrecognizedEntryException, ProductRegistrationException {
    checkCorrectNumberOfFields(components, 5, line);
    _manager.registerNationalProduct(components[1], Integer.valueOf(components[2]), components[3],
                                     Integer.valueOf(components[4]));
  }

  // CLIENT:nome:email
  private void parseClient(String[] components, String line)
    throws UnrecognizedEntryException, ClientRegistrationException {
    checkCorrectNumberOfFields(components, 3, line);
    _manager.registerClient(components[1], components[2]);
  }

  // SUPPLY:idEmpresa:idProduto:unidades:factor
  // O changeProductInventory recebe (produto, empresa, ...): por isso os 2 primeiros vão trocados.
  private void parseSupply(String[] components, String line)
    throws UnrecognizedEntryException, UnknownCompanyException, UnknownProductException {
    checkCorrectNumberOfFields(components, 5, line);
    _manager.changeProductInventory(Integer.valueOf(components[2]), Integer.valueOf(components[1]),
                                    Integer.valueOf(components[3]), Double.valueOf(components[4]));
  }

  // RATE:modalidade:custo:dias
  private void parseRate(String[] components, String line) throws UnrecognizedEntryException {
    checkCorrectNumberOfFields(components, 4, line);
    // TODO: quando existir a ShippingRate:
    // _manager.registerShippingRate(components[1], Integer.valueOf(components[2]), Integer.valueOf(components[3]));
  }

}
