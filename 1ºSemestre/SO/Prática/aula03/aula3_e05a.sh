# !/bin/bash
# For all the files in a folder, show their properties
if (($1 < 1)) then
    echo "The input number is not valid it must be between 0 and 99"
else if [ -d $1 ] || [ -f $1] then 
    for f in $1/*; do
    file "$f"
    done
else 
    echo "The input is not a valid directory or a valid file"
fi