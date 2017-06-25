package com.mobilityguard.acc.CpGui;

import javax.servlet.annotation.WebServlet;

import com.mobilityguard.acc.Data.DataTypeInfo;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.GridLayout.OutOfBoundsException;
import com.vaadin.ui.GridLayout.OverlapsException;
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
    	final VerticalLayout rootLayout = new VerticalLayout();
    	rootLayout.setStyleName("root");
    	rootLayout.setSizeFull();
    	setContent(rootLayout);
    	final HorizontalLayout titleLayout = setHeader();
    	rootLayout.addComponent(titleLayout);  	
    	final HorizontalLayout selectLayout = setMenu(rootLayout); 	
    	setGrid(selectLayout);
    	setButton(rootLayout);
    	setFooter(rootLayout);
    }
    
	private HorizontalLayout setHeader(){
    	final HorizontalLayout titleLayout = new HorizontalLayout();
    	titleLayout.setStyleName("titleLayout");
    	titleLayout.setWidth("100%");
  	
    	final Label titleLabel = new Label("Control Panel");
    	titleLabel.addStyleName("titleLabel");
    	titleLabel.setWidth("100%");
    	titleLayout.addComponent(titleLabel);
		return titleLayout;	
	}
	
	/**
	 * @param rootLayout
	 */
	private void setFooter(final VerticalLayout rootLayout) {
		final HorizontalLayout footerBackground = new HorizontalLayout();
    	footerBackground.setWidth("100%");
    	final Label date = new Label("2017-06");
    	date.setWidth("100%");
    	date.setStyleName("date");
    	footerBackground.addComponent(date);
    	rootLayout.addComponent(footerBackground);
    	rootLayout.setComponentAlignment(footerBackground, Alignment.BOTTOM_CENTER);
	}
	/**
	 * @param rootLayout
	 */
	private void setButton(final VerticalLayout rootLayout) {
		final HorizontalLayout buttonLayout= new HorizontalLayout();
    	buttonLayout.setStyleName("buttonBackground");    	
    	final Button save = new Button("save");
    	final Button cancel = new Button("cancel");  	
    	buttonLayout.addComponents(save,cancel);
    	rootLayout.addComponent(buttonLayout);
    	rootLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);
	}
	/**
	 * @param selectBackground
	 * @throws OverlapsException
	 * @throws OutOfBoundsException
	 */
	private void setGrid(final HorizontalLayout selectLayout) throws OverlapsException, OutOfBoundsException {
		final GridLayout grid = new GridLayout(6,6);
    	grid.addStyleName("grid");
    	grid.setWidth("1000px");
    	grid.setHeight("400px");
    	grid.addComponent(new Label("Text1 : "),1,1);
    	grid.addComponent(new Label("Text2 : "),1,2);
    	grid.addComponent(new Label("Text3 : "),1,3);
    	grid.addComponent(new Label("Text4 : "),1,4);
    	grid.addComponent(new Label("Text5 :"),1,5);
    	grid.addComponent(new TextField(),2,1);
    	grid.addComponent(new TextField(),2,2);
    	grid.addComponent(new TextField(),2,3);
    	grid.addComponent(new TextField(),2,4);
    	grid.addComponent(new TextField(),2,5);
    	selectLayout.addComponent(grid);
	}

	/**
	 * @param rootLayout
	 * @return
	 */
	private HorizontalLayout setMenu(final VerticalLayout rootLayout) {
		final HorizontalLayout menuLayout = new HorizontalLayout();
    	menuLayout.setSizeFull();
    	rootLayout.addComponent(menuLayout);
    	rootLayout.setExpandRatio(menuLayout, 1);
    	
    	final HorizontalLayout selectLayout = new HorizontalLayout();
    	selectLayout.setStyleName("selectLayout");
    	menuLayout.addComponent(selectLayout);
    	selectLayout.setWidth("50%");
    	selectLayout.setHeight("100%");
    	final DataTypeInfo dataTypeInfo = new DataTypeInfo();
    	final String status = dataTypeInfo.getStatus();
    	final String network = dataTypeInfo.getNetwork();
    	final String access = dataTypeInfo.getAccess();
    	final String tlss = dataTypeInfo.getTLSS();
    	final String syslog = dataTypeInfo.getSyslog();
    	final String reportconfig = dataTypeInfo.getReportConfig();
    	final String maintain = dataTypeInfo.getMaintain();
    	final String activeex = dataTypeInfo.getActiveEx();  	
    	final ListSelect<String> selectMenu = new ListSelect<>();
    	selectMenu.setStyleName("selectMenu");
    	selectMenu.setItems(status, network, access, tlss, syslog, reportconfig, maintain, activeex );    	
    	selectMenu.setHeight("100%");
    	selectMenu.setWidth("100%");
    	selectLayout.addComponent(selectMenu);
		return selectLayout;
	}

    @WebServlet(urlPatterns = "/*", name = "CpUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CpUI.class, productionMode = false)
    public static class CpUIServlet extends VaadinServlet {
    }
}
