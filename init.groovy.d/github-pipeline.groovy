import jenkins.model.*
import hudson.plugins.git.*
import hudson.model.*
import org.jenkinsci.plugins.workflow.job.*
import org.jenkinsci.plugins.workflow.cps.*
import hudson.plugins.git.extensions.impl.*

def jenkins = Jenkins.instance

def jobName = "jenkins_tutorial"
def job = jenkins.getItem(jobName)

if (job == null) {
    println "Creating job: ${jobName}"

    // GitSCM setup with explicit branch
    def scm = new GitSCM("https://github.com/kiptootitus/jenkins_tutorial.git")
    scm.branches = [new BranchSpec("*/main")]  // <-- Use "*/master" if your repo uses master!
    scm.extensions.add(new CleanBeforeCheckout())

    def flowDefinition = new CpsScmFlowDefinition(scm, "Jenkinsfile")
    flowDefinition.setLightweight(true)

    def pipelineJob = jenkins.createProject(WorkflowJob, jobName)
    pipelineJob.setDefinition(flowDefinition)
    pipelineJob.save()
    println "✅ Job created: ${jobName}"
} else {
    println "⚠️ Job ${jobName} already exists, skipping creation."
}
