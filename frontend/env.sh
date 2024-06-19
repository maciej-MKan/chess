#!/bin/bash

echo "setup environment"

TARGET_PATH="./.env"
ENV_CONFIG_FILE="VITE_BACKEND_URI=${API_URL}"

echo "$ENV_CONFIG_FILE" > "$TARGET_PATH"

echo "$(<"$TARGET_PATH")"

set -o allexport
source "$./.env"
set +o allexport

echo "run app"
npm run build
npm run serve