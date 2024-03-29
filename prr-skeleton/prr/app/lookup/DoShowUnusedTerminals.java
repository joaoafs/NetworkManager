package prr.app.lookup;


import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;
import java.util.ArrayList;
/**
 * Show unused terminals (without communications).
 */
class DoShowUnusedTerminals extends Command<Network> {

  DoShowUnusedTerminals(Network receiver) {
    super(Label.SHOW_UNUSED_TERMINALS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<String> unusedTerminals = new ArrayList<>(_receiver.showUnusedTerminal());

    for (String c : unusedTerminals){
      _display.addLine(c);
    }
    _display.display();
  }
}
