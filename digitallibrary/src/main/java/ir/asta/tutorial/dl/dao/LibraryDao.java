package ir.asta.tutorial.dl.dao;

import javax.inject.Named;
import org.springframework.stereotype.Repository;

/*PROTECTED REGION ID(LibraryDAO Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.LibraryEntity;

@Repository
@Named("libraryDao")
public class LibraryDao
		extends
			ir.asta.wise.core.data.jpa.AbstractJpaDao<LibraryEntity, java.lang.String> {

	/*PROTECTED REGION ID(LibraryDAO Attributes) ENABLED START*/

	/*PROTECTED REGION END*/

	/*PROTECTED REGION ID(LibraryDAO Methods) ENABLED START*/

	/*PROTECTED REGION END*/
}
