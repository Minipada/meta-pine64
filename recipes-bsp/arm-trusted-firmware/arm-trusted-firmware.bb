#source http://git.yoctoproject.org/cgit/cgit.cgi/meta-xilinx/tree/recipes-bsp/arm-trusted-firmware/arm-trusted-firmware_git.bb

DESCRIPTION = "ARM Trusted Firmware for PIne 64"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.md;md5=829bdeb34c1d9044f393d5a16c068371"

inherit deploy

S = "${WORKDIR}/git"

SRCBRANCH = "allwinner"
SRC_URI = "git://github.com/apritzel/arm-trusted-firmware.git;protocol=git;branch=${SRCBRANCH}"

# This points at the 'sun50i: remove unused sunxi_gic_init' tag
SRCREV ?= "fc1e255c846bc0a0c72c8e6f822e64e24704e136"

PV = "1.0+apritzel+git${SRCPV}"

COMPATIBLE_MACHINE = "pine64"
PLATEFORM = "sun50iw1p1"

LDFLAGS[unexport] = "1"
LD[unexport] = "1"

do_configure() {
  :
}

#TODO Change to yocto syntax
do_compile() {
  #oe_runmake PLAT=${PLATFORM} RESET_TO_BL31=1 bl31
  make clean
  make ARCH=arm CROSS_COMPILE=aarch64-linux-gnu- PLAT=${PLATEFORM} bl31
}

#TODO Find the good folder to copy this file
do_install() {
#  install -d ${D}${includedir}
#  install -m 0644 ${S}/build/${PLATEFORM}/release/bl31.bin ${D}{bindir}
  :
}
