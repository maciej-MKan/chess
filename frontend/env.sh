#!/bin/bash

echo "setup environment"
node env.js

if [ $? -eq 0 ]; then
  echo "Success executing env.js"

  set -o allexport
  source ./.env
  set +o allexport

  echo "$(<./.env )"

  echo "run app"
  npm run start
else
  echo "env.js field"
  exit 1
fi