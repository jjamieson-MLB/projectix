# ProjecTix

Supported targets: Android, Desktop and iOS.

## Setting up your development environment

To setup the environment, please consult these [instructions](https://github.com/JetBrains/compose-multiplatform-template#setting-up-your-development-environment).

## How to run

Choose a run configuration for an appropriate target in Android Studio and run it.

![run-configurations.png](run-configurations.png)

## Run on desktop via Gradle

`./gradlew desktopApp:run`

## Building native desktop distribution
```
./gradlew :desktopApp:packageDistributionForCurrentOS
# outputs are written to desktopApp/build/compose/binaries
```
