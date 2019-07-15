#!/usr/bin/env sh
set -e

name=${1?need avd name as the first parameter}

"$ANDROID_HOME"/emulator/emulator \
  "@$name" \
  -no-window \
  >/dev/null &
emulator_pid=$!
echo $emulator_pid > emulator.pid
echo "emulator started ($emulator_pid)"

adb wait-for-any-device
echo "adb says it is online"

while ! adb shell am get-config 2>/dev/null ; do
  echo "am get-config failed"
  sleep 1
done
sleep 5

echo "device is ready"
