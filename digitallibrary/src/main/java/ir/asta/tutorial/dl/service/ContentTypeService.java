package ir.asta.tutorial.dl.service;
import ir.asta.tutorial.dl.entities.AttachmentTypeEntity;
import ir.asta.wise.core.datamanagement.DataPage;
import ir.asta.wise.core.datamanagement.EntityRestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*PROTECTED REGION ID(ContentTypeService Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.ContentTypeEntity;

@Path("/contentType/")
public interface ContentTypeService
		extends
			EntityRestService<ContentTypeEntity, java.lang.Long> {
	/*PROTECTED REGION ID(ContentTypeService Methods) ENABLED START*/

	/*PROTECTED REGION END*/
}
