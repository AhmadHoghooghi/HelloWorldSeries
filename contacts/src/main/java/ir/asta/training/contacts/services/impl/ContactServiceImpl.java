package ir.asta.training.contacts.services.impl;

import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.training.contacts.manager.ContactManager;
import ir.asta.training.contacts.services.ContactService;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("contactService")
public class ContactServiceImpl implements ContactService {

    @Inject
    private ContactManager manager;

    @Override
    public ContactEntity load(Long id) {
        return manager.load(id);
    }

    @Override
    public List<ContactEntity> loadAll() {
        return manager.findAll();
    }

    @Override
    public ActionResult<Long> saveOrUpdate(ContactEntity entity) {
        ActionResult<Long> response;
        if (entity.getId() == null) {
            manager.save(entity);
            response = new ActionResult<>(true, "مخاطب جدید با موفقیت ذخیره شد.", entity.getId());
        } else {
            manager.update(entity);
            response = new ActionResult<>("مخاطب با موفقیت ویرایش شد.", entity.getId());
        }
        return response;
    }

    @Override
    public ActionResult<Long> delete(Long id) {
        manager.delete(id);
        return new ActionResult<>("مخاطب با موفقیت حذف شد.", id);
    }

    @Override
    public List<ContactEntity> search(HttpServletRequest httpServletRequest) {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        Map<String, String> searchCriteriaMap = new HashMap<>();
        parameterMap.keySet().forEach(key -> {
            searchCriteriaMap.put(key, extractParamFromMap(key, parameterMap));
        });
        return manager.search(searchCriteriaMap);
    }

    private String extractParamFromMap(String name, Map<String, String[]> parameterMap) {
        return parameterMap.get(name) == null ? null : parameterMap.get(name)[0];
    }
}
