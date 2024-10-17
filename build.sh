docker build . -t docker/nvim-lazyvim
docker run --rm -it --mount type=bind,source="$(pwd)",target=/root/.config/nvim -t docker/nvim-lazyvim
