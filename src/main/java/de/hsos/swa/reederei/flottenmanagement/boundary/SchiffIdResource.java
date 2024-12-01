package de.hsos.swa.reederei.flottenmanagement.boundary;

import de.hsos.swa.reederei.flottenmanagement.boundary.dto.SchiffWebDTO;
import de.hsos.swa.reederei.flottenmanagement.enitity.Schiff;
import de.hsos.swa.reederei.flottenmanagement.enitity.SchiffService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/schiffe")
public class SchiffIdResource {
    @Inject
    SchiffService schiffService;

    @GET
    @Transactional
    @Path("/{id}")
    public Response getSchiffId(@PathParam("id") Long id) {
        SchiffWebDTO schiff = schiffService.getSchiff(id);

        return Response.ok(schiff).build();
    }
}
