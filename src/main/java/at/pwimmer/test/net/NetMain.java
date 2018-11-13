package at.pwimmer.test.net;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import at.pwimmer.test.net.config.AppConfig;
import at.pwimmer.test.net.config.ConfigLoader;
import at.pwimmer.test.net.view.NetView;

public class NetMain {
	private static final File CONFIG_FILE = new File("net_config.json");
	
	public static void main(String[] args) {
		AppConfig config = null;
		
		try {
			config = ConfigLoader.loadConfigFrom(CONFIG_FILE);
		}
		catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "Failed to load the Application-Configuration - Using Default-Values!"
					+ "\nError: " + ex.getMessage(), "ERROR @ CONFIG", JOptionPane.ERROR_MESSAGE);
			config = new AppConfig();
		}
		finally {
			SwingUtilities.invokeLater(new NetView(config));
		}
	}
}