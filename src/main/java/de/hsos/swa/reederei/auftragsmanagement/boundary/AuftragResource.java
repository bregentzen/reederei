package de.hsos.swa.reederei.auftragsmanagement.boundary;

import de.hsos.swa.reederei.auftragsmanagement.boundary.dto.AuftragWebDTO;
import de.hsos.swa.reederei.auftragsmanagement.boundary.dto.AuftragWebDTOId;
import de.hsos.swa.reederei.auftragsmanagement.entity.AuftragService;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.net.URI;
import java.util.*;

@Path("/auftraege")
public class AuftragResource {

    @Inject
    AuftragService auftragService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Operation(summary = "Get all orders", description = "Retrieve all orders with optional filtering and pagination")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200", description = "Successfully retrieved orders",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AuftragWebDTO.class))
                    )
            }
    )
    public Response getAllAuftraege(@QueryParam("filter[description]") String descriptionFilter,
                                    @QueryParam("page[number]") @DefaultValue("1") int pageNumber,
                                    @QueryParam("page[size]") @DefaultValue("10") int pageSize) {
        List<AuftragWebDTOId> auftraege = auftragService.getAllAuftraegeDTO().stream().toList();

        List<AuftragWebDTOId> filteredAuftraege = new ArrayList<>(auftraege);
        if (descriptionFilter != null) {
            filteredAuftraege.removeIf(auftrag -> !auftrag.getBeschreibung().contains(descriptionFilter));
        }

        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(start + pageSize, filteredAuftraege.size());
        List<AuftragWebDTOId> pagedAuftraege = filteredAuftraege.subList(start, end);

        String baseUrl = "http://localhost:8080/auftraege";
        String first = baseUrl + "?page[number]=1&page[size]=" + pageSize;
        String prev = pageNumber > 1 ? baseUrl + "?page[number]=" + (pageNumber - 1) + "&page[size]=" + pageSize : null;
        String next = end < filteredAuftraege.size() ? baseUrl + "?page[number]=" + (pageNumber + 1) + "&page[size]=" + pageSize : null;
        String last = baseUrl + "?page[number]=" + ((filteredAuftraege.size() + pageSize - 1) / pageSize) + "&page[size]=" + pageSize;

        Map<String, Object> response = new HashMap<>();
        response.put("data", pagedAuftraege);
        Map<String, String> links = new HashMap<>();
        links.put("first", first);
        links.put("prev", prev);
        links.put("next", next);
        links.put("last", last);
        response.put("links", links);

        return Response.ok(response).build();
    }

    @POST
    @Transactional
    @Operation(summary = "Create a new order", description = "Create a new order")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "201", description = "Successfully created a new order",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AuftragWebDTO.class))
                    )
            }
    )
    public Response createAuftrag(AuftragWebDTO auftragWebDTO, @Context UriInfo uriInfo) {
        AuftragWebDTOId newAuftrag = auftragService.createAuftrag(auftragWebDTO);

        if (newAuftrag == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Unable to create Auftrag. Please try again later.")
                    .build();
        }

        //TODO: Eingangsdatum als String oder Date?
        String eingangsdatumStr = newAuftrag.getEingangsdatum() != null ? newAuftrag.getEingangsdatum().toString() : "heute";


        String selfLink = uriInfo.getAbsolutePathBuilder().path(Long.toString(newAuftrag.getId())).build().toString();
        JsonObject responseJson = Json.createObjectBuilder()
                .add("data", Json.createObjectBuilder()
                        .add("type", "auftraege")
                        .add("id", newAuftrag.getId())
                        .add("attributes", Json.createObjectBuilder()
                                .add("beschreibung", newAuftrag.getBeschreibung())
                                .add("eingangsdatum", eingangsdatumStr)
                                .add("schiffURL", newAuftrag.getSchiffURL()))
                        .add("links", Json.createObjectBuilder()
                                .add("self", selfLink)))
                .build();

        return Response.created(URI.create(selfLink)).entity(responseJson).build();
    }
}