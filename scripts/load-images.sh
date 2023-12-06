#!/usr/bin/env bash

version=${1:-v1.0}

images=("postgres:13" "romanowalex/person-service:$version")

for image in "${images[@]}"; do
  kind load docker-image "$image"
done
