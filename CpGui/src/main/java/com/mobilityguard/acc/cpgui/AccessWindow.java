package com.mobilityguard.acc.cpgui;

import com.mobilityguard.acc.controller.JsonController;
import com.mobilityguard.acc.data.DataTypeInfo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AccessWindow {
    private static final String IP_OF_ONE_GATE_SERVER = "Ip of OneGate Server:";
    private static final String CONTROL_PANEL_ACCESS = "Control panel access";
    private static final String ADMIN_PASSWORD_AGAIN = "Admin password again:";
    private static final String ADMIN_PASSWORD = "Admin password : ";
    private static final String ADMIN_USER = "Admin User : ";
    private static final String ACCESS_URL = "/opt/acc/config/access.json";
    private static final String ADMIN_URL = "/opt/acc/config/admin.json";
    private String userTf = null;
    private String passTf = null;
    private String passAgainTf = null;
    private static JSONObject jsonObj = null;
    private TextField caAdminTf;
    private Map<String, String> itemMap = new HashMap<String,String>();
    private Button change;
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
    private static final Logger log = LoggerFactory.getLogger(AccessWindow.class);

    /**
     * create Access gui and return grid layout.
     */
    public Window createAccessGui(final JSONObject jsonObj) {
        final Window acWindow = new Window();
        final VerticalLayout layoutAc = new VerticalLayout();
        final Panel panel = new Panel("Access Window");
        acWindow.setContent(panel);
        panel.setContent(layoutAc);
        final JSONObject cpaObj = (JSONObject) jsonObj.get("Control Panel Access");
        final JSONArray adminObj = (JSONArray) jsonObj.get("Admin");
        final JSONArray IpObj = (JSONArray) cpaObj.get("Ip");
        final JSONObject Ip1 = (JSONObject) IpObj.get(0);
        for (int i = 0; i < adminObj.size(); i++ ) {
            JSONObject obj = (JSONObject) adminObj.get(i);
            if (obj.get("userid") != null) {
                userStr = (String) obj.get("userid");
            } else {
                log.error("Fel : user id is null");
            }
        }
        if  (Ip1.get("ip1") == null || Ip1.get("ip2") == null || Ip1.get("ip3") == null)  {
            ip1 = "null";
            ip2 = "null";
            ip3 = "null";
        }
        if (Ip1.get("ip1") != null ) {
            ip1 = (String) Ip1.get("ip1");
            tf1.setValue(ip1.toString());
            tf1.setEnabled(false);
            log.info("Ip1", ip1);
        }
        if (Ip1.get("ip2") != null ) {
            ip2 = (String) Ip1.get("ip2");
            tf2.setValue(ip2.toString());
            tf2.setEnabled(false);
            log.info("Ip2", ip2);
        }
        if (Ip1.get("ip3") != null) {
            ip3 = (String) Ip1.get("ip3");
            tf3.setValue(ip3.toString());
            tf3.setEnabled(false);
            log.info("Ip3", ip3);
        }
        tf4.setEnabled(false);
        tf5.setEnabled(false);
        tf6.setEnabled(false);
        acWindow.setPositionX(300);
        acWindow.setPositionY(65);
        acWindow.setHeight("66.5%");
        acWindow.setWidth("32%");

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
        caAdminTf = new TextField();
        caAdminTf.setValue(userStr);
        caAdminTf.setEnabled(false);
        gd.addComponent(cAdminLb,0,14);
        gd.addComponent(caAdminTf,1,14);
        createChangeButton(gd);
        layoutAc.addComponent(gd);
        return acWindow;
    }

    @SuppressWarnings("serial")
    private void createChangeButton(final GridLayout grid) {
        final VerticalLayout buttonLayout = new VerticalLayout();
        change = new Button("Change");
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
                change.setEnabled(false);
                changeUser(grid);
            }
        });
    }

    /**
     * @param grid.
     */
    @SuppressWarnings("serial")
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
            log.info(userTf);
        });
        tfPass.addValueChangeListener(event -> {
            passTf = event.getValue();
            log.info(passTf);
        });
        tfPassAgain.addValueChangeListener(event -> {
            passAgainTf = event.getValue();
            log.info(passAgainTf);
        });

        final HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setStyleName("buttonBackground");
        final Button saveButton = new Button("ok");
        saveButton.setStyleName("saveButton");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                if (userTf == null || passTf == null || passAgainTf == null) {
                        Notification.show("Input error: Admin user and/or Admin password missing.");
                } else if (passTf.equals(passAgainTf)) {
                    try {
                        checkAuthentication(userTf,passTf);
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                } else if (passTf != passAgainTf) {
                    Notification.show("Non matching passwords: You need to enter same password.");
                } else {
                    Notification.show("Input error: Admin user and/or Admin password wrong.");
                }
            }
        });
        final Button cancelButton = new Button("cancel");
        cancelButton.setStyleName("cancelButton");
        cancelButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                tfUser.clear();
                tfPass.clear();
                tfPassAgain.clear();
                tfUser.setEnabled(true);
                tfPass.setEnabled(true);
                tfPassAgain.setEnabled(true);
                tf1.setEnabled(true);
                tf2.setEnabled(true);
                tf3.setEnabled(true);
                tf4.setEnabled(true);
                tf5.setEnabled(true);
                tf6.setEnabled(true);
                change.setEnabled(false);
            }
        });
        buttonLayout.addComponents(saveButton,cancelButton);
        buttonLayout.setSpacing(true);
        grid.addComponent(buttonLayout,3,20);
    }

    /**
     * Check user and password.
     */
    public void checkAuthentication(final String userTf , final String passTf ) throws IOException, ParseException {
        final DataTypeInfo data = new DataTypeInfo();
        jsonObj = data.getData(ADMIN_URL);
        final JSONArray adminObj = (JSONArray) jsonObj.get("Admin");
        for (int i = 0; i < adminObj.size(); i++) {
            final JSONObject obj = (JSONObject) adminObj.get(i);
            final String objUser = (String) obj.get("userid");
            final String objPassword = (String) obj.get("password");
            itemMap.put(objUser,objPassword);
        }
        log.info("Item in map :" + itemMap.toString());
        if (itemMap.containsKey(userTf)) {
            final String password = itemMap.get(userTf);
            if (passTf.equals(password)) {
                Notification.show("Admin User has changs to " + userTf);
                caAdminTf.setValue(userTf);
                caAdminTf.setEnabled(false);
                tf1.setEnabled(false);
                tf2.setEnabled(false);
                tf3.setEnabled(false);
                tf4.setEnabled(false);
                tf5.setEnabled(false);
                tf6.setEnabled(false);
                tfUser.setEnabled(false);
                tfPass.setEnabled(false);
                tfPassAgain.setEnabled(false);
                buildJson(tf1,tf2,tf3,userTf,passTf);
            } else if (passTf != password) {
                Notification.show("Wrong password : please try agin.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void buildJson(final TextField tf1,final TextField tf2,final TextField tf3,
                    final String userTf, final String passTf) throws IOException, ParseException {
        final String tfA = tf1.getValue();
        final String tfB = tf2.getValue();
        final String tfC = tf3.getValue();
        final JSONObject ca = new JSONObject();
        final JSONObject IpObj = new JSONObject();
        ca.put("Control Panel Access", IpObj);
        final JSONArray ipArray = new JSONArray();
        final JSONObject supIp = new JSONObject();
        supIp.put("ip1",tfA.toString());
        supIp.put("ip2",tfB.toString());
        supIp.put("ip3",tfC.toString());
        ipArray.add(supIp);
        IpObj.put("Ip", ipArray);
        final JSONArray adminArray = new JSONArray();
        final JSONObject user = new JSONObject();
        user.put("userid", userTf);
        user.put("password",passTf);
        adminArray.add(user);
        ca.put("Admin", adminArray);
        final JsonController controller = new JsonController();
        controller.writeIntoFile(ACCESS_URL, ca);
    }
}