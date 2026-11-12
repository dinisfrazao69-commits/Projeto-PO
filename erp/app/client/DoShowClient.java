package erp.app.client;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import erp.core.exception.UnknownClientException;
import erp.app.exception.UnknownClientKeyException;

/**
 * Show a specific client. Asks the id of the client to show, and then show it to the user.
 */
class DoShowClient extends Command<ErpManager> {

  DoShowClient(ErpManager receiver) {
    super(Label.SHOW_CLIENT, receiver);
    addStringField("email", Prompt.clientId());
  }

  @Override
  protected final void execute() throws CommandException {
    String email = stringField("email");
    try{
    _display.popup(_receiver.searchClient(email));
    }

    catch (UnknownClientException e){
      throw new UnknownClientKeyException(e.getId());
    }


  }
}
