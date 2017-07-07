package com.mobilityguard.acc.cpgui;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class NetworkWindow {

    public static final String NC_TITLE = "Network Configuration";
    public static final String HOST_NAME = "Host name";
    public static final String DOMAIN_SUFFIX = "Domain Suffix";
    public static final String DNS1 = "DNS Server1";
    public static final String DNS2 = "DNS Server2";
    public static final String IC_TITLE = "Interface Configuration";
    public static final String NI_TITLE_0 = "Network Interface eth0";
    public static final String NI_TITLE_1 = "Network Interface eth1";
    public static final String DHCP = "DHCP";
    public static final String ADDR = "Address";
    public static final String NETMASK = "Netmask";
    public static final String GATEWAY = "Gateway";
    public static final String IPV4 = "IPv4";
    public static final String IPV6 = "IPv6";

    /**
     * return Network window.
     */
    public Window crateNetworkWindow() {
        final Window nwWindow = new Window("Network Window");
        final VerticalLayout layout = new VerticalLayout();

        layout.setSizeFull();
        nwWindow.setContent(layout);
        nwWindow.center();
        nwWindow.setHeight("80%");
        nwWindow.setWidth("50%");

        final GridLayout grid = new GridLayout(7,30);
        grid.addStyleName("gridNetwork");
        grid.setWidth("1000px");
        grid.setHeight("700px");

        final Label ncTitle = new Label(NC_TITLE);
        ncTitle.setStyleName("ncTitle");

        final Label hostName = new Label(HOST_NAME);
        hostName.setStyleName("hostName");

        final Label domain = new Label(DOMAIN_SUFFIX);
        domain.setStyleName("domain");
        final Label dns1 = new Label(DNS1);
        dns1.setStyleName("dns1");
        final Label dns2 = new Label(DNS2);
        dns2.setStyleName("dns2");
        grid.addComponent(ncTitle,0,1);
        grid.addComponent(hostName,1,2);
        grid.addComponent(domain,1,3);
        grid.addComponent(dns1,1,4);
        grid.addComponent(dns2,1,5);

        final TextField tf1 = new TextField();
        final TextField tf2 = new TextField();
        final TextField tf3 = new TextField();
        final TextField tf4 = new TextField();
        grid.addComponent(tf1,2,2);
        grid.addComponent(tf2,2,3);
        grid.addComponent(tf3,2,4);
        grid.addComponent(tf4,2,5);
        final Label icTitle = new Label(IC_TITLE);
        icTitle.setStyleName("icTitle");
        grid.addComponent(icTitle,0,6);
        final Label niTitle = new Label(NI_TITLE_0);
        niTitle.setStyleName("niTitle");
        grid.addComponent(niTitle,0,7);
        final Label dhcp = new Label(DHCP);
        dhcp.setStyleName("dhcp");
        final Label addr = new Label(ADDR);
        addr.setStyleName("addr");
        final Label nm = new Label(NETMASK);
        nm.setStyleName("nm");
        final Label gw = new Label(GATEWAY);
        gw.setStyleName("gw");
        grid.addComponent(dhcp,1,11);
        grid.addComponent(addr,1,12);
        grid.addComponent(nm,1,13);
        grid.addComponent(gw,1,14);
        final Label lbIpv4 = new Label(IPV4);
        lbIpv4.addStyleName("Ipv4");
        final Label lbIpv6 = new Label(IPV6);
        lbIpv6.addStyleName("Ipv6");
        final CheckBox cbV4 = new CheckBox();
        final TextField tfAddV4 = new TextField();
        final TextField tfNmV4 = new TextField();
        final TextField tfGwV4 = new TextField();
        cbV4.addValueChangeListener( event ->
            doEnabledFalseTextFields(tfAddV4,tfNmV4,tfGwV4)
        );
        final CheckBox cbV6 = new CheckBox();
        final TextField tfAddV6 = new TextField();
        final TextField tfNmV6 = new TextField();
        final TextField tfGwV6 = new TextField();
        cbV6.addValueChangeListener( event ->
            doEnabledFalseTextFields(tfAddV6,tfNmV6,tfGwV6)
        );
        grid.addComponent(lbIpv4,2,10);
        grid.addComponent(lbIpv6,3,10);
        grid.addComponent(cbV4,2,11);
        grid.addComponent(cbV6,3,11);
        grid.addComponent(tfAddV4,2,12);
        grid.addComponent(tfNmV4,2,13);
        grid.addComponent(tfGwV4,2,14);
        grid.addComponent(tfAddV6,3,12);
        grid.addComponent(tfNmV6,3,13);
        grid.addComponent(tfGwV6,3,14);
        final Label ni01Title = new Label(NI_TITLE_1);
        ni01Title.setStyleName("ni01Title");
        grid.addComponent(ni01Title,0,15);
        final Label dhcp1 = new Label("DHCP");
        dhcp1.setStyleName("dhcp1");
        final Label addr1 = new Label("Address ");
        addr1.setStyleName("addr1");
        final Label nm1 = new Label("Netmask ");
        nm1.setStyleName("nm1");
        final Label gw1 = new Label("Gateway ");
        gw1.setStyleName("gw1");
        grid.addComponent(dhcp1,1,17);
        grid.addComponent(addr1,1,18);
        grid.addComponent(nm1,1,19);
        grid.addComponent(gw1,1,20);
        final Label emtyLabel = new Label();
        grid.addComponent(emtyLabel,1,21);
        final Label lbIpv41 = new Label("IPv4");
        lbIpv41.addStyleName("Ipv41");
        final Label lbIpv61 = new Label("IPv6");
        lbIpv61.addStyleName("Ipv61");
        final CheckBox cbV41 = new CheckBox();
        final TextField tfAddV41 = new TextField();
        final TextField tfNmV41 = new TextField();
        final TextField tfGwV41 = new TextField();
        cbV41.addValueChangeListener( event ->
            doEnabledFalseTextFields(tfAddV41,tfNmV41,tfGwV41)
        );
        final CheckBox cbV61 = new CheckBox();
        final TextField tfAddV61 = new TextField();
        final TextField tfNmV61 = new TextField();
        final TextField tfGwV61 = new TextField();
        cbV61.addValueChangeListener( event ->
            doEnabledFalseTextFields(tfAddV61,tfNmV61,tfGwV61)
        );
        grid.addComponent(lbIpv41,2,16);
        grid.addComponent(lbIpv61,3,16);
        grid.addComponent(cbV41,2,17);
        grid.addComponent(cbV61,3,17);
        grid.addComponent(tfAddV41,2,18);
        grid.addComponent(tfNmV41,2,19);
        grid.addComponent(tfGwV41,2,20);
        grid.addComponent(tfAddV61,3,18);
        grid.addComponent(tfNmV61,3,19);
        grid.addComponent(tfGwV61,3,20);
        final ButtonsFactory button = new ButtonsFactory();
        button.createSaveCancelButtons(grid);
        //createSaveCancelButtons(grid);
        layout.addComponent(grid);

        return nwWindow;
    }

    /**
     * Enabled the text fields.
     */
    private void doEnabledFalseTextFields(final TextField tfAddV4, final TextField tfNmV4, final TextField tfGwV4) {
        if (tfAddV4.isEnabled() && tfNmV4.isEnabled() && tfGwV4.isEnabled()) {
            tfAddV4.setEnabled(false);
            tfNmV4.setEnabled(false);
            tfGwV4.setEnabled(false);
        } else {
            tfAddV4.setEnabled(true);
            tfNmV4.setEnabled(true);
            tfGwV4.setEnabled(true);
        }
        ;
    }

}
