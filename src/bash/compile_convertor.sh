#!/bin/bash


CLASS_DIR=$(pwd)/output/Convertor
SRC_DIR=$(pwd)/..

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