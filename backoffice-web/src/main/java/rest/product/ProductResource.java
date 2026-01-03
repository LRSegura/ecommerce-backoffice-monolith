package rest.product;

import com.code2ever.backoffice.application.product.dto.CreateProductCommand;
import com.code2ever.backoffice.application.product.dto.ProductView;
import com.code2ever.backoffice.application.product.usecase.CreateProductUseCase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import rest.product.dto.CreateProductRequest;
import rest.product.dto.ProductResponse;
import rest.product.mapper.ProductRestMapper;

import java.net.URI;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ProductResource {

    private CreateProductUseCase createProductService;

    public ProductResource() {

    }

    @Inject
    public ProductResource(CreateProductUseCase createProductService) {
        this.createProductService = createProductService;
    }

    @POST
    public Response create(@Valid CreateProductRequest req, @Context UriInfo uriInfo) {
        CreateProductCommand command = ProductRestMapper.toCommand(req);
        ProductView created = createProductService.execute(command);
        ProductResponse body = ProductRestMapper.toResponse(created);
        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(body.id()))
                .build();
        return Response.created(location).entity(body).build();
    }

//    @GET
//    @Path("/{id}")
//    public ProductResponse findById(@PathParam("id") Long id) {
//        ProductView productView = productService.findById(id);
//        return ProductRestMapper.toResponse(productView);
//    }
}
