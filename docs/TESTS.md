# Android tests

## avd management

### creating an avd from command line

```
echo no | $ANDROID_HOME/tools/bin/avdmanager create avd \
    --name avd-name \
    --package 'system-images;android-28;google_apis;x86'
```

meanings:

- no to not use a custom hardware profile
- requires name for the avd (`avd-name`)
- requires a system package (contains `;` so must quote) run without this to see a list

system image:

might need to find an image that starts up with minimal/no additional required user input, for example Google Play enabled
devices asks to enable play protect, blocking ui tests, and update software when first started

#### configuring avd

copy and modify a `config.ini` file from an existing avd's config (`~/.android/avd/avd-name.avd/config.ini`)

see `docs/example-avd-config.ini` for example based on Pixel 3 without Google Play

```
cp docs/example-avd-config.ini ~/.android/avd/avd-name.avd/config.ini
```

### deleting avd

```
$ANDROID_HOME/tools/bin/avdmanager delete avd \
    --name avd-name
```

the name is required

### starting avd

#### starting

see https://developer.android.com/studio/run/emulator-commandline.html

```
$ANDROID_HOME/emulator/emulator @avd-name -no-window
```

(`emulator/emulator`, not `tools/emulator`)

probably want to run that in the background job

meaning:

- `-no-window` starts without a window

#### wait for it to start up

run this to block until the device is online

```
adb wait-for-any-device
```

or wait for this command to pass (and return "device")

```
adb get-state
```

#### stopping

kill the qemu process:

```
EMULATOR_PID=$(pgrep emulator)
QEMU_PID=pgrep qemu -P $EMULATOR_PID
kill $QEMU_PID
```

can wait for it to complete using

```
adb wait-for-any-disconnect
```
