package erp.app.purchase;

import erp.core.ErpManager;
import erp.core.exception.UnknownClientException;
import erp.core.exception.UnknownCompanyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import erp.app.exception.UnknownClientKeyException;
import erp.app.exception.UnknownCompanyKeyException;
import erp.core.Basket;
//FIXME maybe import classes

/**
 * Starts (or resumes) a purchase: it asks whose basket, at which company, and
 * opens the basket menu over it.
 *
 * <p>The purchase itself is not made here — it is the basket menu's
 * {@code Finalizar compra} that turns the basket into a purchase. Since the
 * basket belongs to the client, leaving the menu without checking out keeps
 * everything for the next visit.</p>
 */
class DoPurchase extends Command<ErpManager> {

  DoPurchase(ErpManager receiver) {
    super(Label.DO_PURCHASE, receiver);
    addStringField("clientId", erp.app.client.Prompt.clientId());
    addIntegerField("companyId", erp.app.company.Prompt.companyId());
  }

  @Override
  protected final void execute() throws CommandException {
    String clientId = stringField("clientId");
    int companyId = integerField("companyId");
    try{
      Basket basket = _receiver.getBasket(clientId, companyId);
      new erp.app.basket.Menu(_receiver, basket).open();
    }
    catch(UnknownClientException e){
      throw new UnknownClientKeyException(clientId);
    }
    catch(UnknownCompanyException j){
      throw new UnknownCompanyKeyException(companyId);
    }

  }
}
