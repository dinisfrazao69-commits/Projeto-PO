package erp.app.main;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Advance the current date.
 */
class DoAdvanceDate extends Command<ErpManager> {

  DoAdvanceDate(ErpManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    addIntegerField("days", Prompt.daysToAdvance());
  }

  @Override
  protected final void execute() throws CommandException {
    _receiver.advanceDate(integerField("days"));
  }
}
