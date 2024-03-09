# amaya-events [![maven-central](https://img.shields.io/maven-central/v/io.github.amayaframework/amaya-events?color=blue)](https://repo1.maven.org/maven2/io/github/amayaframework/amaya-events/)

A library that implements the event system.

## Getting Started

To install it, you will need:

* Java 11+
* Maven/Gradle

### Features

* Universal event descriptor
* Calling events by name
* Java concurrent api based event calls
* Flexible and convenient implementation

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
import io.github.amayaframework.events.*;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final var event = Event.of("CountEvent", Integer.class);
        var factory = new ParallelManagerFactory(ForkJoinPool::commonPool);
        var manager = factory.create();
        manager.set(event, count -> System.out.println("The count is now " + count));
        manager.fireNow(event, 5);
        manager.stop();
    }
}
```

This code will output:

```
The count is now 5
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
