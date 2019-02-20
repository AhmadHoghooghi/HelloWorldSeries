package ir.asta.training.exercise2.features.categories.services;

import ir.asta.training.exercise2.entities.CategoryEntity;
import ir.asta.training.exercise2.features.categories.manager.CategoryDTO;
import ir.asta.training.exercise2.utils.dtos.CategoryProductRel;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
public interface CategoryService {

    @POST
    @Path("/save-or-update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ActionResult<Long> saveOrUpdate(CategoryDTO entity);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/load/{id}")
    CategoryDTO load(@PathParam("id") Long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    List<CategoryDTO> list();

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ActionResult<Long> delete(@PathParam("id") Long id);

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryEntity> search(@Context HttpServletRequest httpServletRequest);

    @POST
    @Path("/set-category-product")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ActionResult<CategoryProductRel> setCategoryProductRel(CategoryProductRel rel);

}
