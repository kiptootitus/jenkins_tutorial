pipeline {
    agent { label 'docker-agent-alpine' }

    stages {
        stage('Wait 2 minutes') {
            steps {
                echo 'Waiting for 2 minutes before starting...'
                sleep time: 2, unit: 'MINUTES'
            }
        }
        stage('Build') {
            steps {
                echo 'Building...'
            }
        }
        stage('Install') {
            steps {
                sh 'pip install -r requirements.txt'
            }
        }
        stage('Running') {
            steps {
                sh 'python hello.py'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
            }
        }
    }
}
