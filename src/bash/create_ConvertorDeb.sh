#!/bin/bash

#######################################################################
# SCRIPT TO CREATE DEB ARCHIVE FOR BINARY CONVERTOR * FOR LINUX
#######################################################################

CURRENT_DIR=$(pwd)
JAR_FILE_DIR=$CURRENT_DIR/../../binary/Convertor/

cd $JAR_FILE_DIR

echo "===> Creating deb package for binary Convertor ..."


jpackage    --name "Binary_Convertor" \
            --input . \
            --main-jar $(find -name "*.jar") \
            --main-class AppConvertor \
            --type deb \
            --icon ../iconConvertor.png \
            --linux-menu-group "Utility" \
            --description "Binary Convertor is a mini software used to transform a decimal number into a binary number" \
            --linux-deb-maintainer "irdnarheriniaina@gmail.com" \
            --app-version "1.0.1" \
            --linux-shortcut


echo "done"