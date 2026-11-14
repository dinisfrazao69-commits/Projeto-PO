package erp.app.company;

import erp.core.ErpManager;
import erp.core.exception.CompanyExceptions.CompanyRegistrationException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import erp.app.exception.CompanyRegistrationFailedException;
//FIXME maybe import classes

/**
 * Register a new company.
 */
class DoRegisterCompany extends Command<ErpManager> {

  DoRegisterCompany(ErpManager receiver) {
    super(Label.REGISTER_COMPANY, receiver);
    addStringField("name", Prompt.companyName());
  }

  @Override
  protected final void execute() throws CommandException {
    String name = stringField("name");
    
    try{
     int id = _receiver.registerCompany(name);
      _display.popup(Message.registrationSuccessful(id));
    }

    catch(CompanyRegistrationException c){
      throw new CompanyRegistrationFailedException(c.getName());

    }
  }
}
