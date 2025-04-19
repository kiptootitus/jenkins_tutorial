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
                    docker.image(env.PYTHON_IMAGE).inside {
                        sh '''
                            python --version
                            python -m ensurepip --upgrade
                            pip install --upgrade pip
                            pip install -r requirements.txt
                        '''
                    }
                }
            }
        }

        stage('Running') {
            steps {
                script {
                    docker.image(env.PYTHON_IMAGE).inside {
                        sh 'python hello.py'
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
