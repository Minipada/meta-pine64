# Base this image on rpi-hwup-image
include pine64-hwup-image.bb

IMAGE_FEATURES += "ssh-server-dropbear"
