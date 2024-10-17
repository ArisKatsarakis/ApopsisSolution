FROM arch:latest

RUN packman -Syu --no-confirm

ENTRYPOINT ['/bin/bash']
