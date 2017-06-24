package com.mobilityguard.acc.CpGui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class CpUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	final VerticalLayout root = new VerticalLayout();
    	root.setStyleName("root");
    	root.setSizeFull();
    	setContent(root);

    	final HorizontalLayout titleBar = new HorizontalLayout();
    	titleBar.setStyleName("titleBar");
    	titleBar.setWidth("100%");
    	root.addComponent(titleBar);   	
    	final Label titleLabel = new Label("Control Panel");
    	titleLabel.addStyleName("titleLabel");
    	titleLabel.setWidth("100%");
    	titleBar.addComponent(titleLabel);

    	final HorizontalLayout horlayout = new HorizontalLayout();
    	horlayout.setSizeFull();
    	root.addComponent(horlayout);
    	root.setExpandRatio(horlayout, 1);
    	
    	final HorizontalLayout selectBackground = new HorizontalLayout();
    	selectBackground.setStyleName("selectBackground");
    	horlayout.addComponent(selectBackground);
    	selectBackground.setWidth("30%");
    	selectBackground.setHeight("100%");
    	
    	
    	final ListSelect<String> selectMenu = new ListSelect<>();
    	selectMenu.setStyleName("selectMenu");
    	selectMenu.setItems("Status","Network","TLSS","Syslog");
    	selectMenu.setHeight("100%");
    	selectMenu.setWidth("100%");
    	selectBackground.addComponent(selectMenu);
    	
    	final GridLayout grid = new GridLayout(6,6);
    	grid.addStyleName("grid");
    	grid.setWidth("1000px");
    	grid.setHeight("500px");
    	
    	grid.addComponent(new Label("a"),1,0);
    	grid.addComponent(new Label("b"),1,1);
    	grid.addComponent(new Label("c"),1,2);
    	grid.addComponent(new Label("d"),1,3);
    	grid.addComponent(new Label("e"),1,4);
    	grid.addComponent(new Label("f"),1,5);
    	
    	grid.addComponent(new Label("1"),2,0);
    	grid.addComponent(new Label("2"),2,1);
    	grid.addComponent(new Label("3"),2,2);
    	grid.addComponent(new Label("4"),2,3);
    	grid.addComponent(new Label("5"),2,4);
    	grid.addComponent(new Label("6"),2,5);

    	selectBackground.addComponent(grid);
    	final HorizontalLayout buttonBackground= new HorizontalLayout();
    	buttonBackground.setStyleName("buttonBackground");    	
    	final Button bOk = new Button("ok");
    	final Button bCancel = new Button("cancel");  	
    	buttonBackground.addComponents(bOk,bCancel);
    	root.addComponent(buttonBackground);
    	root.setComponentAlignment(buttonBackground, Alignment.BOTTOM_RIGHT);
    	
    	final HorizontalLayout footerBackground = new HorizontalLayout();
    	footerBackground.setWidth("100%");
    	final Label date = new Label("2017-06");
    	date.setWidth("100%");
    	date.setStyleName("date");
    	footerBackground.addComponent(date);
    	root.addComponent(footerBackground);
    	root.setComponentAlignment(footerBackground, Alignment.BOTTOM_CENTER);
    }

    @WebServlet(urlPatterns = "/*", name = "CpUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CpUI.class, productionMode = false)
    public static class CpUIServlet extends VaadinServlet {
    }
}
