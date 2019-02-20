package ir.asta.tutorial.dl.dao.meta;
import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import ir.asta.tutorial.dl.entities.*;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QAttachmentType extends PathBuilder<AttachmentTypeEntity> {
	private static final long serialVersionUID = -785829409L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public final NumberPath<Long> id = createNumber("id", Long.class);
	public final StringPath code = createString("code");
	public final StringPath title = createString("title");
	public final NumberPath<Long> sortOrder = createNumber("sortOrder",
			Long.class);
	public final StringPath createdBy = createString("createdBy");
	public final StringPath lastModifiedBy = createString("lastModifiedBy");
	public final BooleanPath deleted = createBoolean("deleted");

	public SetPath<ir.asta.tutorial.dl.entities.AttachmentEntity, ir.asta.tutorial.dl.dao.meta.QAttachment> attachmentsOfType = this
			.<ir.asta.tutorial.dl.entities.AttachmentEntity, ir.asta.tutorial.dl.dao.meta.QAttachment> createSet(
					"attachmentsOfType",
					ir.asta.tutorial.dl.entities.AttachmentEntity.class,
					ir.asta.tutorial.dl.dao.meta.QAttachment.class,
					PathInits.DIRECT2);

	public final ir.asta.tutorial.dl.dao.meta.QContentType type;

	public QAttachmentType(String variable) {
		this(AttachmentTypeEntity.class, forVariable(variable), INITS);
	}

	public QAttachmentType(Path<? extends AttachmentTypeEntity> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(
				path.getMetadata(), INITS));
	}

	public QAttachmentType(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QAttachmentType(PathMetadata metadata, PathInits inits) {
		this(AttachmentTypeEntity.class, metadata, inits);
	}

	public QAttachmentType(Class<? extends AttachmentTypeEntity> type,
			PathMetadata metadata, PathInits inits) {
		super(type, metadata);
		this.type = inits.isInitialized("type")
				? new ir.asta.tutorial.dl.dao.meta.QContentType(
						forProperty("type")) : null;

	}

}
