package controller.product;

import com.code2ever.backoffice.application.product.dto.CreatePriceCommand;
import com.code2ever.backoffice.application.product.usecase.ManageProductPriceUseCase;
import com.code2ever.backoffice.domain.catalog.product.model.PriceType;
import com.code2ever.backoffice.domain.catalog.product.model.ProductPrice;
import com.code2ever.backoffice.domain.catalog.product.port.ProductPriceRepository;
import controller.common.BaseController;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Named
@ViewScoped
public class ProductPriceController extends BaseController implements Serializable {

    private ProductPriceRepository priceRepository;
    private ManageProductPriceUseCase managePriceUseCase;

    public ProductPriceController() {

    }

    @Inject
    public ProductPriceController(ProductPriceRepository priceRepository, ManageProductPriceUseCase managePriceUseCase) {
        this.priceRepository = priceRepository;
        this.managePriceUseCase = managePriceUseCase;
    }


    // Estado del formulario
    private Long currentProductId;
    private List<ProductPrice> priceHistory;

    // Campos nuevo precio
    private BigDecimal newAmount;
    private PriceType newType = PriceType.LIST;
    private LocalDateTime newValidFrom;
    private LocalDateTime newValidTo;

    // Llamado cuando se abre el diálogo de producto o se selecciona la pestaña de precios
    public void loadPrices(Long productId) {
        this.currentProductId = productId;
        refreshList();
        resetForm();
    }

    public void refreshList() {
        if(currentProductId != null) {
            this.priceHistory = priceRepository.findByProductAndType(currentProductId, PriceType.LIST);
            // Podrías agregar un filtro en UI para ver SALE o COST
        }
    }

    public void resetForm() {
        this.newAmount = null;
        this.newType = PriceType.LIST;
        this.newValidFrom = LocalDateTime.now();
        this.newValidTo = null;
    }

    public void addPrice() {
        try {
            CreatePriceCommand cmd = new CreatePriceCommand(
                    currentProductId, newAmount, "USD", newType, newValidFrom, newValidTo
            );
            managePriceUseCase.createPrice(cmd);
            refreshList();
            resetForm();
            addInfoMessage("Price added successfully");
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }
    }

    // Getters y Setters para todos los campos...
    public List<ProductPrice> getPriceHistory() { return priceHistory; }
    public BigDecimal getNewAmount() { return newAmount; }
    public void setNewAmount(BigDecimal newAmount) { this.newAmount = newAmount; }
    // ... agrega el resto
    public PriceType[] getPriceTypes() { return PriceType.values(); }
}