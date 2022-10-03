package org.meglab.resources;

import org.meglab.api.Sequence;
import org.meglab.db.SequenceDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/sequences")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SequenceResource {

    private final SequenceDAO sequenceDAO;

    public SequenceResource(SequenceDAO sequenceDAO) {
        this.sequenceDAO = sequenceDAO;
    }

    @GET
    @Path("/hierarchy")
    public List<Sequence> getClassDescriptions() {
        return sequenceDAO.getHierarchy();
    }

    @GET
    @Path("/mechanisms")
    public List<Sequence> getMechanismsForClass(@QueryParam("class") String theClass) {
        return sequenceDAO.getMechanisms(theClass);
    }

    @GET
    @Path("/groups")
    public List<Sequence> getGroupsOfMechanismAndClass(@QueryParam("class") String theClass,
                                                       @QueryParam("mechanism") String mechanism) {
        return sequenceDAO.getGroupsByClassAndMechanism(theClass, mechanism);
    }

    @GET
    @Path("/fasta")
    public List<Sequence> getFastaOfMechanismAndClassAndGroup(@QueryParam("class") String theClass,
                                                              @QueryParam("mechanism") String mechanism,
                                                              @QueryParam("group") String group) {
        return sequenceDAO.getSequencesByClassAndMechanismAndGroup(theClass, mechanism, group);
    }

    @GET
    @Path("/search-data")
    public List<Sequence> getSearchData() {
        return sequenceDAO.getSearchData();
    }

    @GET
    @Path("/byclass")
    public List<Sequence> getSequencesByClass(@QueryParam("class") String theClass) {
        return sequenceDAO.getSequencesByClass(theClass);
    }

    @GET
    @Path("/bymech")
    public List<Sequence> getSequencesByMech(@QueryParam("class") String theClass,
                                             @QueryParam("mechanism") String mechanism) {
        return sequenceDAO.getSequencesByMech(theClass, mechanism);
    }

}
