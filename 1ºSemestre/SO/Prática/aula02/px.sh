# !/bin/bash
for i in $*; do
    chmod a+x $i
done

echo "All files were granted permission to execute succesfully"
