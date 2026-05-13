#!/bin/bash


CLASS_DIR=$(pwd)/output/Calculator
SRC_DIR=$(pwd)/..
EXT_LIB_DIR=$(pwd)/../../lib/

# #######################################################
# # CLEAN DIR
# #######################################################

echo "===> Cleaning class files ..."
rm -rf $CLASS_DIR
mkdir -p $CLASS_DIR

# #######################################################
# # COMPILATION
# #######################################################

echo "===> Compiling java files ..."

javac $(find $SRC_DIR  -name "*.java") -d $CLASS_DIR

echo "-------------------------------------------------"
echo "===> done"