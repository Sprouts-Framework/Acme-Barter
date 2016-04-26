package es.us.lsi.dp.controllers.core.contracts;

import java.util.List;
import java.util.Map;

public interface AddsToModel {

	public void addToModel(Map<String, Object> objects, List<String> context);

}
