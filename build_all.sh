#!/bin/bash

for project in $(find . -maxdepth 1 -type d -not -name .); do
  if [ -f "$project/pom.xml" ]; then
    echo "Building project $project"
    (cd "$project" && mvn clean package)
  fi
done
