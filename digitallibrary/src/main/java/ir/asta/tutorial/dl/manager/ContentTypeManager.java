package ir.asta.tutorial.dl.manager;

import javax.inject.Inject;
import javax.inject.Named;

/*PROTECTED REGION ID(ContentTypeManager Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.AttachmentTypeEntity;
import ir.asta.tutorial.dl.entities.ContentEntity;
import ir.asta.tutorial.dl.entities.ContentTypeEntity;
import ir.asta.tutorial.dl.dao.ContentTypeDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named("contentTypeManager")
public class ContentTypeManager
		extends
			ir.asta.wise.core.crud.AbstractCrudManager<ContentTypeEntity, java.lang.Long>
		implements
			ir.asta.wise.core.aom2.AdaptiveTypeRepository<java.lang.Long> {

	/*PROTECTED REGION ID(ContentTypeManager Attributes) ENABLED START*/

	/*PROTECTED REGION END*/

	@Inject
	public ContentTypeManager(ContentTypeDao dao) {
		super.setDao(dao);
	}

	@SuppressWarnings("unused")
	private ContentTypeDao getMyDao() {
		return (ContentTypeDao) super.getDao();
	}



    /*PROTECTED REGION ID(ContentTypeManager Methods) ENABLED START*/

	/*PROTECTED REGION END*/
}
