package com.mobilityguard.acc.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public class IpvParser implements IpvType {
    public static final String ADDRESS = "address";
    public static final String NETMASK = "netmask";
    public static final String GATEWAY = "gateway";
    public static final String DHCP = "dhcp";

    private static final Logger log = LoggerFactory.getLogger(IpvParser.class);

    @Override
    public Map<String, Object> parserNodeDataToMap(final JsonNode jsonNode, final Map<String, Object> itemMap) {
        if (jsonNode != null) {
            JsonNode address = jsonNode.path(ADDRESS);
            JsonNode netmask = jsonNode.path(NETMASK);
            JsonNode gateway = jsonNode.path(GATEWAY);
            JsonNode dhcp = jsonNode.path(DHCP);
            String addrStr = address.asText();
            String netmaskStr = netmask.asText();
            String gatewayStr = gateway.asText();
            Boolean isDhcp = dhcp.asBoolean();
            itemMap.put(ADDRESS, addrStr);
            itemMap.put(NETMASK, netmaskStr);
            itemMap.put(GATEWAY, gatewayStr);
            itemMap.put(DHCP,isDhcp );
            for (Map.Entry<String, Object> entry : itemMap.entrySet()) {
                log.info(entry.getKey() + " : " + entry.getValue() );
            }
        }
        return itemMap;
    }
}
