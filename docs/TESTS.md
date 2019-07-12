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
