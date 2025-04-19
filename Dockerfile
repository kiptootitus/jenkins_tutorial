FROM jenkins/jenkins:lts

USER root

# Install Docker CLI + curl, then cleanup to keep image size small
RUN apt-get update && \
    apt-get install -y docker.io curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Add jenkins user to docker group (GID 984 should match host docker group)
RUN groupadd -g 984 docker || true && \
    usermod -aG docker jenkins

# Back to Jenkins user for proper permission context
USER jenkins

# Copy plugin list and install via official plugin CLI
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

# Copy initialization scripts for bootstrapping admin setup, jobs, etc.
COPY init.groovy.d/ /usr/share/jenkins/ref/init.groovy.d/
