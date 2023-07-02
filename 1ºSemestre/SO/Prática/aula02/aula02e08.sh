#!/bin/bash
# Exit status
ping -c 1 www.ua.pt
echo "Exit code: $?" # $? gives exit code of last process
ping -c 1 wwwww.ua.pt
echo "Exit code: $?"