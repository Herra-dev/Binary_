# [binary_](https://github.com/Herra-dev/binary_)
## by [Herra-dev](https://github.com)

### Used languages:
1. Java
2. Shell


## CLI

### convertor
### calculator

## WITH USER INTERFACE

![binary convertor](https://github.com/Herra-dev/Herra-dev-applications-pics/blob/main/java/ui/binaryconvertorint.png) ![binary calculator](https://github.com/Herra-dev/Herra-dev-applications-pics/blob/main/java/ui/binarycalculator.png)

# How to install:
## For linux:
### Debian based distro: (via apt)
#### get public keyrings from Herra-dev-repository:
    sudo wget -O- https://Herra-dev.github.io/Herra_dev_repository/public.gpg | gpg --dearmor | sudo tee /usr/share/keyrings/herra_dev.gpg > /dev/null
#### add Herra-dev-repository to sources list:
    echo "deb [arch=amd64 signed-by=/usr/share/keyrings/herra_dev.gpg] https://Herra-dev.github.io/Herra_dev_repository testing main" | sudo tee /etc/apt/sources.list.d/herra_dev.list
#### update system:
    sudo apt update
#### install binary calculator:
    sudo apt install binarycalculator
#### install binary convertor:
    sudo apt install binaryconvertor

### (via release)
#### Download binary convertor [here](https://github.com/Herra-dev/Binary_/releases/tag/v1.0.0-beta)
#### Download binary calculator [here](https://github.com/Herra-dev/Binary_/releases/tag/calculator-v1.0.0-beta)
#### Change directory (cd) to the folder where your downloaded the deb package: 
#### install via dpkg(change package-name.deb by the name of file you downloaded):
    sudo dpkg -i package-name.deb
#### install via apt(change package-name.deb by the name of file you downloaded):
    sudo apt install ./package-name.deb

## How to uninstall:
#### via dpkg:
    sudo dpkg -r package-name
#### via apt:
    sudo apt remove package-name