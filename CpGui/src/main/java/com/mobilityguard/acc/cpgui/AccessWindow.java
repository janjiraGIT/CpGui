package com.mobilityguard.acc.cpgui;

import org.json.simple.JSONObject;

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
    public Window createAccessGui(final JSONObject jsonObj) {
        final Window acWindow = new Window("Access Window");
        final VerticalLayout layoutAc = new VerticalLayout();
        JSONObject cpaObj = (JSONObject) jsonObj.get("Control Panel Access");
        JSONObject IpObj = (JSONObject) cpaObj.get("Ip");
        String Ip1 = (String) IpObj.get("ip1");
        String Ip2 = (String) IpObj.get("ip2");
        String Ip3 = (String) IpObj.get("ip3");

        layoutAc.setSizeFull();
        acWindow.setContent(layoutAc);
        acWindow.setPositionX(300);
        acWindow.setPositionY(65);
        acWindow.setHeight("85%");
        acWindow.setWidth("45%");

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

//        for (num = 3 ; num < 12 ; num++ ) {
////            final TextField tf = new TextField();
////            tf.setValue(Ip1);
////            gd.addComponent(tf,1,num);
//        	
//        }
        final TextField tf1 = new TextField();
        final TextField tf2 = new TextField();
        final TextField tf3 = new TextField();
        final TextField tf4 = new TextField();
        final TextField tf5 = new TextField();
        final TextField tf6 = new TextField();

        tf1.setValue(Ip1);
        tf2.setValue(Ip2);
        tf3.setValue(Ip3);
 
        gd.addComponent(tf1,1,4);
        gd.addComponent(tf2,1,5);
        gd.addComponent(tf3,1,6);
        gd.addComponent(tf4,1,7);
        gd.addComponent(tf5,1,8);
        gd.addComponent(tf6,1,9);
   
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