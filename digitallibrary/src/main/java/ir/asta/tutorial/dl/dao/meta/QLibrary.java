package ir.asta.tutorial.dl.dao.meta;
import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import ir.asta.tutorial.dl.entities.*;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QLibrary extends PathBuilder<LibraryEntity> {
	private static final long serialVersionUID = -785829409L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public final StringPath id = createString("id");
	public final StringPath title = createString("title");
	public final StringPath code = createString("code");
	public final StringPath createdBy = createString("createdBy");
	public final StringPath lastModifiedBy = createString("lastModifiedBy");
	public final BooleanPath deleted = createBoolean("deleted");

	public SetPath<ir.asta.tutorial.dl.entities.ContentEntity, ir.asta.tutorial.dl.dao.meta.QContent> mediaContents = this
			.<ir.asta.tutorial.dl.entities.ContentEntity, ir.asta.tutorial.dl.dao.meta.QContent> createSet(
					"mediaContents",
					ir.asta.tutorial.dl.entities.ContentEntity.class,
					ir.asta.tutorial.dl.dao.meta.QContent.class,
					PathInits.DIRECT2);

	public final ir.asta.tutorial.dl.dao.meta.QContentType contentType;

	public QLibrary(String variable) {
		this(LibraryEntity.class, forVariable(variable), INITS);
	}

	public QLibrary(Path<? extends LibraryEntity> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(
				path.getMetadata(), INITS));
	}

	public QLibrary(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QLibrary(PathMetadata metadata, PathInits inits) {
		this(LibraryEntity.class, metadata, inits);
	}

	public QLibrary(Class<? extends LibraryEntity> type, PathMetadata metadata,
			PathInits inits) {
		super(type, metadata);
		this.contentType = inits.isInitialized("contentType")
				? new ir.asta.tutorial.dl.dao.meta.QContentType(
						forProperty("contentType")) : null;

	}

}
