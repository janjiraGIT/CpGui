package com.mobilityguard.acc.cpgui;

import com.mobilityguard.acc.controller.JsonController;
import com.mobilityguard.acc.data.DataTypeInfo;
import com.mobilityguard.acc.network.GridFactory;
import com.mobilityguard.acc.network.IpConfig;
import com.mobilityguard.acc.network.IpvParser;
import com.mobilityguard.acc.scripts.ScriptRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class NetworkWindow {
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
    private VerticalLayout layout = new VerticalLayout();
    private String jsonStr;
    private JsonNode eth0;
    private JsonNode eth1;
    private JsonNode eth2;
    private Map<String, Object> itemMap;
    final CheckBox cbV41 = new CheckBox();
    final CheckBox cbV61 = new CheckBox();
    final TextField tfAddV42 = new TextField();
    final TextField tfNmV42 = new TextField();
    final TextField tfGwV42 = new TextField();
    final CheckBox cbV42 = new CheckBox();
    final CheckBox cbV62 = new CheckBox();
    final GridLayout grid = new GridLayout(7,50);
    private ArrayList<Integer> rowList = new ArrayList<Integer>();
    private GridFactory gdFactory = new GridFactory();
    private Panel emptyPanel = new Panel();
    private JsonNode rootNode;
    private CheckBox cb = new CheckBox();
    private TextField tf1 = new TextField();
    private TextField tf2 = new TextField();
    private TextField tf3 = new TextField();
    private Boolean isCbChecked;
    private String addrStr;
    private String netmaskStr;
    private String gatewayStr;
    private Map<String,Object> newDataMap0ipv4 = new LinkedHashMap<String, Object>();
    private Map<String,Object> newDataMap0ipv6 = new LinkedHashMap<String, Object>();
    private static final String LOCAL_URL = "/opt/acc/config/network.json";

    private static final Logger log = LoggerFactory.getLogger(NetworkWindow.class);

    /**
     * return Network window.
     */
    public Window crateNetworkWindow() throws ParseException {
        final Window nwWindow = new Window();
        final Panel panel = new Panel("Network Setttings Window");
        final VerticalLayout layout = new VerticalLayout();
        panel.setContent(layout);
        panel.setHeightUndefined();
        nwWindow.setContent(panel);
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.setSizeFull();
        layout.setSizeFull();
        nwWindow.setPositionX(300);
        nwWindow.setPositionY(65);
        nwWindow.setHeight("100%");
        nwWindow.setWidth("38%");
        final GridLayout grid = setUpGrid();
        layout.addComponent(grid);
        return nwWindow;
    }

    private GridLayout setUpGrid() throws ParseException {
        grid.addStyleName("gridNetwork");
        grid.space();
        grid.setWidth("670px");
        grid.setHeight("900px");
        //Test : load network real data using script
        this.loadNetworkInfo();
        gdFactory.createNetworkConfiguration(grid);
        cratedInterfaceConfiguration(grid);
        return grid;
    }

    private void cratedInterfaceConfiguration(final GridLayout grid) throws ParseException {
        final Label icTitle = new Label(IC_TITLE);
        icTitle.setStyleName("icTitle");
        grid.addComponent(icTitle,0,6);
        loadParserNetworkData(layout);
    }

    private void loadParserNetworkData(final VerticalLayout layout ) throws ParseException {
        final DataTypeInfo data  = new DataTypeInfo();
        try {
            jsonStr = data.loadNetworkInfo();
        } catch (Exception e) {
        	e.getStackTrace();
        	log.error("Could not file data!");
        }
        log.info("Json String : " + jsonStr);
        final ObjectMapper mapper = new ObjectMapper();
        try {
            rootNode = mapper.readTree(jsonStr);
            if (rootNode.isEmpty(null)) {
                layout.addComponent(emptyPanel);
            } else {
                loadDataAndCreateGrid(layout,rootNode);
            }
        } catch (JsonProcessingException e) {
            log.error("Json Processing Exception");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Cound not read the string");
            e.printStackTrace();
        }

        // anvi comments...
        // Code for generating java classes from network config json-data
        // Not in use yet, but later, network config window should use this data to generate the window

        ObjectMapper mapper2 = new ObjectMapper();

        TypeReference<HashMap<String,Map<String, IpConfig>>> typeRef
                = new TypeReference<HashMap<String,Map<String, IpConfig>>>() {};

        try {
            HashMap<String, Map<String, IpConfig>> interfaces = mapper.readValue(jsonStr, typeRef);
            System.out.println("Got " + interfaces);

            for (Map.Entry<String, Map<String, IpConfig>> entry : interfaces.entrySet()) {
                System.out.println("---------------");
                System.out.println("");
                System.out.println(entry.getKey());

                Map<String, IpConfig> iFace = entry.getValue();
                for (Map.Entry<String, IpConfig> ipEntry : iFace.entrySet()) {
                    System.out.println(ipEntry.getKey());
                    IpConfig interfaceConfig = ipEntry.getValue();
                    System.out.println("address: " + interfaceConfig.getAddress());
                    System.out.println("gaterway: " + interfaceConfig.getGateway());
                    System.out.println("netmask: " + interfaceConfig.getNetmask());
                    System.out.println("dhcp: " + interfaceConfig.getDhcp());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadDataAndCreateGrid(final VerticalLayout layout,final JsonNode rootNode) throws IOException, ParseException {
        if (rootNode.path("eth0").isMissingNode()) {
            layout.addComponent(emptyPanel);
        }
        if (rootNode.findPath("eth0") != null) {
            eth0 = rootNode.path("eth0");
            String eth0Str = "eth0";
            final IpvParser ipvParser = new IpvParser();
            itemMap = new LinkedHashMap<String, Object>();
            final JsonNode ipv4 = eth0.path("ipv4");
            if ( eth0 != null && ipv4 != null) {
                ipvParser.parserNodeDataToMap(ipv4,itemMap);
                final CheckBox cb4 = new CheckBox();
                rowList = new ArrayList<Integer>();
                rowList.add(0,7 );
                rowList.add(1,10 );
                rowList.add(2,10 );
                rowList.add(3,11 );
                rowList.add(4,12 );
                rowList.add(5,13 );
                rowList.add(6,14 );
                gdFactory.createEthText(grid,0,rowList);
                Map<String,Object> componentsMap = new LinkedHashMap<String, Object>();
                componentsMap = gdFactory.setValueInEth(grid,itemMap, cb4 , 0, 11,"ipv4", eth0Str);
                final Map<String,Object> newDataMap = new LinkedHashMap<String, Object>();
                newDataMap0ipv4 = updateData(componentsMap, newDataMap, grid);
            }
            final JsonNode ipv6 = eth0.path("ipv6");
            if ( eth0 != null && ipv6 != null) {
                itemMap = ipvParser.parserNodeDataToMap(ipv6,itemMap);
                final CheckBox cb6 = new CheckBox();
                Map<String,Object> componentsMap1 = new LinkedHashMap<String, Object>();
                componentsMap1 = gdFactory.setValueInEth(grid,itemMap,cb6, 1, 11, "ipv6", eth0Str);
                Map<String,Object> newDataMap2 = new LinkedHashMap<String, Object>();
                newDataMap0ipv6 = updateData(componentsMap1, newDataMap2,grid);
            }
        }
        if (rootNode.path("eth1").isMissingNode()) {
            layout.addComponent(emptyPanel);
        } else  {
            eth1 = rootNode.path("eth1");
            String eth1Str = "eth1";
            final IpvParser ipvParser = new IpvParser();
            itemMap = new LinkedHashMap<String, Object>();
            final JsonNode ipv4 = eth1.path("ipv4");
            final JsonNode ipv6 = eth1.path("ipv6");
            if ( ipv4 != null) {
                itemMap = ipvParser.parserNodeDataToMap(ipv4,itemMap);
                rowList = new ArrayList<Integer>();
                rowList.add(0,15 );
                rowList.add(1,16 );
                rowList.add(2,16 );
                rowList.add(3,17 );
                rowList.add(4,18 );
                rowList.add(5,19 );
                rowList.add(6,20 );
                gdFactory.createEthText(grid,1,rowList);
                final CheckBox cb4 = new CheckBox();
                gdFactory.setValueInEth(grid,itemMap, cb4 , 0, 17,"ipv4",eth1Str);
            }
            if ( ipv6 != null) {
                itemMap = ipvParser.parserNodeDataToMap(ipv6,itemMap);
                final CheckBox cb6 = new CheckBox();
                gdFactory.setValueInEth(grid,itemMap, cb6 , 1, 17,"ipv6",eth1Str);
            }
        }
        if (rootNode.path("eth2").isMissingNode()) {
            layout.addComponent(emptyPanel);
        } else  {
            String eth2Str = "eth2";
            eth2 = rootNode.path("eth0");
            final IpvParser ipvParser = new IpvParser();
            itemMap = new LinkedHashMap<String, Object>();
            final JsonNode ipv4 = eth2.path("ipv4");
            final JsonNode ipv6 = eth2.path("ipv6");
            if ( ipv4 != null) {
                itemMap = ipvParser.parserNodeDataToMap(ipv4,itemMap);
                rowList = new ArrayList<Integer>();
                rowList.add(0,23 );
                rowList.add(1,24 );
                rowList.add(2,24 );
                rowList.add(3,25 );
                rowList.add(4,26 );
                rowList.add(5,27 );
                rowList.add(6,28 );
                gdFactory.createEthText(grid,2,rowList);
                final CheckBox cb4 = new CheckBox();
                gdFactory.setValueInEth(grid,itemMap, cb4 , 0, 25,"ipv4",eth2Str);
            }
            if ( ipv6 != null) {
                itemMap = ipvParser.parserNodeDataToMap(ipv6,itemMap);
                final CheckBox cb6 = new CheckBox();
                gdFactory.setValueInEth(grid,itemMap, cb6 , 1, 25,"ipv6",eth2Str);
            }
        }
        ;
        final Map<String, Map<String,Object>> listMap = new HashMap<>();
        listMap.put("eth04", newDataMap0ipv4);
        listMap.put("eth06", newDataMap0ipv6);
        log.info("eth04 " + listMap.get("eth04") );
        log.info("eth06 " + listMap.get("eth06") );
        crateSaveCancelButtons(grid,newDataMap0ipv4 );
        //crateSaveCancelButtons(grid,listMap );
    }

    @SuppressWarnings("unchecked")
    private void saveDataToFile(final Map<String,Object> newDataMap) throws IOException, ParseException {
        final JSONObject objEth = new JSONObject();
        final JSONObject objIpv = new JSONObject();
        final JSONObject obj = new JSONObject();
        obj.put("address", newDataMap.get("addrStr"));
        obj.put("dhcp", newDataMap.get("isCbChecked"));
        obj.put("netmask",newDataMap.get("netmaskStr") );
        obj.put("gateway", newDataMap.get("gatewayStr") );
        objIpv.put("ipv4", obj);
        objEth.put("eth0", objIpv);
        JsonController controller = new JsonController();
        controller.writeIntoFile(LOCAL_URL, objEth);
    }

    private Map<String, Object> updateData(final Map<String, Object> componentsMap, final Map<String, Object> newDataMap,
                                                            final GridLayout grid) throws IOException, ParseException {
        cb = new CheckBox();
        tf1 = new TextField();
        tf2 = new TextField();
        tf3 = new TextField();
        final String ethStr = (String) componentsMap.get("eth");
        componentsMap.get("ipv");
        final String ipvStr = (String) componentsMap.get("ipv");
        cb = (CheckBox) componentsMap.get("checkbox");
        tf1 = (TextField) componentsMap.get("tfAddr");
        tf2 = (TextField) componentsMap.get("tfNetmask");
        tf3 = (TextField) componentsMap.get("tfGateway");
        newDataMap.put("eth", ethStr);
        newDataMap.put("ipv", ipvStr);
        cb.addValueChangeListener(event -> {
            isCbChecked = event.getValue();
            newDataMap.put("isCbChecked", isCbChecked);
        });
        tf1.addValueChangeListener(event -> {
            addrStr = event.getValue();
            newDataMap.put("addrStr", addrStr);
        });
        tf2.addValueChangeListener(event -> {
            netmaskStr = event.getValue();
            newDataMap.put("netmaskStr", netmaskStr);
        });
        tf3.addValueChangeListener(event -> {
            gatewayStr = event.getValue();
            newDataMap.put("gatewayStr", gatewayStr);
        });
        return newDataMap;
    }

    private void crateSaveCancelButtons(final GridLayout grid, final Map<String, Object> newDataMap) {
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setStyleName("buttonBackground");
        final Button save = new Button("save");
        save.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                try {
                    Notification.show("Updated!");
                    saveDataToFile(newDataMap);
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        save.setStyleName("saveButton");
        final Button cancelButton = new Button("cancel");
        cancelButton.setStyleName("cancelButton");
        buttonLayout.addComponents(save,cancelButton);
        buttonLayout.setSpacing(true);
        grid.addComponent(buttonLayout,2,30);
    }

    public void loadNetworkInfo() {
        final String jsonStr = ScriptRunner.getNetworkInterfaces();
        System.err.println(jsonStr);
    }
}