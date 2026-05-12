#!/bin/bash

#######################################################################
# SCRIPT TO CREATE DEB ARCHIVE FOR BINARY CALCULATOR * FOR LINUX
#######################################################################

CURRENT_DIR=$(pwd)
JAR_FILE_DIR=$CURRENT_DIR/../../binary/Calculator/

cd $JAR_FILE_DIR

echo "===> Creating deb package for binary calculator ..."

jpackage    --name "Binary_Calculator" \
            --input . \
            --main-jar $(find -name "*.jar") \
            --main-class AppCalculator \
            --type deb \
            --icon ../iconCalculator.png \
            --linux-menu-group "Utility" \
            --description "Binary Calculator is a mini software used to do calcul between two binaries number" \
            --linux-deb-maintainer "irdnarheriniaina@gmail.com" \
            --app-version "1.0.1" \
            --linux-shortcut

echo "done"