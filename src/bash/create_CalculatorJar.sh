#!/bin/bash

#######################################################################
# SCRIPT TO CREATE JAR ARCHIVE FOR BINARY CALCULATOR * FOR LINUX
#######################################################################

CURRENT_DIR=$(pwd)
CLASS_DIR=$CURRENT_DIR/../../binary/Calculator

echo "===> Create archive jar for Calculator ..."

cd $CLASS_DIR

jar cvmf MANIFEST.MF Calculator.jar $(find -name "*.class")

echo "-------------------------------------------------"
echo "===> done"