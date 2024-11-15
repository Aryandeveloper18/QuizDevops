pipeline {
    agent any

    environment {
        // Define environment variables
        DOCKER_IMAGE = 'quizappdevops-quiz-app'
        DOCKER_TAG = 'latest'
        POSTGRES_HOST = 'postgres_container'
        PGADMIN_HOST = 'pgadmin_container'
        SONARQUBE_HOST = 'quizappdevops-sonarqube'
        SONARQUBE_TOKEN = credentials('sonarqube-token')  // Ensure the token is saved in Jenkins credentials
        GIT_REPO_URL = 'https://github.com/harshitksinghai/QuizAppDevops.git'
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Checkout the application code from GitHub
                git url: "${GIT_REPO_URL}", branch: 'main'
            }
        }

        stage('Start Docker Containers') {
            steps {
                script {
                    // Start Docker containers
                    sh 'docker-compose -f docker-compose.yaml up -d'
                }
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONARQUBE_SERVER = 'sonarqube-server1'  // Name of the SonarQube server configured in Jenkins
                SONARQUBE_TOKEN = credentials('quizapp-devops-token')  // SonarQube token stored in Jenkins credentials
            }
            steps {
                script {
                    // Run the SonarQube analysis
                    sh """
                        mvn clean verify sonar:sonar \
                            -Dsonar.projectKey=quizapp-devops \
                            -Dsonar.host.url=http://${SONARQUBE_SERVER}:9000 \
                            -Dsonar.login=${SONARQUBE_TOKEN}
                    """
                }
            }
        }

        stage('Deploy Application') {
            steps {
                script {
                    // Optionally, restart the app container to deploy the latest version
                    sh 'docker-compose -f docker-compose.yaml restart quizappdevops-quiz-app'
                }
            }
        }
    }

    post {
        success {
            // Notify on successful build
            echo 'Build and deployment successful.'
        }
        failure {
            // Notify on failure
            echo 'Build failed.'
        }
        always {
            // Clean up Docker containers
            sh 'docker-compose -f docker-compose.yaml down --volumes --remove-orphans'
        }
    }
}
