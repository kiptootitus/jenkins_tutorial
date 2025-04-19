pipeline {
    agent any

    environment {
        PYTHON_IMAGE = 'python:3.8-slim'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
            }
        }

        stage('Install') {
            steps {
                script {
                    docker.image(env.PYTHON_IMAGE).inside('-u root') {
                        sh '''
                            export HOME=/tmp  # prevent writing to /.local
                            python --version
                            python -m ensurepip --upgrade || true  # fails if already present
                            pip install --no-cache-dir --upgrade pip
                            pip install --no-cache-dir -r requirements.txt
                        '''
                    }
                }
            }
        }

        stage('Running') {
            steps {
                script {
                    docker.image(env.PYTHON_IMAGE).inside('-u root') {
                        sh '''
                            export HOME=/tmp
                            python hello.py
                        '''
                    }
                }
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
