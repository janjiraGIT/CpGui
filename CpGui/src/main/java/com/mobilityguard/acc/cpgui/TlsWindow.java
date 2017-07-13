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



public class TlsWindow {
    private Embedded files = new Embedded("Upload Image");

    /**
     * @return TLS window.
     */
    public Window createTlsWindow() {
        final Window tlsWindow = new Window("TLS Server Settings Window");
        final VerticalLayout tlsLayout = new VerticalLayout();
        tlsLayout.setSizeFull();
        tlsWindow.setContent(tlsLayout);
        tlsWindow.setPositionX(300);
        tlsWindow.setPositionY(65);
        tlsWindow.setHeight("50%");
        tlsWindow.setWidth("30%");
        final GridLayout tlsGrid = new GridLayout(7,30);
        tlsGrid.addStyleName("tlsGrid");
        tlsGrid.setWidth("400px");
        tlsGrid.setHeight("400px");
        final Label tlsTitle = new Label("Current TLS Certificate:");
        tlsTitle.addStyleName("tlsTitle");
        final Label tlsText = new Label();
        tlsText.addStyleName("tlsText");
        tlsText.setValue(" Please choose a backup file to upload.");
        tlsGrid.addComponent(tlsTitle,0,1);
        tlsGrid.addComponent(tlsText,0,2);
        final Upload upload = new Upload();
        upload.setImmediateMode(false);
        upload.setButtonCaption("Upload");
        upload.setStyleName("upload");
        tlsGrid.addComponent(upload,0,4);
        files = new Embedded("Upload Image");
        files.setVisible(false);
        tlsGrid.addComponent(files);
        final TextField tlsTf = new TextField("Password:");
        tlsGrid.addComponent(tlsTf,0,6);
        tlsLayout.addComponent(tlsGrid);
        return tlsWindow;
    }

    public class ImageUploader implements Upload.SucceededListener, Upload.FailedListener, Upload.Receiver {
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
}
