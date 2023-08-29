package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestPath;

import ch.zli.m223.model.Tags;
import ch.zli.m223.service.TagsService;


@Path("/tags")
@Tag(name = "Tags", description = "Handling of tags")
public class TagsController {

    @Inject
    TagsService tagsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Tags.", description = "Returns a list of all tags.")
    public List<Tags> index() {
        return tagsService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new tag.", description = "Creates a new tag and returns the newly added tag.")
    public Tags create(Tags tag) {
       return tagsService.createTag(tag);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a tag.", description = "Delete a tag.")
    public void delete(@RestPath long id) {
       tagsService.deleteTag(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a tag.", description = "Update a tag and returns the updated tag.")
    public Tags update(Tags tag) {
       return tagsService.updateTag(tag);
    }
}
