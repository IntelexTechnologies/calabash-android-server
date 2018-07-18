#! /usr/bin/env bash

set -e

source log.sh

if [ "$(uname -s)" == "Darwin" ]; then
  export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
elif [ "$(uname -s)" == "Linux" ]; then
  export JAVA_HOME=/usr/lib/jvm/java-8-oracle/
fi

info "Using $JAVA_HOME path for JAVA_HOME variable"
