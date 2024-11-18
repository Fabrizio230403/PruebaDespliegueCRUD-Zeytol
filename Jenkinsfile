pipeline {
    agent any
    stages {
        stage ('Build') {
            steps {
                script {
                    bat "docker build -t my-app-image ."
                }
            }
        }
        stage ('Tests') {
            steps {
                script {
                    bat """
                        docker run --rm my-app-image mvn test
                    """
                }
            }
        }
        stage ('Deploy') {
            steps {
                bat """
                    docker-compose down -v
                    docker-compose up -d
                """
            }
        }
    }
}