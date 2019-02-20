package ir.asta.tutorial.dl.dao;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

/*PROTECTED REGION ID(SystemSettingDAO Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.SystemSettingEntity;

@Repository
@Named("systemSettingDao")
public class SystemSettingDao
		extends
			ir.asta.wise.core.data.jpa.AbstractJpaDao<SystemSettingEntity, java.lang.String> {
    public SystemSettingEntity loadSingleInstance() {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SystemSettingEntity> query = cb.createQuery(SystemSettingEntity.class);
        Root<SystemSettingEntity> systemSettingRoot = query.from(SystemSettingEntity.class);
        CriteriaQuery<SystemSettingEntity> select = query.select(systemSettingRoot);
        TypedQuery<SystemSettingEntity> typedQuery = em.createQuery(select);
        SystemSettingEntity singleResult = typedQuery.getSingleResult();
        return singleResult;
    }

    public Long countInstanceNumbers(){
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        query.select(cb.count(query.from(SystemSettingEntity.class)));
        return em.createQuery(query).getSingleResult();
    }

    /*PROTECTED REGION ID(SystemSettingDAO Attributes) ENABLED START*/

	/*PROTECTED REGION END*/

	/*PROTECTED REGION ID(SystemSettingDAO Methods) ENABLED START*/

	/*PROTECTED REGION END*/
}
