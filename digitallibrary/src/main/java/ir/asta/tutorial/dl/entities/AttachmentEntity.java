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

/*PROTECTED REGION ID(AttachmentEntity : Imports) ENABLED START*/

/*PROTECTED REGION END*/

@Entity
@Table(name = "DL_ATTACHMENT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", doNotUseGetters = true, callSuper = false)
@EntityGenDirective(menuParent = "root", securityParent = "root")
public class AttachmentEntity extends AbstractBaseEntity<java.lang.String>
		implements
			Comparable<AttachmentEntity>,
			TracableEntity<java.lang.String> {
	private static final long serialVersionUID = 1L;

	/*PROTECTED REGION ID(AttachmentEntity Attributes) ENABLED START*/

	/*PROTECTED REGION END*/

	// primary key
	@Id
	@Column(name = "DL_ATTACHMENT_ID")
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	private java.lang.String id;

	// fields
	@Basic
	@Column(name = "CODE_", nullable = false, unique = true)
	@PropertyGenDirective(searchMethod = SearchMethod.LIKE, editorType = EditorType.TEXT)
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

	// many to one
	@ManyToOne(targetEntity = ir.asta.tutorial.dl.entities.AttachmentTypeEntity.class, optional = false, cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ATTACHMENTTYPE", nullable = false)
	@RelationGenDirective(searchable = true, editorType = RelationEditorType.AUTO)
	private ir.asta.tutorial.dl.entities.AttachmentTypeEntity attachmentType;
	@ManyToOne(targetEntity = ir.asta.tutorial.dl.entities.ContentEntity.class, optional = false, cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_CONTENT", nullable = false)
	@RelationGenDirective(editorType = RelationEditorType.MASTER)
	private ir.asta.tutorial.dl.entities.ContentEntity content;

	public int compareTo(ir.asta.tutorial.dl.entities.AttachmentEntity obj) {
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

	/*PROTECTED REGION ID(AttachmentEntity Methods) ENABLED START*/

	/*PROTECTED REGION END*/
}
