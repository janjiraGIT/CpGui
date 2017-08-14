package com.mobilityguard.acc.cpgui;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.SucceededEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class Maintenance {
    private Embedded files = new Embedded("Upload Image");
    private GridLayout mnGrid;
    private Label lb = new Label("File name");
    private static final Logger log = LoggerFactory.getLogger(Maintenance.class);

    /**
     * @return Maintenance window.
     */
    public Window createMaintenanceWindow() {
        final Window mnWindow = new Window();
        final VerticalLayout mnLayout = new VerticalLayout();
        final Panel panel = new Panel("Maintenance Window");
        panel.setContent(mnLayout);
        mnWindow.setContent(panel);
        mnLayout.setSizeFull();
        mnWindow.setPositionX(300);
        mnWindow.setPositionY(65);
        mnWindow.setHeight("36%");
        mnWindow.setWidth("20%");
        mnGrid = new GridLayout(7,30);
        mnGrid.addStyleName("mnGrid");
        mnGrid.setWidth("507px");
        mnGrid.setHeight("300px");
        final Label mnTitle = new Label("Upgrade Software");
        mnTitle.addStyleName("mnTitle");
        final Label mnText = new Label();
        mnText.addStyleName("mnText");
        mnText.setValue("Please choose the software package file to upload.");
        mnGrid.addComponent(mnTitle,0,1);
        mnGrid.addComponent(mnText,0,2);
        final ImageUploader receiver = new ImageUploader();
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
                file = new File("/opt/acc/config/upload/" + filename);
                fos = new FileOutputStream(file);
            } catch (Exception e) {
                log.error("Cound not upload file : " + filename + e.getStackTrace());
                return null;
            }
            return fos;
        }

        @Override
        public void uploadSucceeded(final SucceededEvent event) {
            files.setVisible(true);
            files.setSource(new FileResource(file));
            lb.setValue("File name : " + event.getFilename() + " have been uploaded to address /opt/acc/config/upload/");
            mnGrid.addComponent(lb, 0,8);
        }

        public void uploadFailed(final FailedEvent event) {
            lb.setValue("File name : " + event.getFilename() + " failed uploaded.");
            mnGrid.addComponent(lb, 0,8);
        }
    }
}

