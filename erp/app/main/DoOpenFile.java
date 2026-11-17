package erp.app.main;

import erp.app.exception.FileOpenFailedException;
import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.io.IOException;

import pt.tecnico.uilib.forms.Form;
import erp.core.exception.MissingFileAssociationException;
import erp.core.exception.UnavailableFileException;

/**
 * Replaces the current state of the application with the state previously saved to a
 * file. Asks for the file name.
 */
class DoOpenFile extends Command<ErpManager> {

   DoOpenFile(ErpManager receiver) {
     super(Label.OPEN_FILE, receiver);
   }

   @Override
   protected final void execute() throws CommandException {
    if (_receiver.isModified()) {
      boolean save = Form.confirm(Prompt.saveBeforeExit());

      if (save) {
        try {
          saveCurrentState();
        } catch (IOException e) {
          _display.popup(e.getMessage());
          return;
        }
      }
    }
    String filename = Form.requestString(Prompt.openFile());
    try {
      _receiver.load(filename);
    } catch (UnavailableFileException e) {
      throw new FileOpenFailedException(e);
    }
  }

  private void saveCurrentState() throws IOException {
    try {
      _receiver.save();
    } catch (MissingFileAssociationException e) {
      String filename = Form.requestString(Prompt.newSaveAs());
      _receiver.saveAs(filename);
    }
  }
}
