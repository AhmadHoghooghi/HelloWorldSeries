package ir.asta.tutorial.dl.manager;

import javax.inject.Inject;
import javax.inject.Named;

/*PROTECTED REGION ID(ContentManager Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.dto.ContentReportItem;
import ir.asta.tutorial.dl.dto.ContentStatusChangeDto;
import ir.asta.tutorial.dl.dto.ContentStatusChangeResultDto;
import ir.asta.tutorial.dl.entities.*;
import ir.asta.tutorial.dl.dao.ContentDao;
import ir.asta.tutorial.dl.util.DlUtils;
import ir.asta.wise.core.datamanagement.*;
import ir.asta.wise.core.datamanagement.criteria.Filters;
import ir.asta.wise.core.datamanagement.criteria.MapToFilterConverter;
import ir.asta.wise.core.exceptions.LocalizedException;
import ir.asta.wise.core.util.db.SequenceGenerator;
import ir.asta.wise.core.util.locale.LocaleUtil;
import ir.asta.wise.portal.security.model.UserDTO;
import ir.asta.wise.portal.security.service.query.user.UserQuery;
import ir.asta.wise.portal.security.service.query.user.UserQueryFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Named("contentManager")
public class ContentManager
        extends
        ir.asta.wise.core.crud.AbstractCrudManager<ContentEntity, java.lang.String> {
    private SequenceGenerator sequenceGenerator;
    @Inject
    UserQueryFactory userQueryFactory;

    @Inject
    SystemSettingManager systemSettingManager;


    /*PROTECTED REGION ID(ContentManager Attributes) ENABLED START*/
    private ConfidentialLevelManager confidentialLevelManager;
    /*PROTECTED REGION END*/

    @Inject
    public ContentManager(ContentDao dao, SequenceGenerator sequenceGenerator, ConfidentialLevelManager confidentialLevelManager) {
        super.setDao(dao);
        this.sequenceGenerator = sequenceGenerator;
        this.confidentialLevelManager = confidentialLevelManager;
    }

    @SuppressWarnings("unused")
    private ContentDao getMyDao() {
        return (ContentDao) super.getDao();
    }

    /*PROTECTED REGION ID(ContentManager Methods) ENABLED START*/

    @Override
    protected void beforeMerge(ContentEntity entity, boolean creating) {
        if (!userHasConfidentialToSaveOrEdit(entity)) {
            throw new LocalizedException(LocaleUtil.getText("content_confidential_level_error_message"));
        }
        if (!checkContentTypeOfLibraryAndContentAreSame(entity)) {
            throw new LocalizedException(LocaleUtil.getText("content_content_type_library_content_type_compatibility_error_message"));
        }

        if (creating) {
            entity.setSerial(sequenceGenerator.generate());

            if (DlUtils.isCurrentUserAuthorizedToChangeContentStatus()) {
                entity.setStatus(ContentStatus.VERIFIED);
            } else {
                entity.setStatus(ContentStatus.IN_PROCESS);
            }
        } else {//operations of update

        }
    }

    private boolean checkContentTypeOfLibraryAndContentAreSame(ContentEntity entity) {
        Long libraryContentTypeId = entity.getLibrary().getContentType().getId();
        Long entityContentTypeId = entity.getContentType().getId();
        return libraryContentTypeId.equals(entityContentTypeId);
    }


    private boolean userHasConfidentialToSaveOrEdit(ContentEntity entity) {
        Long userConfidentialLevel = DlUtils.getCurrentUserConfidentialLevelIndex();
        Long entityConfidentialLevel = entity.getConfidentialLevel().getIndex();
        return userConfidentialLevel >= entityConfidentialLevel;
    }

    @Override
    public ContentEntity createEntity() {
        ContentEntity entity = super.createEntity();
        entity.setPurchaseDate(new Date());
        return entity;
    }

    public List<ConfidentialLevelEntity> getConfidentialLevelOptions() {
        return confidentialLevelManager.getValidConfidentialLevelsForCurrentUser();
    }

    @Override
    @Transactional(readOnly = true)
    public DataPage<VersionEntity<ContentEntity>> searchVersions(String id, SearchParam<Map<String, Object>> searchParam) {
        DataPage<VersionEntity<ContentEntity>> versionEntityDataPage = super.searchVersions(id, searchParam);
        List<VersionEntity<ContentEntity>> versions = versionEntityDataPage.getItems();
        versions.stream().forEach(
                versionEntity -> {
                    RevisionInfo revInfo = versionEntity.getRevInfo();
                    String committerId = revInfo.getCommitterId();
                    UserQuery userQuery = userQueryFactory.create();
                    UserDTO committerUserDto = userQuery.addUserIdentifier(committerId).singleResult();
                    String committerFullName = committerUserDto.getFirstName() + " " + committerUserDto.getLastName();
                    revInfo.setCommitterName(committerFullName);
                }
        );
        return versionEntityDataPage;
    }

    @Transactional
    public ContentStatusChangeResultDto changeStatus(ContentStatusChangeDto dto) {
        List<ContentEntity> contents = dto.getPkArray().stream().map(pk -> load(pk)).collect(Collectors.toList());
        Map<Boolean, List<ContentEntity>> groupByUpdatable = groupByUpdatable(contents);

        groupByUpdatable.get(true).forEach(content->{
            content.setStatus(dto.getNewStatus());
            update(content);
        });

        return new ContentStatusChangeResultDto(
                groupByUpdatable.get(true).size(),
                groupByUpdatable.get(false).size()
                );
    }

    protected Map<Boolean,List<ContentEntity>> groupByUpdatable(List<ContentEntity> contents) {
        Map<Boolean, List<ContentEntity>> shouldChangeStatus = new HashMap<>();
        shouldChangeStatus.put(Boolean.TRUE, new ArrayList<>());
        shouldChangeStatus.put(Boolean.FALSE, new ArrayList<>());
        for (ContentEntity dbContent : contents) {
            ContentStatus currentStatus = dbContent.getStatus();
            if (currentStatus == ContentStatus.IN_PROCESS) {
                shouldChangeStatus.get(true).add(dbContent);
            } else {
                shouldChangeStatus.get(false).add(dbContent);
            }
        }
        return shouldChangeStatus;
    }

    @Transactional(readOnly = true)
    public List<ContentReportItem> distributionReport(SearchParam<Map<String, Object>> searchParam) {
        addCurrentUserConfidentialLevelToSearchParams(searchParam);
        ContentDao contentDao = getMyDao();
        SearchCommand<ContentEntity> searchCommand = createSearchCommand();
        searchCommand.setSearchParam(MapToFilterConverter.convertEclosingtParam(searchParam));
        SearchFilter<Object> filter = searchCommand.getFilter();
        List<ContentReportItem> contentReportItems = contentDao.distributionReport(filter);
        return contentReportItems;
    }

    private void addCurrentUserConfidentialLevelToSearchParams(SearchParam<Map<String, Object>> searchParam) {
        Long userConfidentialLevel = DlUtils.getCurrentUserConfidentialLevelIndex();
        String key = Filters.LESS_OR_EQUAL + ContentEntity.CONFIDENTIAL_LEVEL_FIELD_NAME + ".index";
        addToSearchFilter(searchParam, key, userConfidentialLevel);
    }

    private void addToSearchFilter(
            SearchParam<Map<String, Object>> searchParam, String key,
            Object value) {
        if (searchParam.getFilter() == null) {
            searchParam.setFilter(new HashMap<String, Object>());
        }
        searchParam.getFilter().put(key, value);
    }


    public SystemSettingEntity loadSystemSettingInstance() {
        return systemSettingManager.loadSingleInstance();
    }

    @Override
    @Transactional(readOnly = true)
    public DataPage<ContentEntity> doSearch(SearchParam<Map<String, Object>> searchParam) {
        addCurrentUserConfidentialLevelToSearchParams(searchParam);
        SearchCommand<ContentEntity> sc = createSearchCommand();
        sc.setSearchParam(MapToFilterConverter.convertEclosingtParam(searchParam));
        return sc.list();
    }
}
/*PROTECTED REGION END*/
