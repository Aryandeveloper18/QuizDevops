pipeline{
    agent any
    environment{
        PATH = "/opt/maven/bin:$PATH"
        JAVA_HOME = "/opt/java/openjdk"
    }
    stages{
        stage('Build'){
            steps{
                sh "mvn clean install"
            }
        }
        stage('SonarQube analysis'){
            steps{
                withSonarQubeEnv('sonarqube_server'){
                    sh "mvn sonar: sonar"
                }
            }
        }
    }
}