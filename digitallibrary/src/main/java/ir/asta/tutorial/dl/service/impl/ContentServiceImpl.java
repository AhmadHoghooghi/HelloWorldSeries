package ir.asta.tutorial.dl.service.impl;

import java.util.*;

import ir.asta.tutorial.dl.dto.ContentReportItem;
import ir.asta.tutorial.dl.dto.ContentStatusChangeDto;
import ir.asta.tutorial.dl.dto.ContentStatusChangeResultDto;
import ir.asta.tutorial.dl.entities.ConfidentialLevelEntity;
import ir.asta.tutorial.dl.entities.ContentStatus;
import ir.asta.tutorial.dl.entities.SystemSettingConfig;
import ir.asta.tutorial.dl.util.DlUtils;
import ir.asta.wise.core.crud.*;
import ir.asta.wise.core.datamanagement.*;
import ir.asta.wise.core.datamanagement.criteria.Filters;
import ir.asta.wise.core.exceptions.LocalizedException;
import ir.asta.wise.core.remoting.rs.Include;
import ir.asta.wise.core.security.SecurityContextUtils;
import ir.asta.wise.core.security.annotations.Secure;
import ir.asta.wise.core.util.beancopier.Mapper;
import ir.asta.wise.core.util.beancopier.MapperEnabled;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*PROTECTED REGION ID(ContentServiceImpl Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.ContentEntity;
import ir.asta.tutorial.dl.service.ContentService;
import ir.asta.tutorial.dl.manager.ContentManager;
import ir.asta.wise.core.util.locale.LocaleUtil;
import org.apache.commons.fileupload.FileUploadException;

@Named("contentService")
@MapperEnabled
public class ContentServiceImpl
        extends
        AbstractCrudService<ContentEntity, java.lang.String>
        implements
        ContentService {

    public static final String CONTENT_STATUS_CHANGE_PERMISSION_CODE = "content.change-status";
    public static final String CONFIDENTIAL_LEVEL_OPTIONS_PERMISSION_CODE = "confidentialLevel.options";
    public static final String SEC_LOG_CONTENT_STATUS_CHANGE_SUCCESS_KEY = "sec_log_content_status_change_success_key";
    public static final String SEC_LOG_CONTENT_STATUS_CHANGE_FAILURE_KEY = "sec_log_content_status_change_failure_key";

    /*PROTECTED REGION ID(ContentServiceImpl Attributes) ENABLED START*/
    private ContentManager contentManager;
    public static final String CONTENT_LIST_PERMISSION_CODE = "content.list";

    /*PROTECTED REGION END*/

    @Inject
    public ContentServiceImpl(ContentManager manager) {
        super.setManager(manager);
        this.contentManager = manager;
    }

    @Override
    @Mapper(value = "*", enrich = false)
    @Include(url = "#/enums", responsePath = "enums", enabled = "${param['options'] != 'false'}")
    @Include(url = "#/permissions", responsePath = "permissions")
    @Include(url = "contentType/allOptions", responsePath = "options.contentType", enabled = "${param['options'] != 'false'}")
    @Include(url = "#/confidential-levels", responsePath = "options.confidentialLevels", enabled = "${param['options'] != 'false'}")
    public Map<String, Object> searchDefault() {
        return super.searchDefault();
    }

    @Override
    @Mapper(value = "*", enrich = false)
    @Include(url = "#/enums", responsePath = "enums", enabled = "${param['options'] != 'false'}")
    @Include(url = "#/permissions", responsePath = "permissions")
    @Include(url = "contentType/allOptions", responsePath = "options.contentType", enabled = "${param['options'] != 'false'}")
    @Include(url = "#/confidential-levels", responsePath = "options.confidentialLevels", enabled = "${param['options'] != 'false'}")
    @Include(url = "systemSetting/load-single-instance", responsePath = "options.systemSetting", enabled = "${param['options'] != 'false'}")
    public ContentEntity load() {
        return super.load();
    }


    @Override
    @Mapper(value = {"*", "confidentialLevel.pk", "confidentialLevel.toStr",
            "contentType.pk", "contentType.toStr", "file.*", "library.pk",
            "library.toStr", "adaptiveDataModel"}, enrich = false)
    @Include(url = "#/enums", responsePath = "enums", enabled = "${param['options'] != 'false'}")
    @Include(url = "#/entityPermissions/${args[0]}", responsePath = "permissions")
    @Include(url = "contentType/allOptions", responsePath = "options.contentType", enabled = "${param['options'] != 'false'}")
    @Include(url = "#/confidential-levels", responsePath = "options.confidentialLevels", enabled = "${param['options'] != 'false'}")
    @Include(url = "systemSetting/load-single-instance", responsePath = "options.systemSetting", enabled = "${param['options'] != 'false'}")
    public ContentEntity load(java.lang.String id) {
        return super.load(id);
    }

    @Override
    public ActionResult<java.lang.String> saveOrUpdate(
            @Mapper(value = {"*", "contentType.pk", "contentType.toStr",
                    "file.*", "library.pk", "confidentialLevel.pk", "adaptiveDataModel"}, exclude = {"adaptiveDataXML"}) ContentEntity entity) {
        return super.saveOrUpdate(entity);
    }


    @Override
    @Mapper(value = {"*", "items.*", "items.attachments.toStr",
            "items.confidentialLevel.toStr", "items.library.toStr"}, enrich = false)
    public DataPage<ContentEntity> search(HttpServletRequest request) {
        return super.search(request);
    }

    @Override
    @Mapper(value = {"*", "items.*", "items.revInfo.*",
            "items.entity(ir.asta.tutorial.dl.entities.ContentEntity)",
            "items.entity.*"}, enrich = false)
    public DataPage<VersionEntity<ir.asta.tutorial.dl.entities.ContentEntity>> searchVersions(
            java.lang.String pk, HttpServletRequest request) {
        return super.searchVersions(pk, request);
    }

    @Override
    @Mapper(value = {"*", "revisionType.*", "revInfo.*",
            "entity(ir.asta.tutorial.dl.entities.ContentEntity)", "entity.*", "entity.file.*"}, enrich = false)
    public VersionEntity<ir.asta.tutorial.dl.entities.ContentEntity> loadVersion(
            java.lang.String pk, Long revId) {
        return super.loadVersion(pk, revId);
    }

    /*PROTECTED REGION ID(ContentServiceImpl Methods) ENABLED START*/

    @Override
    protected Map<String, Object> searchDefaultInternal() {
        Map<String, Object> filterMap = super.searchDefaultInternal();
        filterMap.put(Filters.EQUALS + "status", ContentStatus.VERIFIED);
        return filterMap;
    }

    @Override
    @Secure(CONFIDENTIAL_LEVEL_OPTIONS_PERMISSION_CODE)
    public List<ConfidentialLevelEntity> getConfidentialLevelOptions() {
        return contentManager.getConfidentialLevelOptions();

    }


    @Override
    @Secure(CONTENT_STATUS_CHANGE_PERMISSION_CODE)
    public ActionResult<String> changeStatus(ContentStatusChangeDto dto) {
        try {
            ContentStatusChangeResultDto resultDto = contentManager.changeStatus(dto);
            successSecurityLogForChangeStatus(dto.getPkArray());
            if (resultDto.getSuccessfulChanges() == 1 && resultDto.getFailedChanges() == 0) {
                return new ActionResult<>(LocaleUtil.getText("msg_content_change_status_success_single_entity"));
            } else {
                Object[] params = {resultDto.getSuccessfulChanges(), resultDto.getFailedChanges()};
                return new ActionResult<>(LocaleUtil.getText("msg_content_change_status_success_several_entity", params));
            }
        } catch (Exception e) {
            String errorMessage = getMessages(new ContentEntity(), e);
            failureSecurityLogForChangeStatus(dto.getPkArray());
            throw new LocalizedException(errorMessage);
        }
    }

    private void successSecurityLogForChangeStatus(List<String> pkArray) {
        Object[] logStringParams = {pkArray};
        String logString = LocaleUtil.getText(SEC_LOG_CONTENT_STATUS_CHANGE_SUCCESS_KEY, logStringParams);
        securityLog(Boolean.TRUE, CONTENT_STATUS_CHANGE_PERMISSION_CODE, logString);
    }

    private void failureSecurityLogForChangeStatus(List<String> pkArray) {
        Object[] logStringParams = {pkArray};
        String logString = LocaleUtil.getText(SEC_LOG_CONTENT_STATUS_CHANGE_FAILURE_KEY, logStringParams);
        securityLog(Boolean.FALSE, CONTENT_STATUS_CHANGE_PERMISSION_CODE, logString);
    }

    @Override
    public Map<String, Boolean> getPermissions() {
        Map<String, Boolean> permissions = super.getPermissions();
        boolean permissionValue = SecurityContextUtils.isCurrentUserAuthorizedForPermission(CONTENT_STATUS_CHANGE_PERMISSION_CODE);
        permissions.put(CONTENT_STATUS_CHANGE_PERMISSION_CODE, permissionValue);
        return permissions;
    }



    @Override
    public void upload(HttpServletRequest request, HttpServletResponse response, String fieldname) throws FileUploadException {
        logger.debug("trying to upload file into '" + fieldname + "'" + " of " + getEntityName());
        SystemSettingConfig config = contentManager.loadSystemSettingInstance().getConfig();
        Long maxSizeKB = config.getMaxSizeKB();
        String[] split = config.getValidFileTypes().split(",");
        split = Arrays.stream(split).map(String::trim).map(fileType -> fileType).toArray(String[]::new);
        upload(request, response, split, maxSizeKB * 1024, null);
    }
    /*PROTECTED REGION END*/
}
