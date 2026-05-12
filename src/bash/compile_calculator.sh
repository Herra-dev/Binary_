#!/bin/bash

CURRENT_DIR=$(pwd)
CLASS_DIR=$CURRENT_DIR/../../binary/Calculator
SRC_DIR=$CURRENT_DIR/../

#######################################################
# CLEAN DIR
#######################################################

echo "===> Cleaning class files ..."
rm -rf $CLASS_DIR
mkdir -p $CLASS_DIR

#######################################################
# COMPILATION
#######################################################

echo "===> Compiling java files ..."

javac $(find $SRC_DIR -name "*.java") -d $CLASS_DIR

cd $CLASS_DIR

rm AppConvertor.class

#######################################################
# CREATING MANIFEST
#######################################################

echo "===> creating MANIFEST.MF ..."
cd $CLASS_DIR

touch MANIFEST.MF
echo "Main-Class: AppCalculator" > MANIFEST.MF

echo "-------------------------------------------------"
echo "===> done"