package com.tmb.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import com.tmb.enums.ConfigProperties;
import com.tmb.exceptions.PropertyFileUsageException;

/**
 * Read the property file and store it in a HashMap for faster processing.
 * Users can prefer to use json instead of property file based on their requirement.
 * 
 * <pre>
 * <b>
 * <a href="https://www.youtube.com/channel/UC6PTXUHb6j4Oxf0ccdRI11A">Testing Mini Bytes Youtube channel</a>
 * </b>
 * </pre>
 * 
 * Jan 22, 2021 
 * @author Amuthan Sakthivel
 * @version 1.0
 * @since 1.0
 */
public final class PropertyUtils {

	private static Properties property = new Properties();
	//private static final Map<String, String> CONFIGMAP = new HashMap<>();
	private static final Map<String, String> CONFIGMAP;
	/**
	 * Private constructor to avoid external instantiation
	 */
	private PropertyUtils() {}

	static {
	    Properties properties = new Properties();
	    try (FileInputStream file = new FileInputStream("config.properties")) {
	        properties.load(file);
	        Map<String, String> tempMap = new HashMap<>();
	        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
	            tempMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
	        }
	        CONFIGMAP = Collections.unmodifiableMap(tempMap); // ✅ Immutable and thread-safe
	    } catch (IOException e) {
	        throw new RuntimeException("Failed to load config file", e);
	    }
	}

	/**
	 * Receives the {@link com.tmb.enums.ConfigProperties},converts to lowercase , return the corresponding value
	 * for the key from the HashMap
	 * @author Amuthan Sakthivel
	 * Jan 22, 2021
	 * @param key To be fetched from property file
	 * @return corresponding value for the requested key if found else {@link PropertyFileUsageException}
	 */
	public static String get(ConfigProperties key)  {
		if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
			throw new PropertyFileUsageException("Property name " + key + " is not found. Please check config.properties");
		}
		return CONFIGMAP.get(key.name().toLowerCase());
	}

}
