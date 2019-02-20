package ir.asta.tutorial.dl.service;
import ir.asta.wise.core.datamanagement.EntityRestService;
import javax.ws.rs.Path;

/*PROTECTED REGION ID(AttachmentService Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.AttachmentEntity;

@Path("/attachment/")
public interface AttachmentService
		extends
			EntityRestService<AttachmentEntity, java.lang.String> {
	/*PROTECTED REGION ID(AttachmentService Methods) ENABLED START*/

	/*PROTECTED REGION END*/
}
