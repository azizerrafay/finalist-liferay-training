# Build a docker image for Liferay.
# NOTE: This file is only used by docker compose, and it is NOT used by the gradlew docker commands,
# instead the gradlew docker commands use the generates a dockerfile in build/docker and adds the
# contents of Dockerfile.ext (see root folder) to the end of that file.

from liferay/dxp:2025.q1.16-lts

user root
add https://github.com/ufoscout/docker-compose-wait/releases/download/2.12.0/wait /wait
run chmod +x /wait

user liferay
env LIFERAY_DISABLE_TRIAL_LICENSE=true
env JPDA_ADDRESS=0.0.0.0:8000

copy configs /home/liferay/configs
copy configs/common/patching /mnt/liferay/patching
copy configs/common/scripts /mnt/liferay/scripts
copy docker/liferay/100_liferay_image_setup.sh /usr/local/liferay/scripts/pre-configure/100_liferay_image_setup.sh

entrypoint /wait && /usr/local/bin/liferay_entrypoint.sh
