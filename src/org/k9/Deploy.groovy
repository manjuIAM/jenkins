package org.k9
import com.cloudbees.groovy.cps.NonCPS
import jenkins.model.Jenkins

class Deploy{
    def config
    def script
    
    Deploy(script, config){
    this.config =config
    this.script=script
    }
    
    void deployimage(){
    this.script.stage('Deploy Image in Openshift'){
        this.script.steps{
           
                openshift.withCluster(){
                openshift.newBuild("--name=this.config.imagename", "--image-stream=this.config.dockerurl", "--binary")
                openshift.newApp("this.config.imagename:latest", "--name=this.config.appname").narrow('svc').expose()
                }
            
     }
    }
    }
}
