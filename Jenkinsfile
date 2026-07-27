pipeline {
    agent any
    stages {
        stage('Pull browser') {
            steps {
                catchError {
                    script {
                        docker.image('aerokube/selenoid:latest').withRun('-p 4444:4444 -e DOCKER_API_VERSION=1.44 -v //var/run/docker.sock:/var/run/docker.sock -v %cd%/selenoid-config:/etc/selenoid aerokube/selenoid:latest')
                    }
                }
            }
        }

        stage('Build') {
            steps {
                catchError {
                    script {
                        sh 'chmod +x gradlew'
                        sh './gradlew clean test'
                    }
                }
            }
        }
    }
}