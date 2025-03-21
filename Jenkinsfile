pipeline {
    agent any

    tools {
        maven 'Maven 3'  // Ensure Maven is installed on your Jenkins instance
    }

    environment {
        JAVA_HOME = tool 'JDK 11' // Ensure Java 11 is installed and configured
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Rajdeep-git776/Go_Rest_API_Automation.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', fingerprint: true
            }
        }
    }

    post {
        failure {
            sh 'say "Build failed! Please check Jenkins logs."'
        }
    }
}
