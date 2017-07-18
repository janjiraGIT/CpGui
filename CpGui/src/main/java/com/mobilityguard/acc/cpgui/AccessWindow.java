package com.mobilityguard.acc.cpgui;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mobilityguard.acc.data.DataTypeInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
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
	private String userTf = null ;
	private String passTf = null ;
	private String passAgainTf = null ;
    private static JSONObject jsonObj = null;
    private TextField cAdminTf ;
    private Map<String, String> itemMap = new HashMap<String,String>();

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
        cAdminTf = new TextField();
        cAdminTf.setValue("Anders Vidal");
        cAdminTf.setEnabled(false);
        gd.addComponent(cAdminLb,0,14);
        gd.addComponent(cAdminTf,1,14);
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
                changeUser(grid);
            }
        });
    }
    /**
     * @param grid.
     */
    private void changeUser(final GridLayout grid) {
        final VerticalLayout layoutUser = new VerticalLayout();
        layoutUser.setSizeFull();
        final Label adUser = new Label(ADMIN_USER);
        adUser.setStyleName("adUser");
        final Label adPass = new Label(ADMIN_PASSWORD);
        adPass.setStyleName("adPass");
        final Label adPassAgain = new Label(ADMIN_PASSWORD_AGAIN);
        adPassAgain.setStyleName("adPassAgain");
        final TextField tfUser = new TextField();
        final PasswordField tfPass = new PasswordField();
        final PasswordField tfPassAgain = new PasswordField();
        grid.addComponent(adUser,0,16);
        grid.addComponent(adPass,0,17);
        grid.addComponent(adPassAgain,0,18);
        grid.addComponent(tfUser,1,16);
        grid.addComponent(tfPass,1,17);
        grid.addComponent(tfPassAgain,1,18);  
        tfUser.addValueChangeListener(event -> {
        	userTf = event.getValue();
        	System.out.println(userTf);
        });
        tfPass.addValueChangeListener(event -> {
        	passTf = event.getValue();
        	System.out.println(passTf);
        });
        tfPassAgain.addValueChangeListener(event -> {
        	passAgainTf = event.getValue();
        	System.out.println(passAgainTf);
        });
        
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setStyleName("buttonBackground");
        final Button saveButton = new Button("ok");
        saveButton.setStyleName("saveButton");
        saveButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (userTf == null || passTf == null || passAgainTf == null) {
					Notification.show("Input error: Admin user and/or Admin password missing.");				
				}else if (passTf.equals(passAgainTf)) {
					checkAuthentication(userTf,passTf);	
				}else if (passTf != passAgainTf){
					Notification.show("Non matching passwords: You need to enter same password.");	
				}
			}
		});
        final Button cancelButton = new Button("cancel");
        cancelButton.setStyleName("cancelButton");
        cancelButton.addClickListener(new Button.ClickListener() {	
			@Override
			public void buttonClick(ClickEvent event) {
				tfUser.clear();;
				tfPass.clear();;
				tfPassAgain.clear();
			}
		});
        buttonLayout.addComponents(saveButton,cancelButton);
        buttonLayout.setSpacing(true);
        grid.addComponent(buttonLayout,3,20);    
    }
    public void checkAuthentication(final String userTf , final String passTf ){
    	final DataTypeInfo data = new DataTypeInfo();
    	jsonObj = data.getAdmins();
    	final JSONArray adminObj = (JSONArray) jsonObj.get("Admin");
	    	for (int i=0;i<adminObj.size();i++) {
	    		final JSONObject obj = (JSONObject) adminObj.get(i);
	    		final String objUser = (String) obj.get("userid");
	    		final String objPassword = (String) obj.get("password");
	    			itemMap.put(objUser,objPassword);
	    	}
	    System.out.println("Item in map :" + itemMap.toString());
	    if (itemMap.containsKey(userTf)) {
	    	 final String password = itemMap.get(userTf);
	    	 if (passTf.equals(password)){
	    		 Notification.show("Admin User has changs to " + userTf);
	    	       cAdminTf.setValue(userTf);
	    	       cAdminTf.setEnabled(false);
	    	 }else if(passTf != password) {
	    		 Notification.show("Wrong password : please try agin.");
	    	 }
	    }
    }
}