package com.mobilityguard.acc.cpgui;

import javax.servlet.annotation.WebServlet;

import com.mobilityguard.acc.data.DataTypeInfo;

import com.vaadin.annotations.PropertyId;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import org.apache.log4j.Logger;

import java.util.Set;


@Theme("mytheme")
public class CpMainGui extends UI {
    private static final String MENU_LAYOUT = "menuLayout";
    private static final String STATUS = "Status";
    private static final String ACTIVATE_CHANGES = "Activate Changes";
    private static final String MAINTENANCE = "Maintenance";
    private static final String REPORT_CONFIG = "Report Config";
    private static final String SYSLOG = "Syslog";
    private static final String SELECTED = "Selected : ";
    private static final String TLS_SERVER_SETTINGS = "TLS Server settings";
    private static final String ACCESS_CONFIG_FOR_CONTROL_PANEL = "Access Config for Control Panel";
    private static final String NETWORK = "Network";
    private static final String SELECT_LAYOUT = "selectLayout";
    private static Window window = new Window();
    private static final Logger logger = Logger.getLogger(CpMainGui.class);

    @Override
    protected void init(final VaadinRequest vaadinRequest) {
        final VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.setStyleName("root");
        rootLayout.setSizeFull();
        setContent(rootLayout);
        final HorizontalLayout titleLayout = addTitle();
        rootLayout.addComponent(titleLayout);
        final HorizontalLayout menuLayout = addMenuBar();
        rootLayout.addComponent(menuLayout);
        rootLayout.setExpandRatio(menuLayout, 1);

        setFooter(rootLayout);
    }

    private HorizontalLayout addTitle() {
        final HorizontalLayout titleLayout = new HorizontalLayout();
        titleLayout.setStyleName("titleLayout");
        titleLayout.setWidth("100%");
        final Label titleLabel = new Label("Control Panel");
        titleLabel.addStyleName("titleLabel");
        titleLabel.setWidth("100%");
        titleLayout.addComponent(titleLabel);
        return titleLayout;
    }


    private void setFooter(final VerticalLayout rootLayout) {
        final HorizontalLayout footerBackground = new HorizontalLayout();
        footerBackground.setWidth("100%");
        final Label date = new Label("Mobilityguard AB");
        date.setWidth("100%");
        date.setStyleName("date");
        footerBackground.addComponent(date);
        rootLayout.addComponent(footerBackground);
        rootLayout.setComponentAlignment(footerBackground, Alignment.BOTTOM_CENTER);
    }

    private HorizontalLayout addMenuBar() {
        final HorizontalLayout menuLayout = new HorizontalLayout();
        menuLayout.setStyleName(MENU_LAYOUT);

        final HorizontalLayout selectLayout = new HorizontalLayout();
        selectLayout.setStyleName(SELECT_LAYOUT);
        menuLayout.addComponent(selectLayout);
        selectLayout.setWidth("80%");
        selectLayout.setHeight("100%");

        final DataTypeInfo dataTypeInfo = new DataTypeInfo();
        final String network = dataTypeInfo.getNetwork();
        final String access = dataTypeInfo.getAccess();
        final String tlss = dataTypeInfo.getTlss();
        final String syslog = dataTypeInfo.getSyslog();
        final String reportconfig = dataTypeInfo.getReportConfig();
        final String maintain = dataTypeInfo.getMaintain();
        final String activeex = dataTypeInfo.getActiveEx();

        final ListSelect<String> selectMenu = new ListSelect<>();
        selectMenu.setStyleName("selectMenu");
        selectMenu.setItems(STATUS, network, access, tlss, syslog, reportconfig, maintain, activeex );
        selectMenu.setHeight("100%");
        selectMenu.setWidth("50%");

        actionAfterSelectMenu(menuLayout, selectMenu);

        selectLayout.addComponent(selectMenu);
        return menuLayout;
    }

    private void actionAfterSelectMenu(final HorizontalLayout menuLayout, final ListSelect<String> selectMenu) {
        selectMenu.addValueChangeListener(event -> {
            final Set<String> selected = event.getValue();
            if (selected.contains(STATUS)) {
                Notification.show(SELECTED + selected.toString());
            } else if ( selected.contains(NETWORK)) {
                final NetworkWindow nwWindow = new NetworkWindow();
                window = nwWindow.crateNetworkWindow();
                addWindow(window);
            } else if (selected.contains(ACCESS_CONFIG_FOR_CONTROL_PANEL)) {
                final AccessWindow acWindow = new AccessWindow();
                window = acWindow.createAccessGui();
                addWindow(window);
            } else if (selected.contains(TLS_SERVER_SETTINGS)) {
            	final TlsWindow tlsWindow = new TlsWindow();
            	window = tlsWindow.createTlsWindow();
            	addWindow(window);
            } else if (selected.contains(SYSLOG)) {
            	final SyslogWindow sysWindow = new SyslogWindow();
            	window = sysWindow.createSyslogGui();
            	addWindow(window);
            } else if (selected.contains(REPORT_CONFIG)) {
                Notification.show(SELECTED + selected.toString());
            } else if (selected.contains(MAINTENANCE)) {
                Notification.show(SELECTED + selected.toString());
            } else if (selected.contains(ACTIVATE_CHANGES)) {
                Notification.show(SELECTED + selected.toString());
            }
        });

    }
    @WebServlet(urlPatterns = "/*", name = "CpUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CpMainGui.class, productionMode = false)
    public static class CpUIServlet extends VaadinServlet {
    }
}
