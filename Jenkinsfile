pipeline {
    agent any
    tools {
            maven 'Maven'
            jdk 'JDK'

       }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/MesVortex/SecurePro.git'
            }
        }
        stage('Build') {
                   steps {
                       script {
                           sh 'mvn clean package -DskipTests'
                       }
                   }
        }
        stage('Docker Build and Push') {
            steps {
                script {

                    def dockerImage = docker.build("${DOCKER_IMAGE}:${env.BUILD_NUMBER}")

                    dockerImage.push("${env.BUILD_NUMBER}")
                    dockerImage.push("latest")
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    sh 'docker-compose down'

                    sh """
                    docker-compose up -d --build
                    """
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Pipeline succeeded.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}