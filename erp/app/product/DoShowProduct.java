package erp.app.product;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import erp.core.exception.UnknownProductException;
import erp.app.exception.UnknownProductKeyException;

/**
 * Show a specific product.
 */
class DoShowProduct extends Command<ErpManager> {

  DoShowProduct(ErpManager receiver) {
    super(Label.SHOW_PRODUCT, receiver);
    addIntegerField("id", Prompt.productId());
  }

  @Override
  protected final void execute() throws CommandException {
    int id = integerField("id");
    try {
      _display.popup(_receiver.searchProduct(id));
    } catch (UnknownProductException e) {
         throw new UnknownProductKeyException(e.getId());
    }
  }
}
