package org.jenkinsci.plugins.kubernetesworkflowsteps;

import org.apache.http.client.methods.HttpDelete;
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
/**
 * TODO: Insert description here. (generated by elibixby)
 */
public class KubeDeleteStep extends KubeStep {

  public final transient String id;
  
  /**
   * @param resource
   * @param id
   */
  @DataBoundConstructor
  public KubeDeleteStep(String resource, String id) {
    super(resource);
    this.id = id;
  }
  
  @Extension public static final class DescriptorImpl 
  extends AbstractStepDescriptorImpl{


    public DescriptorImpl() {
      super(Execution.class);
    }
    
    @Override
    public String getFunctionName(){
      return "kube_delete";
    }
    
    @Override
    public String getDisplayName(){
      return "Delete a resource using the Kubernetes API";
    }
    
  }
  
  
  public static class Execution extends KubeStepExecution<KubeDeleteStep>{
    @Override
    protected String run() throws Exception {
      return parse(makeCall(new HttpDelete(this.prefix+step.resource+"/"+step.id),
          this.readWriteHost,
          this.readWritePort));
    }
  }
}
