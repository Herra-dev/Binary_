#!/bin/bash

#######################################################################
# SCRIPT TO CREATE JAR ARCHIVE FOR BINARY CONVERTOR * FOR LINUX
#######################################################################

CURRENT_DIR=$(pwd)
CLASS_DIR=$CURRENT_DIR/../../binary/Convertor

echo "===> Create archive jar for Convertor ..."

cd $CLASS_DIR

jar cvmf MANIFEST.MF Convertor.jar $(find -name "*.class")

echo "-------------------------------------------------"
echo "===> done"