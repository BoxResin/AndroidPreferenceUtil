# Setup
- `build.gradle`
```gradle
buildscript {
    repositories {
        ...
        maven { url 'https://dl.bintray.com/boxresin/maven/' } // Add this line.
    }
}

allprojects {
    repositories {
        ...
        maven { url 'https://dl.bintray.com/boxresin/maven/' } // Add this line.
    }
}
```

- `app/build.gradle`
```gradle
dependencies {
    ...
    implementation 'boxresin.android.preference:settings:0.1.0' // Add this line.
}
```

# Usage
```kotlin
// Define a key.
val key = StringKey(keyName = "user_id", defaultValue = "none")

// Auto-casting as String, because 'key' is StringKey
val userId: String = Setting[key]

// Set value (only String type is allowed).
Setting[key] = "test"

// Error
Setting[key] = 1234
```
There are also `StringKey`, `IntKey`, `LongKey`, `FloatKey`, and `BooleanKey`.
