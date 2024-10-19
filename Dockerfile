FROM --platform=linux/amd64 archlinux:latest

RUN pacman -Syyu --noconfirm
RUN pacman -S ttf-jetbrains-mono-nerd --noconfirm
RUN pacman -S git neovim gcc gdb ripgrep lazygit fd unzip tmux --noconfirm
RUN pacman -S jdk8-openjdk maven  --noconfirm
RUN mkdir /root/.config

COPY nvim /root/.config/nvim
COPY ./.tmux.conf /root/.tmux.conf

ENTRYPOINT ["/bin/sh"]
