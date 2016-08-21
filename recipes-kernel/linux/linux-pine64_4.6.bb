require recipes-kernel/linux/linux-yocto.inc
include conf/machine/include/sunxi.inc

DESCRIPTION = "Linux kernel for Allwinner a64 processors"

COMPATIBLE_MACHINE = "pine64"

LINUX_VERSION = "4.6"
PV = "${LINUX_VERSION}"

SRCREV_pn-${PN} = "6c0b852b9ab4688534c8e978d2d55cf8a26cbd05"

KERNEL_DEFCONFIG = "defconfig"

SRC_URI = "git://github.com/apritzel/linux.git;protocol=git;branch=a64-v4"

S = "${WORKDIR}/git"

KERNEL_IMAGETYPE="Image"
KERNEL_DEVICETREE = "allwinner/sun50i-a64-pine64.dtb \
                     allwinner/sun50i-a64-pine64-plus.dtb \
"

#do_kernel_configme_prepend() {
#    install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
#}

do_kernel_configme(){
    :
}

# Set a variable in .configure
# $1 - Configure variable to be set
# $2 - value [n/y/value]
#kernel_configure_variable() {
#    # Remove the config
#    CONF_SED_SCRIPT="$CONF_SED_SCRIPT /CONFIG_$1[ =]/d;"
#    if test "$2" = "n"
#    then
#        echo "# CONFIG_$1 is not set" >> ${B}/.config
#    else
#        echo "CONFIG_$1=$2" >> ${B}/.config
#    fi
#}
#
#do_configure_prepend() {
#  kernel_configure_variable BINFMT_ELF y
#  kernel_configure_variable COMPAT y
#  kernel_configure_variable COMPAT_BINFMT_ELF y
#  kernel_configure_variable VIRTUALIZATION n
#}
