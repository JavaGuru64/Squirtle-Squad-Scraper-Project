package edu.mansfield.squirtle_squad.delegates;

import edu.mansfield.squirtle_squad.user_interface.SubGUI;

public interface StartGUIDelegate {
	public void releaseSubGUI(SubGUI scanningGUI);
	public void makeVisable(SubGUI scanningGUI);
}
