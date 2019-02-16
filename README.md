# Android test apps

## version code

add a `getVersionCode` and `getVersionName` to `app/build.gradle`

Then build with `./gradlew assemble -PversionName=1.0.1 -PversionCode=2`

## dependency injection - dagger2

### setup

- create class `di/DefaultModule.kt`
    - annotate with `@Module`
- create interface `di/Injector.kt`
    - annotate with `@Component` with modules set
    - annotate with `@Singleton` (needed if you create singletons in the modules)

build once. need dagger2 to generate `DaggerInjector` class

setup the injector in your `Application`'s `onCreate`

### di in view models

make the class inherit from (or a subclass of) `AndroidViewModel`. Get the injector from the application,
and use that to setup the view model

## networking - retrofit2 (with okhttp3 for logging)

build a custom okhttp3 client, and use that in the retrofit2 builder [logging interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)

add gson converter for json parsing

remember to add INTERNET permission
