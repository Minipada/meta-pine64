include conf/machine/include/pine64-default-settings.inc
include conf/machine/include/pine64-default-versions.inc
include conf/machine/include/pine64-default-providers.inc

SOC_FAMILY = "sun50i"

IMAGE_FSTYPES ?= "tar.bz2 ext3 pine64-sdimg"

#SERIAL_CONSOLE = "115200 ttyS0"

#TODO Add XSERVER
#XSERVER = " \
#    xserver-xorg \
#    xf86-input-evdev \
#    xf86-input-mouse \
#    xf86-input-keyboard \
#    xf86-video-fbdev \
#    "

#TODO Add Kernel device Tree
#KERNEL_DEVICETREE ?= " \
#  sun50i-a64-pine64.dtb \
#"

#KERNEL_IMAGETYPE="Image"

#TODO Check which one the board would need
#MACHINE_FEATURES += "usbhost keyboard screen alsa wifi sdio"

#MACHINE_EXTRA_RRECOMMENDS += " kernel-modules"

#TODO Check which files are needed
#IMAGE_BOOT_FILES ?= "my-folder/* ${KERNEL_IMAGETYPE};${SDIMG_KERNELIMAGE}"
