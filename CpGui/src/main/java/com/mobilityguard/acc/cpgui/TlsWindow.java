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
    private GridLayout tlsGrid;
    private Label lb = new Label("File name");
    private PasswordField pass;

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
        upload.setEnabled(false);
        upload.setStyleName("upload");
        upload.addSucceededListener(receiver);

        tlsGrid.addComponent(upload,0,6);
        files = new Embedded("Upload Image");
        files.setVisible(false);
        tlsGrid.addComponent(files);
        tlsLayout.addComponent(tlsGrid);
        pass = new PasswordField();
        pass.setEnabled(true);
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        final Button ok = new Button("ok");
        final Label lbOk = new Label("Password");
        buttonLayout.addComponent(lbOk);
        buttonLayout.addComponent(pass);
        buttonLayout.addComponent(ok);
        tlsGrid.addComponent(buttonLayout,0,4);
        ok.addClickListener(event -> okHandler(pass,upload));
        return tlsWindow;
    }


    private Object okHandler(final TextField tlsTf, final Upload upload) {
        String pw = tlsTf.getValue();
        if (pw.equals("aaa")) {
            upload.setEnabled(true);
            Notification.show("Correct password ");
        } else {
            Notification.show("Fel password, please try again.");
            upload.setEnabled(false);
        }
        return null;
    }


    public class ImageUploader implements Upload.SucceededListener, Upload.FailedListener, Upload.Receiver {
        private static final long serialVersionUID = -1276759102490466761L;
        public File file;

        @Override
        public OutputStream receiveUpload(final String filename, final String mimeType) {
            FileOutputStream fos = null;
            try {

             // this address for save a file in local Mac.
                file = new File("/home/janjira/code/workspace/acc/cpgui/File/" + filename);
             // this address for save a file local Mac.
             // file = new File("/Users/janjiraeriksson/code/git/CpGui/CpGui/jsonFile/" + filename);
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
            if (pass.getValue().equals("aaa")) {
                lb.setValue("File name : " + event.getFilename() + " have been uploaded.");
                tlsGrid.addComponent(lb, 0,10);
            } else {
                Notification.show("Password is wrong");
            }
        }

        public void uploadFailed(final FailedEvent event) {
            lb.setValue("File name : " + event.getFilename() + " failed uploaded.");
            tlsGrid.addComponent(lb, 0,8);
        }
    }
}