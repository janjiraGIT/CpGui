package com.mobilityguard.acc.cpgui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mobilityguard.acc.data.DataTypeInfo;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class SyslogWindow {
    private static final String ALLOW_SYS_FROM = "Allow syslog from :";
    private static final String SYS_SETTING = "Syslog Settings";
    int num = 0;
	int count = 0;
    private String ip = null;
    private TextField tf = new TextField();
    private TextField tf1 = new TextField();
    private TextField tf2 = new TextField();
    private TextField tf3 = new TextField();
    private TextField tf4 = new TextField();
    private TextField tf5 = new TextField();
    private TextField tf6 = new TextField();
    private TextField tf7 = new TextField();
    private TextField tf8 = new TextField();
    private JSONObject IpObj = null;
    private ArrayList<TextField> listTf = new ArrayList<TextField>();
    private ArrayList<String> listSyslog= null;
	private ArrayList<String> ipNotInList = new ArrayList<String>(); 
    String tfA ;
    String tfB ;
    String tfC ;
    String tfD ;
    String tfE ;
	private FileWriter file;

    /**
     * create Access gui and return grid layout.
     */
    public Window createSyslogGui(final JSONObject syslog) {
        final Window sysWindow = new Window("Syslog Settings Window");
        final VerticalLayout layoutSys = new VerticalLayout();
        
        listSyslog = getSyslog(syslog);

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

        //TEST :
        // Check size of Syslog data and crate textfield 
//        for(int i = 0 ; i < listSyslog.size(); i++){
//        	listTf.add(tf); // textfield crate same size of syslog data // not work vaadin not allow user same tf.
//
//        }
//   	System.out.println(listTf.toString());
        listTf.add(tf1);
        listTf.add(tf2);
        listTf.add(tf3);
        listTf.add(tf4);
        listTf.add(tf5);
        listTf.add(tf6);
        listTf.add(tf7);
        listTf.add(tf8);

        gd.addComponent(sysTitle,0,1);
        gd.addComponent(allowTitle,0,2);
        gd.addComponent(example,0,3);
        gd.addComponent(tf1,1,4);
        gd.addComponent(tf2,1,5);
        gd.addComponent(tf3,1,6);
        gd.addComponent(tf4,1,7);
        gd.addComponent(tf5,1,8);
        gd.addComponent(tf6,1,9);
        gd.addComponent(tf7,1,10);
        gd.addComponent(tf8,1,11);    
       // for(int i = 0 ; i < listTf.size(); i++){
//        	for (int row = 4 ; row < 12; row++){
//        		gd.addComponent(tf1);
        		//gd.addComponent(listTf.get(i),1,row);
//        		if (listSyslog.get(i) != null){
//        			listTf.get(i).setValue(listSyslog.get(i));
//        		}
//        }
        
        if (listSyslog.get(0)!=null){
            tf1.setValue(listSyslog.get(0));
        }else {
        	tf1.setValue(" ");
        }if (listSyslog.get(1)!=null){
            tf2.setValue(listSyslog.get(1));
        }else {
        	tf2.setValue(" ");
        }if (listSyslog.get(2)!=null){
            tf3.setValue(listSyslog.get(2));
        }else {
        	tf3.setValue(" ");
        }
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setStyleName("buttonBackground");
        final Button save = new Button("save");
        save.setStyleName("saveButton");
        final DataTypeInfo data = new DataTypeInfo();
        // get list of ip address.
        IpObj = data.getIP();
        System.out.println("IP : " + IpObj.toString());
        final JSONArray ipArray = (JSONArray) IpObj.get("IP");
        System.out.println("ipArray : " + ipArray.toString());
        @SuppressWarnings("unchecked")
		final Iterator<String> iteratorListIp = ipArray.iterator();
        final ArrayList<String> arrayListIp = new ArrayList<String>();
        while (iteratorListIp.hasNext()){
        	String objIp = iteratorListIp.next();
        	arrayListIp.add(objIp);
        }
    	System.out.println("ArrayListIp " + arrayListIp); 
        save.addClickListener(new Button.ClickListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(ClickEvent event) {
			  	for (int i = 0; i < arrayListIp.size(); i++){
		    		if ( tf1.getValue().equals(arrayListIp.get(i))){
		    			tfA = tf1.getValue();
		    		}else if (!tf1.getValue().equals(arrayListIp.get(i))){
		    			String tfX = tf1.getValue();
		    			ipNotInList.add(tfX);
		    		}if ( tf2.getValue().equals(arrayListIp.get(i))){
		        		tfB = tf2.getValue();
		    		}else if (!tf2.getValue().equals(arrayListIp.get(i))){
		    			String tfY = tf2.getValue();
		    			ipNotInList.add(tfY);
		        	}if ( tf3.getValue().equals(arrayListIp.get(i))){
		        		tfC = tf3.getValue();
		    		}else if (!tf3.getValue().equals(arrayListIp.get(i))){
		    			String tfZ = tf3.getValue();
		    			ipNotInList.add(tfZ);
		            }if ( tf4.getValue().equals(arrayListIp.get(i))){
		            	tfD = tf4.getValue();  
		    		}else if (!tf4.getValue().equals(arrayListIp.get(i))){
		    			String tfK = tf4.getValue();
		    			ipNotInList.add(tfK);
		    		} 
		         }
				Notification.show("Save new list in the ip file");
				
		        final JSONObject obj = new JSONObject();
		        final JSONArray array = new JSONArray();
		        array.add(tfA);
		        array.add(tfB);
		        array.add(tfC);
		        array.add(tfD);
		        obj.put("Syslog", array);
		        String FILE = "./jsonFile/syslog.json";
				try {
					file = new FileWriter(FILE);
					file.write(obj.toString());
					file.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
		        

				
			}
		});
  
        final Button cancelButton = new Button("cancel");
        cancelButton.setStyleName("cancelButton");
        buttonLayout.addComponents(save,cancelButton);
        buttonLayout.setSpacing(true);
        gd.addComponent(buttonLayout,2,21);
        layoutSys.addComponent(gd);
        return sysWindow;
    }

	/**
	 * @param syslog
	 * @return
	 */
	private ArrayList<String> getSyslog(final JSONObject syslog) {
		final JSONArray sysArray = (JSONArray) syslog.get("Syslog");
    	System.out.println(sysArray); 
        @SuppressWarnings("unchecked")
		Iterator<String> list = sysArray.iterator();
        ArrayList<String> listSyslog = new ArrayList<String>();
        while (list.hasNext()){
        	String obj = list.next();
        	System.out.println("Iterator " + obj);  
        	listSyslog.add(obj);
        	System.out.println("ArrayList " + listSyslog); 
        }
		return listSyslog;
	}
}

