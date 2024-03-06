# amaya-events [![maven-central](https://img.shields.io/maven-central/v/io.github.amayaframework/amaya-events?color=blue)](https://repo1.maven.org/maven2/io/github/amayaframework/amaya-events/)

A library that implements the event system.

## Getting Started

To install it, you will need:

* java 11+
* Maven/Gradle

### Features

* Universal event descriptor
* Grouping events
* Calling events by name or by group
* Selecting an event call policy
* Java concurrent api based event calls

## Installing

### Gradle dependency

```Groovy
dependencies {
    implementation group: 'io.github.amayaframework', name: 'amaya-events', version: 'LATEST'
}
```

### Maven dependency

```
<dependency>
    <groupId>io.github.amayaframework</groupId>
    <artifactId>amaya-events</artifactId>
    <version>LATEST</version>
</dependency>
```

## Usage example

```Java
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import io.github.amayaframework.events.*;

public class Main {
    public static void main(String[] args) {
        // Declare events
        var common = new EventGroup("/common");
        var app = new EventGroup("/common/app", common);
        var event1 = new Event(app, "event1", FirePolicy.ANY);
        var event2 = new Event(app, "event2", FirePolicy.ANY);
        // Define event manager
        var factory = new ParallelManagerFactory(() -> Executors.newFixedThreadPool(2));
        var manager = factory.create();
        // Register
        manager.set(event1, c -> System.out.println("EVENT 1"));
        manager.set(event2, c -> System.out.println("EVENT 2"));
        // Fire
        manager.fire(app, null);
        // Await for specified events
        manager.stop();
    }
}

```

This code will output:
```
EVENT 2
EVENT 1
```

## Built With

* [Gradle](https://gradle.org) - Dependency management
* [jfunc](https://github.com/RomanQed/jfunc) - Functional interfaces, utilities

## Authors

* **[RomanQed](https://github.com/RomanQed)** - *Main work*

See also the list of [contributors](https://github.com/AmayaFramework/amaya-events/contributors)
who participated in this project.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details
