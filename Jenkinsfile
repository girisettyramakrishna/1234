pipeline {
    agent any

    environment {
        ANDROID_HOME = "/home/ubuntu/android-sdk"
        PATH = "${ANDROID_HOME}/cmdline-tools/latest/bin:${ANDROID_HOME}/platform-tools:${PATH}"
        GRADLE_USER_HOME = "${env.WORKSPACE}/.gradle"  // Optional: to avoid permission issues
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/girisettyramakrishna/restaurant.git', branch: 'master'
            }
        }

        stage('Prepare SDK') {
            steps {
                sh '''
                    echo "sdk.dir=$ANDROID_HOME" > local.properties
                    chmod +x gradlew
                '''
            }
        }

        stage('Build APK') {
            steps {
                sh './gradlew clean assembleDebug'
            }
        }

        stage('Archive APK') {
            steps {
                archiveArtifacts artifacts: '**/build/outputs/**/*.apk', allowEmptyArchive: false
            }
        }
    }

    post {
        failure {
            echo '❌ Build failed!'
        }
        success {
            echo '✅ APK built successfully!'
        }
    }
}
