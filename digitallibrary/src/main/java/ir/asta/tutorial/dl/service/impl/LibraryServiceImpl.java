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

/*PROTECTED REGION ID(LibraryServiceImpl Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.LibraryEntity;
import ir.asta.tutorial.dl.service.LibraryService;
import ir.asta.tutorial.dl.manager.LibraryManager;

@Named("libraryService")
@MapperEnabled
public class LibraryServiceImpl
		extends
			AbstractCrudService<LibraryEntity, java.lang.String>
		implements
			LibraryService {

	/*PROTECTED REGION ID(LibraryServiceImpl Attributes) ENABLED START*/

	/*PROTECTED REGION END*/

	@Inject
	public LibraryServiceImpl(LibraryManager manager) {
		super.setManager(manager);
	}

	@Override
	@Mapper(value = "*", enrich = false)
	@Include(url = "#/enums", responsePath = "enums", enabled = "${param['options'] != 'false'}")
	@Include(url = "#/permissions", responsePath = "permissions")
	@Include(url = "contentType/allOptions", responsePath = "options.contentType", enabled = "${param['options'] != 'false'}")
	public Map<String, Object> searchDefault() {
		return super.searchDefault();
	}

	@Override
	@Mapper(value = "*", enrich = false)
	@Include(url = "#/enums", responsePath = "enums", enabled = "${param['options'] != 'false'}")
	@Include(url = "#/permissions", responsePath = "permissions")
	@Include(url = "contentType/allOptions", responsePath = "options.contentType", enabled = "${param['options'] != 'false'}")
	public LibraryEntity load() {
		return super.load();
	}

	@Override
	@Mapper(value = {"*", "contentType.pk", "contentType.toStr"}, enrich = false)
	@Include(url = "#/enums", responsePath = "enums", enabled = "${param['options'] != 'false'}")
	@Include(url = "#/entityPermissions/${args[0]}", responsePath = "permissions")
	@Include(url = "contentType/allOptions", responsePath = "options.contentType", enabled = "${param['options'] != 'false'}")
	public LibraryEntity load(java.lang.String id) {
		return super.load(id);
	}

	@Override
	public ActionResult<java.lang.String> saveOrUpdate(@Mapper(value = {"*",
			"contentType.pk", "contentType.toStr"}) LibraryEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@Mapper(value = {"*", "items.*", "items.contentType.toStr"}, enrich = false)
	public DataPage<LibraryEntity> search(HttpServletRequest request) {
		return super.search(request);
	}

	/*PROTECTED REGION ID(LibraryServiceImpl Methods) ENABLED START*/

	/*PROTECTED REGION END*/

}
