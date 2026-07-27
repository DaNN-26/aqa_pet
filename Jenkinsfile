pipeline {
    agent any

    stages {
        stage('Pull and Run Selenoid') {
            steps {
                script {
                    // Запускаем Selenoid в фоновом режиме
                    sh '''
                        docker stop selenoid || true
                        docker rm selenoid || true
                        docker run -d --name selenoid \
                          -p 4444:4444 \
                          -e DOCKER_API_VERSION=1.44 \
                          -v /var/run/docker.sock:/var/run/docker.sock \
                          -v $(pwd)/selenoid-config:/etc/selenoid \
                          aerokube/selenoid:latest
                    '''
                }
            }
        }

        stage('Wait for Selenoid') {
            steps {
                script {
                    sh '''
                        echo "Waiting for Selenoid to be ready..."
                        timeout 30 bash -c 'while ! curl -s http://localhost:4444/status | grep -q "ready"; do sleep 1; done'
                        echo "Selenoid is ready!"
                    '''
                }
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    sh 'chmod +x gradlew'
                    sh './gradlew clean test'
                }
            }
        }
    }

    post {
        always {
            script {
                sh '''
                    docker stop selenoid || true
                    docker rm selenoid || true
                '''
            }
        }
    }
}