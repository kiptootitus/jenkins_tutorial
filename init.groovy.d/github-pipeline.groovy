import jenkins.model.*
import hudson.model.*
import hudson.plugins.git.*
import org.jenkinsci.plugins.workflow.job.*
import org.jenkinsci.plugins.workflow.cps.*

def jenkins = Jenkins.instance

def jobName = "jenkins_tutorial"
def job = jenkins.getItem(jobName)

if (job == null) {
    try {
        println "Creating job: ${jobName}"

        // Create GitSCM with an explicit refspec
        def gitScm = new GitSCM(
            GitSCM.createRepoList("https://github.com/kiptootitus/jenkins_tutorial.git", null),
            [new BranchSpec("*/main")], // Specify the default branch (adjust to "*/master" if needed)
            null, // No user remote configs
            null, // No browser
            null, // No git tool
            [new UserRemoteConfig("https://github.com/kiptootitus/jenkins_tutorial.git", "origin", "+refs/heads/*:refs/remotes/origin/*", null)] // Explicit refspec
        )

        def flowDefinition = new CpsScmFlowDefinition(gitScm, "Jenkinsfile")
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