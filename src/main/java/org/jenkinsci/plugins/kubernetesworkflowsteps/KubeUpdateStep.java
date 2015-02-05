package org.jenkinsci.plugins.kubernetesworkflowsteps;

import com.google.gson.Gson;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

import java.util.Map;

/**
 * TODO: Insert description here. (generated by elibixby)
 */
public class KubeUpdateStep extends KubeStep {

  public final transient Map<?,?> data;
  public final transient String id;
  /**
   * @param resource
   */
  @DataBoundConstructor
  public KubeUpdateStep(String resource, String id, Map<?,?> data) {
    super(resource);
    this.data=data;
    this.id = id;
  }
  
  @Extension public static final class DescriptorImpl extends AbstractStepDescriptorImpl{

    public DescriptorImpl() {
      super(Execution.class);
    }
    
    @Override
    public String getFunctionName(){
      return "kube_update";
    }
    
    @Override
    public String getDisplayName(){
      return "Update a resource using the Kubernetes API";
    }
    
  }
  

  
  public static class Execution extends KubeStepExecution<KubeUpdateStep>{
    
    /* (non-Javadoc)
     * @see org.jenkinsci.plugins.kubernetesworkflowsteps.KubeStepExecution#request()
     */
    @Override
    protected String run() throws Exception {
      StringEntity entity = new StringEntity((new Gson()).toJson(step.data));
      entity.setContentType("application/json");
      HttpPut put = new HttpPut(this.prefix+step.resource+"/"+step.id);
      put.setEntity(entity);
      return parse(makeCall(put, this.readWriteHost, this.readWritePort));
    }
    
  }



}
