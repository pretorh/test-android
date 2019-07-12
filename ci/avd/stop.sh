#!/usr/bin/env sh
set -e

emulator_pid=$(cat emulator.pid)
qemu_pid=$(pgrep qemu -P "$emulator_pid")
echo "kill qemu process ($qemu_pid) that was started by emulator ($emulator_pid)"
kill "$qemu_pid"

echo "waiting for emulator to finish"
adb wait-for-any-disconnect
rm emulator.pid
