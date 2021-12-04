package com.caglayan.fxmoviedb.utils;

import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.util.Properties;

public class PropertiesUtil {
	private static PropertiesUtil instance;
	private Properties properties;
	private String filesPath;
	private String propertiesFilePath;

	private PropertiesUtil() {
		super();
	}

	public static PropertiesUtil getInstance() {
		if (instance == null) {
			instance = new PropertiesUtil();
		}
		return instance;
	}

	public String getFilesPath() {
		if (filesPath == null || filesPath == "") {
			this.filesPath = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "\\files\\";
		}
		return this.filesPath;
	}

	private String getPropetiesFilePath() {
		if (propertiesFilePath == null || propertiesFilePath == "") {
			propertiesFilePath = this.getFilesPath() + "properties.properties";
		}
		return this.propertiesFilePath;
	}

	private Properties getProperties() {
		if (this.properties == null) {
			this.properties = new Properties();
			try {
				this.properties.load(new FileInputStream(getPropetiesFilePath()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.properties;
	}

	public String getProperty(String key) {
		return this.getProperties().get(key).toString();
	}

	public String getLanguageProperty() {
		return this.getProperties().getProperty("LANGUAGE", "tr");
	}

	public String getDataBaseProperty() {
		return this.getProperties().getProperty("DATABASE", "hibernate_movies");
	}
}
