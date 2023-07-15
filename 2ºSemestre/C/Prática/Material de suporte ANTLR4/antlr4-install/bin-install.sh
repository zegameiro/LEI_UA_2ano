#!/bin/bash
#
# Miguel Oliveira e Silva (mos@ua.pt), 2020

if (( $# == 0 )); then
   FILES_PATH="."
elif (( $# == 1 )); then
   FILES_PATH="$1"
else
   echo "Usage: ./bin-install.sh [path-to-files]!"
   exit 10
fi

if ! [ -d "$FILES_PATH" ]; then
   echo "ERROR: directory $FILES_PATH does not exists!"
   exit 10
fi

TMP_DIR="TMP_DIR"
BIN_DST_PATH=/usr/local/bin

N=$(find "$FILES_PATH" -maxdepth 1 -name antlr4-bin*.zip | wc -l)
if (( N == 0 )); then
   echo "ERROR: antlr4 scripts zip file not found in path $FILES_PATH!"
   exit 1
elif (( N > 1 )); then
   echo "ERROR: found more than one antlr4 scripts zip files in path $FILES_PATH!"
   exit 1
fi
ScriptsFile=$(find "$FILES_PATH" -maxdepth 1 -name antlr4-bin*.zip)
ScriptsVersion=$(basename $ScriptsFile | sed 's/^antlr4-bin-v\(.*\).zip/\1/g')
echo "- found antlr4 scripts zip file version $ScriptsVersion, in file: $ScriptsFile"

if [ -d "$TMP_DIR" ]; then
   echo "ERROR: temporary directory $TMP_DIR already exists (remove it, and rerun installation script)!"
   exit 1
fi

echo ""
echo "Phase #4: installing script files (sudo required)"
read -p "Execute phase #4 (Y/n)? " ans
if [[ $ans != "n" && $ans != "N" ]]; then
   mkdir -v "$TMP_DIR"
   unzip "$ScriptsFile" -d "$TMP_DIR"
   sudo mv -vf "$TMP_DIR"/bin/* "$BIN_DST_PATH"/
   rm -vrf "$TMP_DIR"
   echo "Phase #4 done."
fi

exit 0

