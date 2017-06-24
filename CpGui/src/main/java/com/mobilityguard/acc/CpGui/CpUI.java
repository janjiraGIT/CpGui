package com.mobilityguard.acc.CpGui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
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
    	root.setStyleName("titleBar");
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
    	
    	final GridLayout grid = new GridLayout(4,6);
    	grid.addStyleName("grid");
    	grid.setWidth("600px");
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
    	
//    	 grid.addComponent(titleText, 2,0);
//         grid.addComponent(descriptionText,1,2);
//         grid.addComponent(descriptionValue,2,2);
//         grid.addComponent(validTimeText,1,3);
//         grid.addComponent(timeSelectCombobox,2,3);
//         grid.addComponent(certProfileText,1,4);
//         grid.addComponent(profileSelectCombobox,2,4);
//         grid.addComponent(providerText,1,5);
//         grid.addComponent(providerCombobox,2,5);
//         grid.addComponent(userSelectText,1,6);
//         grid.addComponent(userSelectedValue,2,6);
//         grid.addComponent(okButton,2,7);
//         grid.addComponent(cancelButton,3,7);
//         grid.setComponentAlignment(okButton, Alignment.TOP_RIGHT);
//         grid.setComponentAlignment(cancelButton, Alignment.TOP_LEFT);
//         grid.setMargin(true);
//         grid.setSpacing(true);
    	
//    	final HorizontalLayout HoriBackgroundLeft = new HorizontalLayout();
//    	HoriBackgroundLeft.setSpacing(true);
//    	final VerticalLayout vartiInside = new VerticalLayout();
//    	vartiInside.setWidth("80%");
//    	vartiInside.setSpacing(true);
//    	vartiInside.setMargin(true);
//    	final Label label1 = new Label("text 1");
//    	label1.setStyleName("label1");
//    	label1.setHeight("30%");
//    	final Label label2 = new Label("text 2");
//    	final Label label3 = new Label("text 3");
//    	final Label label4 = new Label("text 4");
//    	final Label label5 = new Label("text 5");
//    	final Label label6 = new Label("text 6");
//    	final Label label7 = new Label("");
//    	final Label label8 = new Label(" ");
//    	label1.setStyleName("label2");
//    	label2.setHeight("30%");
//    	vartiInside.addComponents(label1,label2,label3,label4,label5,label6,label7,label8);
//    	HoriBackgroundLeft.addComponent(vartiInside);
//    	
//    	final VerticalLayout vertiInsideHoriRight = new VerticalLayout();
//    	vertiInsideHoriRight.setWidth("80%");
//    	vertiInsideHoriRight.setSpacing(true);
//    	vertiInsideHoriRight.setMargin(true);
//    	final Label labelR1 = new Label("text 1");
//    	final Label labelR2 = new Label("text 2");
//    	final Label labelR3= new Label("text 3");
//    	final Label labelR4 = new Label("text 4");
//    	final Label labelR5 = new Label("text 5");
//    	final Label labelR6 = new Label("text 6");
//
//
//    	vertiInsideHoriRight.addComponents(labelR1,labelR2,labelR3,labelR4,
//    									   labelR5,labelR6);
//    	HoriBackgroundLeft.addComponent(vertiInsideHoriRight);
//    	horlayout.addComponent(HoriBackgroundLeft);
//    	//detailPanel.setContent(HoriBackgroundLeft);
//    	final HorizontalLayout horiButton = new HorizontalLayout();
//    	Button buttonOk = new Button("ok");
//    	Button buttonCancel = new Button("cancel");
//    	
//    	horiButton.addComponents(buttonOk,buttonCancel);
//    	horiButton.setSpacing(true);
//    	vertiInsideHoriRight.addComponent(horiButton);
    	
    	final Label dateLabel = new Label("Date : 2017-06 ");
    	dateLabel.setWidth("100%");
    	dateLabel.setStyleName("dateLabel");
    	root.addComponent(dateLabel);

    }

    @WebServlet(urlPatterns = "/*", name = "CpUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CpUI.class, productionMode = false)
    public static class CpUIServlet extends VaadinServlet {
    }
}
