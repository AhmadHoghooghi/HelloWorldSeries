package ir.asta.tutorial.dl.dao.meta;
import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import ir.asta.tutorial.dl.entities.*;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QContentType extends PathBuilder<ContentTypeEntity> {
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
	public final StringPath adaptiveTypeXML = createString("adaptiveTypeXML");

	public SetPath<ir.asta.tutorial.dl.entities.LibraryEntity, ir.asta.tutorial.dl.dao.meta.QLibrary> libraries = this
			.<ir.asta.tutorial.dl.entities.LibraryEntity, ir.asta.tutorial.dl.dao.meta.QLibrary> createSet(
					"libraries",
					ir.asta.tutorial.dl.entities.LibraryEntity.class,
					ir.asta.tutorial.dl.dao.meta.QLibrary.class,
					PathInits.DIRECT2);
	public SetPath<ir.asta.tutorial.dl.entities.AttachmentTypeEntity, ir.asta.tutorial.dl.dao.meta.QAttachmentType> validAttachmentTypes = this
			.<ir.asta.tutorial.dl.entities.AttachmentTypeEntity, ir.asta.tutorial.dl.dao.meta.QAttachmentType> createSet(
					"validAttachmentTypes",
					ir.asta.tutorial.dl.entities.AttachmentTypeEntity.class,
					ir.asta.tutorial.dl.dao.meta.QAttachmentType.class,
					PathInits.DIRECT2);
	public SetPath<ir.asta.tutorial.dl.entities.ContentEntity, ir.asta.tutorial.dl.dao.meta.QContent> contents = this
			.<ir.asta.tutorial.dl.entities.ContentEntity, ir.asta.tutorial.dl.dao.meta.QContent> createSet(
					"contents",
					ir.asta.tutorial.dl.entities.ContentEntity.class,
					ir.asta.tutorial.dl.dao.meta.QContent.class,
					PathInits.DIRECT2);

	public QContentType(String variable) {
		this(ContentTypeEntity.class, forVariable(variable), INITS);
	}

	public QContentType(Path<? extends ContentTypeEntity> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(
				path.getMetadata(), INITS));
	}

	public QContentType(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QContentType(PathMetadata metadata, PathInits inits) {
		this(ContentTypeEntity.class, metadata, inits);
	}

	public QContentType(Class<? extends ContentTypeEntity> type,
			PathMetadata metadata, PathInits inits) {
		super(type, metadata);

	}

}
