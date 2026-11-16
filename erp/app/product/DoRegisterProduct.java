package erp.app.product;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import erp.core.exception.ProductRegistrationException;
import erp.app.exception.ProductRegistrationFailedException;
import pt.tecnico.uilib.forms.Form;

/**
 * Registers a product in the system's global registry. The extra fields of the
 * imported type are only asked for once that type is chosen, so a new product
 * type only adds its own branch here.
 */
class DoRegisterProduct extends Command<ErpManager> {

  DoRegisterProduct(ErpManager receiver) {
    super(Label.REGISTER_PRODUCT, receiver);
    addOptionField("type", Prompt.productType(), _receiver.productTypes());
    addStringField("name", Prompt.productName());
    addIntegerField("basePrice", Prompt.basePrice());
    addOptionField("category", Prompt.productCategory(), _receiver.productCategories());
    addIntegerField("baseDeliveryTime", Prompt.baseDelay());
  }

  @Override
  protected final void execute() throws CommandException {
    String type = optionField("type");
    String name = stringField("name");
    int basePrice = integerField("basePrice");
    String category = optionField("category");
    int baseDeliveryTime = integerField("baseDeliveryTime");
  
    try {
      if (type.equals("NACIONAL")) {
        int id = _receiver.registerNationalProduct(name, basePrice, category, baseDeliveryTime);
        _display.popup(Message.registrationSuccessful(id));
      }
      else if (type.equals("IMPORTADO")) { //aqui vamos ter 2 perguntas que so existem no importado
        String country = Form.requestString(Prompt.originCountry());
        double tax = Form.requestReal(Prompt.customsTax());
        int id = _receiver.registerImportedProduct(name, basePrice, category, baseDeliveryTime, country, tax);
        _display.popup(Message.registrationSuccessful(id));
      }
    } catch (ProductRegistrationException e) {
        throw new ProductRegistrationFailedException(name);
      }
    
  }
}
