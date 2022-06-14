#!/bin/sh
if [ -z "$1" ]
then
  FILES=$(find -mindepth 2 -type f -name "*.tex")
elif [ "$1" = "combined" ]
then
  FILES=$(find -maxdepth 1 -type f -name "*.tex")
else
  FILES=$(find $1 -type f -name "*.tex")
fi

CURRENT=$(pwd)
for f in $FILES
do
 cd $CURRENT
 cd $(dirname $f)
 echo $(pwd)
 echo $(basename $f)
 pdflatex -interaction=batchmode $(basename $f) > /dev/null
 if [ "$?" -ne 0 ]
 then
  echo "Error creating $(basename $f), see logfile."
 fi
done