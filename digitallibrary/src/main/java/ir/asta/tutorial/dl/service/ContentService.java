package ir.asta.tutorial.dl.service;

import ir.asta.tutorial.dl.dto.ContentReportItem;
import ir.asta.tutorial.dl.dto.ContentStatusChangeDto;
import ir.asta.wise.core.datamanagement.ActionResult;
import ir.asta.wise.core.datamanagement.EntityRestService;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/*PROTECTED REGION ID(ContentService Imports) ENABLED START*/
import ir.asta.tutorial.dl.entities.ConfidentialLevelEntity;

import java.util.List;
/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.ContentEntity;



@Path("/content/")
public interface ContentService
		extends
			EntityRestService<ContentEntity, java.lang.String> {
	/*PROTECTED REGION ID(ContentService Methods) ENABLED START*/

	@Path("/confidential-levels")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<ConfidentialLevelEntity> getConfidentialLevelOptions();

	@Path("/changeStatus")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	ActionResult<String> changeStatus(ContentStatusChangeDto dto);


	/*PROTECTED REGION END*/
}
