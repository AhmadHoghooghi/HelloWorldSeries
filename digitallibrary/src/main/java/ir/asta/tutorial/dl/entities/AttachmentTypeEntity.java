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

/*PROTECTED REGION ID(AttachmentTypeEntity : Imports) ENABLED START*/

/*PROTECTED REGION END*/

@Entity
@Table(name = "DL_ATTACHMENT_TYPE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", doNotUseGetters = true, callSuper = false)
@EntityGenDirective(menuParent = "root", securityParent = "root")
public class AttachmentTypeEntity extends AbstractBaseEntity<Long>
		implements
			Comparable<AttachmentTypeEntity>,
			TracableEntity<Long>,
			LogicalDeletableEntity {
	private static final long serialVersionUID = 1L;

	/*PROTECTED REGION ID(AttachmentTypeEntity Attributes) ENABLED START*/

	/*PROTECTED REGION END*/

	// primary key
	@Id
	@Column(name = "DL_ATTACHMENT_TYPE_ID")
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
	@SortDefault(order = 0, dir = SortDirection.ASC)
	private Long sortOrder;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_time", nullable = true, unique = false, length = 0, updatable = false)
	@SortDefault(order = 1, dir = SortDirection.ASC)
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

	// many to one
	@ManyToOne(targetEntity = ir.asta.tutorial.dl.entities.ContentTypeEntity.class, optional = false, cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_TYPE", nullable = false)
	@RelationGenDirective(searchable = true, editorType = RelationEditorType.AUTO)
	private ir.asta.tutorial.dl.entities.ContentTypeEntity type;

	// collections

	@OneToMany(targetEntity = ir.asta.tutorial.dl.entities.AttachmentEntity.class, mappedBy = "attachmentType", cascade = {})
	@RelationGenDirective(viewable = false, editorType = RelationEditorType.AUTO)
	private Set<ir.asta.tutorial.dl.entities.AttachmentEntity> attachmentsOfType;

	public int compareTo(ir.asta.tutorial.dl.entities.AttachmentTypeEntity obj) {
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

	/*PROTECTED REGION ID(AttachmentTypeEntity Methods) ENABLED START*/

	/*PROTECTED REGION END*/
}
