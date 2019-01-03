package ir.asta.training.contacts.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.wise.core.datamanagement.ActionResult;

import java.util.List;

@Path("/contact")
public interface ContactService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    List<ContactEntity> loadAll();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/find/{id}")
    ContactEntity load(@PathParam("id") Long id);

    @POST
    @Path("/save-or-update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ActionResult<Long> saveOrUpdate(ContactEntity entity);

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ActionResult<Long> delete(@PathParam("id") Long id);

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    List<ContactEntity> search(@Context HttpServletRequest httpServletRequest);

}
