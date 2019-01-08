package ir.asta.training.exercise2.features.products.services.impl;

import ir.asta.training.exercise2.entities.ProductEntity;
import ir.asta.training.exercise2.features.products.manager.ProductDTO;
import ir.asta.training.exercise2.features.products.manager.ProductManager;
import ir.asta.training.exercise2.features.products.services.ProductService;
import ir.asta.training.exercise2.utils.UtilMethods;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("productService")
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductManager productManager;



    @Override
    public ActionResult<Long> saveOrUpdate(ProductDTO product) {
        ActionResult<Long> response;
        if (product.getId() == null) {
            productManager.save(product);
            response = new ActionResult<>(true, "محصول جدید با موفقیت ذخیره شد.", product.getId());
        } else {
            productManager.update(product);
            response = new ActionResult<>("محصول با موفقیت ویرایش شد.", product.getId());
        }
        return response;
    }

    @Override
    public ProductDTO load(Long id) {
        return productManager.findDTO(id);
    }

    @Override
    public List<ProductDTO> list() {
        return productManager.listDTO();

    }

    @Override
    public ActionResult<Long> delete(Long id) {
        productManager.delete(id);
        return new ActionResult<>("محصول با موفقیت حذف شد.",id);
    }

    @Override
    public List<ProductEntity> search(HttpServletRequest httpServletRequest) {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        Map<String, String> searchCriteriaMap = new HashMap<>();
        parameterMap.keySet().forEach(key -> {
            searchCriteriaMap.put(key, UtilMethods.extractParamFromMap(key, parameterMap));
        });

        throw new RuntimeException("not implemented yet");
        //return categoryManager.search(searchCriteriaMap);
        // return null;
    }


}
