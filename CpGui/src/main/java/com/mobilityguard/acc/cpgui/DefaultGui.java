package com.mobilityguard.acc.cpgui;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class DefaultGui {
	
	public Panel createDefaultGui(){
		Panel panel = new Panel();
		panel.setWidth("100%");
		panel.setHeight("100%");
		panel.setStyleName("panel");
		panel.setContent(new Label ("This is default page"));
		return panel;		
	}
}
