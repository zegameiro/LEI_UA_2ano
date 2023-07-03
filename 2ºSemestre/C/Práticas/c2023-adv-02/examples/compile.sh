#!/bin/bash

usage() { echo "Usage: $0 -i <path_file_to_compile> [-o <path_to_output_file>] [-c <path_to_compiler>] [-h]" 1>&2; exit 1; }
help() { echo "Usage: $0 -i <path_file_to_compile> [-o <path_to_output_file>] [-c <path_to_compiler>]" ; 
         echo " Compiles .adv file using advCompiler.";
         echo " -i is mandatory and it's the path to file to be compiled. It must end in .adv"
         echo " -o is optional and it's the path to the compiled file. By default it's [name_of_file_input] ";
         echo " -c is the path to compiler directory. By deafult it's ../src"; exit 1;}

pathToCompiler="../src/"
pathToSource=""
pathToDest=""

while getopts ":c:i:o:h" opt;do
    case $opt in
        c)
            pathToCompiler=$OPTARG
            ;;
        i)
            pathToSource=$OPTARG
            ;;
        o)
            pathToDest=$OPTARG
            ;;
        h)
            help
            ;;
        *)
            echo "Invalid argument: " $OPTARG 
            usage
            ;;
    esac
done

if [ -z "$pathToSource" ]
then
    echo "Invalid argument: i is mandatory"
    exit 1;
fi

if ! [[ $pathToSource =~ ^.*[.]adv$ ]]
then
    echo "Invalid file: input file must end in .adv"
    exit 1;
fi

if [ -z "$pathToDest" ]
then
    pathToDest=$(basename $pathToSource .adv)
fi

pathToDest=$(echo "$pathToDest".py)

curDir=$(pwd)
curDir=$(echo "$curDir"/ )
pathToDest=$(echo $curDir$pathToDest)
pathToSource=$(echo $curDir$pathToSource)

cd $pathToCompiler
cat $pathToSource | java advMain $pathToDest