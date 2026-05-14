#!/bin/bash

#######################################################################
# CREATE PACKAGE DEB FOR CONVERTER
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
CLASS_DIR=$DIR/output/Converter
PACKAGE_DEB_DIR=$CLASS_DIR/deb
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
Package: binaryConverter
Version: 1.0.0
Architecture: amd64
Depends: default-jre (>= 21) | openjdk-21-jre
Maintainer: RANDRIAMANANTSOA Heriniaina <irdnarheriniaina@gmail.com>
Description: Java Application used to convert decimal number into binary number
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

cat > binaryConverter << 'EOF'
#!/bin/bash
java -jar /usr/local/bin/binaryConverter.jar
EOF
chmod +x binaryConverter

cp $(find $CLASS_DIR -name "*.jar") $BIN_DIR/binaryConverter.jar
cp $(find $DIR -name "binaryConverter.png") $ICON_DIR


#######################################################################
# 
#######################################################################

cd $DESKTOP_DIR

cat > binaryConverter.desktop << 'EOF'
[Desktop Entry]
Type=Application
Name=Binary Converter
comment=Convert any decimal number into binary number
Exec=/usr/local/bin/binaryConverter
Icon=binaryConverter.png
Categories=Accessories
Terminal=false
EOF

echo "===> configuration done ..."
echo "===> creating package deb ..."

#######################################################################
# 
#######################################################################

cp $(find $ICON_DIR -name "binaryConverter.png") $PIXMAP_DIR

#######################################################################
# 
#######################################################################


cd $CLASS_DIR

echo "===> building deb package ..."

dpkg-deb --root-owner-group --build deb/

echo "===> renaming output ..."
mv deb.deb binary-converter_v1.0.0_amd64.deb

echo "DONE !"