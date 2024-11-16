pipeline {
    agent any
    tools {
            maven 'Maven3.9'
    }
    environment {
        PATH = "/opt/homebrew/bin/mvn:/usr/local/mysql-8.0.32-macos13-arm64/bin:/bin:/usr/bin"
        JAVA_HOME = "/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home"
    }
    stages {

        stage('Verify Shell') {
            steps {
                sh 'echo $0'
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean install"
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh "mvn sonar:sonar"
                }
            }
        }
    }
}
