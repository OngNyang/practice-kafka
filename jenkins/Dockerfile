From	jenkins/jenkins:lts

# use plugin-cli
COPY	plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN	jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

# JCasC 적용
ENV	CASC_JENKINS_CONFIG=/var/jenkins_home/casc_configs
COPY	jenkins.yaml /var/jenkins_home/casc_configs/jenkins.yaml

# COPY	init.groovy.d/ /var/jenkins_home/init.groovy.d/

