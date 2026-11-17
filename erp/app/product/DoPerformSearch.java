package erp.app.product;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Searches the product registry by one of the criteria the core offers.
 */
class DoPerformSearch extends Command<ErpManager> {

  DoPerformSearch(ErpManager receiver) {
    super(Label.PERFORM_SEARCH, receiver);
    addOptionField("criteria", Prompt.searchCriterion(), _receiver.searchCriteria());
    addStringField("term", Prompt.searchTerm());
  }

  @Override
  protected final void execute() throws CommandException {
    String criteria = optionField("criteria");
    String term = stringField("term");
    _display.popup(_receiver.searchProducts(criteria, term));

  }
}
