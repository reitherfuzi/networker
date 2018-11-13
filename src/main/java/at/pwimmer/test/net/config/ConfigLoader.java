package at.pwimmer.test.net.config;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigLoader {
	
	public static AppConfig loadConfigFrom(File configFile) throws IOException {
		if(configFile != null) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.readValue(configFile, AppConfig.class);
			}
			catch(IOException ex) {
				throw new IOException("Failed to load the App-Config from the JSON-File!");
			}
		}
		else {
			throw new IllegalArgumentException("The passed Config-File is null!");
		}
	}
	
	public static void saveConfigTo(AppConfig config, File configFile) throws IOException {
		if(configFile != null) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(configFile, config);
			}
			catch(IOException ex) {
				throw new IOException("Failed to save the passed App-Config to the JSON-File!");
			}
		}
		else {
			throw new IllegalArgumentException("The passed File and/or the passed AppConfig is null!");
		}
	}
	
	private ConfigLoader() {}
}
