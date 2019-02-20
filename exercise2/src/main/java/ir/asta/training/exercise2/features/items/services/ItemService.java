package ir.asta.training.exercise2.features.items.services;

import ir.asta.training.exercise2.entities.ItemEntity;
import ir.asta.training.exercise2.features.items.manager.ItemDTO;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.List;

@Path("/item")
public interface ItemService {
    /**
     * Searches items between two dates:
     * `purchaseTimeFrom` and `purchaseTimeTo` in UTCz format. for example: 2018-12-01T00:00:00.000Z
     *
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @Path("/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ItemDTO> search(@Context HttpServletRequest httpServletRequest) throws ParseException;

    @Path("/total-sale-by-category/{category}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ItemDTO> totalSaleByCategory(@PathParam("category")String category);
}
