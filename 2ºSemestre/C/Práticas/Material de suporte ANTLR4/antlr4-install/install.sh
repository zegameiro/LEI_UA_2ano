#!/bin/bash
#
# Miguel Oliveira e Silva (mos@ua.pt), 2020-2022

if (( $# == 0 )); then
   FILES_PATH="."
elif (( $# == 1 )); then
   FILES_PATH="$1"
else
   echo "Usage: ./install.sh [path-to-files]!"
   exit 10
fi

if ! [ -d "$FILES_PATH" ]; then
   echo "ERROR: directory $FILES_PATH does not exists!"
   exit 10
fi

TMP_DIR="TMP_DIR"
JAR_DST_PATH=/usr/local/lib
BIN_DST_PATH=/usr/local/bin
#DOC_DST_PATH=/usr/local/doc/javadoc
DOC_DST_PATH=/usr/share/doc/javadoc


echo "ANTLR4 installation script (Miguel Oliveira e Silva, mos@ua.pt, 2020-2022)."
echo ""
echo "This script will ask for sudo execution permission (but run it without sudo: ./install.sh)."
echo ""
echo "This scripts accepts a previously installed java jdk (eg: sudo apt-get install openjdk-11-jdk)."
echo "However, if not installed, it will ask for a new installation of default-jdk."
echo ""
echo "The following directories and files will be changed:"
echo "- directory $JAR_DST_PATH"
echo "- directory $BIN_DST_PATH"
echo "- directory $DOC_DST_PATH"
echo "- file ~/.bashrc"
echo ""
echo "If some of these condition are not possible or desired, please abort this installation script."
echo "Manual installation instructions can be found in file INSTALL.txt."
echo ""
read -p "Proceed with installation (y/N)? " ans
if [[ $ans != "y" && $ans != "Y" ]]; then
   echo "Installation aborted"
   exit 10
fi

if [[ $(whoami) == "root" ]]; then
   echo "ERROR: run this script without sudo!"
   exit 1
fi

echo "Phase #1: checking files"
read -p "<press RETURN to continue> " ans
N=$(find "$FILES_PATH" -maxdepth 1 -name antlr-4.*-complete.jar | wc -l)
if (( N == 0 )); then
   echo "ERROR: antlr4 complete jar file not found in path $FILES_PATH!"
   exit 1
elif (( N > 1 )); then
   echo "ERROR: found more than one antlr4 complete jar files in path $FILES_PATH!"
   exit 1
fi
ANTLR4Path=$(find "$FILES_PATH" -maxdepth 1 -name antlr-4.*-complete.jar)
ANTLR4File=$(basename $ANTLR4Path)
ANTLR4Version=$(echo $ANTLR4File | sed 's/^antlr-\(.*\)-complete.jar/\1/g')
echo "- found antlr4 version $ANTLR4Version, in file $ANTLR4Path"

ANTLR4RunTimePath="$FILES_PATH/antlr-runtime-$ANTLR4Version.jar"
if ! [ -f "$ANTLR4RunTimePath" ]; then
   echo "WARNING: antlr4 runtime file not found in path $FILES_PATH!"
   ANTLR4RunTimePath=""
else
   echo "- found antlr4 runtime version $ANTLR4Version in file $ANTLR4RunTimePath"
fi

ANTLR4DocFile="$FILES_PATH/antlr4-$ANTLR4Version-javadoc.zip"
if ! [ -f "$ANTLR4DocFile" ]; then
   echo "WARNING: antlr4 documentation zip file not found in path $FILES_PATH!"
   ANTLR4DocFile=""
else
   echo "- found antlr4 version $ANTLR4Version documentation in file $ANTLR4DocFile"
fi

N=$(find "$FILES_PATH" -maxdepth 1 -name ST-*.jar | wc -l)
if (( N == 0 )); then
   echo "ERROR: StringTemplate jar file not found in path $FILES_PATH!"
   exit 1
elif (( N > 1 )); then
   echo "ERROR: found more than one StringTemplate files in path $FILES_PATH!"
   exit 1
fi
STPath=$(find "$FILES_PATH" -maxdepth 1 -name ST-*.jar)
STFile=$(basename $STPath)
STVersion=$(echo $STFile | sed 's/^ST-\(.*\).jar/\1/g')
echo "- found StringTemplate version $STVersion, in file: $STPath"
STDocFile="$FILES_PATH/ST-$STVersion-javadoc.zip"
if ! [ -f "$STDocFile" ]; then
   echo "WARNING: StringTemplate documentation zip file not found in path $FILES_PATH!"
   STDocFile=""
else
   echo "- found StringTemplate version $STVersion documentation in file $STDocFile"
fi

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

if ! ( command -v gawk >& /dev/null ); then
   echo "ERROR: gawk not found!"
   read -p "Install gawk (Y/n)? " ans
   if [[ $ans != "n" && $ans != "N" ]]; then
      sudo apt install gawk
   else
      echo "  run command:"
      echo "    sudo apt install gawk"
      exit 2
   fi
fi

if ! ( command -v unzip >& /dev/null ); then
   echo "ERROR: unzip not found!"
   read -p "Install unzip (Y/n)? " ans
   if [[ $ans != "n" && $ans != "N" ]]; then
      sudo apt install unzip
   else
      echo "  run command:"
      echo "    sudo apt install unzip"
      exit 2
   fi
fi
echo "Phase #1 done."


echo ""
echo "Phase #2: checking java versions"
read -p "<press RETURN to continue> " ans
JAVA_INSTALLED=0; command -v java >& /dev/null && command -v javac >& /dev/null && JAVA_INSTALLED=1
if [[ $JAVA_INSTALLED == 0 ]]; then
   read -p "Install java default-jdk (Y/n)? " ans
   if [[ $ans != "n" && $ans != "N" ]]; then
      sudo apt install default-jdk
   else
      echo "  Install java jdk! For example:"
      echo "    sudo apt install default-jdk"
      exit 2
   fi
fi
if ! ( command -v java >& /dev/null ); then
   echo "ERROR: java virtual machine not found!"
   exit 2
fi
JAVA_VERSION=$(java -version |& head -1 | sed 's/[^0-9._]\+//g' | sed 's/\.[0-9_]\+$//g')
echo "- java version: $JAVA_VERSION"

if ! ( command -v javac >& /dev/null ); then
   echo "ERROR: java compiler not found!"
   exit 2
fi
JAVAC_VERSION=$(javac -version |& head -1 | sed 's/[^0-9._]\+//g' | sed 's/\.[0-9_]\+$//g')
echo "- javac version: $JAVAC_VERSION"

if [[ $JAVA_VERSION != $JAVAC_VERSION ]]; then
   echo "WARNING: java and javac have different versions!"
   read -p "Ignore error and procceed with installation (y/N)? " ans
   if [[ $ans != "y" && $ans != "Y" ]]; then
      exit 2
   fi
fi
echo "Phase #2 done."


echo ""
echo "Phase #3: installing jar files and update bashrc (sudo required, and bash shell usage assumed)"
read -p "Execute phase #3 (Y/n)? " ans
if [[ $ans != "n" && $ans != "N" ]]; then
   if [[ $(find "$JAR_DST_PATH" -maxdepth 1 -name antlr\*) == *antlr* ]]; then
      read -p "  Remove existing antlr jar files (Y/n)? " ans
      if [[ $ans != "n" && $ans != "N" ]]; then
         echo -n "  WARNING: removing existing antlr jar files: "
         find "$JAR_DST_PATH" -maxdepth 1 -name antlr\* | tr '\n' ' '
         echo
         sudo rm -vf "$JAR_DST_PATH"/antlr-*.jar
      fi
   fi
   if [[ $(find "$JAR_DST_PATH" -maxdepth 1 -name ST\*) == *ST* ]]; then
      read -p "  Remove existing ST jar files (Y/n)? " ans
      if [[ $ans != "n" && $ans != "N" ]]; then
         echo -n "  WARNING: removing existing ST jar files: "
         find "$JAR_DST_PATH" -maxdepth 1 -name ST\* | tr '\n' ' '
         echo
         sudo rm -vf "$JAR_DST_PATH"/ST-*.jar
      fi
   fi
   echo "- installing jar files:"
   sudo cp -v "$ANTLR4Path" "$ANTLR4RunTimePath" "$STPath" "$JAR_DST_PATH" || (echo "ERROR: copying jar files!"; exit 3)
   echo "- updating CLASSPATH and add ANTLR4_PATH to ~/.bashrc file:"
   # commenting existing variables (if any):
   sed -i 's/\(^\<export\> \<CLASSPATH\>.*$\)/\#\1/g' ~/.bashrc || (echo "ERROR: changing ~/.bashrc file!"; exit 3)
   sed -i 's/\(^\<export\> \<ANTLR4_PATH\>.*$\)/\#\1/g' ~/.bashrc || (echo "ERROR: changing ~/.bashrc file!"; exit 3)
   # add new:
   echo "export CLASSPATH=\".:"$JAR_DST_PATH"/${ANTLR4File}:"$JAR_DST_PATH"/${STFile}\"" >> ~/.bashrc || (echo "ERROR: changing ~/.bashrc file!"; exit 3)
   echo "export ANTLR4_PATH=\""$JAR_DST_PATH"\"" >> ~/.bashrc || (echo "ERROR: changing ~/.bashrc file!"; exit 3)
   echo "Phase #3 done."
fi


echo ""
echo "Phase #4: installing script files (sudo required)"
read -p "Execute phase #4 (Y/n)? " ans
if [[ $ans != "n" && $ans != "N" ]]; then
   mkdir -v "$TMP_DIR"
   unzip -o "$ScriptsFile" -d "$TMP_DIR"
   names=$(cd "$TMP_DIR"/bin; echo * | sed 's/ / -or -name /g')
   if [[ $(echo "-maxdepth 1 -name $names" | xargs find "$BIN_DST_PATH"/ | wc -l) > 0 ]]; then
      #read -p "1111" ans
      names=$(cd "$TMP_DIR"/bin; echo -n " "; echo * | sed "s| | $BIN_DST_PATH/|g")
      #read -p "2222" ans
      echo $names | sudo xargs rm -vf
   fi
   sudo mv -vf "$TMP_DIR"/bin/* "$BIN_DST_PATH"/
   rm -vrf "$TMP_DIR"
   echo "Phase #4 done."
fi

echo ""
echo "Phase #5: installing ANTLR4 and Java documentation"
read -p "Execute phase #5 (Y/n)? " ans
if [[ $ans != "n" && $ans != "N" ]]; then
   if [[ $ANTLR4DocFile != "" ]]; then
      if [[ $(find "$DOC_DST_PATH" -maxdepth 1 -name antlr4\*) == *antlr4* ]]; then
         read -p "  Remove existing antlr4 documentation files (Y/n)? " ans
         if [[ $ans != "n" && $ans != "N" ]]; then
            echo -n "  WARNING: removing existing antlr4 documentation files: "
            find "$DOC_DST_PATH" -maxdepth 1 -name antlr\* | tr '\n' ' '
            echo
            sudo rm -vrf "$DOC_DST_PATH"/antlr4*
         fi
      fi
      echo "- install antlr4 library documentation in path $DOC_DST_PATH"
      if ! [ -d "$DOC_DST_PATH" ]; then
         sudo mkdir -v --parents "$DOC_DST_PATH"
      fi
      echo "sudo unzip -o ${ANTLR4DocFile} -d $DOC_DST_PATH"
      sudo unzip -o "${ANTLR4DocFile}" -d "$DOC_DST_PATH" > /dev/null || (echo "ERROR: installing antlr4 documentation files!"; exit 5)
   fi
   if [[ $STDocFile != "" ]]; then
      if [[ $(find "$DOC_DST_PATH" -maxdepth 1 -name ST\*) == *ST* ]]; then
         read -p "  Remove existing ST documentation files (Y/n)? " ans
         if [[ $ans != "n" && $ans != "N" ]]; then
            echo -n "  WARNING: removing existing ST documentation files: "
            find "$DOC_DST_PATH" -maxdepth 1 -name ST\* | tr '\n' ' '
            echo
            sudo rm -vrf "$DOC_DST_PATH"/ST-*
         fi
      fi
      echo "- install StringTemplate library documentation in path $DOC_DST_PATH"
      if ! [ -d "$DOC_DST_PATH" ]; then
         sudo mkdir -v --parents "$DOC_DST_PATH"
      fi
      echo "sudo unzip -o ${STDocFile} -d $DOC_DST_PATH"
      sudo unzip -o "${STDocFile}" -d "$DOC_DST_PATH" > /dev/null || (echo "ERROR: installing StringTemplate documentation files!"; exit 5)
   fi
   JAVADOC_VERSION=$(echo $JAVAC_VERSION | sed 's/^1\.//g' | sed 's/\..*$//g')
   read -p "Install java standard library documentation  (Y/n)? " ans
   if [[ $ans != "n" && $ans != "N" ]]; then
      sudo apt install openjdk-${JAVADOC_VERSION}-doc
   else
      echo "- to install java standard library documentation run command:"
      echo "    sudo apt install openjdk-${JAVADOC_VERSION}-doc"
   fi
   echo "Phase #5 done."
fi

echo ""
echo "Phase #6: installing vim syntax highlight"
read -p "Execute phase #6 (Y/n)? " ans
if ! ( command -v vim >& /dev/null ); then
   echo "ERROR: vim not found!"
   read -p "Install vim (Y/n)? " ans
   if [[ $ans != "n" && $ans != "N" ]]; then
      sudo apt install vim
   else
      echo "  run command:"
      echo "    sudo apt install vim"
      exit 2
   fi
fi
if [[ $ans != "n" && $ans != "N" ]]; then
   if ! ( command -v gawk >& /dev/null ); then
      echo "ERROR: vim not found!"
      read -p "Install vim (Y/n)? " ans
      if [[ $ans != "n" && $ans != "N" ]]; then
         sudo apt install vim
      else
         echo "  run command:"
         echo "    sudo apt install vim"
         exit 2
      fi
   fi
   mkdir --parents ~/.vim/syntax/
   cp -a ${FILES_PATH}/antlr4.vim ~/.vim/syntax/
   echo "" >> ~/.vimrc
   echo "syntax on" >> ~/.vimrc
   echo "au BufRead,BufNewFile *.g4 set filetype=antlr4" >> ~/.vimrc
   echo "Phase #6 done."
fi

echo ""
echo "Start a new terminal before start ANTLR4ring! (necessary to ensure that the new ./bashrc is executed)"

exit 0

