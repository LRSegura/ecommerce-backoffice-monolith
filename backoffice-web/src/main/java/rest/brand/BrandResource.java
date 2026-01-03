package rest.brand;

import com.code2ever.backoffice.application.brand.dto.BrandCreateRequest;
import com.code2ever.backoffice.application.brand.dto.BrandResponse;
import com.code2ever.backoffice.application.brand.dto.BrandUpdateRequest;
import com.code2ever.backoffice.application.brand.usecase.CreateBrandUseCase;
import com.code2ever.backoffice.application.brand.usecase.UpdateBrandUseCase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@Path("/brand")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class BrandResource {

    private CreateBrandUseCase createBrand;
    private UpdateBrandUseCase updateBrand;

    public BrandResource(){
    }

    @Inject
    public BrandResource(CreateBrandUseCase createBrand,
                         UpdateBrandUseCase updateBrand) {
        this.createBrand = createBrand;
        this.updateBrand = updateBrand;
    }

    @POST
    public Response create(BrandCreateRequest request, @Context UriInfo uriInfo) {
        BrandResponse created = createBrand.execute(request);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(created.id()))
                .build();

        return Response.created(location).entity(created).build();
    }


    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, BrandUpdateRequest request) {
        BrandResponse updated = updateBrand.execute(id, request);
        return Response.ok(updated).build();
    }
}
