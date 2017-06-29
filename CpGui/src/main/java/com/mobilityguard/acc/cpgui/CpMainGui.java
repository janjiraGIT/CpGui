package com.mobilityguard.acc.cpgui;

import java.util.Set;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import com.mobilityguard.acc.data.DataTypeInfo;
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
import com.vaadin.ui.Notification;
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
public class CpMainGui extends UI {
	final static Logger logger = Logger.getLogger(CpMainGui.class);
    final NetworkGui gridComponent = new NetworkGui();
    private GridLayout gridLayout = null;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.setStyleName("root");
        rootLayout.setSizeFull();
        setContent(rootLayout);
        final HorizontalLayout titleLayout = setHeader();
        rootLayout.addComponent(titleLayout);   
        final HorizontalLayout selectLayout = setMenu(rootLayout);  
        //rootLayout.addComponent(selectLayout);
       // setButton(rootLayout);
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
        selectLayout.setHeight("50%");
        final DataTypeInfo dataTypeInfo = new DataTypeInfo();
        final String network = dataTypeInfo.getNetwork();
        final String access = dataTypeInfo.getAccess();
        final String tlss = dataTypeInfo.getTLSS();
        final String syslog = dataTypeInfo.getSyslog();
        final String reportconfig = dataTypeInfo.getReportConfig();
        final String maintain = dataTypeInfo.getMaintain();
        final String activeex = dataTypeInfo.getActiveEx();     
        final ListSelect<String> selectMenu = new ListSelect<>();
        selectMenu.setStyleName("selectMenu");
        selectMenu.setItems("Status", network, access, tlss, syslog, reportconfig, maintain, activeex );        
        selectMenu.setHeight("100%");
        selectMenu.setWidth("70%");    
        selectMenu.addValueChangeListener(event -> {

            Set<String> selected = event.getValue();
            if ( selected.contains("Network")){
                Notification.show("Selected : " + selected.toString());
                gridLayout = gridComponent.crateNetworkGui();
                selectLayout.addComponent(gridLayout);
            }else {
                // TODO : error NullPointerException when select other menu. Need to be fix.
                Notification.show("Not Selected Network : " + selected.toString());
                if (selectLayout != null ){
                    selectLayout.removeComponent(gridLayout);
                }
            }        
        });
        selectLayout.addComponent(selectMenu);
        return selectLayout;
    }

    
    @WebServlet(urlPatterns = "/*", name = "CpUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CpMainGui.class, productionMode = false)
    public static class CpUIServlet extends VaadinServlet {
    }
}
