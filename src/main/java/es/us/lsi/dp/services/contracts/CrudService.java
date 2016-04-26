package es.us.lsi.dp.services.contracts;

import es.us.lsi.dp.validation.contracts.Validable;

public interface CrudService<D extends Validable> extends CreateService<D>, UpdateService<D>, DeleteService<D>, ListService<D>, ShowService<D> {
}
