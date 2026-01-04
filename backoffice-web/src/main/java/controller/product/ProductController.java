package controller.product;

import com.code2ever.backoffice.application.common.exception.BusinessRuleException;
import com.code2ever.backoffice.application.product.dto.CreateProductCommand;
import com.code2ever.backoffice.application.product.dto.UpdateProductCommand;
import com.code2ever.backoffice.application.product.usecase.CreateProductUseCase;
import com.code2ever.backoffice.application.product.usecase.DeleteProductUseCase;
import com.code2ever.backoffice.application.product.usecase.UpdateProductUseCase;
import com.code2ever.backoffice.domain.catalog.brand.model.Brand;
import com.code2ever.backoffice.domain.catalog.brand.port.BrandRepository;
import com.code2ever.backoffice.domain.catalog.category.model.Category;
import com.code2ever.backoffice.domain.catalog.category.port.CategoryRepository;
import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.catalog.product.port.ProductRepository;
import controller.common.BaseController;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named("productController")
@ViewScoped
public class ProductController extends BaseController implements Serializable {

    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;

    private CreateProductUseCase createProductUseCase;
    private UpdateProductUseCase updateProductUseCase;
    private DeleteProductUseCase deleteProductUseCase;

    private List<Product> products;
    private List<Brand> brands;
    private List<Category> categories;

    private Product currentProduct;

    // Variables temporales para el formulario (ya que el entity tiene objetos completos, pero el formulario envía IDs)
    private Long selectedBrandId;
    private Long selectedCategoryId;
    private BigDecimal initialPrice;

    public ProductController() {
    }

    @Inject
    public ProductController(ProductRepository productRepository,
                             BrandRepository brandRepository,
                             CategoryRepository categoryRepository,
                             CreateProductUseCase createProductUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             DeleteProductUseCase deleteProductUseCase) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @PostConstruct
    public void init() {
        loadData();
    }

    public void loadData() {
        this.products = productRepository.findAll();
        this.brands = brandRepository.findAll();
        this.categories = categoryRepository.findAll();
    }

    public void prepareNewProduct() {
        this.currentProduct = new Product();
        this.selectedBrandId = null;
        this.selectedCategoryId = null;
        this.initialPrice = BigDecimal.ZERO;
    }

    public void saveProduct() {
        try {
            if (currentProduct.getId() == null) {
                CreateProductCommand command = new CreateProductCommand(
                        currentProduct.getSku(),
                        currentProduct.getName(),
                        currentProduct.getDescription(),
                        selectedBrandId,
                        selectedCategoryId,
                        initialPrice
                );
                createProductUseCase.execute(command);
                addInfoMessage("Product created successfully");
            }
            loadData();
            executeScript("PF('manageProductDialog').hide()");
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }
    }

    public void onEditProduct() {
        try {
            UpdateProductCommand command = new UpdateProductCommand(
                    currentProduct.getName(),
                    currentProduct.getDescription(),
                    selectedBrandId,
                    selectedCategoryId
            );
            updateProductUseCase.execute(currentProduct.getId(), command);
            loadData();
            addInfoMessage("Product updated successfully");
            executeScript("PF('manageProductDialog').hide()");
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }
    }

    public void onDeleteProduct() {
        try {
            deleteProductUseCase.execute(currentProduct.getId());
            loadData();
            addInfoMessage("Product deleted successfully");
        } catch (Exception e) {
            if (e instanceof BusinessRuleException){
                addErrorMessage(e.getMessage());
                return;
            }
            addErrorMessage("Error deleting product");
        }
        executeScript("PF('deleteProductDialog').hide()");
    }

    public void selectProductForEdit(Product product) {
        this.currentProduct = product;
        this.selectedBrandId = product.getBrand() != null ? product.getBrand().getId() : null;
        this.selectedCategoryId = product.getCategory() != null ? product.getCategory().getId() : null;
    }

    public List<Product> getProducts() { return products; }
    public List<Brand> getBrands() { return brands; }
    public List<Category> getCategories() { return categories; }
    public Product getCurrentProduct() { return currentProduct; }
    public void setCurrentProduct(Product currentProduct) { this.currentProduct = currentProduct; }
    public Long getSelectedBrandId() { return selectedBrandId; }
    public void setSelectedBrandId(Long selectedBrandId) { this.selectedBrandId = selectedBrandId; }
    public Long getSelectedCategoryId() { return selectedCategoryId; }
    public void setSelectedCategoryId(Long selectedCategoryId) { this.selectedCategoryId = selectedCategoryId; }
    public BigDecimal getInitialPrice() { return initialPrice; }
    public void setInitialPrice(BigDecimal initialPrice) { this.initialPrice = initialPrice; }
}