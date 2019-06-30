# Android test apps

## version code

add a `getVersionCode` and `getVersionName` to `app/build.gradle`

Then build with `./gradlew assemble -PversionName=1.0.1 -PversionCode=2`

## flavors

add `applicationIdSuffix` for debug builds

add `values/strings.xml` with different application names per flavor/build type

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

## tests

Add custom class that extends `androidx.test.runner.AndroidJUnitRunner`, override `newApplication` and save
the application to a field

Change the `android.testInstrumentationRunner` field to this class

### in memory db

pass in the database into the persistence module. add a function (visible for testing, else private) in
the application that builds given a passed-in persistence module.

in the tests, build an in-memory database. use this database to build a persistence module for the tests,
passing this module into the application's dependency builder function in the onStart function

### screenshots

use `androidx.test.runner.screenshot.Screenshot.capture` to get the screenshot details (which is enough to view the bitmap when debugging).

Then save it to a file, using a custom processor (to change the destination, else you might get permission errors)

## general

### change activity into activity+fragment

- inherit from `Fragment`
- move layout creation into `onCreateView`
- split other `onCreate` code into `onActivityCreated`
- add companion object to create new instance of fragment
- add layout for activity with just a `FrameLayout`
- add activity that sets up `supportFragmentManager`

### events in live data

The problem: live data keeps old values, so observing on an already-setup live data will load the old values after the observer is setup

see https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150

Create a class (`SingleHandledEvent`) that holds the data to return. When the data is read the first time, return it, and also mark the event as "handled".
On subsequent reads, return null
