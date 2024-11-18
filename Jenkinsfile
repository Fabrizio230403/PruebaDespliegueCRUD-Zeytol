pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Agregar comandos de construcción (compilación)
                echo 'Building the project...'
            }
        }
        stage('Test') {
            steps {
                // Agregar comandos de pruebas
                echo 'Running tests...'
            }
        }
        stage('Deploy') {
            steps {
                script {
                    sh 'docker-compose down -v'
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}