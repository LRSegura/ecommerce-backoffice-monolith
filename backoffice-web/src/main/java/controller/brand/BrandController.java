package controller.brand;

import com.code2ever.backoffice.application.brand.dto.BrandCreateRequest;
import com.code2ever.backoffice.application.brand.dto.BrandUpdateRequest;
import com.code2ever.backoffice.application.brand.usecase.CreateBrandUseCase;
import com.code2ever.backoffice.application.brand.usecase.DeleteBrandUseCase;
import com.code2ever.backoffice.application.brand.usecase.UpdateBrandUseCase;
import com.code2ever.backoffice.application.common.exception.BusinessRuleException;
import com.code2ever.backoffice.domain.catalog.brand.model.Brand;
import com.code2ever.backoffice.domain.catalog.brand.port.BrandRepository;
import controller.common.BaseController;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BrandController extends BaseController implements Serializable {

    private BrandRepository brandRepository;
    private CreateBrandUseCase createBrandUseCase;
    private UpdateBrandUseCase updateBrandUseCase;
    private DeleteBrandUseCase deleteBrandUseCase;
    private List<Brand> brands;

    private Brand currentBrand;

    public BrandController(){
    }

    @Inject
    public BrandController(BrandRepository brandRepository,
                           CreateBrandUseCase createBrandUseCase,
                           UpdateBrandUseCase updateBrandUseCase,
                           DeleteBrandUseCase deleteBrandUseCase){
        this.brandRepository = brandRepository;
        this.createBrandUseCase = createBrandUseCase;
        this.updateBrandUseCase = updateBrandUseCase;
        this.deleteBrandUseCase = deleteBrandUseCase;
    }


    @PostConstruct
    public void init() {
        loadBrands();
    }

    public void loadBrands() {
        this.brands = brandRepository.findAll();
    }

    public void prepareNewBrand() {
        this.currentBrand = new Brand();
        this.currentBrand.setActive(true);
    }

    public void saveBrand() {
        try {
            if (currentBrand.getId() == null) {
                BrandCreateRequest req = new BrandCreateRequest(currentBrand.getName(), currentBrand.getActive());
                createBrandUseCase.execute(req);
                loadBrands();
                addInfoMessage("Brand created successfully");
            }
            executeScript("PF('manageBrandDialog').hide()");
        } catch (Exception e) {
            addErrorMessage("Error creating brand. ");
        }
    }

    public void onEditBrand() {
        try {
            BrandUpdateRequest req = new BrandUpdateRequest(currentBrand.getName(), currentBrand.getActive());
            updateBrandUseCase.execute(currentBrand.getId(), req);
            loadBrands();
            addInfoMessage("Brand updated successfully.");
            executeScript("PF('manageBrandDialog').hide()");
        } catch (Exception e) {
            addErrorMessage("Error updating brand. ");
        }
    }

    public void onDeleteBrand() {
        try {
            deleteBrandUseCase.execute(currentBrand.getId());
            loadBrands();
            addInfoMessage("Brand deleted successfully.");
        } catch (Exception e) {
            if (e instanceof BusinessRuleException){
                addErrorMessage(e.getMessage());
                return;
            }
            addErrorMessage("Error deleting brand. ");
        }
        executeScript("PF('manageBrandDialog').hide()");
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public Brand getCurrentBrand() {
        return currentBrand;
    }

    public void setCurrentBrand(Brand currentBrand) {
        this.currentBrand = currentBrand;
    }


}
