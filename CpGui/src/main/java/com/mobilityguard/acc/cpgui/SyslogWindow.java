package com.mobilityguard.acc.cpgui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.mobilityguard.acc.controller.JsonController;
import com.mobilityguard.acc.data.DataTypeInfo;
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
    private TextField tf1 = new TextField();
    private TextField tf2 = new TextField();
    private TextField tf3 = new TextField();
    private TextField tf4 = new TextField();
    private TextField tf5 = new TextField();
    private TextField tf6 = new TextField();
    private TextField tf7 = new TextField();
    private TextField tf8 = new TextField();    
    private JSONObject IpObj = null;
    private ArrayList<String> listSyslog= null;
	private ArrayList<String> ipNotInList = new ArrayList<String>(); 
    private String tfA ;
    private String tfB ;
    private String tfC ;
    private String tfD ;
    List<TextField> listf;
    TextField tf;
    int i = 0;
    int num = 0;
	int count = 0;

    /**
     * create Access gui and return grid layout.
     */
    @SuppressWarnings("serial")
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
        gd.addComponent(sysTitle,0,1);
        gd.addComponent(allowTitle,0,2);
        gd.addComponent(example,0,3);
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setStyleName("buttonBackground");
        final Button save = new Button("save");
        save.setStyleName("saveButton");
		final Button cancelButton = new Button("cancel");
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
		gd.addComponent(tf1,2,5);
		gd.addComponent(tf2,2,6);
		gd.addComponent(tf3,2,7);
		gd.addComponent(tf4,2,8);
		gd.addComponent(tf5,2,9);
		gd.addComponent(tf6,2,10);
		gd.addComponent(tf7,2,11);
		gd.addComponent(tf8,2,12);	
        for (i = 0 ; i < listSyslog.size() ; i++){
        		if (listSyslog.get(i)!=null) {
        			gd.addComponent( new TextField() {{ setValue(listSyslog.get(i)); } {setEnabled(false);}},1,i+5);
        		}else if  (listSyslog.get(i) == null){
        			gd.addComponent(new TextField(),1,i+5);
        		}		
        } 
      clickCancel(cancelButton);
      clickSave(save, arrayListIp);
      buttonLayout.addComponents(save,cancelButton);
      buttonLayout.setSpacing(true);
      gd.addComponent(buttonLayout,2,21);
      layoutSys.addComponent(gd);
      return sysWindow;
    }

	/**
	 * @return
	 */
	private Button clickCancel(final Button cancelButton) {
        cancelButton.setStyleName("cancelButton");
        cancelButton.addClickListener(new Button.ClickListener() {	
			@Override
			public void buttonClick(ClickEvent event) {
				tf1.clear();
				tf2.clear();
				tf3.clear();
				tf4.clear();
				tf5.clear();
				tf6.clear();
				tf7.clear();
				tf8.clear();			
			}
		});
		return cancelButton;
	}

	/**
	 * @param save
	 * @param arrayListIp
	 */
	private void clickSave(final Button save, final ArrayList<String> arrayListIp) {
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
		        final JSONObject obj = new JSONObject();
		        final JSONArray array = new JSONArray();
						        array.add(tfA);
						        array.add(tfB);
						        array.add(tfC);
						        array.add(tfD);
						        obj.put("Syslog", array);
				final JsonController controller = new JsonController();
				try {
					final JSONObject writeJsonInAccessFile = controller.writeJsonInSyslogFile(obj);
					writeJsonInAccessFile.toString();
					Notification.show("Save new list in the ip file" + writeJsonInAccessFile.toString());
				} catch (IOException | ParseException e) {
					e.printStackTrace();
				}
			}
		});
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

