#!/bin/bash
#This script does a very simple test for checking disk space.
space=$(df -h | awk '{print $5}' | grep % | grep -v Use | sort -n \
| tail -1 | cut -d "%" -f1 -)

spac=$(df -k | awk '{print$0$1}' -)

echo "largest occupied space = $space%"
echo "Name of the partition with more free space = $spac"

#copilot auto-complete
case $space in
[1-6]*)
# espaço < 70%
Message="All OK."
;;
[7-8]*)
# 70% <= espaço < 90%
Message="Cleaning out. One partition is $space % full."
;;
[9-9]*)
# 90% <= espaço < 99%
Message="Better buy a new disk. One partition is $space % full."
;;
[1-9][0-9])
# espaço = 99%
Message="I'm drowning here! There's a partition at $space %!"
;;
* )
Message="I seem to be running with a non-existent disk..."
;;
esac
echo $Message