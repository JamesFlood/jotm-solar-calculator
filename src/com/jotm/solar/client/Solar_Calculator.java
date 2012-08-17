package com.jotm.solar.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.jotm.solar.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Solar_Calculator implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */


	public void onModuleLoad() {
		//Image image = new Image("Header.png");
		//image.setSize("1600px", "200px");
		//RootPanel.get().add(image, 0, 0);
		RootPanel.get().add(new Main(),0,0);
		RootPanel.get().setPixelSize(1600, 900);
		//RootPanel.get().set
	}
}
