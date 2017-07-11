package com.mobilityguard.acc.cpgui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class TlsWindow implements Upload.SucceededListener, Upload.FailedListener, Upload.Receiver {
    Embedded files = new Embedded("Upload Image");
	
	public Window createTlsWindow(){
		final Window tlsWindow = new Window("TLS Window");
	    final VerticalLayout tlsLayout = new VerticalLayout();
	    
	    tlsLayout.setSizeFull();
	    tlsWindow.setContent(tlsLayout);
	    tlsWindow.setPositionX(300);
	    tlsWindow.setPositionY(65);
	    tlsWindow.setHeight("85%");
	    tlsWindow.setWidth("55%");
	    
	    final GridLayout tlsGrid = new GridLayout(7,30);
	    tlsGrid.addStyleName("tlsGrid");
	    tlsGrid.setWidth("600px");
	    tlsGrid.setHeight("600px");
	    
	    final Label tlsTitle = new Label("Current TLS Certificate:");
	    tlsTitle.addStyleName("tlsTitle");
	    final Label tlsText = new Label();
	    tlsText.addStyleName("tlsText");
	    final Label tlsText2 = new Label();
	    tlsText.addStyleName("tlsText2");
	    tlsText.setWidth("80%");
	    tlsText.setHeight("30%");
	    tlsText2.setWidth("80%");
	    tlsText2.setHeight("30%");
	    tlsText.setValue(" text 1 .......");
	    tlsText2.setValue("text 2 .......");
	    
	    tlsGrid.addComponent(tlsTitle,0,1);
	    tlsGrid.addComponent(tlsText,0,2);
	    tlsGrid.addComponent(tlsText2,0,6);
	    
	    final Upload upload = new Upload();
	    upload.setImmediateMode(false);
	    upload.setButtonCaption("Upload");
	    upload.setStyleName("upload");
	    tlsGrid.addComponent(upload,0,15);
	    
	    
	    files = new Embedded("Upload Image");
	    files.setVisible(false);
	    tlsGrid.addComponent(files);
	    
	    
	    final TextField tlsTf = new TextField("Password:");
	    tlsGrid.addComponent(tlsTf,0,18);
	    
	    

	    tlsLayout.addComponent(tlsGrid);
	   
		return tlsWindow;
		
	}
	public class ImageUploader implements Receiver, SucceededListener{
		private static final long serialVersionUID = -1276759102490466761L;
		public File file;
	
		@Override
		public OutputStream receiveUpload(String filename, String mimeType) {
			FileOutputStream fos = null;
			try{
				file = new File("/Users/janjiraeriksson/code/git/CpGui/CpGui/jsonFile/"+filename);
				fos = new FileOutputStream(file);
			}catch (Exception e){
				System.out.println(e.getStackTrace());	
				return null;
			}		
			return fos;
		}

		@Override
		public void uploadSucceeded(SucceededEvent event) {
			// TODO Auto-generated method stub
			files.setVisible(true);
			files.setSource(new FileResource(file));			
		}
	}
	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void uploadFailed(FailedEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void uploadSucceeded(SucceededEvent event) {
		// TODO Auto-generated method stub
		
	}
	

}
