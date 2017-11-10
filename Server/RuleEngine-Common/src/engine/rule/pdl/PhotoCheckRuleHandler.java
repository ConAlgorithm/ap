package engine.rule.pdl;

import static catfish.base.business.common.AttachmentType.UserHeadPhoto;
import static catfish.base.business.common.AttachmentType.UserIOU;
import static catfish.base.business.common.AttachmentType.UserIdPhoto;
import static catfish.base.business.common.UploadFileStatus.BankPhotoUploaded;
import static catfish.base.business.common.UploadFileStatus.HeadPhotoUploaded;
import static catfish.base.business.common.UploadFileStatus.IOUUploaded;
import static catfish.base.business.common.UploadFileStatus.IdPhotoUploaded;

import java.util.EnumSet;

import catfish.base.business.common.UploadFileStatus;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.dao.PaymentInfoDao;
import catfish.base.business.dao.UserAttachmentDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.PaymentObject;
import catfish.base.business.util.DatabaseApiUtil;
import catfish.base.queue.QueueMessager;
import engine.util.ApplicationHelper;

public class PhotoCheckRuleHandler extends
		ApplicationRuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {
		/***************just for testing********************/
		if(isForTesting)
		{
			message.setJobStatus("3");
			return message;
		}
		/***************************************************/
		
		String appId = message.getAppId();
		String jobName = message.getJobName();

		if (!DatabaseApiUtil.isNormalOrTestUser(appId)) {
			return new QueueMessager(appId, jobName, "0");
		}

		EnumSet<UploadFileStatus> checkItems = EnumSet.of(HeadPhotoUploaded,
				IdPhotoUploaded, BankPhotoUploaded);
		EnumSet<UploadFileStatus> uploadStatus = GetUploadStatusByAttachmentStatus(
				appId, checkItems);
		boolean isContinuable = uploadStatus.equals(checkItems);

		int requiredUploadStatus = 0;

		for (UploadFileStatus status : checkItems) {
			if (!uploadStatus.contains(status)) {
				requiredUploadStatus += status.getValue();
			}
		}

		ProcessRuleResult(appId, jobName, isContinuable, requiredUploadStatus);

		if (!isContinuable) {
			ApplicationHelper.ResetUploadedStatus(appId, requiredUploadStatus, false);
		}

		return new QueueMessager(appId, jobName,
				Integer.toString(requiredUploadStatus));
	}

	private EnumSet<UploadFileStatus> GetUploadStatusByAttachmentStatus(
			String appId, EnumSet<UploadFileStatus> checkItems) {
		EnumSet<UploadFileStatus> status = EnumSet
				.noneOf(UploadFileStatus.class);
		InstallmentApplicationObject app = new InstallmentApplicationDao(appId)
				.getSingle();
		String userId = app.UserId;

		if (checkItems.contains(HeadPhotoUploaded)
				&& new UserAttachmentDao(userId, UserHeadPhoto).getSingle() != null) {
			status.add(HeadPhotoUploaded);
		}

		if (checkItems.contains(IdPhotoUploaded)
				&& new UserAttachmentDao(userId, UserIdPhoto).getSingle() != null) {
			status.add(IdPhotoUploaded);
		}

		if (checkItems.contains(BankPhotoUploaded)) {
			PaymentObject pay = new PaymentInfoDao(appId).getSingle();
			if (pay != null && pay.AttachmentId != null) {
				status.add(BankPhotoUploaded);
			}
		}

		if (checkItems.contains(IOUUploaded)
				&& new UserAttachmentDao(userId, UserIOU).getSingle() != null) {
			status.add(IOUUploaded);
		}

		return status;
	}
}
