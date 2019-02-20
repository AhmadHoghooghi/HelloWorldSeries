package ir.asta.training.events.services;

import ir.asta.training.events.entities.EventEntity;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.List;

@Path("/event")
public interface EventService {

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    List<EventEntity> findAll();

    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    EventEntity find(@PathParam("id")Long id);

    @POST
    @Path("/save-or-update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ActionResult<Long> saveOrUpdate(EventEntity entity);

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    List<EventEntity> search(@Context HttpServletRequest httpServletRequest) throws ParseException;


    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ActionResult<Long> delete(@PathParam("id") Long id);
}
