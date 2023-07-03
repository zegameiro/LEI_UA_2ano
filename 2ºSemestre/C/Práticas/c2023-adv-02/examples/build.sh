#!/usr/bin/bash
usage() { echo "Usage: $0 [-d <directory_path>] [-h]" 1>&2; exit 1; }
help() { echo "Usage: $0 [-d <directory_path>]" ; echo "Runs antlr4-build on ../src. If you want to change the directory use the -d flag."; exit 1;}
path="../src/"
while getopts ":d:h" opt;do
    case $opt in
        d)
            $path = $OPTARG
            ;;
        h)
            help
            ;;
        *)
            echo "Invalid argument:" $OPTARG 
            usage
            ;;
    esac
done

cd $path
antlr4-clean
antlr4-build
antlr4-build -python