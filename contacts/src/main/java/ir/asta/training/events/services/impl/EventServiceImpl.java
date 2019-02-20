package ir.asta.training.events.services.impl;

import ir.asta.training.events.entities.EventEntity;
import ir.asta.training.events.manager.EventManager;
import ir.asta.training.events.services.EventService;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("eventService")
public class EventServiceImpl implements EventService {

    @Inject
    private EventManager manager;

    @Override
    public ActionResult<Long> saveOrUpdate(EventEntity entity) {
        if (entity.getId() == null) {
            manager.save(entity);
            return new ActionResult<>("رویداد با موفقیت ذخیره شد.", entity.getId());
        } else {
            manager.update(entity);
            return new ActionResult<>("رویداد با موفقیت ویرایش شد.", entity.getId());
        }
    }


    @Override
    public List<EventEntity> findAll() {
        return manager.findAll();
    }

    @Override
    public EventEntity find(Long id) {
        return manager.load(id);
    }


    @Override
    public List<EventEntity> search(HttpServletRequest httpServletRequest) throws ParseException {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        Map<String , String> searchCriteriaMap = new HashMap<>();

        searchCriteriaMap.put("title", extractParamFromMap("title", parameterMap));
        searchCriteriaMap.put("startDateTimeFrom", extractParamFromMap("eventStartFrom", parameterMap));
        searchCriteriaMap.put("startDateTimeTo", extractParamFromMap("eventStartTo", parameterMap));
        searchCriteriaMap.put("endDateTimeFrom", extractParamFromMap("eventEndFrom", parameterMap));
        searchCriteriaMap.put("endDateTimeTo", extractParamFromMap("eventEndTo", parameterMap));

        return manager.search(searchCriteriaMap);
    }

    @Override
    public ActionResult<Long> delete(Long id) {
        manager.delete(id);
        return new ActionResult<>("رویداد با موفقیت حذف شد.", id);
    }

    private String extractParamFromMap(String name, Map<String, String[]> parameterMap) {
        return parameterMap.get(name) == null ? null : parameterMap.get(name)[0];
    }
}
