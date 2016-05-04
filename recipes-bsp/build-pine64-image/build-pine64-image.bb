DESCRIPTION = "Set of tools to postprocess U-Boot"

LICENSE = "CLOSED"

inherit deploy

S = "${WORKDIR}/git"

SRCBRANCH = "master"
SRC_URI = "git://github.com/longsleep/build-pine64-image.git;protocol=git;branch=${SRCBRANCH}"

# This points at the 'Add sane alsa defaults.' tag
SRCREV ?= "d9a2fd29624efb70a6abeb17eaa759d03f79c047"

PV = "1.0+longsleep+git${SRCPV}"

COMPATIBLE_MACHINE = "pine64"
PLATEFORM = "sun50iw1p1"

LDFLAGS[unexport] = "1"
LD[unexport] = "1"

do_configure() {
  :
}

#TODO Change to yocto syntax
do_compile() {
  :
}

#TODO Find the good folder to copy this file
do_install() {
  install -d ${D}/${includedir}
  cp -r ${S}/blobs ${D}${includedir}
}
