#!/bin/bash

artifacts="4"
count=0
while [ $artifacts -eq "4" ]
do
  sbt ";clean;debug;plugins;+publishLocal"
  artifacts=`cat /Users/mhughes/.ivy2/local/test-org/the-client_2.10/1.0.0-SNAPSHOT/ivys/ivy.xml | xml sel -t -v "count(//publications/artifact)"`
  echo "---------------------------"
  echo "---------------------------"
  echo "---------------------------"
  echo "---------------------------"
  count=$((count+1))
  echo "Results are $artifacts [attempt: $count]"
  echo "---------------------------"
  echo "---------------------------"
  echo "---------------------------"
  echo "---------------------------"
  echo "---------------------------"
  echo "---------------------------"
done

echo "Found 0 artifacts: $artifacts"
