package com.mobilityguard.acc.object;

	public class IpvInfo {
	        private String address;
	        private boolean isDhcp;
	        private String netmask;
	        private String gateway;

	        public String getAddress() {
	            return address;
	        }

	        public void setAddress(final String address) {
	            this.address = address;
	        }

	        public boolean getDhcp() {
	            return isDhcp;
	        }

	        public void setDhcp(final boolean dhcp) {
	            this.isDhcp = dhcp;
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

}
