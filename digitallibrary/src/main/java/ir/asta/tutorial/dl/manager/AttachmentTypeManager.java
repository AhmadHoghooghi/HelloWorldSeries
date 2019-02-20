package ir.asta.tutorial.dl.manager;

import javax.inject.Inject;
import javax.inject.Named;

/*PROTECTED REGION ID(AttachmentTypeManager Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.AttachmentTypeEntity;
import ir.asta.tutorial.dl.dao.AttachmentTypeDao;
import ir.asta.tutorial.dl.entities.ContentEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named("attachmentTypeManager")
public class AttachmentTypeManager
		extends
			ir.asta.wise.core.crud.AbstractCrudManager<AttachmentTypeEntity, java.lang.Long> {

	/*PROTECTED REGION ID(AttachmentTypeManager Attributes) ENABLED START*/

	/*PROTECTED REGION END*/

	@Inject
	public AttachmentTypeManager(AttachmentTypeDao dao) {
		super.setDao(dao);
	}

	@Inject
	public ContentManager contentManager;

	@SuppressWarnings("unused")
	private AttachmentTypeDao getMyDao() {
		return (AttachmentTypeDao) super.getDao();
	}

	/*PROTECTED REGION ID(AttachmentTypeManager Methods) ENABLED START*/
	@Transactional(readOnly = true)
	public List<AttachmentTypeEntity> validContentTypeOptionsForContentId(String pk) {
		ContentEntity content = contentManager.load(pk);
		Set<AttachmentTypeEntity> validAttachmentTypes = content.getContentType().getValidAttachmentTypes();
		List<AttachmentTypeEntity> validAttachmentTypesAsList = validAttachmentTypes.stream().collect(Collectors.toList());
		return validAttachmentTypesAsList;
	}
	/*PROTECTED REGION END*/
}
