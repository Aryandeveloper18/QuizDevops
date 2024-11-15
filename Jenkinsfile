pipeline{
    agent any
    environment{
        PATH = "/opt/homebrew/bin/mvn"
        JAVA_HOME = "/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home"
    }
    stages{
        stage('Build'){
            steps{
                sh '/bin/zsh -c "mvn clean install"'
            }
        }
        stage('SonarQube analysis'){
            steps{
                withSonarQubeEnv('sonarqube'){
                    sh '/bin/zsh -c "mvn sonar: sonar"'
                }
            }
        }
    }
}