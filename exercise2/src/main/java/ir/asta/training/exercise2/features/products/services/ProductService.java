package ir.asta.training.exercise2.features.products.services;

import ir.asta.training.exercise2.entities.ProductEntity;
import ir.asta.training.exercise2.features.products.manager.ProductDTO;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/product")
public interface ProductService {

    @POST
    @Path("/save-or-update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ActionResult<Long> saveOrUpdate(ProductDTO entity);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/load/{id}")
    ProductDTO load(@PathParam("id") Long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    List<ProductDTO> list();

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ActionResult<Long> delete(@PathParam("id") Long id);

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductEntity> search(@Context HttpServletRequest httpServletRequest);

}
