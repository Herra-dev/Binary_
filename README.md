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

## How to install it:
### For linux:
#### Debian based distro:
1. via Herra-dev repository:
    - sudo wget -O- https://Herra-dev.github.io/Herra_dev_repository/public.gpg | gpg --dearmor | sudo tee /usr/share/keyrings/herra_dev.gpg > /dev/null 
    - echo "deb [arch=amd64 signed-by=/usr/share/keyrings/herra_dev.gpg] https://Herra-dev.github.io/Herra_dev_repository testing main" | sudo tee /etc/apt/sources.list.d/herra_dev.list
    - sudo apt update
    - sudo apt install binarycalculator
    - sudo apt install binaryconvertor

2. via release:
    - Download binary convertor [here](https://github.com/Herra-dev/Binary_/releases/tag/v1.0.0-beta)
    - Download binary calculator [here](https://github.com/Herra-dev/Binary_/releases/tag/calculator-v1.0.0-beta)
    - after, change directory to the folder where your downloaded the deb package: 
    - sudo dpkg -i package-name.deb ---- or ---- sudo apt install ./package-name.deb (to install package-name.deb)

## How to uninstall:
    - **sudo dpkg -r package-name** ---- or ---- **sudo apt remove package-name** (to uninstall package-name)