#!/bin/bash

#######################################################################
# SCRIPT TO CREATE JAR ARCHIVE FOR BINARY CALCULATOR * FOR LINUX
#######################################################################

CLASS_DIR=$(pwd)/output/Calculator
MANIFEST_DIR=$CLASS_DIR/META-INF

#######################################################################
# MANIFEST
#######################################################################

echo "===> Create MANIFEST.MF ..."

rm -rf $MANIFEST_DIR
mkdir -p $MANIFEST_DIR
cd $MANIFEST_DIR
touch MANIFEST.MF
echo "Main-Class: AppCalculator" > MANIFEST.MF


#######################################################################
# JAR
#######################################################################

echo "===> Create archive jar for Calculator ..."

cd $CLASS_DIR

jar cmf $MANIFEST_DIR/MANIFEST.MF Calculator.jar $(find -name "*.class")

echo "-------------------------------------------------"
echo "===> done"