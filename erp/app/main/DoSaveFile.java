package erp.app.main;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import erp.core.exception.MissingFileAssociationException;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;

/**
 * Saves the current state of the application to a file using the serialization
 * mechanism. If no file is currently associated with the application, asks the user for
 * the name of the file to save to.
 */
class DoSaveFile extends Command<ErpManager> {

  DoSaveFile(ErpManager receiver) {
    super(Label.SAVE_FILE, receiver);
  }

  @Override
  protected final void execute() {
    try {
      try {
        _receiver.save();
      } catch (MissingFileAssociationException e) {
        String filename = Form.requestString(Prompt.newSaveAs());
        _receiver.saveAs(filename);
      }
    } catch (IOException e) {
      _display.popup(e.getMessage());
    }
  }
}
