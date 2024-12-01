package de.hsos.swa.reederei.flottenmanagement.boundary;

import de.hsos.swa.reederei.flottenmanagement.boundary.dto.SchiffWebDTO;
import de.hsos.swa.reederei.flottenmanagement.boundary.dto.SchiffWebDTOId;
import de.hsos.swa.reederei.flottenmanagement.enitity.SchiffService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.net.URI;

@Path("/schiffe")
public class SchiffResource {

    @Inject
    SchiffService schiffService;

    @POST
    @Transactional
    @Operation(summary = "Create a new ship", description = "Create a new ship")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "201", description = "Successfully created a new ship",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SchiffWebDTO.class))
                    )
            }
    )
    public Response createSchiff(SchiffWebDTO schiffWebDTO, @Context UriInfo uriInfo) {
        SchiffWebDTOId newSchiff = schiffService.createSchiff(schiffWebDTO);

        if (newSchiff == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Unable to create Schiff. Please try again later.")
                    .build();
        }

        String selfLink = uriInfo.getAbsolutePathBuilder().path(Long.toString(newSchiff.getId())).build().toString();
        JsonObject responseJson = Json.createObjectBuilder()
                .add("data", Json.createObjectBuilder()
                        .add("type", "schiffe")
                        .add("id", newSchiff.getId())
                        .add("attributes", Json.createObjectBuilder()
                                .add("name", newSchiff.getName())
                                .add("gebucht", newSchiff.isGebucht()))
                        .add("links", Json.createObjectBuilder()
                                .add("self", selfLink)))
                .build();

        return Response.created(URI.create(selfLink)).entity(responseJson).build();
    }
}