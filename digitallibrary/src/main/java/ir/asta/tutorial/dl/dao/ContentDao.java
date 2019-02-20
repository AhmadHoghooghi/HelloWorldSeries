package ir.asta.tutorial.dl.dao;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ir.asta.tutorial.dl.dto.ContentReportItem;
import ir.asta.wise.core.datamanagement.SearchFilter;
import ir.asta.wise.core.datamanagement.criteria.JpaCriteriaAssociationMap;
import org.springframework.stereotype.Repository;

/*PROTECTED REGION ID(ContentDAO Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.ContentEntity;

import java.util.List;

@Repository
@Named("contentDao")
public class ContentDao
		extends
			ir.asta.wise.core.data.jpa.AbstractJpaDao<ContentEntity, java.lang.String> {
    public List<ContentReportItem> distributionReport(SearchFilter<Object> searchFilter) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<ContentReportItem> criteriaQuery = cb.createQuery(ContentReportItem.class);
        Root<ContentEntity> contentRoot = criteriaQuery.from(ContentEntity.class);
        //Selection
        CriteriaQuery<ContentReportItem> select = criteriaQuery.multiselect(contentRoot.get("contentType"), cb.count(contentRoot));
        //Restriction
        fillCriteria(criteriaQuery, contentRoot,searchFilter , null, false, new JpaCriteriaAssociationMap(true));
        //Projection
        criteriaQuery.groupBy(contentRoot.get("contentType"));
        //get result
        TypedQuery<ContentReportItem> query = em.createQuery(select);
        List<ContentReportItem> resultList = query.getResultList();
        return resultList;
    }

    /*PROTECTED REGION ID(ContentDAO Attributes) ENABLED START*/

	/*PROTECTED REGION END*/

	/*PROTECTED REGION ID(ContentDAO Methods) ENABLED START*/

	/*PROTECTED REGION END*/
}
