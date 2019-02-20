package ir.asta.tutorial.dl.dao.meta;
import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import ir.asta.tutorial.dl.entities.*;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QAttachment extends PathBuilder<AttachmentEntity> {
	private static final long serialVersionUID = -785829409L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public final StringPath id = createString("id");
	public final StringPath code = createString("code");
	public final StringPath title = createString("title");
	public final StringPath createdBy = createString("createdBy");
	public final StringPath lastModifiedBy = createString("lastModifiedBy");

	public final ir.asta.tutorial.dl.dao.meta.QAttachmentType attachmentType;
	public final ir.asta.tutorial.dl.dao.meta.QContent content;

	public QAttachment(String variable) {
		this(AttachmentEntity.class, forVariable(variable), INITS);
	}

	public QAttachment(Path<? extends AttachmentEntity> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(
				path.getMetadata(), INITS));
	}

	public QAttachment(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QAttachment(PathMetadata metadata, PathInits inits) {
		this(AttachmentEntity.class, metadata, inits);
	}

	public QAttachment(Class<? extends AttachmentEntity> type,
			PathMetadata metadata, PathInits inits) {
		super(type, metadata);
		this.attachmentType = inits.isInitialized("attachmentType")
				? new ir.asta.tutorial.dl.dao.meta.QAttachmentType(
						forProperty("attachmentType")) : null;
		this.content = inits.isInitialized("content")
				? new ir.asta.tutorial.dl.dao.meta.QContent(
						forProperty("content")) : null;

	}

}
