pipeline {
    agent any

    environment {
        // Environment Variables
        DOCKER_IMAGE = 'quizappdevops-quiz-app'
        DOCKER_TAG = 'latest'
        POSTGRES_HOST = 'postgres_container'
        PGADMIN_HOST = 'pgadmin_container'
        SONARQUBE_HOST = 'quizappdevops-sonarqube'
        SONARQUBE_TOKEN = credentials('sonarqube-token')  // Ensure SonarQube token is stored in Jenkins credentials
        GIT_REPO_URL = 'https://github.com/your-repository-url.git'  // Replace with your GitHub repository URL
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Checkout the application code from GitHub
                git url: "${GIT_REPO_URL}"
            }
        }

        stage('Start Docker Containers') {
            steps {
                script {
                    // Start PostgreSQL, pgAdmin, and other required services using Docker Compose
                    sh 'docker-compose up -d'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Run SonarQube analysis (Ensure the SonarQube instance is running and accessible)
                    sh """
                    mvn sonar:sonar \
                        -Dsonar.projectKey=your_project_key \
                        -Dsonar.host.url=http://${SONARQUBE_HOST}:9000 \
                        -Dsonar.login=${SONARQUBE_TOKEN}
                    """
                }
            }
        }

        stage('Deploy Application') {
            steps {
                script {
                    // Optionally, restart the app container to deploy the latest version
                    sh 'docker-compose restart quizappdevops-quiz-app'
                }
            }
        }
    }

    post {
        always {
            // Clean up the Docker containers and volumes to avoid resource leakage
            sh 'docker-compose down --volumes --remove-orphans'
        }
    }
}
