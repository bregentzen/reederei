package de.hsos.swa.reederei.flottenmanagement.boundary;


import de.hsos.swa.reederei.flottenmanagement.boundary.dto.SchiffWebDTO;
import de.hsos.swa.reederei.flottenmanagement.boundary.dto.SchiffWebDTOId;
import de.hsos.swa.reederei.flottenmanagement.enitity.Schiff;
import de.hsos.swa.reederei.flottenmanagement.enitity.SchiffService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/schiffe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SchiffResource {

    @Inject
    SchiffService schiffService;

    @GET
    public Response createSchiff(SchiffWebDTO schiffWebDTO) {
        SchiffWebDTOId newSchiff = schiffService.createSchiff(schiffWebDTO);

        return Response.ok().status(Response.Status.CREATED).build();
    }
}
