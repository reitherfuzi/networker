package at.pwimmer.test.net.model;

public class PingSystem {
	
	private String name;
	private String ip;
	
	public PingSystem(String name, String ipAddress) {
		setName(name);
		setIPAddress(ipAddress);
	}

	public String getName() {
		return name;
	}

	public String getIPAddress() {
		return ip;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIPAddress(String ip) {
		this.ip = ip;
	}
}