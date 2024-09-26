# 🧵 Spindle

Spindle is a flexible Java library for handling YAML configurations with added functionality made specifically for Minecraft Modding. Built using SnakeYAML, Spindle provides an intuitive API for loading, parsing, and accessing configuration data, with support for nested structures, type conversion, and text component serialization.

---

## 🎯 Key Features

| Feature | Description |
| --- | --- |
| **📁 Easy Configuration Setup** | Quickly set up and manage multiple configuration files. |
| **🔍 Flexible Value Access** | Retrieve configuration values with type-safe methods. |
| **🌳 Nested Structure Support** | Access deeply nested configuration values using dot notation. |
| **🎨 Text Component Serialization** | Integrate with Adventure API for rich text formatting. |
| **🔧 Customizable Serialization** | Choose between legacy, MiniMessage, or no serialization. |
| **🔄 Runtime Configuration Reloading** | Load updated configurations without restarting your application. |

---

## 💻 Usage Examples

```java
// Setup configuration
File configFolder = new File("config");
List<String> configNames = Arrays.asList("config.yml", "messages.yml");
Config.setupConfig(configFolder, configNames);

// Load a configuration file
Parser parser = Config.load("config.yml");

// Get values from the configuration
String serverName = parser.getString("server.name");
int maxPlayers = parser.getInt("server.max-players");
List<String> enabledWorlds = parser.getList("worlds.enabled");

// Set serializer type for string deserialization
Config.setSerializerType(SerializerType.MINIMESSAGE);
```

---

## 📩 Installation

<details open>
<summary>Gradle</summary>

```groovy
dependencies {
    modImplementation(include("org.yaml:snakeyaml:2.2"))
    modImplementation(include("dev.furq:spindle:1.0.0"))

    // Required if using any serialization.
    // modImplementation(include("net.kyori:adventure-text-serializer-legacy:4.17.0"))
    // Optional: Include only if using Adventure API
    // For Fabric:
    // modImplementation(include("net.kyori:adventure-platform-fabric:5.14.1"))
    // For NeoForge:
    // modImplementation(include("net.kyori:adventure-platform-neoforge:5.14.1"))
}
```

</details>

<details>
<summary>Gradle (Kotlin DSL)</summary>

```kotlin
dependencies {
    modImplementation(include("org.yaml", "snakeyaml", "2.2"))
    modImplementation(include("dev.furq", "spindle", "1.0.0"))
    
    // Required if using any serialization.
    // modImplementation(include("net.kyori", "adventure-text-serializer-legacy", "4.17.0"))
    // Optional: Include only if using Adventure API
    // For Fabric:
    // modImplementation(include("net.kyori", "adventure-platform-fabric", "5.14.1"))
    // For NeoForge:
    // modImplementation(include("net.kyori", "adventure-platform-neoforge", "5.14.1"))
}
```

</details>

<details>
<summary>Maven</summary>

```xml
<dependencies>
    <dependency>
        <groupId>org.yaml</groupId>
        <artifactId>snakeyaml</artifactId>
        <version>2.2</version>
    </dependency>
    <dependency>
        <groupId>dev.furq</groupId>
        <artifactId>spindle</artifactId>
        <version>1.0.0</version>
    </dependency>
    <!-- Required if using any serialization. -->
    <dependency>
        <groupId>net.kyori</groupId>
        <artifactId>adventure-text-serializer-legacy</artifactId>
        <version>4.17.0</version>
    </dependency>
    <!-- Optional: Include only if using Adventure API -->
    <!-- For Fabric: -->
    <!--
    <dependency>
        <groupId>net.kyori</groupId>
        <artifactId>adventure-platform-fabric</artifactId>
        <version>5.14.1</version>
    </dependency>
    -->
    <!-- For NeoForge: -->
    <!--
    <dependency>
        <groupId>net.kyori</groupId>
        <artifactId>adventure-platform-neoforge</artifactId>
        <version>5.14.1</version>
    </dependency>
</dependencies>
```

</details>

**Note: The Adventure API platform dependencies are optional. Include the appropriate one based on your modloader (Fabric or NeoForge) only if you plan to use Adventure API features. The legacy serializer is required if you're using any form of serialization. If you're using SerializerType.NONE, you don't need to include these dependencies. The version of `adventure-platform` may vary depending on the Minecraft version you are using. Please refer to the Adventure API documentation for the correct version.**

### Repository

To use Spindle, you'll need to add the Maven Central repository to your project.

<details>
<summary>Gradle</summary>

```groovy
repositories {
    mavenCentral()
}
```

</details>

<details>
<summary>Gradle (Kotlin DSL)</summary>

````kotlin
repositories {
    mavenCentral()
}
````

</details>

<details>
<summary>Maven</summary>

```xml
<repositories>
    <repository>
        <id>central</id>
        <name>Maven Central</name>
        <url>https://repo.maven.apache.org/maven2</url>
    </repository>
</repositories>
```

</details>

---

## 📚 Documentation

For detailed documentation and advanced usage, please refer to the [Wiki](WIKI.md).

---

## 📜 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

---

## 📞 Support

If you encounter any problems or have any questions, please open an issue on the GitHub repository.

---

## 🤝 Sponsor

<p align="center">
  <a href="https://billing.revivenode.com/aff.php?aff=517">
    <img src="https://versions.revivenode.com/resources/banner_wide_one.gif" alt="Sponsor GIF">
  </a>
</p>
<p align="center">
  Use code <b>FURQ</b> for 15% off your order!
</p>