package es.us.lsi.dp.controllers;

public class Codes {

	public static final String MODEL_OBJECT_NAME = "modelObject";

	public static final String VIEW_NAME = "_viewName";
	public static final String TABLE_INDEX = "_tableIndex";

	// Mapping values ----------------------------------------------------------

	public final static String ENTITY_PATH_VARIABLE_NAME = "context";
	private final static String ENTITY_PATH_VARIABLE = "{" + ENTITY_PATH_VARIABLE_NAME + "}";

	public final static String CREATE_MAPPING_VALUE = "/create";
	public final static String CREATE_MAPPING_VALUE_PARAMS = ENTITY_PATH_VARIABLE + "/create";
	public final static String DELETE_MAPPING_VALUE = ENTITY_PATH_VARIABLE + "/delete";
	public final static String DELETE_MAPPING_VALUE_PARAMS = ENTITY_PATH_VARIABLE + "/delete";
	public final static String UPDATE_MAPPING_VALUE = "/update";
	public final static String UPDATE_MAPPING_VALUE_PARAMS = ENTITY_PATH_VARIABLE + "/update";
	public final static String SHOW_MAPPING_VALUE = "/show";
	public final static String SHOW_MAPPING_VALUE_PARAMS = ENTITY_PATH_VARIABLE + "/show";

	/* Update ajax call if this route is changed */
	public final static String LIST_DATA_MAPPING_VALUE_PARAMS = ENTITY_PATH_VARIABLE + "/list/data";
	public final static String LIST_DATA_MAPPING_VALUE = "/list/data";
	public final static String LIST_MAPPING_VALUE_PARAMS = ENTITY_PATH_VARIABLE + "/list";
	public final static String LIST_MAPPING_VALUE = "/list";

	// Defaults ----------------------------------------------------------------

	public final static String DEFAULT_FORM_ERROR_CODE = "form-error.message";
	public final static String DEFAULT_SUCCESS_CODE = "success.message";
	public final static String DEFAULT_REDIRECT_VIEW_NAME = "../list.do";
	public final static String CREATE_REDIRECT_VIEW_NAME = "list.do";

	// Code names --------------------------------------------------------------

	public final static String FAILURE_CODE = "failureCode";
	public final static String SUCCESS_CODE = "successCode";
	public final static String THROWABLE = "throwable";

	// Custom success codes ----------------------------------------------------

	public final static String CREATE_SUCCESS_CODE = "create.success.message";
	public final static String UPDATE_SUCCESS_CODE = "update.success.message";
	public final static String DELETE_SUCCESS_CODE = "delete.success.message";

	// CRUD actions ------------------------------------------------------------

	public final static String CRUD_ACTION_CREATING = "creating";
	public final static String CRUD_ACTION_UPDATING = "updating";
	public final static String CRUD_ACTION_DELETING = "deleting";
	public final static String CRUD_ACTION_LISTING = "listing";
	public final static String CRUD_ACTION_SHOWING = "showing";

}
