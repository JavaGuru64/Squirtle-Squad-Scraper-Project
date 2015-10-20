package edu.mansfield.squirtle_squad.delegates;

import edu.mansfield.squirtle_squad.user_interface.ScanningGUI;
import edu.mansfield.squirtle_squad.user_interface.SubGUI;

public interface StartGUIDelegate {
	public void releaseSubGUIs(SubGUI scanningGUI);
}
