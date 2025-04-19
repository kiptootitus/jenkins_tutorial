pipeline {
    agent { label 'docker-agent-python3' }

    stages {
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
