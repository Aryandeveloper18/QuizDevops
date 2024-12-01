pipeline {
    agent any
    tools {
            maven 'Maven3.9'
    }
    environment {
        PATH = "/opt/homebrew/bin:/opt/homebrew/opt/postgresql@14/bin:/bin:/usr/bin"
        JAVA_HOME = "/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home"
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
        
    }
}

