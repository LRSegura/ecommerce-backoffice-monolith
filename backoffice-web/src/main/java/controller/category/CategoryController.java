package controller.category;

import com.code2ever.backoffice.application.category.dto.CategoryCreateRequest;
import com.code2ever.backoffice.application.category.dto.CategoryUpdateRequest;
import com.code2ever.backoffice.application.category.usecase.CreateCategoryUseCase;
import com.code2ever.backoffice.application.category.usecase.DeleteCategoryUseCase;
import com.code2ever.backoffice.application.category.usecase.UpdateCategoryUseCase;
import com.code2ever.backoffice.application.common.exception.BusinessRuleException;
import com.code2ever.backoffice.domain.catalog.category.model.Category;
import com.code2ever.backoffice.domain.catalog.category.port.CategoryRepository;
import controller.common.BaseController;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CategoryController extends BaseController implements Serializable {

    private CategoryRepository categoryRepository;
    private CreateCategoryUseCase createCategoryUseCase;
    private UpdateCategoryUseCase updateCategoryUseCase;
    private DeleteCategoryUseCase deleteCategoryUseCase;

    private List<Category> categories;
    private Category currentCategory;

    public CategoryController() {
    }

    @Inject
    public CategoryController(CategoryRepository categoryRepository,
                              CreateCategoryUseCase createCategoryUseCase,
                              UpdateCategoryUseCase updateCategoryUseCase,
                              DeleteCategoryUseCase deleteCategoryUseCase) {
        this.categoryRepository = categoryRepository;
        this.createCategoryUseCase = createCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
    }

    @PostConstruct
    public void init() {
        loadCategories();
    }

    public void loadCategories() {
        this.categories = categoryRepository.findAll();
    }

    public void prepareNewCategory() {
        this.currentCategory = new Category();
        this.currentCategory.setActive(true);
    }

    public void saveCategory() {
        try {
            if (currentCategory.getId() == null) {
                CategoryCreateRequest req = new CategoryCreateRequest(currentCategory.getName(), currentCategory.getActive());
                createCategoryUseCase.execute(req);
                addInfoMessage("Category created successfully");
            }
            loadCategories();
            executeScript("PF('manageCategoryDialog').hide()");
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }
    }

    public void onEditCategory() {
        try {
            CategoryUpdateRequest req = new CategoryUpdateRequest(currentCategory.getName(), currentCategory.getActive());
            updateCategoryUseCase.execute(currentCategory.getId(), req);
            loadCategories();
            addInfoMessage("Category updated successfully.");
            executeScript("PF('manageCategoryDialog').hide()");
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }
    }

    public void onDeleteCategory() {
        try {
            deleteCategoryUseCase.execute(currentCategory.getId());
            loadCategories();
            addInfoMessage("Category deleted successfully.");
        } catch (Exception e) {
            if (e instanceof BusinessRuleException){
                addErrorMessage(e.getMessage());
                return;
            }
            addErrorMessage("Error deleting category.");
        }
        executeScript("PF('manageCategoryDialog').hide()");
    }


    public List<Category> getCategories() { return categories; }
    public Category getCurrentCategory() { return currentCategory; }
    public void setCurrentCategory(Category currentCategory) { this.currentCategory = currentCategory; }
}