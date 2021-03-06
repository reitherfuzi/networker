package at.pwimmer.test.net.config;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import at.pwimmer.test.net.model.PingSystem;

public class AppConfig {
	private String gateway;
	private List<PingSystem> systemList;

	public AppConfig(String gateway, List<PingSystem> pingSystemList) {
		setGateway(gateway);
		setSystemList(pingSystemList);
	}
	
	public AppConfig() {
		setSystemList(new ArrayList<>());
		setGateway(InetAddress.getLoopbackAddress().getHostAddress());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gateway == null) ? 0 : gateway.hashCode());
		result = prime * result + ((systemList == null) ? 0 : systemList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppConfig other = (AppConfig) obj;
		if (gateway == null) {
			if (other.gateway != null)
				return false;
		} else if (!gateway.equals(other.gateway))
			return false;
		if (systemList == null) {
			if (other.systemList != null)
				return false;
		} else if (!systemList.equals(other.systemList))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "AppConfig [GW-IP : " + gateway + " - IP-Systems : " + systemList + "]";
	}
	
	public void setGateway(String gateway) {
		if(gateway == null || gateway.trim().isEmpty()) 	throw new IllegalArgumentException("The passed Gateway is null or empty!");
		this.gateway = gateway;
	}
	
	public void setSystemList(List<PingSystem> pingSystemList) {
		if(pingSystemList == null)	throw new IllegalArgumentException("The passed Map is null!");
		this.systemList = pingSystemList;
	}

	public String getGateway() {
		return gateway;
	}
	
	public List<PingSystem> getSystemList() {
		return systemList;
	}
}