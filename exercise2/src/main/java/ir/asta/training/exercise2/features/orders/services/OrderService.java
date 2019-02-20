package ir.asta.training.exercise2.features.orders.services;

import ir.asta.training.exercise2.entities.OrderEntity;
import ir.asta.training.exercise2.features.orders.manager.OrderDTO;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/order")
public interface OrderService {
    @POST
    @Path("/save-or-update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ActionResult<Long> saveOrUpdate(OrderDTO entity);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/load/{id}")
    OrderDTO load(@PathParam("id") Long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    List<OrderDTO> list();

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ActionResult<Long> delete(@PathParam("id") Long id);

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    List<OrderEntity> search(@Context HttpServletRequest httpServletRequest);

}
