package ir.asta.tutorial.dl.service.impl;
import java.util.List;
import java.util.Map;

import ir.asta.tutorial.dl.dto.ContentReportItem;
import ir.asta.tutorial.dl.entities.ContentEntity;
import ir.asta.wise.core.crud.*;
import ir.asta.wise.core.datamanagement.*;
import ir.asta.wise.core.exceptions.LocalizedException;
import ir.asta.wise.core.remoting.rs.Include;
import ir.asta.wise.core.security.annotations.Secure;
import ir.asta.wise.core.util.beancopier.Mapper;
import ir.asta.wise.core.util.beancopier.MapperEnabled;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/*PROTECTED REGION ID(SystemSettingServiceImpl Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.SystemSettingEntity;
import ir.asta.tutorial.dl.service.SystemSettingService;
import ir.asta.tutorial.dl.manager.SystemSettingManager;
import ir.asta.wise.core.util.locale.LocaleUtil;

@Named("systemSettingService")
@MapperEnabled
public class SystemSettingServiceImpl
		extends
			AbstractCrudService<SystemSettingEntity, java.lang.String>
		implements
			SystemSettingService {
	public static final String SYSTEMSETTING_VIEW_PERMISSION_CODE = "systemsetting.view";
	public static final String SEC_LOG_SYSTEM_SETTING_INSTANCE_LOAD_SUCCESS_MSG = "sec_log_system_setting_instance_load_success_msg";
	public static final String SEC_LOG_SYSTEM_SETTING_INSTANCE_LOAD_FAIL_MSG = "sec_log_system_setting_instance_load_fail_msg";

	/*PROTECTED REGION ID(SystemSettingServiceImpl Attributes) ENABLED START*/

	/*PROTECTED REGION END*/

	@Inject
	public SystemSettingServiceImpl(SystemSettingManager manager) {
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
	public SystemSettingEntity load() {
		return super.load();
	}

	@Override
	@Mapper(value = {"*"}, enrich = false)
	@Include(url = "#/enums", responsePath = "enums", enabled = "${param['options'] != 'false'}")
	@Include(url = "#/entityPermissions/${args[0]}", responsePath = "permissions")
	public SystemSettingEntity load(java.lang.String id) {
		return super.load(id);
	}

	@Override
	public ActionResult<java.lang.String> saveOrUpdate(
			@Mapper(value = {"*","config.*"}) SystemSettingEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@Mapper(value = {"*", "items.*"}, enrich = false)
	public DataPage<SystemSettingEntity> search(HttpServletRequest request) {
		return super.search(request);
	}

	@Override
	@Mapper(value = {"*","config.*"},enrich = false)
	@Secure(SYSTEMSETTING_VIEW_PERMISSION_CODE)
	public SystemSettingEntity loadSingleInstance() {
		try {
			SystemSettingEntity systemSettingEntity = ((SystemSettingManager) getManager()).loadSingleInstance();
			securityLog(Boolean.TRUE, SYSTEMSETTING_VIEW_PERMISSION_CODE, LocaleUtil.getText(SEC_LOG_SYSTEM_SETTING_INSTANCE_LOAD_SUCCESS_MSG));
			return systemSettingEntity;
		} catch (Exception e) {
			String errorMessage = getMessages(new ContentEntity(), e);
			securityLog(Boolean.FALSE, SYSTEMSETTING_VIEW_PERMISSION_CODE, LocaleUtil.getText(SEC_LOG_SYSTEM_SETTING_INSTANCE_LOAD_FAIL_MSG));
			throw new LocalizedException(errorMessage);
		}

	}

	/*PROTECTED REGION ID(SystemSettingServiceImpl Methods) ENABLED START*/

	/*PROTECTED REGION END*/

}
