package ir.asta.tutorial.dl.service.impl;
import java.util.List;
import java.util.Map;

import ir.asta.wise.core.crud.*;
import ir.asta.wise.core.datamanagement.*;
import ir.asta.wise.core.remoting.rs.Include;
import ir.asta.wise.core.remoting.rs.Includes;
import ir.asta.wise.core.util.beancopier.Mapper;
import ir.asta.wise.core.util.beancopier.MapperEnabled;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/*PROTECTED REGION ID(AttachmentTypeServiceImpl Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.AttachmentTypeEntity;
import ir.asta.tutorial.dl.service.AttachmentTypeService;
import ir.asta.tutorial.dl.manager.AttachmentTypeManager;

@Named("attachmentTypeService")
@MapperEnabled
public class AttachmentTypeServiceImpl
		extends
			AbstractCrudService<AttachmentTypeEntity, java.lang.Long>
		implements
			AttachmentTypeService {

	/*PROTECTED REGION ID(AttachmentTypeServiceImpl Attributes) ENABLED START*/

	/*PROTECTED REGION END*/
	AttachmentTypeManager manager;

	@Inject
	public AttachmentTypeServiceImpl(AttachmentTypeManager manager) {
		super.setManager(manager);
		this.manager = manager;
	}

	@Override
	@Mapper(value = "*", enrich = false)
	@Include(url = "#/enums", responsePath = "enums", enabled = "${param['options'] != 'false'}")
	@Include(url = "#/permissions", responsePath = "permissions")
	public Map<String, Object> searchDefault() {
		return super.searchDefault();
	}

	@Override
	@Mapper(value = "*", enrich = false)
	@Include(url = "#/enums", responsePath = "enums", enabled = "${param['options'] != 'false'}")
	@Include(url = "#/permissions", responsePath = "permissions")
	public AttachmentTypeEntity load() {
		return super.load();
	}

	@Override
	@Mapper(value = {"*", "type.pk", "type.toStr"}, enrich = false)
	@Include(url = "#/enums", responsePath = "enums", enabled = "${param['options'] != 'false'}")
	@Include(url = "#/entityPermissions/${args[0]}", responsePath = "permissions")
	public AttachmentTypeEntity load(java.lang.Long id) {
		return super.load(id);
	}

	@Override
	public ActionResult<java.lang.Long> saveOrUpdate(@Mapper(value = {"*",
			"type.pk"}) AttachmentTypeEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@Mapper(value = {"*", "items.*", "items.attachmentsOfType.toStr",
			"items.type.toStr"}, enrich = false)
	public DataPage<AttachmentTypeEntity> search(HttpServletRequest request) {
		return super.search(request);
	}

	/*PROTECTED REGION ID(AttachmentTypeServiceImpl Methods) ENABLED START*/
	@Override
	@Mapper(value = {"*", "items.*", "items.attachmentsOfType.toStr",
			"items.type.toStr"}, enrich = false)
	public DataPage<AttachmentTypeEntity> validContentTypeOptionsForContentId(String pk) {
		List<AttachmentTypeEntity> validAttachmentTypes= manager.validContentTypeOptionsForContentId(pk);

		DataPage<AttachmentTypeEntity> dataPage = new DataPage<>(
				validAttachmentTypes,
				validAttachmentTypes.size(),
				1L,
				validAttachmentTypes.size(), "title", false);
		return dataPage;
	}
	/*PROTECTED REGION END*/

}
