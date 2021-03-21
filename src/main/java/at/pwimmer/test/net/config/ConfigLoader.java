package at.pwimmer.test.net.config;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLoader {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigLoader.class);
	
	public static Optional<AppConfig> loadConfigFrom(File configFile) {
		if(configFile == null)	throw new IllegalArgumentException("The passed Config-File is null!");

		try {
			final ObjectMapper mapper = new ObjectMapper();
			return Optional.of(mapper.readValue(configFile, AppConfig.class));
		}
		catch(IOException ex) {
			LOGGER.error("Failed to load the App-Config from the JSON-File!", ex);
			return Optional.empty();
		}
	}

	public static boolean saveConfigTo(AppConfig config, File configFile) {
		if(configFile == null) 	throw new IllegalArgumentException("The passed File and/or the passed AppConfig is null!");

		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(configFile, config);
			return true;
		}
		catch(JsonGenerationException ex) {
			LOGGER.error("Failed to serialize the passed App-Config to a valid json-object", ex);
			return false;
		}
		catch(IOException ex) {
			LOGGER.error("Failed to save the passed App-Config to the JSON-File!", ex);
			return false;
		}
	}

	private ConfigLoader() {}
}
