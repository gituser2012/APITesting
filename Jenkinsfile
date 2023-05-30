Pipeline{
          agent any {
                stages{
                stage('checkout') {
                     git branch: 'main', url: 'https://github.com/gituser2012/APITesting'
                }
            env.PATH = "C:\\Windows\\System32;C:\\apache-maven-3.9.2-bin\\apache-maven-3.9.2\\bin"
    
    stage('build'){    
            bat 'mvn package'
    }
    stage('archive'){
        archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
    }
                }
            }
}
