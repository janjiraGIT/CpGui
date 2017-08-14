package com.mobilityguard.acc.cpgui;

import com.mobilityguard.acc.data.DataTypeInfo;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import javax.servlet.annotation.WebServlet;
import java.util.Set;


@SuppressWarnings("serial")
@Theme("cpTheme")
public class CpMainGui extends UI {
    private static final String ACCESS = "Access Config for Control Panel";
    private static final String MENU_LAYOUT = "menuLayout";
    private static final String STATUS = "Status";
    private static final String ACTIVATE_CHANGES = "Activate Changes";
    private static final String MAINTENANCE = "Maintenance";
    private static final String REPORT_CONFIG = "Report Config";
    private static final String SYSLOG = "Syslog";
    private static final String TLS_SERVER_SETTINGS = "TLS Server settings";
    private static final String ACCESS_CONFIG_FOR_CONTROL_PANEL = ACCESS;
    private static final String NETWORK = "Network";
    private static final String SELECT_LAYOUT = "selectLayout";
    private static final String ACCESS_URL = "/opt/acc/config/access.json";
    private static final String SYSLOG_URL = "/opt/acc/config/syslog.json";
    private static Window window = new Window();
    private ImagesWindow image = new ImagesWindow();
    private DataTypeInfo dataTypeInfo = new DataTypeInfo();
    private JSONObject access = null;
    private JSONObject syslog = null;

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
        rootLayout.setExpandRatio(menuLayout,1);
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
        final ImagesWindow imageWindow = new ImagesWindow();
        final Image logoMobilityGuard = imageWindow.getLogoMobilityGuard();
        logoMobilityGuard.setHeight("10%");
        logoMobilityGuard.setWidth("10%");
        footerBackground.addComponent(logoMobilityGuard);
        rootLayout.addComponent(footerBackground);
    }

    private HorizontalLayout addMenuBar() {
        final HorizontalLayout menuLayout = new HorizontalLayout();
        menuLayout.setStyleName(MENU_LAYOUT);
        final HorizontalLayout selectLayout = new HorizontalLayout();
        selectLayout.setStyleName(SELECT_LAYOUT);
        menuLayout.addComponent(selectLayout);
        selectLayout.setWidth("80%");
        selectLayout.setHeight("100%");

        final String network = dataTypeInfo.getNetwork();
        access = dataTypeInfo.getData(ACCESS_URL);
        final String tlss = dataTypeInfo.getTlss();
        syslog = dataTypeInfo.getData(SYSLOG_URL);
        final String reportconfig = dataTypeInfo.getReportConfig();
        final String maintain = dataTypeInfo.getMaintain();
        final String activeex = dataTypeInfo.getActiveEx();
        final ListSelect<String> selectMenu = new ListSelect<>();
        selectMenu.setStyleName("selectMenu");
        selectMenu.setItems(STATUS, network, ACCESS, tlss, SYSLOG, reportconfig, maintain, activeex );
        final ImagesWindow image = new ImagesWindow();
        window = image.createImageTheme();
        addWindow(window);
        actionAfterSelectMenu(menuLayout, selectMenu);

        selectLayout.addComponent(selectMenu);
        return menuLayout;
    }

    private void actionAfterSelectMenu(final HorizontalLayout menuLayout, final ListSelect<String> selectMenu) {
        selectMenu.addValueChangeListener(event -> {
            final Set<String> selected = event.getValue();
            if (selected.contains(STATUS)) {
                window.close();
                window = image.createImageTheme();
                addWindow(window);
            } else if ( selected.contains(NETWORK)) {
                window.close();
                final NetworkWindow nwWindow = new NetworkWindow();
                try {
                    window = nwWindow.crateNetworkWindow();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                addWindow(window);
            } else if (selected.contains(ACCESS_CONFIG_FOR_CONTROL_PANEL)) {
                window.close();
                final AccessWindow acWindow = new AccessWindow();
                window = acWindow.createAccessGui(access);
                addWindow(window);
            } else if (selected.contains(TLS_SERVER_SETTINGS)) {
                window.close();
                final TlsWindow tlsWindow = new TlsWindow();
                window = tlsWindow.createTlsWindow();
                addWindow(window);
            } else if (selected.contains(SYSLOG)) {
                window.close();
                final SyslogWindow sysWindow = new SyslogWindow();
                window = sysWindow.createSyslogGui(syslog);
                addWindow(window);
            } else if (selected.contains(REPORT_CONFIG)) {
                window.close();
                window = image.createImageTheme();
                addWindow(window);
            } else if (selected.contains(MAINTENANCE)) {
                window.close();
                final Maintenance mainteNance = new Maintenance();
                window = mainteNance.createMaintenanceWindow();
                addWindow(window);
            } else if (selected.contains(ACTIVATE_CHANGES)) {
                window.close();
                window = image.createImageTheme();
                addWindow(window);
            }
        });

    }

    @WebServlet(urlPatterns = "/*", name = "cpGuiServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CpMainGui.class, productionMode = false)
    public static class CpGuiServlet extends VaadinServlet {
        private static final long serialVersionUID = 1L;
    }
}
