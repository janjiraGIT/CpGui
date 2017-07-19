package com.mobilityguard.acc.cpgui;

import java.io.FileWriter;
import java.io.IOException;
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
    private TextField tf1 = new TextField();
    private TextField tf2 = new TextField();
    private TextField tf3 = new TextField();
    private TextField tf4 = new TextField();
    private TextField tf5 = new TextField();
    private TextField tf6 = new TextField();
    private TextField tfUser = null;
    private PasswordField tfPass = null;
    private PasswordField tfPassAgain = null;
    private String ip1 = null;
    private String ip2 = null;
    private String ip3 = null;
    private String userStr = null;
	private FileWriter file;

    /**
     * create Access gui and return grid layout.
     */
    public Window createAccessGui(final JSONObject jsonObj) {
        final Window acWindow = new Window("Access Window");
        final VerticalLayout layoutAc = new VerticalLayout();
        final JSONObject cpaObj = (JSONObject) jsonObj.get("Control Panel Access");
        final JSONArray adminObj = (JSONArray) jsonObj.get("Admin");
        
        final JSONArray IpObj = (JSONArray) cpaObj.get("Ip");
        final JSONObject Ip1 = (JSONObject) IpObj.get(0);
        for (int i = 0; i< adminObj.size(); i++ ){   
        	JSONObject obj = (JSONObject) adminObj.get(i);
        	if (obj.get("userid")!=null){
        		userStr = (String) obj.get("userid");
        	}
        	else {
        		System.out.println("Fel");
        	}
        }
        if  (Ip1.get("ip1") == null || Ip1.get("ip2") == null || Ip1.get("ip3") == null)  {
        	ip1 = "null";
        	ip2 = "null";
        	ip3 = "null";
        }if (Ip1.get("ip1")!=null){
        	ip1 = (String) Ip1.get("ip1"); 
            tf1.setValue(ip1.toString());
            tf1.setEnabled(false);
            System.out.println("IP1" + ip1);
        }if (Ip1.get("ip2")!=null){
        	ip2 = (String) Ip1.get("ip2"); 
        	tf2.setValue(ip2.toString());
        	tf2.setEnabled(false);
        	System.out.println(" IP2" + ip2);
        }if (Ip1.get("ip3")!=null){
        	ip3 = (String) Ip1.get("ip3"); 
        	tf3.setValue(ip3.toString());
        	tf3.setEnabled(false);
        	System.out.println(" IP3" + ip3);
        }	
        	tf4.setEnabled(false);
        	tf5.setEnabled(false);
        	tf6.setEnabled(false);
            System.out.println("Here :" + Ip1 );
            System.out.println(" ip1 : " + ip1 + "ip2 : " + ip2 + "ip3 : " + ip3 );
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
        gd.addComponent(tf1,1,4);
        gd.addComponent(tf2,1,5);
        gd.addComponent(tf3,1,6);
        gd.addComponent(tf4,1,7);
        gd.addComponent(tf5,1,8);
        gd.addComponent(tf6,1,9);
   
        final Label cAdminLb = new Label("Current admin user id:");
        cAdminLb.setStyleName("cAdminLb");
        cAdminTf = new TextField();
        cAdminTf.setValue(userStr);
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
                tf1.setEnabled(true);
                tf2.setEnabled(true);
                tf3.setEnabled(true);
                tf4.setEnabled(true);
                tf5.setEnabled(true);
                tf6.setEnabled(true);
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
        tfUser = new TextField();
        tfPass = new PasswordField();
        tfPassAgain = new PasswordField();
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
				}else {
					Notification.show("Input error: Admin user and/or Admin password wrong.");
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
                tf1.setEnabled(true);
                tf2.setEnabled(true);
                tf3.setEnabled(true);
                tf4.setEnabled(true);
                tf5.setEnabled(true);
                tf6.setEnabled(true);
			}
		});
        buttonLayout.addComponents(saveButton,cancelButton);
        buttonLayout.setSpacing(true);
        grid.addComponent(buttonLayout,3,20);    
    }

    /**
     * 
     */
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
	                tf1.setEnabled(false);
	                tf2.setEnabled(false);
	                tf3.setEnabled(false);
	                tf4.setEnabled(false);
	                tf5.setEnabled(false);
	                tf6.setEnabled(false);
	                tfUser.setEnabled(false);
	                tfPass.setEnabled(false);
	                tfPassAgain.setEnabled(false);
	                
	    	     buildJson(tf1,tf2,tf3,userTf); 
	    	 }else if(passTf != password) {
	    		 Notification.show("Wrong password : please try agin.");
	    	 }
	    }
    }
    @SuppressWarnings("unchecked")
	private void buildJson(final TextField tf1,final TextField tf2,final TextField tf3, final String userTf){
    	String tfA = tf1.getValue();
    	String tfB = tf2.getValue();
    	String tfC = tf3.getValue(); 	
		JSONObject ca = new JSONObject();
		JSONObject IpObj = new JSONObject();	
		ca.put("Control Panel Access", IpObj);
		JSONArray ipArray = new JSONArray();
		JSONObject supIp = new JSONObject();	
		supIp.put("ip1",tfA.toString());
		supIp.put("ip2",tfB.toString());
		supIp.put("ip3",tfC.toString());	
		ipArray.add(supIp);
		IpObj.put("Ip", ipArray);
		
		JSONArray adminArray = new JSONArray();
		JSONObject user = new JSONObject();
		user.put("userid", userTf);
		adminArray.add(user);
		ca.put("Admin", adminArray);
		System.out.println(ca.toString());	
		String FILE = "./jsonFile/test.json";
		try {
			file = new FileWriter(FILE);
			file.write(ca.toString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}