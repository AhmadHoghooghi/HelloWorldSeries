package ir.asta.tutorial.dl.service;
import ir.asta.wise.core.datamanagement.EntityRestService;
import javax.ws.rs.Path;

/*PROTECTED REGION ID(LibraryService Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.LibraryEntity;

@Path("/library/")
public interface LibraryService
		extends
			EntityRestService<LibraryEntity, java.lang.String> {
	/*PROTECTED REGION ID(LibraryService Methods) ENABLED START*/

	/*PROTECTED REGION END*/
}
