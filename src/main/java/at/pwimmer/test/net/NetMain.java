package at.pwimmer.test.net;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import at.pwimmer.test.net.config.AppConfig;
import at.pwimmer.test.net.config.ConfigLoader;
import at.pwimmer.test.net.view.NetView;

public class NetMain {
	private static final File CONFIG_FILE = new File("net-config.json");
	
	public static void main(String[] args) {
		final Optional<AppConfig> result = ConfigLoader.loadConfigFrom(CONFIG_FILE);

		if(result.isPresent()) {
			SwingUtilities.invokeLater(new NetView(result.get()));
		}
		else {
			JOptionPane.showMessageDialog(null, "Failed to load the Application-Configuration - Using Default-Values!",
					"ERROR @ CONFIG", JOptionPane.ERROR_MESSAGE);
			SwingUtilities.invokeLater(new NetView(new AppConfig()));
		}
	}
}