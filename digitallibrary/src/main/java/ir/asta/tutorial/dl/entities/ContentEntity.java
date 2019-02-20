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

/*PROTECTED REGION ID(ContentEntity : Imports) ENABLED START*/

/*PROTECTED REGION END*/

@Entity
@Table(name = "DL_Content")
@Audited
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", doNotUseGetters = true, callSuper = false)
@EntityGenDirective(menuParent = "root", securityParent = "root")
public class ContentEntity extends AbstractBaseEntity<java.lang.String>
		implements
			Comparable<ContentEntity>,
			TracableEntity<java.lang.String>,
			LogicalDeletableEntity,
			ir.asta.wise.core.datamanagement.AdaptiveDataEntity {
	private static final long serialVersionUID = 1L;

	/*PROTECTED REGION ID(ContentEntity Attributes) ENABLED START*/
	public final static String CONFIDENTIAL_LEVEL_FIELD_NAME = "confidentialLevel";
	/*PROTECTED REGION END*/

	// primary key
	@Id
	@Column(name = "DL_CONTENT_ID")
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	private java.lang.String id;

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
	@Column(name = "FILE_", nullable = false, unique = false)
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "fileName", column = @Column(name = "file_FileName")),
			@AttributeOverride(name = "contentType", column = @Column(name = "file_ContentType")),
			@AttributeOverride(name = "content", column = @Column(name = "file_Content"))})
	@PropertyGenDirective(listable = false, editorType = EditorType.FILE)
	private ir.asta.wise.core.datamanagement.FileComponent file;

	@Basic
	@Column(name = "PURCHASE_DATE", nullable = true, unique = false)
	@PropertyGenDirective(searchMethod = SearchMethod.RANGE, editorType = EditorType.DATE)
	private java.util.Date purchaseDate;

	@Basic
	@Column(name = "STATUS_", nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	@PropertyGenDirective(searchMethod = SearchMethod.EQUAL, editorType = EditorType.COMBO)
	private ir.asta.tutorial.dl.entities.ContentStatus status;

	@Transient
	public String getStatusStr() {
		if (status == null) {
			return null;
		}
		return status.toString();
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_time", nullable = true, unique = false, length = 0, updatable = false)
	@SortDefault(order = 0, dir = SortDirection.ASC)
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
	@Column(name="SERIAL", nullable = false, unique = true)
	private String serial;

	@Basic
	@Column(name = "DELETED", nullable = true, unique = false, length = 0)
	@PropertyGenDirective(editable = false, listable = false, searchMethod = SearchMethod.LIKE, editorType = EditorType.AUTO)
	private java.lang.Boolean deleted;

	@Basic
	@Column(name = "adaptive_data_xml", length = 10000)
	@Convert(converter = AdaptiveDataConverter.class)
	private DataModel adaptiveDataModel;

	@JsonDeserialize(using = ir.asta.wise.core.remoting.rs.DataModelDeserializer.class)
	public void setAdaptiveDataModel(DataModel adaptiveDataModel) {
		this.adaptiveDataModel = adaptiveDataModel;
	}

	// many to one
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(targetEntity = ir.asta.tutorial.dl.entities.ContentTypeEntity.class, optional = false, cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_CONTENTTYPE", nullable = false)
	@RelationGenDirective(listable = false, searchable = true, adaptiveType = true, editorType = RelationEditorType.COMBO)
	private ir.asta.tutorial.dl.entities.ContentTypeEntity contentType;
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(targetEntity = ir.asta.tutorial.dl.entities.LibraryEntity.class, optional = false, cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_LIBRARY", nullable = false)
	@RelationGenDirective(searchable = true, editorType = RelationEditorType.AUTO)
	private ir.asta.tutorial.dl.entities.LibraryEntity library;



	// collections

	@NotAudited
	@OneToMany(targetEntity = ir.asta.tutorial.dl.entities.AttachmentEntity.class, mappedBy = "content", cascade = {})
	@RelationGenDirective(editorType = RelationEditorType.DETAILS)
	private Set<ir.asta.tutorial.dl.entities.AttachmentEntity> attachments;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(targetEntity = ConfidentialLevelEntity.class)
	@JoinColumn(name = "confidential_level", nullable = false)
	private ConfidentialLevelEntity confidentialLevel;

	@Basic
	@Column(name = "description", nullable = false, unique = false)
	private String description;



	public int compareTo(ir.asta.tutorial.dl.entities.ContentEntity obj) {
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
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Don't remove this method
	 * This is related to some Lombok bugs!
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}
	@Transient
	public java.lang.String getPk() {
		return this.id;
	}
	public void setPk(java.lang.String pk) {
		this.id = pk;
	}

	/*PROTECTED REGION ID(ContentEntity Methods) ENABLED START*/

	/*PROTECTED REGION END*/
}
