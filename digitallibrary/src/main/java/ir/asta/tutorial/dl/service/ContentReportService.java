package ir.asta.tutorial.dl.service;

import ir.asta.tutorial.dl.dto.ContentReportItem;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/contentReport/")
public interface ContentReportService {
    @Path("/distReport")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ContentReportItem> distributionReport(@Context HttpServletRequest request);
}
