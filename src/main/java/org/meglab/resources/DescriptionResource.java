package org.meglab.resources;

import org.meglab.api.Description;
import org.meglab.db.DescriptionDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/descriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DescriptionResource {

    private final DescriptionDAO descriptionDAO;

    public DescriptionResource(DescriptionDAO descriptionDAO) {
        this.descriptionDAO = descriptionDAO;
    }

    @GET
    public List<Description> getClassDescriptions() {
        return descriptionDAO.getClassDescriptions();
    }

    @GET
    @Path("/fetch")
    public Response getDescriptionByTerm(@QueryParam("term") String term){
        Optional<Description> optionalDescription = descriptionDAO.getDescriptionByTerm(term);
        if (optionalDescription.isPresent()){
            return Response.ok(optionalDescription.get()).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
