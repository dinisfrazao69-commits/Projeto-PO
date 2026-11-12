package erp.app.client;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Show all clients (ordered lexicographically by name).
 */
class DoShowClients extends Command<ErpManager> {

  DoShowClients(ErpManager receiver) {
    super(Label.SHOW_CLIENTS, receiver);
  }

  @Override
  protected final void execute() {
    _display.popup(_receiver.listClients());
  }
}
