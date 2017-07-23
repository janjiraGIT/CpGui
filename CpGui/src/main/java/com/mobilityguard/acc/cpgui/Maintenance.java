package com.mobilityguard.acc.cpgui;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;



public class Maintenance {
    private Embedded files = new Embedded("Upload Image");

    /**
     * @return Maintenance window.
     */
    public Window createMaintenanceWindow() {
        final Window mnWindow = new Window("Maintenance Window");
        final VerticalLayout mnLayout = new VerticalLayout();
        mnLayout.setSizeFull();
        mnWindow.setContent(mnLayout);
        mnWindow.setPositionX(300);
        mnWindow.setPositionY(65);
        mnWindow.setHeight("50%");
        mnWindow.setWidth("30%");
        final GridLayout mnGrid = new GridLayout(7,30);
        mnGrid.addStyleName("mnGrid");
        mnGrid.setWidth("400px");
        mnGrid.setHeight("400px");
        final Label mnTitle = new Label("Upgrade Software");
        mnTitle.addStyleName("mnTitle");
        final Label mnText = new Label();
        mnText.addStyleName("mnText");
        mnText.setValue("Please choose the software package file to upload.");
        mnGrid.addComponent(mnTitle,0,1);
        mnGrid.addComponent(mnText,0,2);
        ImageUploader receiver = new ImageUploader();
        final Upload upload = new Upload("upload here ", receiver);
        upload.setImmediateMode(false);
        upload.setButtonCaption("Start Upload");
        upload.setStyleName("upload");
        upload.addSucceededListener(receiver);

        mnGrid.addComponent(upload,0,4);
        files = new Embedded("Upload Image");
        files.setVisible(false);
        mnGrid.addComponent(files);
        mnLayout.addComponent(mnGrid);
        return mnWindow;
    }
		class ImageUploader implements Upload.SucceededListener, Upload.FailedListener, Upload.Receiver {
    	        private static final long serialVersionUID = -1276759102490466761L;
    	        public File file;

    	        @Override
    	        public OutputStream receiveUpload(final String filename, final String mimeType) {
    	            FileOutputStream fos = null;
    	            try {
    	                file = new File("/Users/janjiraeriksson/code/git/CpGui/CpGui/jsonFile/" + filename);
    	                fos = new FileOutputStream(file);
    	            } catch (Exception e) {
    	                System.out.println(e.getStackTrace());
    	                return null;
    	            }
    	            return fos;
    	        }

    	        @Override
    	        public void uploadSucceeded(final SucceededEvent event) {
    	            files.setVisible(true);
    	            files.setSource(new FileResource(file));
    	        }


    	        public void uploadFailed(final FailedEvent event) {
    	            // TODO Auto-generated method stub
    	        }
    	    }
    	   ImageUploader receiver = new ImageUploader();
    	   
    	   
}

