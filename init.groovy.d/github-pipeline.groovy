import jenkins.model.*
import hudson.model.*
import org.jenkinsci.plugins.workflow.job.*
import org.jenkinsci.plugins.workflow.cps.*

def jenkins = Jenkins.instance

def jobName = "jenkins_tutorial"
def job = jenkins.getItem(jobName)

if (job == null) {
    try {
        println "Creating job: ${jobName}"

        def flowDefinition = new CpsScmFlowDefinition(
            new hudson.plugins.git.GitSCM("https://github.com/kiptootitus/jenkins_tutorial.git"),
            "Jenkinsfile"
        )
        flowDefinition.setLightweight(true)

        def pipelineJob = jenkins.createProject(WorkflowJob, jobName)
        pipelineJob.setDefinition(flowDefinition)
        pipelineJob.save()

        println "Successfully created job: ${jobName}"
    } catch (Exception e) {
        println "Failed to create job: ${jobName}. Error: ${e.message}"
        throw e
    }
} else {
    println "Job ${jobName} already exists, skipping creation."
}