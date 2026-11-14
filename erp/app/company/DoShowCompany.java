package erp.app.company;

import erp.app.exception.UnknownCompanyKeyException;
import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import erp.core.exception.CompanyExceptions.*;
//FIXME maybe import classes

/**
 * Show a specific company.
 */
class DoShowCompany extends Command<ErpManager> {

  DoShowCompany(ErpManager receiver) {
    super(Label.SHOW_COMPANY, receiver);
    addIntegerField("id", Prompt.companyId());
  }
  
  @Override
  protected final void execute() throws CommandException {
    int id = integerField("id");
    try{
    _display.popup(_receiver.searchCompany(id));
    }

    catch (UnknownCompanyException e){
      throw new UnknownCompanyKeyException(e.getId());
    }
  }
}
