pipeline{
    agent any
    environment{
        PATH = "/usr/share/maven/bin:$PATH"
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
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