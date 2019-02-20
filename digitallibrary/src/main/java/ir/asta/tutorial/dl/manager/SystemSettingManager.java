package ir.asta.tutorial.dl.manager;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NonUniqueResultException;

/*PROTECTED REGION ID(SystemSettingManager Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.SystemSettingConfig;
import ir.asta.tutorial.dl.entities.SystemSettingEntity;
import ir.asta.tutorial.dl.dao.SystemSettingDao;
import ir.asta.wise.core.exceptions.LocalizedException;
import ir.asta.wise.core.util.locale.LocaleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

@Named("systemSettingManager")
public class SystemSettingManager
        extends
        ir.asta.wise.core.crud.AbstractCrudManager<SystemSettingEntity, java.lang.String> {

    private final static String MSG_NON_UNIQUE_INSTANCE_IN_DB = "msg_non_unique_instance_in_db";
    private static final String MSG_THERE_IS_AN_INSTANCE_IN_DB_SAVE_FAILED = "msg_there_is_an_instance_in_db_save_failed";

    /*PROTECTED REGION ID(SystemSettingManager Attributes) ENABLED START*/

    /*PROTECTED REGION END*/

    @Inject
    public SystemSettingManager(SystemSettingDao dao) {
        super.setDao(dao);
    }

    @SuppressWarnings("unused")
    private SystemSettingDao getMyDao() {
        return (SystemSettingDao) super.getDao();
    }

    @Override
    protected void beforeMerge(SystemSettingEntity entity, boolean creating) {
        if(creating){
            if(getMyDao().countInstanceNumbers()>=1){
                throw new LocalizedException(MSG_THERE_IS_AN_INSTANCE_IN_DB_SAVE_FAILED);
            }
        }

    }

    /*PROTECTED REGION ID(SystemSettingManager Methods) ENABLED START*/

    @Transactional
    public SystemSettingEntity loadSingleInstance() {
        try {

            SystemSettingEntity systemSetting = getMyDao().loadSingleInstance();
            return systemSetting;
        } catch (EmptyResultDataAccessException noResultException) {
            SystemSettingEntity systemSetting = new SystemSettingEntity();
            SystemSettingConfig config = new SystemSettingConfig();
            config.setMaxSizeKB(20*1024L);
            config.setValidFileTypes("pdf, zip");
            systemSetting.setConfig(config);
            save(systemSetting);
            return load(systemSetting.getId());
        } catch (NonUniqueResultException nonUniqueResultException) {
            String errorMessage = LocaleUtil.getText(MSG_NON_UNIQUE_INSTANCE_IN_DB);
            logger.error(errorMessage);
            throw new LocalizedException(errorMessage, nonUniqueResultException);
        }
    }
    /*PROTECTED REGION END*/
}
