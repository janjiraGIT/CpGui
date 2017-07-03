package com.mobilityguard.acc.cpgui;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class AccessGui {
	private static final String IP_OF_ONE_GATE_ACCESS = "IP of OneGate access :";
	private static final String AC_TITLE = "Control Panel Access";
	
	public GridLayout createAccessGui(){
		final GridLayout grid = new GridLayout(7,30);
		grid.addStyleName("grid");
        grid.setWidth("1000px");
        grid.setHeight("200px");
        
        final Label acTitle = new Label(AC_TITLE);
        acTitle.setStyleName("acTitle");
        
        final Label ipOfoneGateAccess= new Label(IP_OF_ONE_GATE_ACCESS);
        ipOfoneGateAccess.setStyleName("ipOfoneGateAccess");
        
        grid.addComponent(acTitle,0,1);
        grid.addComponent(acTitle,1,2);
        int i = 3;
        for (i=3 ; i <10 ; i++ ){
        	TextField tf = new TextField();
        	grid.addComponent(tf,1,i);    	
        }
		return grid;
	}
}
