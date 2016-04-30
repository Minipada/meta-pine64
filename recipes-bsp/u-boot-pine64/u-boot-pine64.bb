DESCRIPTION = "U-Boot port for Pine64"

require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PROVIDES += "u-boot"

SRC_URI = " \
	git://github.com/longsleep/u-boot-pine64.git;protocol=git;branch=pine64-hacks \
	"

PV = "1" 
# Corresponds 2016.01.19 in Makefile
SRCREV = "a47674ee9fb026349768eba6be8af9e0640c8a6c"

S = "${WORKDIR}/git"

#SPL_BINARY="u-boot-sun50iw1p1"

#TODO Use yocto syntax
do_configure(){
  make ARCH=arm CROSS_COMPILE=arm-linux-gnueabihf- sun50iw1p1_config
}

#TODO Use yocto syntax
do_compile(){
  make ARCH=arm CROSS_COMPILE=arm-linux-gnueabihf-
}
