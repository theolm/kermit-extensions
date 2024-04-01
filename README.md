# Kermit Extensions

[![Maven Central](https://img.shields.io/maven-central/v/dev.theolm/txtlogwriter)](https://mvnrepository.com/artifact/dev.theolm)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.23-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/theolm/kermit-extensions)

Welcome to **Kermit Extensions**, a collection of utilities aimed at enhancing the debugging experience by leveraging the powerful logging API of [Kermit](https://github.com/touchlab/Kermit). This repository is home to custom extensions designed to make your development process smoother, more efficient, and ultimately more productive.

## About Kermit

Kermit is a Kotlin Multiplatform logging utility that provides a common logging interface for all platforms. While Kermit itself offers extensive capabilities, our extensions are here to fill in specific needs that arise during the development and debugging processes.

## Current Extensions

### TxtLogWriter

`TxtLogWriter`, is a custom `LogWriter` that, when integrated with Kermit, allows you to write logs to a `.txt` file. This is particularly useful for sharing logs with teammates or for detailed analysis during the debugging process. It’s designed to be easy to set up and integrates seamlessly with your existing Kermit setup.
Currently, `TxtLogWriter` is available for Android, iOS and desktop platforms.

#### Instalation
To integrate TxtLogWriter into your Kotlin Multiplatform project, add the following dependency to your build.gradle.kts (for Kotlin DSL) file within the commonMain source set. Please ensure to replace <latest.version> with the most current version of TxtLogWriter available.

```kt
sourceSets {
    commonMain.dependencies {
        implementation("dev.theolm:txtlogwriter:<last.version>")
    }
}
```

#### Usage Guidelines

To employ TxtLogWriter within your application, instantiate and register it as a log writer with Kermit using the following syntax. The default configuration is optimized for immediate use in most scenarios, allowing for straightforward integration without additional adjustments.


```kt
Logger.addLogWriter(TxtLogWriter())
```

TxtLogWriter is designed to operate without specific operating system permissions and will automatically create a new log file, named after the current date and time, upon each application initialization.

For situations that demand customized log configurations, TxtLogWriter supports instantiation with detailed settings to better align with your specific logging needs:

```kt
TxtLogWriter(
    config = TxtLogWriter.Config(
        filePath = "path/to/log/directory", // Define the directory path for the log files.
        fileName = "custom_log_file.txt", // Set the name of the log file, which must end with .txt.
        linePrefix = LinePrefix.Time, // Select a prefix to add before each log message.
        canWrite = { tag, severity -> true }, // Function to determine whether a log entry should be written.
        customMessageBuilder = CustomMessageBuilder() // Optionally, define a custom format for log messages.
    )
)
```

Supported Line prefix:
```kt
LinePrefix.None // Omits the prefix from log messages.
LinePrefix.Date // Adds the date of the log entry as a prefix to messages.
LinePrefix.Time // Adds the time of the log entry as a prefix to messages.
LinePrefix.DateTime // Prefixes log messages with both the date and time of the entry.
LinePrefix.Custom // Enables the use of a custom-defined prefix for log messages.
```