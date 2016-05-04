DESCRIPTION = "U-Boot port for Pine64"

require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS += "sunxi-pack-tools arm-trusted-firmware"

PROVIDES += "u-boot"
COMPATIBLE_MACHINE = "pine64"


SRC_URI = " \
	git://github.com/longsleep/u-boot-pine64.git;protocol=git;branch=pine64-hacks \
	"

PV = "1" 
# Corresponds 2016.01.19 in Makefile
SRCREV = "a47674ee9fb026349768eba6be8af9e0640c8a6c"

S = "${WORKDIR}/git"

UBOOT_WORKDIR = "${STAGING_DIR}/${MACHINE}/${includedir}"

#BLOBS as provided in the BSP
BLOBS = "${TMPDIR}/work/${TARGET_SYS}/build-pine64-image/1.0+longsleep+gitAUTOINC+d9a2fd2962-r0/git/blobs"

#Uboot as provided
UBOOT = "${TMPDIR}/work/${TARGET_SYS}/build-pine64-image/1-r0/git"

TRUSTED_FIRMWARE = "${TMPDIR}/work/${TARGET_SYS}/arm-trusted-firmware/1.0+apritzel+gitAUTOINC+fc1e255c84-r0/git/build/sun50iw1p1/release"

TRUSTED_FIRMWARE_BUILD = "release"

SUNXI_PACK_TOOLS = "${TMPDIR}/work/pine64-poky-linux/sunxi-pack-tools/1.0+longsleep+gitAUTOINC+19a7afb740-r0/git/bin"

UBOOT_BUILD_DIR = "${TMPDIR}/work/pine64-poky-linux/u-boot-pine64/1-r0/git"

UBOOT_TMP_BUILD_DIR = "../build"

#TODO Use yocto syntax
do_configure(){
  make ARCH=arm CROSS_COMPILE=arm-linux-gnueabihf- sun50iw1p1_config
}

#TODO Use yocto syntax
do_compile(){
  make ARCH=arm CROSS_COMPILE=arm-linux-gnueabihf-
}

do_compile_append(){
  mkdir -p ${UBOOT_TMP_BUILD_DIR}

  cp -avf ${TRUSTED_FIRMWARE}/bl31.bin ${UBOOT_TMP_BUILD_DIR}
  cp -avf ${UBOOT_BUILD_DIR}/u-boot-sun50iw1p1.bin ${UBOOT_TMP_BUILD_DIR}/u-boot.bin
  cp -avf ${BLOBS}/scp.bin ${UBOOT_TMP_BUILD_DIR}
  cp -avf ${BLOBS}/sys_config.fex ${UBOOT_TMP_BUILD_DIR}

  # build binary device tree
  #sudo apt-get install device-tree-compiler
  dtc -Odtb -o ${UBOOT_TMP_BUILD_DIR}/pine64.dtb ${BLOBS}/pine64.dts

  #http://www.virtualhelp.me/linux/164-dos2unix-missing-ubuntu-1004
  unix2dos ${UBOOT_TMP_BUILD_DIR}/sys_config.fex
  ${SUNXI_PACK_TOOLS}/script ${UBOOT_TMP_BUILD_DIR}/sys_config.fex

  # merge_uboot.exe u-boot.bin infile outfile mode[secmonitor|secos|scp]
  echo ${SUNXI_PACK_TOOLS}
  ${SUNXI_PACK_TOOLS}/merge_uboot ${UBOOT_TMP_BUILD_DIR}/u-boot.bin ${UBOOT_TMP_BUILD_DIR}/bl31.bin ${UBOOT_TMP_BUILD_DIR}/u-boot-merged.bin secmonitor
  ${SUNXI_PACK_TOOLS}/merge_uboot ${UBOOT_TMP_BUILD_DIR}/u-boot-merged.bin ${UBOOT_TMP_BUILD_DIR}/scp.bin ${UBOOT_TMP_BUILD_DIR}/u-boot-merged2.bin scp

  # update_fdt.exe u-boot.bin xxx.dtb output_file.bin
  ${SUNXI_PACK_TOOLS}/update_uboot_fdt ${UBOOT_TMP_BUILD_DIR}/u-boot-merged2.bin ${UBOOT_TMP_BUILD_DIR}/pine64.dtb ${UBOOT_TMP_BUILD_DIR}/u-boot-with-dtb.bin

  # Add fex file to u-boot so it actually is accepted by boot0.
  ${SUNXI_PACK_TOOLS}/update_uboot ${UBOOT_TMP_BUILD_DIR}/u-boot-with-dtb.bin ${UBOOT_TMP_BUILD_DIR}/sys_config.bin

  echo "Done - created ${UBOOT_TMP_BUILD_DIR}/u-boot-with-dtb.bin"
  cp ${UBOOT_TMP_BUILD_DIR}/u-boot-with-dtb.bin ${TMPDIR}/work/pine64-poky-linux/u-boot-pine64/1-r0/image/boot/u-boot.bin
}

