pipeline {
    agent any

    environment {
        ANDROID_SDK_ROOT = '/home/ubuntu/android-sdk'
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64' // Update path if needed
    }

    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout SCM') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/girisettyramakrishna/1234.git',
                        credentialsId: '' // Add credentials ID if needed
                    ]],
                    extensions: [[
                        $class: 'SubmoduleOption',
                        disableSubmodules: false,
                        parentCredentials: true,
                        recursiveSubmodules: true,
                        reference: '',
                        trackingSubmodules: false
                    ]]
                ])
            }
        }

        stage('Prepare Android SDK') {
            steps {
                sh '''
                    echo "sdk.dir=$ANDROID_SDK_ROOT" > local.properties
                    chmod +x gradlew
                '''
            }
        }

        stage('Build APK') {
            steps {
                sh './gradlew clean assembleDebug --stacktrace --no-daemon'
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/build/outputs/apk/**/*.apk', fingerprint: true
                }
                failure {
                    echo '❌ Build failed! Check the logs for details.'
                }
            }
        }

        stage('Archive APK') {
            when {
                expression { currentBuild.result == 'SUCCESS' }
            }
            steps {
                archiveArtifacts artifacts: '**/build/outputs/apk/**/*.apk', fingerprint: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed - sending notifications'
            // Add notification steps here (email, Slack, etc.)
        }
        success {
            echo '✅ Build succeeded!'
        }
        failure {
            echo '❌ Build failed!'
        }
        unstable {
            echo '⚠️ Build unstable!'
        }
    }
}
