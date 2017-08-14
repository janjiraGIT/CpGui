package com.mobilityguard.acc.network;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class GridFactory {
    public static final String NC_TITLE = "Network Configuration";
    public static final String HOST_NAME = "Host name";
    public static final String DOMAIN_SUFFIX = "Domain Suffix";
    public static final String DNS1 = "DNS Server1";
    public static final String DNS2 = "DNS Server2";
    public static final String IC_TITLE = "Interface Configuration";
    public static final String NI_TITLE = "Network Interface eth ";
    public static final String DHCP = "DHCP";
    public static final String ADDR = "Address";
    public static final String NETMASK = "Netmask";
    public static final String GATEWAY = "Gateway";
    public static final String IPV4 = "IPv4";
    public static final String IPV6 = "IPv6";
    private TextField tfHostname;
    private TextField tfDomain;
    private TextField tfDns1;
    private TextField tfDns2;
    private Map<String, String> list;
    private String hostStr;
    private String domainStr;
    private String dns1Str;
    private String dns2Str;
    private TextField tfAddr = null;
    private TextField tfNetmask = null;
    private TextField tfGateway = null;
    private static final Logger log = LoggerFactory.getLogger(GridFactory.class);

    /**
     * create network configuration.
     */
    public void createNetworkConfiguration(final GridLayout grid) {
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

        tfHostname = new TextField();
        tfDomain = new TextField();
        tfDns1 = new TextField();
        tfDns2 = new TextField();
        list = new LinkedHashMap<String, String>();

        tfHostname.addValueChangeListener(event -> {
            hostStr = event.getValue();
            list.put("host", hostStr);
        });
        tfDomain.addValueChangeListener(event -> {
            domainStr = event.getValue();
            list.put("domain", domainStr);

        });
        tfDns1.addValueChangeListener(event -> {
            dns1Str = event.getValue();
            list.put("dns1Str", dns1Str);

        });
        tfDns2.addValueChangeListener(event -> {
            dns2Str = event.getValue();
            list.put("dns2Str", dns2Str);
        });
        for ( Map.Entry<String, String> entry : list.entrySet()) {
            log.info(entry.getKey());
            log.info(entry.getValue());
        }

        grid.addComponent(tfHostname,2,2);
        grid.addComponent(tfDomain,2,3);
        grid.addComponent(tfDns1,2,4);
        grid.addComponent(tfDns2,2,5);
    }

    /**
     * set text of eth.
     */
    @SuppressWarnings("serial")
    public void createEthText(final GridLayout grid, final int nr, final ArrayList<Integer> rowList) {
        grid.addComponent(new Label(NI_TITLE + nr) {
                {
                    setStyleName("niTitle");
                }
            }, 0, rowList.get(0) );
        grid.addComponent(new Label("Ipv4") {
                {
                    setStyleName("Ipv4");
                }
            }, 2, rowList.get(1) );
        grid.addComponent(new Label(IPV6) {
                {
                    setStyleName("Ipv6");
                }
            }, 3, rowList.get(2) );

        grid.addComponent(new Label(DHCP)    ,1, rowList.get(3));
        grid.addComponent(new Label(ADDR)    ,1, rowList.get(4));
        grid.addComponent(new Label(NETMASK) ,1, rowList.get(5));
        grid.addComponent(new Label(GATEWAY) ,1, rowList.get(6));
    }

    /**
     * set value of eth.
     */
    @SuppressWarnings("serial")
    public Map<String,Object> setValueInEth(final GridLayout grid, final Map<String, Object> itemMap,
                                final CheckBox cb4, final int col, final int row, final String ipvStr,
                                final String eth) throws IOException, ParseException {
        // put data to textfields
        grid.addComponent(cb4, 2 + col, row);
        if (itemMap.containsKey("dhcp") && (itemMap.get("dhcp").equals(true))) {
            cb4.setValue(true);
        }
        grid.addComponent(tfAddr = new TextField() {
                {
                    setValue((String) itemMap.get("address"));
                }
            }, 2 + col,row + 1 );
        grid.addComponent(tfNetmask = new TextField() {
                {
                    setValue((String) itemMap.get("netmask"));
                }
            }, 2 + col,row + 2 );
        grid.addComponent(tfGateway = new TextField() {
                {
                    setValue((String) itemMap.get("gateway"));
                }
            }, 2 + col,row + 3 );
        /*
         * Add components to hash map.
         */
        Map<String, Object> componentsMap = new LinkedHashMap<String,Object>();
        componentsMap.put("eth", eth);
        componentsMap.put("ipv", ipvStr);
        componentsMap.put("checkbox", cb4);
        componentsMap.put("tfAddr", tfAddr);
        componentsMap.put("tfNetmask", tfNetmask);
        componentsMap.put("tfGateway", tfGateway);
        return componentsMap;
    }
}


