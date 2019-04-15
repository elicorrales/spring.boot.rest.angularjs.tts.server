#!/bin/bash
echo "$1 $2 $3 $4 $5 $6 $7 $8";
echo "========================";
command="$1 $2 $3 $4 $5 $6 $7 $8";
echo "========================";
echo $command;
echo "========================";
eval "$command";
rtn=$?;
echo "========================";
echo $rtn;
exit $rtn;
