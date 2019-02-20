package ir.asta.tutorial.dl.service.impl;

import ir.asta.tutorial.dl.dto.ContentReportItem;
import ir.asta.tutorial.dl.entities.ContentEntity;
import ir.asta.tutorial.dl.manager.ContentManager;
import ir.asta.tutorial.dl.service.ContentReportService;
import ir.asta.tutorial.dl.service.ContentService;
import ir.asta.tutorial.dl.util.DlUtils;
import ir.asta.wise.core.datamanagement.AbstractRestService;
import ir.asta.wise.core.datamanagement.SearchParam;
import ir.asta.wise.core.datamanagement.criteria.Filters;
import ir.asta.wise.core.exceptions.LocalizedException;
import ir.asta.wise.core.security.SecurityLogger;
import ir.asta.wise.core.security.annotations.Secure;
import ir.asta.wise.core.util.StringUtil;
import ir.asta.wise.core.util.beancopier.Mapper;
import ir.asta.wise.core.util.beancopier.MapperEnabled;
import ir.asta.wise.core.util.locale.LocaleUtil;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("contentReportService")
@MapperEnabled
public class ContentReportServiceImpl extends AbstractRestService implements ContentReportService  {

    @Inject
    private ContentManager contentManager;

    public static final String SEC_LOG_CONTENT_REPORT_SUCCESS_KEY = "sec_log_content_report_success_key";
    public static final String SEC_LOG_CONTENT_REPORT_FAILURE_KEY = "sec_log_content_report_failure_key";


    @Override
    @Mapper(value = {"*", "contentType.title"}, enrich = false)
    @Secure(ContentServiceImpl.CONTENT_LIST_PERMISSION_CODE)
    public List<ContentReportItem> distributionReport(HttpServletRequest request) {
        try {
            SearchParam<Map<String, Object>> searchParam = createSearchParam(request);
            List<ContentReportItem> contentReportItems = contentManager.distributionReport(searchParam);
            successSecurityLogForReport();
            return contentReportItems;
        } catch (Exception e) {
            String errorMessage = getMessages(new ContentEntity(), e);
            failureSecurityLogForReport();
            throw new LocalizedException(errorMessage);
        }
    }

    private void successSecurityLogForReport() {
        String logString = LocaleUtil.getText(SEC_LOG_CONTENT_REPORT_SUCCESS_KEY);
        securityLog(Boolean.TRUE, ContentServiceImpl.CONTENT_LIST_PERMISSION_CODE, logString);
    }

    private void failureSecurityLogForReport() {
        String logString = LocaleUtil.getText(SEC_LOG_CONTENT_REPORT_FAILURE_KEY);
        securityLog(Boolean.FALSE, ContentServiceImpl.CONTENT_LIST_PERMISSION_CODE, logString);
    }

    @Override
    protected SearchParam<Map<String, Object>> createSearchParam(HttpServletRequest request) {
        SearchParam<Map<String, Object>> searchParams = super.createSearchParam(request);

        return searchParams;
    }




}
