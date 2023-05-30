pipeline{
          agent any 
                stages{
                stage('checkout') {
                          steps{
                     git branch: 'main', url: 'https://github.com/gituser2012/APITesting'
                          }
                }
               stage('env'){ 
              steps{
                        env.PATH = "C:\\Windows\\System32;C:\\apache-maven-3.9.2-bin\\apache-maven-3.9.2\\bin"
           
              }
    
    stage('build'){ 
              steps{
                       
            bat 'mvn package'
              }
    }
    stage('archive'){
              steps{
        archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
              }
    }
                }
            
}
