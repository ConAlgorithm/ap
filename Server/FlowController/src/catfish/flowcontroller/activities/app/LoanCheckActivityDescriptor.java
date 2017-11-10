package catfish.flowcontroller.activities.app;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.ActivityDescriptor;
import catfish.flowcontroller.activities.ApprovalEvidenceCheckBaseActivity;
import catfish.flowcontroller.activities.StateHelper;

public class LoanCheckActivityDescriptor extends ActivityDescriptor{
    public LoanCheckActivityDescriptor(){
        super("CheckInformation");
    }
    
    @Override
    public String getDescription(String activity, Map<String, Object> state, Properties resources, List<QueueMessager> messages){
        int needUploadStatus = StateHelper.loadInt(state, ApprovalEvidenceCheckBaseActivity.NeedUploadStatus, 0);
        int alreadyUploadedStatus = StateHelper.loadInt(state, ApprovalEvidenceCheckBaseActivity.AlreadyUploadedStatus, 0);
        int evidenceCheckCount = StateHelper.loadInt(state, ApprovalEvidenceCheckBaseActivity.EvidenceCheckCount, 0);
        
        StringBuilder sb = new StringBuilder();
        if(((needUploadStatus & ApprovalEvidenceCheckBaseActivity.BIT_IOU) > 0) 
                && (alreadyUploadedStatus & ApprovalEvidenceCheckBaseActivity.BIT_IOU) ==0){
            sb.append(resources.getProperty("IOU"));
        }
        if(((needUploadStatus & ApprovalEvidenceCheckBaseActivity.BIT_BUCKLE) > 0) 
                && (alreadyUploadedStatus & ApprovalEvidenceCheckBaseActivity.BIT_BUCKLE) ==0){
            if(sb.length()>0){
                sb.append(",");
            }
            sb.append(resources.getProperty("Buckle"));
        }

        if(sb.length()>0){
            String uploadId = null;
            if(evidenceCheckCount == 0){
                uploadId = "UploadFile";
            } else {
                uploadId = "Reupload";
            }
            return resources.getProperty(uploadId) + sb.toString();
        } else {
            return null;
        }
    }
}
