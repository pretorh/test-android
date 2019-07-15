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
echo "device is ready"
