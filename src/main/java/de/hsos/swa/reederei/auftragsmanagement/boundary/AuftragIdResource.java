package de.hsos.swa.reederei.auftragsmanagement.boundary;

import de.hsos.swa.reederei.auftragsmanagement.boundary.dto.AuftragWebDTOId;
import de.hsos.swa.reederei.auftragsmanagement.entity.AuftragService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/auftraege")
public class AuftragIdResource {
//TODO: Implement me
    AuftragService auftragService;

    @GET
    @Path("/{id}")
    public Response getAuftrag(@PathParam("id") long id) {
        AuftragWebDTOId auftrag = auftragService.getAuftrag(id);

        if(auftrag == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(auftrag).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAuftrag(@PathParam("id") long id, AuftragWebDTOId auftrag) {
        AuftragWebDTOId updatedAuftrag = auftragService.updateAuftrag(id, auftrag);

        if(updatedAuftrag == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updatedAuftrag).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuftrag(@PathParam("id") long id) {
       boolean isDeleted =  auftragService.deleteAuftrag(id);

       if(isDeleted) {
           return Response.ok().build();
       } else {
           return Response.status(Response.Status.NOT_FOUND).build();
       }
    }
}
