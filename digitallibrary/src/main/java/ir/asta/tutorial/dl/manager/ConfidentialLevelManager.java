package ir.asta.tutorial.dl.manager;

import javax.inject.Inject;
import javax.inject.Named;

/*PROTECTED REGION ID(ConfidentialLevelManager Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.ConfidentialLevelEntity;
import ir.asta.tutorial.dl.dao.ConfidentialLevelDao;
import ir.asta.tutorial.dl.util.DlUtils;

import java.util.List;
import java.util.stream.Collectors;

@Named("confidentialLevelManager")
public class ConfidentialLevelManager
        extends
        ir.asta.wise.core.crud.AbstractCrudManager<ConfidentialLevelEntity, java.lang.Long> {

    /*PROTECTED REGION ID(ConfidentialLevelManager Attributes) ENABLED START*/

    /*PROTECTED REGION END*/

    @Inject
    public ConfidentialLevelManager(ConfidentialLevelDao dao) {
        super.setDao(dao);
    }

    @SuppressWarnings("unused")
    private ConfidentialLevelDao getMyDao() {
        return (ConfidentialLevelDao) super.getDao();
    }

    /*PROTECTED REGION ID(ConfidentialLevelManager Methods) ENABLED START*/

    public List<ConfidentialLevelEntity> getValidConfidentialLevelsForCurrentUser() {
        Long userConfidentialLevel = DlUtils.getCurrentUserConfidentialLevelIndex();

        List<ConfidentialLevelEntity> confidentialLevelsUserHasAccessTo =
                loadAll()
                        .stream()
                        .filter(entity -> entity.getIndex() <= userConfidentialLevel)
                        .collect(Collectors.toList());
        return confidentialLevelsUserHasAccessTo;
    }

    /*PROTECTED REGION END*/
}
