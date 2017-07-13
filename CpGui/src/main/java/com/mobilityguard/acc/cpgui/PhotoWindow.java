package com.mobilityguard.acc.cpgui;

import com.vaadin.server.ClassResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PhotoWindow {

	public Window createPhotoTheme(){
		final Window nwWindow = new Window("");
        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        nwWindow.setContent(layout);
        nwWindow.setPositionX(300);
        nwWindow.setPositionY(65);
        nwWindow.setHeight("80%");
        nwWindow.setWidth("70%");      
        final Panel panel = new Panel();
        layout.addComponent(panel);
        layout.addComponent(new Image(null,new ClassResource("images.jpeg")));
		final ThemeResource resource = new ThemeResource("images.jpeg");
		final Image image = new Image("Image",resource);
		image.setSizeFull();
		panel.setContent(image);
		panel.setWidth("300px");
		panel.setHeight("200px");
		
		return nwWindow;		
	}
}
