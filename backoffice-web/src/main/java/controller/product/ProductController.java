package controller.product;

import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.catalog.product.port.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("productController")
@ViewScoped
public class ProductController implements Serializable {

    private ProductRepository productRepository;
    private List<Product> products;

    public ProductController(){
    }

    @Inject
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        this.products = productRepository.findAll();
    }

    public List<Product> getProducts() {
        return products;
    }

}
