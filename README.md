# Kermit Extensions

Welcome to **Kermit Extensions**, a collection of utilities aimed at enhancing the debugging experience by leveraging the powerful logging API of [Kermit](https://github.com/touchlab/Kermit). This repository is home to custom extensions designed to make your development process smoother, more efficient, and ultimately more productive.

## About Kermit

Kermit is a Kotlin Multiplatform logging utility that provides a common logging interface for all platforms. While Kermit itself offers extensive capabilities, our extensions are here to fill in specific needs that arise during the development and debugging processes.

## Current Extensions

### TxtLogWriter

`TxtLogWriter`, is a custom `LogWriter` that, when integrated with Kermit, allows you to write logs to a `.txt` file. This is particularly useful for sharing logs with teammates or for detailed analysis during the debugging process. It’s designed to be easy to set up and integrates seamlessly with your existing Kermit setup.
Currently, `TxtLogWriter` is available for Android, iOS and desktop platforms.

