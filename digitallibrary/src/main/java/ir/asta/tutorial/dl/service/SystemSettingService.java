package ir.asta.tutorial.dl.service;
import ir.asta.wise.core.datamanagement.DataPage;
import ir.asta.wise.core.datamanagement.EntityRestService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/*PROTECTED REGION ID(SystemSettingService Imports) ENABLED START*/

/*PROTECTED REGION END*/

import ir.asta.tutorial.dl.entities.SystemSettingEntity;

@Path("/systemSetting/")
public interface SystemSettingService
		extends
			EntityRestService<SystemSettingEntity, java.lang.String> {
	/*PROTECTED REGION ID(SystemSettingService Methods) ENABLED START*/

	@Path("/load-single-instance")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	SystemSettingEntity loadSingleInstance();
	/*PROTECTED REGION END*/
}
