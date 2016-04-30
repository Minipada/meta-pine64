DESCRIPTION = "Sunxi Pack Tools for PIne 64"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

S = "${WORKDIR}/git"

SRCBRANCH = "master"
SRC_URI = "git://github.com/longsleep/sunxi-pack-tools.git;protocol=git;branch=${SRCBRANCH}"
#           file://0001-disable-strip.patch"
# This points at the 'Added script tool from lichee_linux_310.tar.gz Allwinner Linux ...' tag
SRCREV ?= "19a7afb74035bb72ecb5878e73699880a9547131"

PV = "1.0+longsleep+git${SRCPV}"

COMPATIBLE_MACHINE = "pine64"

# It is not cross compiled

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_configure() {
  :
}

#TODO Change to yocto syntax
do_compile() {
  make CC=${BUILD_CC}
}

#do_install() {
#  install -d ${D}${includedir}
#  mkdir -p ${D}${includedir}/sunxi-pack-tools
#  install -d ${D}${includedir}/sunxi-pack-tools
#  install bin/* ${D}${includedir}/sunxi-pack-tools
#  cp bin/merge_uboot ${D}${includedir}
#  install -m 0755 bin/script ${D}${includedir}
#  install -m 0755 bin/update_uboot ${D}${includedir}
#  install -m 0755 bin/update_uboot_fdt ${D}${includedir}
#}

do_install(){
  :
}

