package ir.asta.tutorial.dl.dao.meta;
import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import ir.asta.tutorial.dl.entities.*;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QConfidentialLevel extends PathBuilder<ConfidentialLevelEntity> {
	private static final long serialVersionUID = -785829409L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public final NumberPath<Long> id = createNumber("id", Long.class);
	public final StringPath code = createString("code");
	public final StringPath title = createString("title");
	public final NumberPath<Long> index = createNumber("index", Long.class);
	public final BooleanPath isDefault = createBoolean("isDefault");
	public final StringPath createdBy = createString("createdBy");
	public final StringPath lastModifiedBy = createString("lastModifiedBy");
	public final BooleanPath deleted = createBoolean("deleted");

	public QConfidentialLevel(String variable) {
		this(ConfidentialLevelEntity.class, forVariable(variable), INITS);
	}

	public QConfidentialLevel(Path<? extends ConfidentialLevelEntity> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(
				path.getMetadata(), INITS));
	}

	public QConfidentialLevel(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QConfidentialLevel(PathMetadata metadata, PathInits inits) {
		this(ConfidentialLevelEntity.class, metadata, inits);
	}

	public QConfidentialLevel(Class<? extends ConfidentialLevelEntity> type,
			PathMetadata metadata, PathInits inits) {
		super(type, metadata);

	}

}
