package at.pwimmer.test.net.view.panels;

import java.awt.Font;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import at.pwimmer.test.net.config.AppConfig;
import net.miginfocom.swing.MigLayout;

public class SysInfoPanel extends JPanel {
	private static final Font DEFAULT_FONT = new Font("Tahoma", Font.PLAIN, 14);

	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldIP;
	private JTextField textFieldConnType;
	private JTextField textFieldGateway;
	private JTextField textFieldOS;
	private JTextField textFieldJavaVersion;
	private JTextField textFieldUser;
	
	public SysInfoPanel() {
		initComponents();
	}
	
	public SysInfoPanel(AppConfig config) {
		if(config != null) {
			initComponents();
			setGatewayIP(config.getGateway());
			getAndSetSystemInfos();
		}
	}
	
	public void setGatewayIP(String gateway) {
		if(gateway != null && !gateway.trim().isEmpty() && textFieldGateway != null) {
			textFieldGateway.setText(gateway);
		}
	}
	
	public void getAndSetSystemInfos() {
		String osName = System.getProperty("os.name");
		String osArch = System.getProperty("os.arch");
		
		String java = System.getProperty("java.version");
		String user = System.getProperty("user.name");
		
		String localIP = getLocalIPAddress();
		
		textFieldIP.setText(localIP);
		textFieldJavaVersion.setText(java);
		textFieldUser.setText(user);
		textFieldOS.setText(osName + " [" + osArch + "]");
	}
	
	private String getLocalIPAddress() {
		try {
			return Inet4Address.getLocalHost().getHostAddress();
		}
		catch(UnknownHostException ex) {
			return "IP could not be resolved!";
		}
	}
	
	private void initComponents() {
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
		
		JLabel lblIpaddress = new JLabel("IP-Address :");
		lblIpaddress.setFont(DEFAULT_FONT);
		add(lblIpaddress, "cell 0 0,alignx trailing");
		
		textFieldIP = new JTextField();
		textFieldIP.setColumns(10);
		add(textFieldIP, "cell 1 0,growx");
		
		JLabel lblGateway = new JLabel("Gateway :");
		lblGateway.setFont(DEFAULT_FONT);
		add(lblGateway, "cell 0 1,alignx trailing");
		
		textFieldGateway = new JTextField();
		textFieldGateway.setColumns(10);
		add(textFieldGateway, "cell 1 1,growx");
		
		JLabel lblConnectiontype = new JLabel("Connection-Type :");
		lblConnectiontype.setFont(DEFAULT_FONT);
		add(lblConnectiontype, "cell 0 2,alignx trailing");
		
		textFieldConnType = new JTextField();
		textFieldConnType.setColumns(10);
		add(textFieldConnType, "cell 1 2,growx");
		
		JSeparator separator = new JSeparator();
		add(separator, "cell 0 3 2 1,growx");
		
		JLabel lblSystemos = new JLabel("Operating-System :");
		lblSystemos.setFont(DEFAULT_FONT);
		add(lblSystemos, "cell 0 4,alignx trailing");
		
		textFieldOS = new JTextField();
		textFieldOS.setColumns(10);
		add(textFieldOS, "cell 1 4,growx");
		
		JLabel lblJavaversion = new JLabel("Java-Version :");
		lblJavaversion.setFont(DEFAULT_FONT);
		add(lblJavaversion, "cell 0 5,alignx trailing");
		
		textFieldJavaVersion = new JTextField();
		textFieldJavaVersion.setColumns(10);
		add(textFieldJavaVersion, "cell 1 5,growx");
		
		JLabel lblUser = new JLabel("Current User :");
		lblUser.setFont(DEFAULT_FONT);
		add(lblUser, "cell 0 6,alignx trailing");
		
		textFieldUser = new JTextField();
		textFieldUser.setFont(DEFAULT_FONT);
		textFieldUser.setColumns(10);
		add(textFieldUser, "cell 1 6,growx");
	}
}