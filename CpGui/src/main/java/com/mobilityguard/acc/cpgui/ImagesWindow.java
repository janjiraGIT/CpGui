package com.mobilityguard.acc.cpgui;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ImagesWindow {

	private ThemeResource resource = null;
	private Image image = null;

	/**
	 * @return the default image window.
	 */
	public Window createImageTheme(){
		final Window nwWindow = new Window("");
        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        nwWindow.setContent(layout);
        nwWindow.setPositionX(300);
        nwWindow.setPositionY(65);
        nwWindow.setHeight("85%");
        nwWindow.setWidth("77%");      
        final Panel panel = new Panel();
        layout.addComponent(panel);
		resource = new ThemeResource("photo.png");
		image = new Image("Image",resource);
		image.setSizeFull();
		panel.setContent(image);
		panel.setSizeFull();	
		return nwWindow;		
	}

	/**
	 * @return logo of MobilityGuard AB.
	 */	
	public Image getLogoMobilityGuard(){
		resource = new ThemeResource("images.jpeg");
		image = new Image(null, resource);
		return image;
		
	}
}
