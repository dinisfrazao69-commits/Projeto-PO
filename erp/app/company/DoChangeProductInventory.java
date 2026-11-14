package erp.app.company;

import erp.app.exception.UnknownCompanyKeyException;
import erp.app.exception.UnknownProductKeyException;
import erp.core.ErpManager;
import erp.core.exception.UnknownProductException;
import erp.core.exception.CompanyExceptions.UnknownCompanyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Changes what a company holds of a product — the amount is added to its
 * inventory and the factor replaces the previous one — starting the supply if
 * the company did not offer the product yet.
 *
 * <p>Reaching zero units only makes the product unavailable at that company: the
 * product itself stays in the system.</p>
 */
class DoChangeProductInventory extends Command<ErpManager> {

  DoChangeProductInventory(ErpManager receiver) {
    super(Label.CHANGE_PRODUCT_INVENTORY, receiver);
    addIntegerField("companyId", Prompt.companyId());
    addIntegerField("productId", erp.app.product.Prompt.productId());
    addIntegerField("quantity", Prompt.amountToUpdate());
    addRealField("factor",Prompt.priceFactor());
  }

  @Override
  protected final void execute() throws CommandException {
    int companyId = integerField("companyId");
    int productId = integerField("productId");
    int quantity = integerField("quantity");
    double factor = realField("factor");

    try{
      boolean valor = _receiver.changeProductInventory(productId, companyId, quantity, factor);
      if (valor){
        _display.popup(Message.supplySuccessful(companyId, productId));
      }
      else{
        _display.popup(Message.cannotChangeInventory(productId));
      }
    }
    
    catch(UnknownProductException e){
      throw new UnknownProductKeyException(e.getId());
    }

    catch(UnknownCompanyException c){
      throw new UnknownCompanyKeyException(c.getId());
    }

  }
}
