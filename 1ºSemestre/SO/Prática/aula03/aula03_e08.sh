#!/bin/bash
# select structure to create menus
select arg in $@; do
    echo "You picked $arg ($REPLY)."
done