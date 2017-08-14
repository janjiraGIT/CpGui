package com.mobilityguard.acc.cpgui;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupView;
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



public class TlsWindow {
    private Embedded files = new Embedded("Upload Image");
    private GridLayout tlsGrid;
    private Label lb = new Label("File name");
    private static final Logger log = LoggerFactory.getLogger(TlsWindow.class);

    /**
     * @return TLS window.
     */
    public Window createTlsWindow() {
        final Window tlsWindow = new Window();
        final VerticalLayout tlsLayout = new VerticalLayout();
        final Panel panel = new Panel("TLS Server Settings Window");
        tlsWindow.setContent(panel);
        panel.setSizeFull();
        panel.setContent(tlsLayout);
        tlsLayout.setSizeFull();
        tlsWindow.setPositionX(300);
        tlsWindow.setPositionY(65);
        tlsWindow.setHeight("36%");
        tlsWindow.setWidth("20%");
        tlsGrid = new GridLayout(7,30);
        tlsGrid.addStyleName("tlsGrid");
        tlsGrid.setWidth("507px");
        tlsGrid.setHeight("300px");
        final Label tlsTitle = new Label("Current TLS Certificate:");
        tlsTitle.addStyleName("tlsTitle");
        final Label tlsText = new Label();
        tlsText.addStyleName("tlsText");
        tlsText.setValue(" Please choose a backup file to upload.");
        tlsGrid.addComponent(tlsTitle,0,1);
        tlsGrid.addComponent(tlsText,0,2);
        final ImageUploader receiver = new ImageUploader();
        final Upload upload = new Upload("Upload here" , receiver);
        upload.setImmediateMode(false);
        upload.setButtonCaption("Start Upload");
        upload.setEnabled(true);
        upload.setStyleName("upload");
        upload.addSucceededListener(receiver);

        tlsGrid.addComponent(upload,0,6);
        files = new Embedded("Upload Image");
        files.setVisible(true);
        tlsGrid.addComponent(files);
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
                file = new File("/opt/acc/config/upload/" + filename);
                fos = new FileOutputStream(file);
            } catch (Exception e) {
                log.error("Could not upload file :" + filename + e.getStackTrace());
                return null;
            }
            return fos;
        }

        @Override
        public void uploadSucceeded(final SucceededEvent event) {
            files.setSource(new FileResource(file));
            final HorizontalLayout buttonLayout = new HorizontalLayout();
            final Button ok = new Button("ok");
            final PasswordField passw = new PasswordField();
            buttonLayout.addComponent(passw);
            buttonLayout.addComponent(ok);
            PopupView popup = new PopupView("Please click to enter password for upload file ",buttonLayout );
            tlsGrid.addComponent(popup,0,4);
            ok.addClickListener(even -> checkPw( passw,lb,event) ) ;
        }

        public void uploadFailed(final FailedEvent event) {
            lb.setValue("File name : " + event.getFilename() + " failed uploaded.");
            tlsGrid.addComponent(lb, 0,8);
        }

        private void checkPw(final PasswordField passw ,final Label lb , final SucceededEvent event ) {
            if (passw.getValue().equals("aaa")) {
                Notification.show("Correct passwod");
                lb.setValue("File name :" + event.getFilename() + " have been uploaded to address /opt/acc/config/upload/");
                tlsGrid.addComponent(lb, 0,10);
            } else {
                Notification.show("Password is wrong");
            }
        }
    }
}
