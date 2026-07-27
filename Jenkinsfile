pipeline {
    agent any
    stages {
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