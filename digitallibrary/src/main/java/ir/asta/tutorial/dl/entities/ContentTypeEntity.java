package ir.asta.tutorial.dl.entities;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import ir.asta.wise.core.validation.constraints.*;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;
import org.hibernate.envers.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.gfaces.facelet.DataModel;
import ir.asta.wise.core.data.annotation.*;
import ir.asta.wise.core.datamanagement.*;
import ir.asta.wise.core.data.jpa.*;

import lombok.*;

/*PROTECTED REGION ID(ContentTypeEntity : Imports) ENABLED START*/

/*PROTECTED REGION END*/

@Entity
@Table(name = "DL_CONTENT_TYPE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", doNotUseGetters = true, callSuper = false)
@EntityGenDirective(menuParent = "root", securityParent = "root")
public class ContentTypeEntity extends AbstractBaseEntity<Long>
        implements
        Comparable<ContentTypeEntity>,
        TracableEntity<Long>,
        LogicalDeletableEntity,
        ir.asta.wise.core.datamanagement.AdaptiveTypeEntity {
    private static final long serialVersionUID = 1L;

    /*PROTECTED REGION ID(ContentTypeEntity Attributes) ENABLED START*/

    /*PROTECTED REGION END*/

    // primary key
    @Id
    @Column(name = "DL_CONTENT_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // fields
    @Basic
    @Column(name = "CODE_", nullable = false, unique = true)
    @PropertyGenDirective(searchMethod = SearchMethod.EQUAL, editorType = EditorType.TEXT)
    private java.lang.String code;

    @Basic
    @Column(name = "TITLE", nullable = false, unique = true)
    @PropertyGenDirective(searchMethod = SearchMethod.LIKE, editorType = EditorType.TEXT)
    private java.lang.String title;

    @Basic
    @Column(name = "SORT_ORDER", nullable = false, unique = true)
    @PropertyGenDirective(listable = false, editorType = EditorType.TEXT)
    @SortDefault(order = 1, dir = SortDirection.ASC)
    private Long sortOrder;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time", nullable = true, unique = false, length = 0, updatable = false)
    @PropertyGenDirective(editable = false, listable = false, editorType = EditorType.DATE)
    private java.util.Date creationTime;

    @Basic
    @Column(name = "created_by", nullable = true, unique = false, length = 0)
    @PropertyGenDirective(editable = false, viewable = false, listable = false, editorType = EditorType.TEXT)
    private java.lang.String createdBy;

    @Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastmodification_time", nullable = true, unique = false, length = 0)
    @PropertyGenDirective(editable = false, listable = false, editorType = EditorType.DATE)
    private java.util.Date lastModificationTime;

    @Basic
    @Column(name = "lastmodified_by", nullable = true, unique = false, length = 0)
    @PropertyGenDirective(editable = false, viewable = false, listable = false, editorType = EditorType.TEXT)
    private java.lang.String lastModifiedBy;

    @Basic
    @Column(name = "DELETED", nullable = true, unique = false, length = 0)
    @PropertyGenDirective(editable = false, listable = false, searchMethod = SearchMethod.LIKE, editorType = EditorType.AUTO)
    private java.lang.Boolean deleted;

    @Basic
    @Column(name = "ADAPTIVE_TYPE_XML", length = 10000)
    @Convert(converter = AdaptiveTypeConverter.class)
    private DataModel adaptiveTypeModel;

    @JsonDeserialize(using = ir.asta.wise.core.remoting.rs.DataModelDeserializer.class)
    public void setAdaptiveTypeModel(DataModel adaptiveTypeModel) {
        this.adaptiveTypeModel = adaptiveTypeModel;
    }

    // collections

    @OneToMany(targetEntity = ir.asta.tutorial.dl.entities.LibraryEntity.class, mappedBy = "contentType", cascade = {})
    @RelationGenDirective(viewable = false, listable = false, editorType = RelationEditorType.AUTO)
    private Set<ir.asta.tutorial.dl.entities.LibraryEntity> libraries;
    @OneToMany(targetEntity = ir.asta.tutorial.dl.entities.AttachmentTypeEntity.class, mappedBy = "type", cascade = {})
    @RelationGenDirective(editorType = RelationEditorType.AUTO)
    private Set<ir.asta.tutorial.dl.entities.AttachmentTypeEntity> validAttachmentTypes;
    @OneToMany(targetEntity = ir.asta.tutorial.dl.entities.ContentEntity.class, mappedBy = "contentType", cascade = {})
    @RelationGenDirective(viewable = false, listable = false, editorType = RelationEditorType.AUTO)
    private Set<ir.asta.tutorial.dl.entities.ContentEntity> contents;

    public int compareTo(ir.asta.tutorial.dl.entities.ContentTypeEntity obj) {
        if (obj.hashCode() > hashCode())
            return 1;
        else if (obj.hashCode() < hashCode())
            return -1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return toString(getTitle());
    }

    @Override
    @Transient
    protected String getToStringTemplate() {
        return "%s";
    }

    /**
     * Don't remove this method
     * This is related to some Lombok bugs!
     */
    public Long getId() {
        return id;
    }

    /**
     * Don't remove this method
     * This is related to some Lombok bugs!
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public Long getPk() {
        return this.id;
    }

    public void setPk(Long pk) {
        this.id = pk;
    }

    /*PROTECTED REGION ID(ContentTypeEntity Methods) ENABLED START*/

    /*PROTECTED REGION END*/
}
