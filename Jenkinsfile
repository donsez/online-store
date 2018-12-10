#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }

    docker.image('openjdk:8').inside('-u root -e GRADLE_USER_HOME=.gradle') {
        stage('check java') {
            sh "java -version"
        }

        stage('clean') {
            sh "chmod +x gradlew"
            sh "./gradlew clean --no-daemon"
        }

#        stage('install tools') {
#            sh "./gradlew yarn_install -PnodeInstall --no-daemon"
#        }

#        stage('backend tests') {
#            try {
#                sh "./gradlew test -PnodeInstall --no-daemon"
#            } catch(err) {
#                throw err
#            } finally {
#                junit '**/build/**/TEST-*.xml'
#            }
#        }

#        stage('frontend tests') {
#            try {
#                sh "./gradlew yarn_test -PnodeInstall --no-daemon"
#            } catch(err) {
#                throw err
#            } finally {
#                junit '**/build/test-results/karma/TESTS-*.xml'
#            }
#        }

        stage('packaging') {
            sh "./gradlew bootRepackage -x test -Pprod -PnodeInstall --no-daemon"
            archiveArtifacts artifacts: '**/build/libs/*.war', fingerprint: true
        }

        stage('quality analysis') {
            withSonarQubeEnv('Sonar') {
                sh "./gradlew sonarqube --no-daemon"
            }
        }
    }
}
