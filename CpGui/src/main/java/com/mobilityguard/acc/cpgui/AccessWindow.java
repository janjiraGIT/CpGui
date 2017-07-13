package com.mobilityguard.acc.cpgui;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class AccessWindow {
    private static final String IP_OF_ONE_GATE_SERVER = "Ip of OneGate Server:";
    private static final String CONTROL_PANEL_ACCESS = "Control panel access";
    int num = 0;

    /**
     * create Access gui and return grid layout.
     */
    public Window createAccessGui() {
        final Window acWindow = new Window("Access Window");
        final VerticalLayout layoutAc = new VerticalLayout();

        layoutAc.setSizeFull();
        acWindow.setContent(layoutAc);
        acWindow.setPositionX(300);
        acWindow.setPositionY(65);
        acWindow.setHeight("75%");
        acWindow.setWidth("35%");

        final GridLayout gd = new GridLayout(7,30);
        gd.addStyleName("gdAccessWindow");
        gd.setWidth("600px");
        gd.setHeight("600px");

        final Label accessTitle = new Label(CONTROL_PANEL_ACCESS);
        accessTitle.addStyleName("accessTitle");
        final Label ipTitle = new Label(IP_OF_ONE_GATE_SERVER);
        ipTitle.addStyleName("ipTitle");
        final Label example = new Label("Example: 192.168.1.1 or 192.168.1.0/24");
        example.addStyleName("example");

        gd.addComponent(accessTitle,0,1);
        gd.addComponent(ipTitle,0,2);
        gd.addComponent(example,0,3);
        int num = 0;
        for (num = 3 ; num < 12 ; num++ ) {
            final TextField tf = new TextField();
            gd.addComponent(tf,1,num);
        }
        final Label cAdminLb = new Label("Current admin user id:");
        cAdminLb.setStyleName("cAdminLb");
        final TextField cAdminTf = new TextField();
        cAdminTf.setValue("Anders Vidal");
        cAdminTf.setEnabled(false);
        gd.addComponent(cAdminLb,0,14);
        gd.addComponent(cAdminTf,1,14);
        final ButtonsFactory buttons = new ButtonsFactory();
        buttons.createChangeButton(gd);
        layoutAc.addComponent(gd);
        return acWindow;
    }
}