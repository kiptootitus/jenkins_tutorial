FROM jenkins/jenkins:lts

USER root

# Install Docker CLI & curl, then cleanup
RUN apt-get update && \
    apt-get install -y docker.io curl && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Add jenkins user to the docker group (only if group doesn't exist)
RUN groupadd -g 984 docker || true && \
    usermod -aG docker jenkins

# Optional: Verify membership (just for debug use, remove in prod)
# RUN id jenkins

USER jenkins

# Install Jenkins plugins via CLI
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

# Copy any init scripts to bootstrap Jenkins config
COPY init.groovy.d/ /usr/share/jenkins/ref/init.groovy.d/
