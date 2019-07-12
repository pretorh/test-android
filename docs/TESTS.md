# Android tests

## avd management

### creating an avd from command line

```
echo no | $ANDROID_HOME/tools/bin/avdmanager create avd \
    --name avd-name \
    --package 'system-images;android-21;google_apis;x86_64'
```

meanings:

- no to not use a custom hardware profile
- requires name for the avd (`avd-name`)
- requires a system package (contains `;` so must quote) run without this to see a list

### deleting avd

```
$ANDROID_HOME/tools/bin/avdmanager delete avd \
    --name cli-avd
```

the name is required

### starting avd

#### starting

see https://developer.android.com/studio/run/emulator-commandline.html

```
$ANDROID_HOME/tools/emulator @avd-name -no-window
```

probably want to run that in the background

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
