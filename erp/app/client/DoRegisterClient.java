package erp.app.client;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import erp.core.exception.ClientRegistrationException;
import erp.app.exception.ClientRegistrationFailedException;

/** 
 * Registers a client. Asks the name and email of the new client to the user. The e-mail
 * address is its identifier, so it must be new.
 */
class DoRegisterClient extends Command<ErpManager> {

  DoRegisterClient(ErpManager receiver) {
    super(Label.REGISTER_CLIENT, receiver);
    addStringField("name", Prompt.clientName());
    addStringField("email", Prompt.clientEMail());
  }

  @Override
  protected final void execute() throws CommandException {
    String name = stringField("name");
    String email = stringField("email");

    try{
      _receiver.registerClient(name, email);
      _display.popup(Message.registrationSuccessful(email));

    }
    catch (ClientRegistrationException e){
      throw new ClientRegistrationFailedException(e.getName(), e.getEmail());
    }
  }
}
