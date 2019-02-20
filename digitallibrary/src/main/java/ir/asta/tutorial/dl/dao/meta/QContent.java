package ir.asta.tutorial.dl.dao.meta;
import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import ir.asta.tutorial.dl.entities.*;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QContent extends PathBuilder<ContentEntity> {
	private static final long serialVersionUID = -785829409L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public final StringPath id = createString("id");
	public final StringPath code = createString("code");
	public final StringPath title = createString("title");
	public final EnumPath<ir.asta.tutorial.dl.entities.ContentStatus> status = createEnum(
			"status", ir.asta.tutorial.dl.entities.ContentStatus.class);
	public final StringPath createdBy = createString("createdBy");
	public final StringPath lastModifiedBy = createString("lastModifiedBy");
	public final BooleanPath deleted = createBoolean("deleted");
	public final StringPath adaptiveDataXML = createString("adaptiveDataXML");

	public SetPath<ir.asta.tutorial.dl.entities.AttachmentEntity, ir.asta.tutorial.dl.dao.meta.QAttachment> attachments = this
			.<ir.asta.tutorial.dl.entities.AttachmentEntity, ir.asta.tutorial.dl.dao.meta.QAttachment> createSet(
					"attachments",
					ir.asta.tutorial.dl.entities.AttachmentEntity.class,
					ir.asta.tutorial.dl.dao.meta.QAttachment.class,
					PathInits.DIRECT2);

	public final ir.asta.tutorial.dl.dao.meta.QContentType contentType;
	public final ir.asta.tutorial.dl.dao.meta.QLibrary library;

	public QContent(String variable) {
		this(ContentEntity.class, forVariable(variable), INITS);
	}

	public QContent(Path<? extends ContentEntity> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(
				path.getMetadata(), INITS));
	}

	public QContent(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QContent(PathMetadata metadata, PathInits inits) {
		this(ContentEntity.class, metadata, inits);
	}

	public QContent(Class<? extends ContentEntity> type, PathMetadata metadata,
			PathInits inits) {
		super(type, metadata);
		this.contentType = inits.isInitialized("contentType")
				? new ir.asta.tutorial.dl.dao.meta.QContentType(
						forProperty("contentType")) : null;
		this.library = inits.isInitialized("library")
				? new ir.asta.tutorial.dl.dao.meta.QLibrary(
						forProperty("library")) : null;

	}

}
