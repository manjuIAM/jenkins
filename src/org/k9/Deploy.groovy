package org.k9

class Deploy{
    def config
    def script
    
    Deploy(script, config){
    this.cofig =config
    this.script=script
    }
    
    void deployimage(){
    this.script.stage('Deploy Image in Openshift'){
        this.steps{
            this.script{
                openshift.withCluster(){
                openshift.newBuild("--name=this.config.imagename", "--image-stream=this.config.dockerurl", "--binary")
                openshift.newApp("this.config.imagename:latest", "--name=this.config.appname").narrow('svc').expose()
                }
            }
     }
    }
    }
}
