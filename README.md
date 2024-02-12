# TypingTextView Library

Typing-Text is a library that provides a typewriter-like animation effect for your Android TextView.

## Installation
### Pre-Requisites

* Minumum SDK 21
* Built with 7.2 Gradle
***

### Gradle (build.gradle)

#### Groovy

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.syedhaseeb1:TypingTextView:$latestVersion'
}
```

#### KTS
```KTS
repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("com.github.syedhaseeb1:TypingTextView:$latestVersion")
}
```
## Preview
## Videos in Horizontal Table

| Default| Reversed | Customised |
| --- | --- | --- |
| <video width="300" height="200" controls><source src="prev01.mp4" type="video/mp4"></video> | <video width="300" height="200" controls><source src="prev02.mp4" type="video/mp4"></video> | <video width="300" height="200" controls><source src="prev03.mp4" type="video/mp4"></video> |

## Usage in XML
````
<com.hsb.typingtext.TypingTextView
    android:id="@+id/typing-Text"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:padding="@dimen/_10sdp"
    android:text="@string/something_went_bazinngistan"
    android:textAlignment="textStart"
    android:textColor="#000000"
    android:textSize="16sp"
    app:animSpeed="20"
    app:fadeOnEnd="true"
    app:layout_constraintBottom_toTopOf="@+id/startBtn"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:repeatMode="restart"
    app:reversed="false" />
````

### XML Attributes
````
app:animSpeed:"int"
Sets the animation speed (default is 20).
````

````
app:fadeOnEnd:"boolean"
Enables fading of the text at the end of the animation (default is true).
````
````
app:repeatMode:"mode"
Sets the repeat mode of the animation (default is restart).
restart, reversed, noRepeat
````

````
app:reversed:"boolean"
Reverses the animation if true (default is false).
````

## Usage in Code
````
typingText.setTextToAnimate("your_text")

````
## Contributing

If you'd like to contribute to this project, please follow the [Contribution Guidelines](CONTRIBUTING.md).


## License

This project is licensed under the [GNU General Public License, Version 3.0](http://www.gnu.org/licenses/#GPL). Any fork of
this project is requested to maintain the same license as per the law.

Please see the [LICENSE](LICENSE.md) file for full reference.
