#!/bin/bash

#######################################################################
# CREATE PACKAGE DEB FOR CALCULATOR
#######################################################################

#TRYING TO HAVE THIS KIND OF TREE
#   Project/ 
#   |____ deb/   
#          |____ DEBIAN/  
#          |      |____ control
#          |      |____ postinst
#          |
#          |____ usr/
#                  |____ local/
#                  |      |____ bin/
#                  |             |____ executable-name
#                  |             |____ executable-name.jar
#                  |             |____ assets/
#                  |____ share/
#                         |____ applications/
#                                |____ executable-name.desktop
#
#


# DIRECTORIES
DIR=$(pwd)
CLASS_DIR=$DIR/output/Calculator
PACKAGE_DEB_DIR=$CLASS_DIR/calculator-deb-package
DEBIAN_DIR=$PACKAGE_DEB_DIR/DEBIAN
USR_DIR=$PACKAGE_DEB_DIR/usr
BIN_DIR=$USR_DIR/local/bin
DESKTOP_DIR=$USR_DIR/share/applications
PIXMAP_DIR=$USR_DIR/share/pixmaps
ICON_DIR=$BIN_DIR/assets

#######################################################################
# CLEAR DEB FOLDER
#######################################################################

echo "===> Clearing deb folder ..."
rm -rf $PACKAGE_DEB_DIR
mkdir -p $PACKAGE_DEB_DIR $DEBIAN_DIR $USR_DIR

#######################################################################
# CREATE CONTROL & POST INSTALLATION FILE
#######################################################################

cd $DEBIAN_DIR

echo "===> Creating control and postinst ..."

cat > control << 'EOF'
Package: binaryCalculator
Version: 1.0.0
Architecture: amd64
Depends: default-jre (>= 21) | openjdk-21-jre
Maintainer: RANDRIAMANANTSOA Heriniaina <irdnarheriniaina@gmail.com>
Description: Java Application used to do calcul between two numbers(in binary)
EOF

cat > postinst << 'EOF'
#!/bin/bash
update-desktop-database /usr/share/applications 2>/dev/null  || true
EOF

chmod 755 postinst

#######################################################################
# 
#######################################################################

echo "===> "
mkdir -p $BIN_DIR $DESKTOP_DIR $PIXMAP_DIR $ICON_DIR
cd $BIN_DIR

cat > binaryCalculator << 'EOF'
#!/bin/bash
java -jar /usr/local/bin/binaryCalculator.jar
EOF
chmod +x binaryCalculator

cp $(find $CLASS_DIR -name "*.jar") $BIN_DIR/binaryCalculator.jar
cp $(find $DIR -name "binaryCalculator.png") $ICON_DIR


#######################################################################
# 
#######################################################################

cd $DESKTOP_DIR

cat > binaryCalculator.desktop << 'EOF'
[Desktop Entry]
Type=Application
Name=Binary Calculator
comment=Calculator
Exec=/usr/local/bin/binaryCalculator
Icon=binaryCalculator.png
Categories=Other
Terminal=false
EOF

echo "===> configuration done ..."
echo "===> creating package deb ..."

#######################################################################
# 
#######################################################################

cp $(find $ICON_DIR -name "binaryCalculator.png") $PIXMAP_DIR

#######################################################################
# 
#######################################################################


cd $CLASS_DIR

echo "===> building deb package ..."

dpkg-deb --root-owner-group --build $PACKAGE_DEB_DIR

echo "===> renaming output ..."
mv $(find -type f -name "*.deb") binary-calculator_v1.0.0_amd64.deb

echo "DONE !"