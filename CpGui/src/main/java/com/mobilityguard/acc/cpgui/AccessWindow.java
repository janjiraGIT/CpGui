package com.mobilityguard.acc.cpgui;

import org.json.simple.JSONObject;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class AccessWindow {
    private static final String IP_OF_ONE_GATE_SERVER = "Ip of OneGate Server:";
    private static final String CONTROL_PANEL_ACCESS = "Control panel access";
    private static final String ADMIN_PASSWORD_AGAIN = "Admin password again:";
    private static final String ADMIN_PASSWORD = "Admin password : ";
    private static final String ADMIN_USER = "Admin User : ";
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
        //final ButtonsFactory buttons = new ButtonsFactory();
        createChangeButton(gd);
        layoutAc.addComponent(gd);
        return acWindow;
    }
    
    private void createChangeButton(final GridLayout grid) {
        final VerticalLayout buttonLayout = new VerticalLayout();
        final Button change = new Button("Change");
        change.setStyleName("changeButton");
        buttonLayout.addComponents(change);
        grid.addComponent(buttonLayout,1,15);
        change.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                //final UserChangWindow ucw = new UserChangWindow();
                //ucw.changeUser(grid);
                changeUser(grid);
                String ok = "ok";
                String cancel = "cancel";
                final int col = 3;
                final int row = 20;
                createSaveCancelButtons(grid,ok,cancel,col,row );
            }
        });
    }
    /**
     * create button for save and cancel.
     */
    private void createSaveCancelButtons(final GridLayout grid, final String okSave,
                                    final String cancel, final int row, final int col) {
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setStyleName("buttonBackground");
        final Button save = new Button(okSave);
        save.setStyleName("saveButton");
        final Button cancelButton = new Button(cancel);
        cancelButton.setStyleName("cancelButton");
        buttonLayout.addComponents(save,cancelButton);
        buttonLayout.setSpacing(true);
        grid.addComponent(buttonLayout,row,col);
    }
    /**
     * @param grid.
     */
    private void changeUser(final GridLayout grid) {
        //final Window userWindow = new Window("Change admin credentials");
        final VerticalLayout layoutUser = new VerticalLayout();
        layoutUser.setSizeFull();
        final Label adUser = new Label(ADMIN_USER);
        adUser.setStyleName("adUser");
        final Label adPass = new Label(ADMIN_PASSWORD);
        adPass.setStyleName("adPass");
        final Label adPassAgain = new Label(ADMIN_PASSWORD_AGAIN);
        adPassAgain.setStyleName("adPassAgain");
        final TextField tfUser = new TextField();
        final TextField tfPass = new TextField();
        final TextField tfPassAgain = new TextField();

        grid.addComponent(adUser,0,16);
        grid.addComponent(adPass,0,17);
        grid.addComponent(adPassAgain,0,18);
        grid.addComponent(tfUser,1,16);
        grid.addComponent(tfPass,1,17);
        grid.addComponent(tfPassAgain,1,18);
        
        tfUser.addValueChangeListener(event -> {
        	String userTf = event.getValue();
        });
        tfPass.addValueChangeListener(event -> {
        	String passTf = event.getValue();
        });
        tfPassAgain.addValueChangeListener(event -> {
        	String passAgainTf = event.getValue();
        });
        
    }
}