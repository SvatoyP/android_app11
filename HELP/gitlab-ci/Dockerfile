FROM sebbia/androidsdk-docker:30.0.3-java11

ENV GRADLE_OPTS -Dorg.gradle.daemon=false

WORKDIR /build

# Load gradle

COPY gradlew ./
COPY gradle ./gradle
COPY gradle.properties settings.gradle build.gradle ./
COPY app/build.gradle ./app/
COPY app/src/main/AndroidManifest.xml ./app/src/main/

RUN git init && \
    ./gradlew --write-verification-metadata sha256 help --dry-run && \
    rm gradle/verification-metadata.dryrun.xml && \
    rm -rf .git

# Build apk

COPY . ./

ARG FLAVOR
ARG BUILD_NUMBER

# Reset all possible changes
RUN git reset --hard

# Build
RUN echo "buildNumber=${BUILD_NUMBER}, flavor=${FLAVOR}" && \
    ./gradlew -PbuildNumber=$BUILD_NUMBER assemble${FLAVOR} && \
    find -name "*.apk"
