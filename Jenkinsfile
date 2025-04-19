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
                    docker.image(env.PYTHON_IMAGE).inside('--user root') {
                        sh '''
                            export HOME=/tmp
                            python --version
                            python -m ensurepip --upgrade || true
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
                    docker.image(env.PYTHON_IMAGE).inside('--user root') {
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
                script {
                    docker.image(env.PYTHON_IMAGE).inside('--user root') {
                        sh '''
                            export HOME=/tmp
                            pytest --maxfail=1 --disable-warnings -q
                        '''
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying...'
            }
        }
    }
}
