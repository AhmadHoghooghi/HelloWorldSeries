package ir.asta.tutorial.dl.service.impl;
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

/*PROTECTED REGION ID(AttachmentServiceImpl Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.AttachmentEntity;
import ir.asta.tutorial.dl.service.AttachmentService;
import ir.asta.tutorial.dl.manager.AttachmentManager;

@Named("attachmentService")
@MapperEnabled
public class AttachmentServiceImpl
		extends
			AbstractCrudService<AttachmentEntity, java.lang.String>
		implements
			AttachmentService {

	/*PROTECTED REGION ID(AttachmentServiceImpl Attributes) ENABLED START*/

	/*PROTECTED REGION END*/

	@Inject
	public AttachmentServiceImpl(AttachmentManager manager) {
		super.setManager(manager);
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
	// @Include(url = "attachmentType/valid-options/${param['masterPK']}", responsePath = "validAttachmentTypes", enabled = "${param['options'] != 'false'}")
	public AttachmentEntity load() {
		return super.load();
	}

	@Override
	@Mapper(value = {"*", "attachmentType.pk", "attachmentType.toStr",
			"content.pk", "content.toStr", "file.*"}, enrich = false)
	@Include(url = "#/enums", responsePath = "enums", enabled = "${param['options'] != 'false'}")
	@Include(url = "#/entityPermissions/${args[0]}", responsePath = "permissions")
	public AttachmentEntity load(java.lang.String id) {
		return super.load(id);
	}

	@Override
	public ActionResult<java.lang.String> saveOrUpdate(
			@Mapper(value = {"*", "attachmentType.pk", "content.pk", "file.*"}) AttachmentEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@Mapper(value = {"*", "items.*", "items.attachmentType.toStr",
			"items.content.toStr"}, enrich = false)
	public DataPage<AttachmentEntity> search(HttpServletRequest request) {
		return super.search(request);
	}

	/*PROTECTED REGION ID(AttachmentServiceImpl Methods) ENABLED START*/

	/*PROTECTED REGION END*/

}
