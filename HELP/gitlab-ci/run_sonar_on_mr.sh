#!/bin/bash

set -e
set -x

[ -z ${PROJECT_KEY} ] && ( echo "PROJECT_KEY is not defined"; exit 1 )

run_sonar() {
    sonar-scanner \
        -D sonar.scm.exclusions.disabled=true \
        -D sonar.host.url=${SONAR_HOST} \
        -D sonar.sources=./app/src \
        -D sonar.projectKey=${PROJECT_KEY} \
        -D sonar.login=${SONAR_LOGIN}
}

echo "=== Delete previous analysys"
curl -X POST -u "${SONAR_LOGIN}:" ${SONAR_HOST}/api/projects/delete?project=${PROJECT_KEY} || echo "Ignore error"

echo "=== First run scanner on base branch then run on MR to get a diff"
git checkout origin/${CI_DEFAULT_BRANCH} && run_sonar || echo "Ignore error"

echo "=== Second run scanner on MR to get a diff between base branch"
git checkout ${CI_COMMIT_SHORT_SHA}
run_sonar