package com.mobilityguard.acc.cpgui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class TlsWindow {
    Embedded files = new Embedded("Upload Image");
	
	public Window createTlsWindow(){
		final Window tlsWindow = new Window("TLS Window");
	    final VerticalLayout tlsLayout = new VerticalLayout();
	    
	    tlsLayout.setSizeFull();
	    tlsWindow.setContent(tlsLayout);
	    tlsWindow.setPositionX(300);
	    tlsWindow.setPositionY(65);
	    tlsWindow.setHeight("85%");
	    tlsWindow.setWidth("65%");
	    
	    final GridLayout tlsGrid = new GridLayout(7,30);
	    tlsGrid.addStyleName("tlsGrid");
	    tlsGrid.setWidth("500px");
	    tlsGrid.setHeight("200px");
	    
	    final Label tlsTitle = new Label("Current TLS Certificate:");
	    tlsTitle.addStyleName("tlsTitle");
	    final Label tlsText = new Label();
	    final Label tlsText2 = new Label();
	    tlsText.setWidth("100%");
	    tlsText.setHeight("20%");
	    tlsText.setValue("dkfjaöljdflöajlfjdlajsdfjkdjfaöffdlaöffffffffffffffffffffffffffffffffffffffffjjdfueurwehjhdsvhdsklavhkhdvnoruiperiklöjfuyrhjoijfkfjkjfkshföafjafaskfj");
	    tlsText2.setValue("klkfj7840349820408320+81+2384021849075097501+731075+2751+275017510+750+23701+279203577+17+1984930714+0174+147");
	    
	    tlsGrid.addComponent(tlsTitle,0,1);
	    tlsGrid.addComponent(tlsText,0,2);
	    tlsGrid.addComponent(tlsText2,0,6);
	    
	    final Upload upload = new Upload();
	    upload.setImmediateMode(false);
	    upload.setButtonCaption("Upload");
	    tlsGrid.addComponent(upload,0,8);
	    
	    
	    files = new Embedded("Upload Image");
	    files.setVisible(false);
	    tlsGrid.addComponent(files);

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
	

}
