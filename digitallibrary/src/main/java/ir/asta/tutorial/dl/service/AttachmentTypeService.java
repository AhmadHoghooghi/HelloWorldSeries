package ir.asta.tutorial.dl.service;
import ir.asta.tutorial.dl.entities.ContentTypeEntity;
import ir.asta.wise.core.datamanagement.DataPage;
import ir.asta.wise.core.datamanagement.EntityRestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*PROTECTED REGION ID(AttachmentTypeService Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.AttachmentTypeEntity;

@Path("/attachmentType/")
public interface AttachmentTypeService
		extends
			EntityRestService<AttachmentTypeEntity, java.lang.Long> {
	/*PROTECTED REGION ID(AttachmentTypeService Methods) ENABLED START*/
	@Path("/validAttachmentTypeOptionsForContent/{pk}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public DataPage<AttachmentTypeEntity> validContentTypeOptionsForContentId(@PathParam("pk") String pk);
	/*PROTECTED REGION END*/
}
      //https://localhost:3456/dl/rest/attachmentType/validAttachmentTypeOptionsForContent/4028eea268ae89990168ae8d83170000