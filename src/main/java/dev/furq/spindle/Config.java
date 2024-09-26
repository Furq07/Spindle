package dev.furq.spindle;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The Config class handles configuration file management and loading.
 */
public class Config {

    private static final Yaml yaml = new Yaml();
    private static File configFolder;

    private Config(File configFolder) {
        Config.configFolder = configFolder;
    }

    /**
     * Sets up the configuration with the specified folder and config names.
     *
     * @param configFolder The folder where configuration files are stored.
     * @param configNames A list of configuration file names to set up.
     * @return A new Config instance.
     */
    public static Config setupConfig(File configFolder, List<String> configNames) {
        if (!configFolder.exists()) {
            configFolder.mkdirs();
        }

        Config config = new Config(configFolder);
        for (String configName : configNames) {
            File configFile = new File(configFolder, configName);
            if (!configFile.exists()) {
                config.saveResource(configName, configFile);
            }
        }
        return config;
    }

    /**
     * Loads a configuration file and returns a Parser instance.
     *
     * @param name The name of the configuration file to load.
     * @return A Parser instance for the loaded configuration.
     * @throws IllegalArgumentException if the config file is not found.
     */
    public static Parser load(String name) {
        File configFile = new File(configFolder, name);
        if (!configFile.exists()) {
            throw new IllegalArgumentException("Config file " + name + " not found!");
        }
        return Parser.fromFile(configFile);
    }

    /**
     * Loads a configuration file and returns its content as a Map.
     *
     * @param file The configuration file to load.
     * @return A Map containing the configuration data.
     * @throws RuntimeException if there's an error loading the config file.
     */
    protected static Map<String, Object> loadConfigFile(File file) {
        if (file.exists()) {
            try (FileInputStream inputStream = new FileInputStream(file)) {
                return yaml.loadAs(inputStream, Map.class);
            } catch (Exception e) {
                throw new RuntimeException("Error loading config file", e);
            }
        } else {
            return Collections.emptyMap();
        }
    }

    /**
     * Sets the serializer type for string deserialization.
     *
     * @param serializerType The SerializerType to set.
     */
    public static void setSerializerType(SerializerType serializerType) {
        Parser.serializerType = serializerType;
        if (serializerType != SerializerType.NONE) {
            try {
                Class.forName("net.kyori.adventure.text.Component");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Adventure API is required for LEGACY and MINIMESSAGE serialization types. Please add the necessary dependencies.");
            }
        }
    }

    private void saveResource(String resourceName, File target) {
        try (InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (resourceStream != null) {
                Files.copy(resourceStream, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } else {
                System.err.println("Resource " + resourceName + " not found!");
            }
        } catch (Exception e) {
            System.err.println("Failed to save resource " + resourceName + " to " + target.getAbsolutePath());
            e.printStackTrace();
        }
    }
}