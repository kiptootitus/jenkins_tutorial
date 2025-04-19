FROM jenkins/jenkins:lts

USER root

# Install Docker CLI and clean up
RUN apt-get update && \
    apt-get install -y docker.io curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Add jenkins to docker group
RUN groupadd -g 984 docker || true && \
    usermod -aG docker jenkins

USER jenkins

# Copy plugins.txt and install plugins using jenkins-plugin-cli
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

# Copy init scripts
COPY init.groovy.d/ /usr/share/jenkins/ref/init.groovy.d/