package com.mobilityguard.acc.network;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IpConfig {

    @JsonProperty
    private String address;

    @JsonProperty
    private String netmask;

    @JsonProperty
    private String gateway;

    @JsonProperty
    private boolean dhcp;

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(final String netmask) {
        this.netmask = netmask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(final String gateway) {
        this.gateway = gateway;
    }

    public boolean getDhcp() {
        return dhcp;
    }

    public void setDhcp(final boolean dhcp) {
        this.dhcp = dhcp;
    }

}
