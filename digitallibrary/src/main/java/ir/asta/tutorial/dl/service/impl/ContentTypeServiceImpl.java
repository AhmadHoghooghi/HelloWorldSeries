package ir.asta.tutorial.dl.service.impl;
import java.util.List;
import java.util.Map;

import ir.asta.tutorial.dl.entities.AttachmentTypeEntity;
import ir.asta.wise.core.crud.*;
import ir.asta.wise.core.datamanagement.*;
import ir.asta.wise.core.remoting.rs.Include;
import ir.asta.wise.core.remoting.rs.Includes;
import ir.asta.wise.core.util.beancopier.Mapper;
import ir.asta.wise.core.util.beancopier.MapperEnabled;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/*PROTECTED REGION ID(ContentTypeServiceImpl Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.ContentTypeEntity;
import ir.asta.tutorial.dl.service.ContentTypeService;
import ir.asta.tutorial.dl.manager.ContentTypeManager;

@Named("contentTypeService")
@MapperEnabled
public class ContentTypeServiceImpl
		extends
			AbstractCrudService<ContentTypeEntity, java.lang.Long>
		implements
			ContentTypeService {

	/*PROTECTED REGION ID(ContentTypeServiceImpl Attributes) ENABLED START*/

	/*PROTECTED REGION END*/


	@Inject
	public ContentTypeServiceImpl(ContentTypeManager manager) {
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
	public ContentTypeEntity load() {
		return super.load();
	}

	@Override
	@Mapper(value = {"*", "adaptiveTypeModel"}, enrich = false)
	@Include(url = "#/enums", responsePath = "enums", enabled = "${param['options'] != 'false'}")
	@Include(url = "#/entityPermissions/${args[0]}", responsePath = "permissions")
	public ContentTypeEntity load(java.lang.Long id) {
		return super.load(id);
	}

	@Override
	public ActionResult<java.lang.Long> saveOrUpdate(@Mapper(value = {"*",
			"adaptiveTypeModel"}) ContentTypeEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@Mapper(value = {"*", "items.*", "items.validAttachmentTypes.toStr"}, enrich = false)
	public DataPage<ContentTypeEntity> search(HttpServletRequest request) {
		return super.search(request);
	}

	/*PROTECTED REGION ID(ContentTypeServiceImpl Methods) ENABLED START*/


	/*PROTECTED REGION END*/

}
