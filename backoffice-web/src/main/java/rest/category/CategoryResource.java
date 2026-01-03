package rest.category;

import com.code2ever.backoffice.application.category.dto.CategoryCreateRequest;
import com.code2ever.backoffice.application.category.dto.CategoryResponse;
import com.code2ever.backoffice.application.category.dto.CategoryUpdateRequest;
import com.code2ever.backoffice.application.category.usecase.CreateCategoryUseCase;
import com.code2ever.backoffice.application.category.usecase.UpdateCategoryUseCase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@Path("/categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CategoryResource {

    private CreateCategoryUseCase createCategory;
    private UpdateCategoryUseCase updateCategory;

    public CategoryResource(){
    }

    @Inject
    public CategoryResource(CreateCategoryUseCase createCategory,
                            UpdateCategoryUseCase updateCategory) {
        this.createCategory = createCategory;
        this.updateCategory = updateCategory;
    }

    @POST
    public Response create(CategoryCreateRequest request, @Context UriInfo uriInfo) {
        CategoryResponse created = createCategory.execute(request);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(created.id()))
                .build();

        return Response.created(location).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CategoryUpdateRequest request) {
        CategoryResponse updated = updateCategory.execute(id, request);
        return Response.ok(updated).build();
    }
}
