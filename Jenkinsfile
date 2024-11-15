pipeline{
    agent any
    environment{
        PATH = "/opt/maven/bin:$PATH"
        JAVA_HOME = "/usr/lib/jvm/java-1.11.0-openjdk-amd64"
    stages{
        stage( 'Build' ){
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