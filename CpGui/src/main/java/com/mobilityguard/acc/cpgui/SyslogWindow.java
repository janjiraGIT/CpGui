package com.mobilityguard.acc.cpgui;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class SyslogWindow {
    private static final String ALLOW_SYS_FROM = "Allow syslog from :";
    private static final String SYS_SETTING = "Syslog Settings";
    int num = 0;
	int count = 0;
    private String ip = null;
    private TextField tf1 = new TextField();
    private TextField tf2 = new TextField();
    private TextField tf3 = new TextField();
    private TextField tf4 = new TextField();
    private TextField tf5 = new TextField();
    private TextField tf6 = new TextField();
    private TextField tf7 = new TextField();
    private TextField tf8 = new TextField();

    /**
     * create Access gui and return grid layout.
     */
    public Window createSyslogGui(final JSONObject syslog) {
        final Window sysWindow = new Window("Syslog Settings Window");
        final VerticalLayout layoutSys = new VerticalLayout();
        final JSONArray sysArray = (JSONArray) syslog.get("Syslog");
    	System.out.println(sysArray); 
        
        @SuppressWarnings("unchecked")
		Iterator<String> list = sysArray.iterator();
        ArrayList<String> arrayList = new ArrayList<String>();
        while (list.hasNext()){
        	String obj = list.next();
        	System.out.println("Iterator " + obj);  
        	arrayList.add(obj);
        	System.out.println("ArrayList " + arrayList); 
        }

        layoutSys.setSizeFull();
        sysWindow.setContent(layoutSys);
        sysWindow.setPositionX(300);
        sysWindow.setPositionY(65);
        sysWindow.setHeight("75%");
        sysWindow.setWidth("45%");

        final GridLayout gd = new GridLayout(7,30);
        gd.addStyleName("gdSysWindow");
        gd.setWidth("600px");
        gd.setHeight("600px");

        final Label sysTitle = new Label(SYS_SETTING);
        sysTitle.addStyleName("sysTittle");
        final Label allowTitle = new Label(ALLOW_SYS_FROM);
        allowTitle.addStyleName("allowTitle");
        final Label example = new Label("Example: 192.168.1.1 or 192.168.1.0/24");
        example.addStyleName("example");

        gd.addComponent(sysTitle,0,1);
        gd.addComponent(allowTitle,0,2);
        gd.addComponent(example,0,3);
        //int num = 0;

//        for (num = 3 ; num < 12 ; num++ ) {
//            final TextField tf = new TextField();
//            ArrayList<TextField> tfArray = new ArrayList<TextField>();
//            tfArray.add(tf);
//            gd.addComponent(tfArray.add(tf),1,num);
//            gd.addComponent(tf,1,num);
//        }
        
        gd.addComponent(tf1,1,4);
        gd.addComponent(tf2,1,5);
        gd.addComponent(tf3,1,6);
        gd.addComponent(tf4,1,7);
        gd.addComponent(tf5,1,8);
        gd.addComponent(tf6,1,9);
        gd.addComponent(tf7,1,10);
        gd.addComponent(tf8,1,11);
        
//        for (int i = 0; i < arrayList.size() ; i++){
//        	count = arrayList.size();
//        	while (i < count) {
//        		ArrayList<TextField> textf = new ArrayList<TextField>();
//        		textf.add(tf1);
//            	System.out.println(textf);
//        	}
//        }
        
        if (arrayList.get(0)!=null){
            tf1.setValue(arrayList.get(0));
        }else {
        	tf1.setValue(null);
        }if (arrayList.get(1)!=null){
            tf2.setValue(arrayList.get(1));
        }else {
        	tf2.setValue(null);
        }if (arrayList.get(2)!=null){
            tf3.setValue(arrayList.get(2));
        }else {
        	tf3.setValue(null);
//        }if (arrayList.get(3)!=null){
//            tf4.setValue(arrayList.get(3));
//        }else {
//        	tf4.setValue(null);
//        }if (arrayList.get(4).isEmpty()){
//        	tf5.setValue(null);
//        	
//        for (int i = 0; i<arrayList.size();i++)
//        	if (arrayList.get(i) != null ){
//                tf1.setValue(arrayList.get(i));
//        	}
        }
//        }else {
//            tf5.setValue(arrayList.get(4));
//        }if (arrayList.get(5)!=null){
//            tf6.setValue(arrayList.get(5));
//        }else {
//        	tf6.setValue(null);
//        }if (arrayList.get(6)!=null){
//            tf7.setValue(arrayList.get(6));
//        }else {
//        	tf7.setValue(null);
//        }if (arrayList.get(7)!=null){
//            tf8.setValue(arrayList.get(7));
//        }else {
//        	tf8.setValue(null);
//
//        }
        

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

