package ir.asta.tutorial.dl.manager;

import javax.inject.Inject;
import javax.inject.Named;

/*PROTECTED REGION ID(AttachmentManager Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.AttachmentEntity;
import ir.asta.tutorial.dl.dao.AttachmentDao;
import ir.asta.wise.core.exceptions.LocalizedException;
import ir.asta.wise.core.util.locale.LocaleUtil;

import java.util.List;
import java.util.stream.Collectors;

@Named("attachmentManager")
public class AttachmentManager
		extends
			ir.asta.wise.core.crud.AbstractCrudManager<AttachmentEntity, java.lang.String> {

	/*PROTECTED REGION ID(AttachmentManager Attributes) ENABLED START*/

	/*PROTECTED REGION END*/

	@Inject
	public AttachmentManager(AttachmentDao dao) {
		super.setDao(dao);
	}

	@SuppressWarnings("unused")
	private AttachmentDao getMyDao() {
		return (AttachmentDao) super.getDao();
	}

	/*PROTECTED REGION ID(AttachmentManager Methods) ENABLED START*/

	@Override
	protected void beforeMerge(AttachmentEntity attachmentEntity, boolean creating) {
		super.beforeMerge(attachmentEntity, creating);
		if(creating){
			Long attachmentTypeId = attachmentEntity.getAttachmentType().getId();
			List<Long> validAttachmentTypeIds = attachmentEntity.getContent().getContentType().getValidAttachmentTypes().stream()
					.map(attachmentType -> attachmentType.getId())
					.collect(Collectors.toList());
			if(!validAttachmentTypeIds.contains(attachmentTypeId)){
				throw new LocalizedException(LocaleUtil.getText("attachment_invalid_attachment_type_error_message"));
			}
		}else{

		}
	}
	/*PROTECTED REGION END*/
}
