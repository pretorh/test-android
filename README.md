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
