package com.mobilityguard.acc.cpgui;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class SyslogWindow {
    private static final String ALLOW_SYS_FROM = "Allow syslog from :";
    private static final String SYS_SETTING = "Syslog Settings";
    int num = 0;

    /**
     * create Access gui and return grid layout.
     */
    public Window createSyslogGui() {
        final Window sysWindow = new Window("Syslog Window");
        
        final VerticalLayout layoutSys = new VerticalLayout();

        layoutSys.setSizeFull();
        sysWindow.setContent(layoutSys);
        sysWindow.setPositionX(300);
	    sysWindow.setPositionY(65);
	    sysWindow.setHeight("85%");
	    sysWindow.setWidth("55%");

        final GridLayout gd = new GridLayout(7,30);
        gd.addStyleName("gdSysWindow");
        gd.setWidth("600px");
        gd.setHeight("600px");

        final Label sysTitle = new Label(SYS_SETTING);
        sysTitle.addStyleName("sysTittle");
        final Label allowTitle = new Label(ALLOW_SYS_FROM);
        allowTitle.addStyleName("allowTitle");

        gd.addComponent(sysTitle,0,1);
        gd.addComponent(allowTitle,0,2);
        int num = 0;
        for (num = 3 ; num < 12 ; num++ ) {
            final TextField tf = new TextField();
            gd.addComponent(tf,1,num);
        }

        final ButtonsFactory buttons = new ButtonsFactory();
        String save = "save";
        String cancel = "cancel";
        int col = 2;
        int row = 21;
        buttons.createSaveCancelButtons(gd, save,cancel, col, row);;

        layoutSys.addComponent(gd);        
        return sysWindow;
    }
}
