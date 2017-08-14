package com.mobilityguard.acc.network;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public interface IpvType {
    public Map<String, Object> parserNodeDataToMap(final JsonNode jsonNode, final Map<String, Object> itemMap);
}
