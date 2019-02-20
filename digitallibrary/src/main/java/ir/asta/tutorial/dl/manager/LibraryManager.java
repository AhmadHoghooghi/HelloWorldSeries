package ir.asta.tutorial.dl.manager;

import javax.inject.Inject;
import javax.inject.Named;

/*PROTECTED REGION ID(LibraryManager Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.LibraryEntity;
import ir.asta.tutorial.dl.dao.LibraryDao;

@Named("libraryManager")
public class LibraryManager
		extends
			ir.asta.wise.core.crud.AbstractCrudManager<LibraryEntity, java.lang.String> {

	/*PROTECTED REGION ID(LibraryManager Attributes) ENABLED START*/

	/*PROTECTED REGION END*/

	@Inject
	public LibraryManager(LibraryDao dao) {
		super.setDao(dao);
	}

	@SuppressWarnings("unused")
	private LibraryDao getMyDao() {
		return (LibraryDao) super.getDao();
	}

	/*PROTECTED REGION ID(LibraryManager Methods) ENABLED START*/

	/*PROTECTED REGION END*/
}
