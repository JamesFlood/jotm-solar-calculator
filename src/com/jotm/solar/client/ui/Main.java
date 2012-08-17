package com.jotm.solar.client.ui;

import com.google.gwt.user.client.ui.*;

public class Main extends Composite {
	DockPanel MainPanel = new DockPanel();
	VerticalPanel menuBG = new VerticalPanel();
	Hyperlink lnkHome, lnkHouseSetUp, lnkPanelSetUp;
	VerticalPanel menuOption;
	public Main(){
		MainPanel.setBorderWidth(5);
		MainPanel.setStyleName("main");
		this.initWidget(MainPanel);
		MainPanel.setSize("1600px", "900px");
		
		Image image = new Image("Header.png");
		MainPanel.add(image, DockPanel.NORTH);
		image.setSize("1600px", "200px");
		
		layoutMenu(MainPanel);
		AboutUs aboutUs = new AboutUs();
		aboutUs.setWidth("1400px");
		MainPanel.add(aboutUs, DockPanel.CENTER);
		}

	/**
	 * Create the main navigation menu
	 * @param MainPanel base Panel
	 */
	private void layoutMenu(DockPanel MainPanel) {
		menuBG.setStyleName("menu");
		MainPanel.add(menuBG, DockPanel.WEST);
		menuBG.setSize("200px", "700px");
		
		menuOption = new VerticalPanel();
		menuOption.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		menuOption.setStyleName("h1");
		menuBG.add(menuOption);
		menuOption.setWidth("200px");
		
		lnkHome = new Hyperlink("Home", false, "AboutUs");
		lnkHome.setHTML("About Us");
		lnkHome.setStyleName("menuOption");
		menuOption.add(lnkHome);
		
		lnkHouseSetUp = new Hyperlink("House Set Up", false, "HouseSetUp");
		lnkHouseSetUp.setStyleName("menuOption");
		menuOption.add(lnkHouseSetUp);
		
		lnkPanelSetUp = new Hyperlink("Panel Set Up", false, "PanelSetUp");
		lnkPanelSetUp.setStyleName("menuOption");
		menuOption.add(lnkPanelSetUp);
	}
}
