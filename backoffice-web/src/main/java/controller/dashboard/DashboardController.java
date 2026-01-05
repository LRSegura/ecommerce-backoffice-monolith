package controller.dashboard;

import com.code2ever.backoffice.domain.catalog.brand.port.BrandRepository;
import com.code2ever.backoffice.domain.catalog.category.port.CategoryRepository;
import com.code2ever.backoffice.domain.catalog.product.port.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class DashboardController implements Serializable {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    private int productCount;
    private int brandCount;
    private int categoryCount;

    @Inject
    public DashboardController(ProductRepository productRepository,
                               BrandRepository brandRepository,
                               CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void init() {
        this.productCount = productRepository.findAll().size();
        this.brandCount = brandRepository.findAll().size();
        this.categoryCount = categoryRepository.findAll().size();
    }

    public int getProductCount() { return productCount; }
    public int getBrandCount() { return brandCount; }
    public int getCategoryCount() { return categoryCount; }
}