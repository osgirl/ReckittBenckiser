package com.cpm.Constants;

public class CommonString {

	public static final String PLS_FILL_DATA = "Please fill the data";
	
	public static final String CALLS_INVALID_DATA = "Productive Call cannot be greater than Total Calls";
	
	public static final String ONBACK_ALERT_MESSAGE="Unsaved data will be lost - Do you want to continue?";
	
	public static final String DATA_DELETE_ALERT_MESSAGE="Saved data will be lost - Do you want to continue?";

	public static final String KEY_QUESTION_CD = "question_cd";
	public static final String KEY_ANSWER_CD = "answer_cd";
	public static final String KEY_IS_QUIZ_DONE = "is_quiz_done";


	// preferenec keys
	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_REMEMBER = "remember";
	public static final String KEY_RIGHT_NAME = "right_name";
	public static final String KEY_PATH = "path";
	public static final String KEY_VERSION = "version";
	
	public static final String METHOD_UPLOAD_XML = "DrUploadXml";
	public static final String MEHTOD_UPLOAD_COVERAGE_STATUS = "UploadCoverage_Status";

	public static final String TABLE_INSERT_SKU_DATA = "SKU_ASSET_DATA";
	public static final String TABLE_INSERT_SKU_PACKD = "SKU_PACKD_DATA";
	public static final String TABLE_INSERT_SECONDARY_SKU_DATA = "SECONDARY_SKU_DATA";
	// public static final String KEY_APPVERSION = "1.1";

	public static final String KEY_USER_TYPE = "RIGHTNAME";
	
	public static final String KEY_DATE = "date";
	public static final String MID = "MID";
	public static final String KEY_P = "P";
	public static final String KEY_D = "D";
	public static final String KEY_U = "U";
	public static final String KEY_C = "Y";
	public static final String KEY_CHECK_IN = "I";
	public static final String KEY_L = "Leave";
	public static final String KEY_N = "NOT_VISITED";
	public static final String KEY_INVALID = "INVALID";
	public static final String STORE_STATUS_LEAVE = "L";
	public static final String KEY_VALID = "Valid";
	public static final String KEY_STORE_IN_TIME = "Store_in_time";
	public static final String SOAP_ACTION = "http://tempuri.org/";
	
	public static final String KEY_MERCHANDISER_ID = "MERCHANDISER_ID";
	
	public static final String KEY_AVAIL = "AVAIL";
	
	public static final String ERROR = " PROBLEM OCCURED IN " ; 
	
	public static final String KEY_SUCCESS_chkout = "Success";
	
	
	public static final String KEY_IMAGE_PATH1 = "IMAGE_PATH1";
	public static final String KEY_IMAGE_PATH2 = "IMAGE_PATH2";
	public static final String KEY_IMAGE_PATH3 = "IMAGE_PATH3";
	public static final String TABLE_INSERT_GEO_TAG = "INSERT_GEO_TAG_DATA";
	public static final String TABLE_GEO_TAG_MAPPING = "GEO_TAG_MAPPING";
	public static final String TABLE_GEOTAG_CITY = "GEO_TAG_CITY";

	// webservice constants

	public static final String KEY_SUCCESS = "Success";
	public static final String KEY_FAILURE = "Failure";
	public static final String KEY_FALSE = "False";
	public static final String KEY_CHANGED = "Changed";

	public static final String KEY_NO_DATA = "NoData";

	public static final String KEY_MID = "MID";
	public static final String KEY_SKU_STOCK = "SKU_STOCK";
	public static final String KEY_SKU_ID = "SKU_ID";
	public static final String KEY_SKUFACEUP = "SKUFACEUP";
	public static final String KEY_SKUNAME = "SKUNAME";

	public static final String KEY_DOM1 = "DOM1";
	public static final String KEY_DOM2 = "DOM2";
	public static final String KEY_DOM3 = "DOM3";
	public static final String KEY_DOM4 = "DOM4";
	public static final String KEY_DOM5 = "DOM5";

	public static final String KEY_IMAGE1 = "cam1";
	public static final String KEY_IMAGE2 = "cam2";
	public static final String KEY_IMAGE3 = "cam3";
	
	public static final String KEY_ENTRY = "ENTRY";
	public static final String KEY_IMAGE = "IMAGE1";
	public static final String KEY_IMAGE_SELFIE = "IMAGE_SELFIE";
	public static final String KEY_IMAGE_1 = "IMAGE2";
	public static final String KEY_IMAGE_2= "IMAGE3";
	public static final String KEY_IMAGE_SAVE_STATUS = "Save_Status";
	public static final String KEY_REMARK = "KEY_REMARK";
	
	public static final String KEY_REMARK_STORE = "REMARK_STORE";
	
	
	public static final String KEY_DATA_SOURCE = "DATA_SOURCE";
	public static final String KEY_COVERAGE_REMARK = "REMARK";
	public static final String KEY_DEV_REASON = "DEV_REASON";
	
	public static final String KEY_SOD_ID = "SOD_ID";

	public static final String KEY_ASSETS_ID = "ASSETS_ID";
	public static final String KEY_ASSETS = "ASSET";

	public static final String KEY_STOCK_REASON_ID = "STOCKREASON_ID";
	public static final String KEY_STOCK_REASON = "STOCK_REASON";
	
	public static final String METHOD_UPLOAD_IMAGE = "GetImageWithFolderName";
	
	public static final String SOAP_ACTION_GEOTAGGINGIMAGE = "http://tempuri.org/"+METHOD_UPLOAD_IMAGE;
	
	public static final String SOAP_ACTION_UPLOAD_IMAGE = "http://tempuri.org/"+METHOD_UPLOAD_IMAGE;

	public static final String TABLE_INSERT_OPENINGHEADER_DATA = "openingHeader_data";
	
	public static final String CREATE_TABLE_insert_OPENINGHEADER_DATA = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_INSERT_OPENINGHEADER_DATA
			+ " ("
			+ "KEY_ID"
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			
			+ "STORE_CD"
			+ " VARCHAR,"
			+ "BRAND_CD"
			+ " VARCHAR,"
			+ "BRAND" + " VARCHAR)";
	
public static final String TABLE_INSERT_OPENINGHEADER_CLOSING_DATA = "openingHeader_Closing_data";
	
	public static final String CREATE_TABLE_insert_OPENINGHEADER_CLOSING_DATA = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_INSERT_OPENINGHEADER_CLOSING_DATA
			+ "("
			+ "KEY_ID"
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			
			+ "STORE_CD"
			+ " VARCHAR,"
			+ "BRAND_CD"
			+ " VARCHAR,"
			+ "BRAND" + " VARCHAR)";
	
public static final String TABLE_INSERT_PROMOTION_HEADER_DATA = "openingHeader_Promotion_data";
	
	public static final String CREATE_TABLE_insert_HEADER_PROMOTION_DATA = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_INSERT_PROMOTION_HEADER_DATA
			+ "("
			+ "KEY_ID"
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			
			+ "STORE_CD"
			+ " VARCHAR,"
			+ "BRAND_CD"
			+ " VARCHAR,"
			+ "BRAND" + " VARCHAR)";
	
public static final String TABLE_INSERT_ASSET_HEADER_DATA = "openingHeader_Asset_data";
	
	public static final String CREATE_TABLE_insert_HEADER_ASSET_DATA = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_INSERT_ASSET_HEADER_DATA
			+ "("
			+ "KEY_ID"
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			
			+ "STORE_CD"
			+ " VARCHAR,"
			+ "BRAND_CD"
			+ " VARCHAR,"
			+ "BRAND" + " VARCHAR)";
	
	
public static final String TABLE_INSERT_HEADER_MIDDAY_DATA = "openingHeader_Midday_data";
	
	public static final String CREATE_TABLE_insert_HEADER_MIDDAY_DATA = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_INSERT_HEADER_MIDDAY_DATA
			+ "("
			+ "KEY_ID"
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			
			+ "STORE_CD"
			+ " VARCHAR,"
			+ "BRAND_CD"
			+ " VARCHAR,"
			+ "BRAND" + " VARCHAR)";
	
public static final String TABLE_INSERT_HEADER_FOOD_STORE_DATA = "openingHeader_FOOD_STORE_data";
	
	public static final String CREATE_TABLE_insert_HEADER_FOOD_STORE_DATA = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_INSERT_HEADER_FOOD_STORE_DATA
			+ "("
			+ "KEY_ID"
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			
			+ "STORE_CD"
			+ " VARCHAR,"
			+ "BRAND_CD"
			+ " VARCHAR,"
			+ "BRAND" + " VARCHAR)";
	
	
	public static final String TABLE_DEEPFREEZER_DATA="DEEPFREEZER_DATA";
	public static final String CREATE_TABLE_DEEPFREEZER_DATA = "CREATE TABLE IF NOT EXISTS DEEPFREEZER_DATA(FID INTEGER, STORE_CD VARCHAR,DEEP_FREEZER VARCHAR, FREEZER_TYPE VARCHAR, STATUS VARCHAR, REMARK VARCHAR)";
	
	public static final String TABLE_FACING_COMPETITOR_DATA="FACING_COMPETITOR_DATA";
	public static final String CREATE_TABLE_FACING_COMPETITOR_DATA = "CREATE TABLE IF NOT EXISTS FACING_COMPETITOR_DATA(KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD INTEGER, COMPANY_CD INTEGER, COMPANY VARCHAR, FACING VARCHAR)";
	
	public static final String TABLE_STOCK_DATA="STOCK_DATA";
	public static final String CREATE_TABLE_STOCK_DATA = "CREATE TABLE IF NOT EXISTS STOCK_DATA(Common_Id INTEGER, SKU_CD INTEGER,SKU VARCHAR, BRAND_CD VARCHAR, BRAND VARCHAR, STORE_CD VARCHAR, OPENING_TOTAL_STOCK VARCHAR, FACING VARCHAR, STOCK_UNDER_DAYS VARCHAR, MIDDAY_TOTAL_STOCK VARCHAR, CLOSING_STOCK VARCHAR)";
	
	public static final String TABLE_OPENING_STOCK_DATA="OPENING_STOCK_DATA";
	public static final String CREATE_TABLE_OPENING_STOCK_DATA = "CREATE TABLE IF NOT EXISTS OPENING_STOCK_DATA(Common_Id INTEGER, SKU_CD INTEGER,STORE_CD VARCHAR, CATEGORY_TYPE VARCHAR, AS_PER_MCCAIN VARCHAR, ACTUAL_LISTED VARCHAR, OPENING_STOCK_COLD_ROOM VARCHAR, OPENING_STOCK_MCCAIN_DF VARCHAR, TOTAL_FACING_MCCAIN_DF VARCHAR, OPENING_STOCK_STORE_DF VARCHAR, TOTAL_FACING_STORE_DF VARCHAR, MATERIAL_WELLNESS VARCHAR)";
	
	public static final String TABLE_CLOSING_STOCK_DATA="CLOSING_STOCK_DATA";
	public static final String CREATE_TABLE_CLOSING_STOCK_DATA = "CREATE TABLE IF NOT EXISTS CLOSING_STOCK_DATA(Common_Id INTEGER, SKU_CD INTEGER,STORE_CD VARCHAR, COLD_ROOM VARCHAR, MCCAIN_DF VARCHAR, STORE_DF VARCHAR)";
	
	public static final String TABLE_MIDDAY_STOCK_DATA="MIDDAY_STOCK_DATA";
	public static final String CREATE_TABLE_MIDDAY_STOCK_DATA = "CREATE TABLE IF NOT EXISTS MIDDAY_STOCK_DATA(Common_Id INTEGER,SKU_CD INTEGER,STORE_CD VARCHAR, MIDDAY_STOCK VARCHAR)";
	
	public static final String TABLE_FOOD_STORE_DATA="FOOD_STORE_DATA";
	public static final String CREATE_TABLE_FOOD_STORE_DATA = "CREATE TABLE IF NOT EXISTS FOOD_STORE_DATA(Common_Id INTEGER,STORE_CD VARCHAR, SKU_CD VARCHAR, SKU VARCHAR,AS_PER_MCCAIN VARCHAR,ACTUAL_LISTED VARCHAR, MCCAIN_DF VARCHAR, STORE_DF VARCHAR, MTD_SALES VARCHAR, PACKING_SIZE VARCHAR)";
	
	public static final String TABLE_PROMOTION_DATA="PROMOTION_DATA";
	public static final String CREATE_TABLE_PROMOTION_DATA = "CREATE TABLE IF NOT EXISTS PROMOTION_DATA(Common_Id INTEGER,PID INTEGER,STORE_CD VARCHAR, PROMOTION VARCHAR, CATEGORY_TYPE VARCHAR, PRESENT VARCHAR, REMARK VARCHAR, IMAGE VARCHAR)";
	
	public static final String TABLE_ASSET_DATA="ASSET_DATA";
	public static final String CREATE_TABLE_ASSET_DATA = "CREATE TABLE IF NOT EXISTS ASSET_DATA(Common_Id INTEGER,ASSET_CD INTEGER,STORE_CD VARCHAR, ASSET VARCHAR, PRESENT VARCHAR, REMARK VARCHAR,IMAGE VARCHAR)";
	
	public static final String TABLE_CALLS_DATA="CALLS_DATA";
	public static final String CREATE_TABLE_CALLS_DATA = "CREATE TABLE IF NOT EXISTS CALLS_DATA(Key_Id INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD VARCHAR, TOTAL_CALLS VARCHAR, PRODUCTIVE_CALLS VARCHAR)";

	public static final String TABLE_SAMPLE_DATA="SAMPLE_DATA";
	public static final String CREATE_TABLE_SAMPLE_DATA = "CREATE TABLE IF NOT EXISTS SAMPLE_DATA(Key_Id INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD VARCHAR, CUSTOMER_NAME VARCHAR, PHONE_NUMBER VARCHAR, QUANTITY_SAMPLE VARCHAR, QUANTITY_SALE VARCHAR)";

	public static final String TABLE_COMPETITION_POI="COMPETITION_POI";
	public static final String CREATE_TABLE_COMPETITION_POI = "CREATE TABLE IF NOT EXISTS COMPETITION_POI(Key_Id INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD VARCHAR, CATEGORY_CD VARCHAR, ASSET_CD VARCHAR, CATEGORY VARCHAR, ASSET VARCHAR, COMPANY_CD VARCHAR, COMPANY VARCHAR, REMARK VARCHAR)";

	public static final String TABLE_POI="POI";
	public static final String CREATE_TABLE_POI = "CREATE TABLE IF NOT EXISTS POI(Key_Id INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD VARCHAR, CATEGORY_CD VARCHAR, ASSET_CD VARCHAR, CATEGORY VARCHAR, ASSET VARCHAR, BRAND_CD VARCHAR, BRAND VARCHAR, REMARK VARCHAR, IMAGE_POI VARCHAR)";

	public static final String TABLE_COMPETITION_PROMOTION="COMPETITION_PROMOTION";
	public static final String CREATE_TABLE_COMPETITION_PROMOTION = "CREATE TABLE IF NOT EXISTS COMPETITION_PROMOTION(Key_Id INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD VARCHAR, CATEGORY_CD VARCHAR, CATEGORY VARCHAR, COMPANY_CD VARCHAR, COMPANY VARCHAR, REMARK VARCHAR, PROMOTION VARCHAR, SKU VARCHAR)";

	public static final String TABLE_STOCK_IMAGE="STOCK_IMAGE";
	public static final String CREATE_TABLE_STOCK_IMAGE = "CREATE TABLE IF NOT EXISTS STOCK_IMAGE(Key_Id INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD VARCHAR,  BRAND_CD VARCHAR, BRAND VARCHAR, IMAGE_STK VARCHAR, VISIT_DATE VARCHAR)";

	// location
	public static final String TABLE_LOCATION_STATUS = "LOCATION_STATUS";
	public static final String KEY_NETWORK_STATUS = "NETWORK_STATUS";
	public static final String KEY_CURRENT_TIME = "CURRENT_TIME";

	public static final String NAMESPACE = "http://tempuri.org/";

	public static final String URL = "http://mj.parinaam.in/FoodService.asmx";

	public static final String URL_Notice_Board = "http://mj.parinaam.in//notice/notice.html";
	
	//public static final String URL = "http://mccain.parinaam.in/MccainWebService.asmx";
	public static final String local_URL = "http://10.200.20.133/GSK_GT_SERVICE/GSKWebservice.asmx";

	public static final String METHOD_LOGIN = "UserLoginDetail";
	public static final String SOAP_ACTION_LOGIN = "http://tempuri.org/"
			+ METHOD_LOGIN;

	public static final String METHOD_UPLOAD_STORE_STATUS = "InsertUserCurrentLocation";
	
	public static final String METHOD_UPLOAD_STATUS = "DEVIATION_APPROVAL_SUP_STATUS";
	
	
	public static final String SOAP_ACTION_UPLOAD_STORE_STATUS = "http://tempuri.org/"
			+ METHOD_UPLOAD_STATUS;

	// public static final String METHOD_NAME_JCP = "DownLoadStoreJcp";
	public static final String METHOD_NAME_JCP = "DownLoadStoreJcp_Special";
	public static final String SOAP_ACTION_JCP = "http://tempuri.org/"
			+ METHOD_NAME_JCP;

	public static final String METHOD_NAME_STORE_LAYOUT = "DownLoad_Store_Layout";
	public static final String SOAP_ACTION_STORE_LAYOUT = "http://tempuri.org/"
			+ METHOD_NAME_STORE_LAYOUT;

	public static final String METHOD_NAME_STORE_SIZE = "DownLoad_Store_Size";
	public static final String SOAP_ACTION_STORE_SIZE = "http://tempuri.org/"
			+ METHOD_NAME_STORE_SIZE;

	public static final String METHOD_NAME_UPLOAD_GEOTAG_IMAGE = "Upload_StoreGeoTag_IMAGES";
	public static final String SOAP_ACTION_UPLOAD_GEOTAG_IMAGE = "http://tempuri.org/"
			+ METHOD_NAME_UPLOAD_GEOTAG_IMAGE;

	public static final String METHOD_NAME_PLANOGRAM_IMAGES = "DownLoad_PlanoGramMapping";
	public static final String SOAP_ACTION_PLANOGRAM_IMAGES = "http://tempuri.org/"
			+ METHOD_NAME_PLANOGRAM_IMAGES;

	public static final String METHOD_NAME_delete_coverage = "DeleteChekoutAndCoverage";
	public static final String SOAP_ACTION_delete_coverage = "http://tempuri.org/"
			+ METHOD_NAME_delete_coverage;

	public static final String METHOD_Checkout_StatusNew = "Upload_Store_ChecOut_Status_V1";
	public static final String SOAP_ACTION_Checkout_StatusNew = "http://tempuri.org/"
			+ METHOD_Checkout_StatusNew;
	
	
	// String value for promotional master

	public static final String METHOD_NAME_DownLoad_Promotional_Master = "DownLoad_Promotional_Master";
	public static final String SOAP_ACTION_Promotional_Master = "http://tempuri.org/"
			+ METHOD_NAME_DownLoad_Promotional_Master;

	// String value for SKU master

	public static final String METHOD_NAME_DOWNLOAD_SKU_MASTER = "DownLoad_SKU_Master";
	public static final String SOAP_ACTION_DOWNLAOD_SKU_MASTER = "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_SKU_MASTER;

	// string value for Master

	public static final String METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER = "DownLoad_NonWorkingReason_Master";
	
	public static final String METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER_subReason = "DownLoad_NonWorkingSubReason_ByReason";
	
	public static final String SOAP_ACTION_DOWNLAOD_NON_WORKING_MASTER = "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER;
	
	public static final String SOAP_ACTION_DOWNLAOD_NON_WORKING_MASTER_SUBREASON = "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER_subReason;

	public static final String METHOD_NAME_DOWNLOAD_sku_mapping = "DownLoad_SKU_By_Mapping";
	public static final String SOAP_ACTION_DOWNLAOD_sku_mapping = "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_sku_mapping;

	// string value for DowloadComplaince

	public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE = "DowloadComplaince";
	public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE = "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_COMPLIANCE;

	// STRING VALUE FOR DowloadPromotionWithComplainceByMapping

	public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING = "DowloadPromotionWithComplainceByMapping";
	public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE_MAPPING = "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING;

	public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING_SPECIAL = "DownLoad_PROMOTION_COMPLIANCE_MAPPING_SPECIAL";
	public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE_MAPPING_SPECIAL = "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING_SPECIAL;

	public static final String METHOD_VERTICAL_MASTER = "DOWLOAD_VERTICALMASTER";
	public static final String SOAP_ACTION_VERTICAL_MASTER = "http://tempuri.org/"
			+ METHOD_VERTICAL_MASTER;

	public static final String METHOD_BRAND_MASTER = "DOWLOAD_BRANDMASTER";
	public static final String SOAP_ACTION_BRAND_Master = "http://tempuri.org/"
			+ METHOD_BRAND_MASTER;

	public static final String METHOD_VERTICAL_BRAND_MAPPING = "DOWLOAD_VERTICALBRANDMAPPING";
	public static final String SOAP_ACTION_VERTICAL_BRAND_Mapping = "http://tempuri.org/"
			+ METHOD_VERTICAL_BRAND_MAPPING;

	public static final String METHOD_VERTICAL_SKU_MAPPING = "SKUBRANDMAPPINGDownload";
	public static final String SOAP_ACTION_VERTICAL_SKU_Mapping = "http://tempuri.org/"
			+ METHOD_VERTICAL_SKU_MAPPING;

	public static final String METHOD_CATEGORY_MASTER = "DOWLOAD_CATEGORYMASTER";
	public static final String SOAP_ACTION_CATEGORY_MASTER = "http://tempuri.org/"
			+ METHOD_CATEGORY_MASTER;

	public static final String METHOD_CATEGORY_SKU_MAPPING = "CATEGORYSKUMAPPINGDownload";
	public static final String SOAP_ACTION_CATEGORY_SKU_MAPPING = "http://tempuri.org/"
			+ METHOD_CATEGORY_SKU_MAPPING;

	public static final String METHOD_CATEGORY_VERTICAL_MAPPING = "CATEGORYVERTICALMAPPINGDownload";
	public static final String SOAP_ACTION_CATEGORY_VERTICAL_MAPPING = "http://tempuri.org/"
			+ METHOD_CATEGORY_VERTICAL_MAPPING;

	public static final String METHOD_CATEGORY_POSM_MAPPING = "POSMBRANDMAPPINGDownload";
	public static final String SOAP_ACTION_POSM_MAPPING = "http://tempuri.org/"
			+ METHOD_CATEGORY_POSM_MAPPING;

	public static final String METHOD_SKU_MASTER_DOWNLOAD = "SKU_MASTERDownload";
	public static final String SOAP_ACTION_SKU_MASTER = "http://tempuri.org/"
			+ METHOD_SKU_MASTER_DOWNLOAD;

	public static final String METHOD_COMPANY_MASTER_DOWNLOAD = "COMPANY_MASTERDownload";
	public static final String SOAP_ACTION_COMPANY_MASTER = "http://tempuri.org/"
			+ METHOD_COMPANY_MASTER_DOWNLOAD;

	// Shahab
	public static final String METHOD_NONSKU_REASON = "DOWLOAD_NON_STOCK_REASON_MASTER";
	public static final String SOAP_ACTION_NONSKU_REASON = "http://tempuri.org/"
			+ METHOD_NONSKU_REASON;

	public static final String METHOD_SKU_FOCUS_DOWNLOAD = "SKUAVALIBILITY_FOCUS";
	public static final String SOAP_ACTION_SKU_FOCUS = "http://tempuri.org/"
			+ METHOD_SKU_FOCUS_DOWNLOAD;

	public static final String METHOD_MAPPING_COMPETITOR = "DOWLOAD_MAPPINGCOMPEPITORBRAND";
	public static final String SOAP_ACTION_MAPPING_COMPETITOR = "http://tempuri.org/"
			+ METHOD_MAPPING_COMPETITOR;

	public static final String METHOD_POSM_MASTER_DOWNLOAD = "DOWLOAD_POSMMASTER";
	public static final String SOAP_ACTION_POSM_MASTER_DOWNLOAD = "http://tempuri.org/"
			+ METHOD_POSM_MASTER_DOWNLOAD;

	// Upload Coverage
	public static final String METHOD_UPLOAD_DR_STORE_COVERAGE = "UPLOAD_COVERAGE";
	public static final String METHOD_UPLOAD_DR_STORE_COVERAGE_LOC = "UPLOAD_COVERAGE_SUP";
	
	public static final String SOAP_ACTION_UPLOAD_DR_STORE_COVERAGE = "http://tempuri.org/"
			+ METHOD_UPLOAD_DR_STORE_COVERAGE_LOC;

	public static final String METHOD_GENERIC_UPLOAD = "DrUploadXml";
	public static final String METHOD_UPLOAD_DR_STORE_DATA = "Upload_Store_Layout_V1";
	public static final String SOAP_ACTION_UPLOAD_DR_STORE_DATA = "http://tempuri.org/"
			+ METHOD_UPLOAD_DR_STORE_DATA;
	
	public static final String SOAP_ACTION_METHOD_GENERIC_UPLOAD = "http://tempuri.org/"
			+ METHOD_GENERIC_UPLOAD;

	public static final String METHOD_UPLOAD_DR_RETAILER_INFO = "Upload_DR_STORE_PAYMENT";
	public static final String SOAP_ACTION_UPLOAD_DR_RETAILER_INFO = "http://tempuri.org/"
			+ METHOD_UPLOAD_DR_RETAILER_INFO;

	public static final String METHOD_UPLOAD_ASSET = "Upload_Stock_Availiablity_V1";
	public static final String SOAP_ACTION_UPLOAD_ASSET = "http://tempuri.org/"
			+ METHOD_UPLOAD_ASSET;

	public static final String METHOD_UPLOAD_SEC_SKU = "Upload_Stock_Availiablity_SECONDARY";
	public static final String SOAP_ACTION_UPLOAD_SEC_SKU = "http://tempuri.org/"
			+ METHOD_UPLOAD_SEC_SKU;

	public static final String METHOD_UPLOAD_PCKGE_SKU = "Upload_DR_CORE_SKU_PACKAGING";
	public static final String SOAP_ACTION_UPLOAD_PCKGE_SKU = "http://tempuri.org/"
			+ METHOD_UPLOAD_PCKGE_SKU;

	public static final String METHOD_UPLOAD_POSM = "Upload_Posm_Deployed";
	public static final String SOAP_ACTION_UPLOAD_POSM = "http://tempuri.org/"
			+ METHOD_UPLOAD_POSM;

	public static final String METHOD_UPLOAD_COMPLIANCE = "Upload_Promotion_WindowExists";
	public static final String SOAP_ACTION_COMPLIANCE = "http://tempuri.org/"
			+ METHOD_UPLOAD_COMPLIANCE;

	public static final String METHOD_UPLOAD_COMPLIANCE_SPECIAL = "Upload_Promotion_Special";
	public static final String SOAP_ACTION_COMPLIANCE_SPECIAL = "http://tempuri.org/"
			+ METHOD_UPLOAD_COMPLIANCE_SPECIAL;

	public static final String METHOD_NON_WORKING_MASTER = "DOWLOAD_NONWORKINGREGIONMASTER";
	public static final String SOAP_ACTION_NONWORKING = "http://tempuri.org/"
			+ METHOD_NON_WORKING_MASTER;

	public static final String METHOD_SET_COVERAGE_STATUS = "Upload_Status";
	public static final String SOAP_ACTION_SET_COVERAGE_STATUS = "http://tempuri.org/"
			+ METHOD_SET_COVERAGE_STATUS;

	public static final String METHOD_SET_UPLOAD_GEODATA = "Upload_Store_Geo_Tag";
	public static final String SOAP_ACTION_UPLOAD_GEODATA = "http://tempuri.org/"
			+ METHOD_SET_UPLOAD_GEODATA;

	// database

	public static final String TABLE_COVERAGE_DATA = "COVERAGE_DATA";
	
	public static final String TABLE_ATTENDENCE_DATA = "ATTENDENCE_DATA";
	
	public static final String TABLE_HEADER_DATA = "HEADER_DATA";
	
	public static final String TABLE_CHILD_DATA = "CHILD_DATA";
	
	public static final String TABLE_GATE_COVERAGE_DATA = "GATE_COVERAGE_DATA";
	public static final String TABLE_NON_WORKING_REASON = "NON_WORKING_DATA";

	public static final String TABLE_NON_SKU_REASON = "NON_SKU_REASON";

	public static final String CREATE_TABLE_NON_SKU_REASON = "CREATE TABLE NON_SKU_REASON(STOCK_REASON VARCHAR, STOCK_REASON_ID VARCHAR)";
	// public static final String CREATE_TABLE_KEY_MODEL_DATA =
	// "CREATE TABLE KEY_MODEL_DATA (MID INTEGER, KEY_MODEL_NAME VARCHAR,KEY_MODEL_ID INTEGER,KEY_MODEL_QTNY INTEGER)";

	// CONSTANT FIELD NAMES FOR GTGSK

	// FOR JCP DOWNLOAD

	public static final String KEY_ID = "_id";
	public static final String KEY_STORE_ID = "STORE_ID";
	public static final String KEY_DISTRIBUTOR_ID = "DISTRIBUTOR_ID";
	
	public static final String KEY_BRAND_ID = "BRAND_ID";
	public static final String KEY_BRAND = "BRAND";
	
	public static final String KEY_CATEGORY = "CATEGORY";
	
	public static final String KEY_STORE_NAME = "STORE_NAME";
	public static final String KEY_STORE_LAYOUT = "LAYOUT";
	public static final String KEY_STORE_SIZE = "SIZE";
	public static final String KEY_ADDRESS = "ADDRESS";
	public static final String KEY_USER_ID = "USER_ID";
	public static final String KEY_IN_TIME = "IN_TIME";
	public static final String KEY_OUT_TIME = "OUT_TIME";
	public static final String KEY_VISIT_DATE = "VISIT_DATE";
	public static final String KEY_LATITUDE = "LATITUDE";
	public static final String KEY_LONGITUDE = "LONGITUDE";
	public static final String KEY_COVERAGE_STATUS = "Coverage";
	public static final String KEY_REASON_ID = "REASON_ID";
	public static final String KEY_SUB_REASON_ID = "SUB_REASON_ID";
	public static final String KEY_MEET = "MEET";
	public static final String KEY_ISSUE = "ISSUE";
	public static final String KEY_REASON = "REASON";
	public static final String KEY_STATUS = "STATUS";
	public static final String KEY_CHECKOUT_STATUS = "CHECKOUT_STATUS";

	public static final String KEY_CHANNEL = "channel";
	public static final String KEY_CLASSIFICATION = "classification";
	public static final String KEY_NO_OF_SHELFS = "shelfs";

	public static final String KEY_STORE_CD = "STORE_CD";
	public static final String KEY_STORE = "STORE_NAME";
	public static final String KEY_ADDRES = "ADDRESS";
	public static final String KEY_USER_CITY = "CITY";
	public static final String KEY_EMP_CD = "EMP_CD";
	public static final String KEY_CURRENT_DATETIME = "CURRENT_DATETIME";
	public static final String KEY_STAT = "STATUS";

	public static final String KEY_STOREVISITED = "STORE_VISITED";
	public static final String KEY_STOREVISITED_STATUS = "STORE_VISITED_STATUS";
	
	public static final String KEY_FOOD_STORE = "FOOD_STORE";

	// For

	// FOR TABLE DOWNOAD_PROMOTION
	public static final String PROMOTION_CD = "PROMOTION_CD";
	public static final String PROMOTION_WINDOW = "PROMOTION_NAME";
	// FOR TABLE SKU_MASTER
	public static final String SKU_CD = "SKU_CD";
	public static final String SKU_seq = "SKU_seq";
	public static final String KEY_CORE_SKU = "Core_Sku";
	public static final String is_competitor = "IS_COMPETITOR";
	public static final String region_id = "REGION_ID";
	public static final String storetype_id = "STORETYPE_ID";
	public static final String KEY_CATEGORY_ID = "CATEGORY_ID";

	public static final String KEY_BRAND_CATEGORY_ID = "BRAND_CATEGORY_ID";
	public static final String KEY_BRAND_CATEGORY = "BRAND_CATEGORY";
	public static final String KEY_MONTH_HERO = "MONTH_HERO";

	public static final String BRAND_SEQ = "BRAND_SEQ";
	public static final String SKU = "SKU";
	// FOR TBALE NON_WORKING_REASON
	public static final String REASON_ID = "REASON_ID";
	public static final String REASON_VALID ="REASON_VALID";
	public static final String REASON = "REASON";
	
	public static final String SUB_REASON_ID = "SUB_REASON_ID";
	public static final String SUB_REASON = "SUB_REASON";
	
	
	
	// FOR TABLE COMPLIANCE_MASTER
	public static final String COMPLIANCE_ID = "COMPLIANCE_CD";
	public static final String COMPLIANCE = "COMPLIANCE";
	// FOR TABLE COMPLIANCE_MAPPING_MASTER
	public static final String COMPLIANCE_CD = "COMPLIANCE_CD";
	public static final String PROMOTIONCD = "PROMOTION_CD";
	// Posm
	public static final String KEY_POSM_CD = "POSM_CD";
	public static final String KEY_POSM = "POSM";
	// POSM Master
	public static final String METHOD_NAME_DOWNLOAD_POSM_MASTER = "POSM";
	public static final String SOAP_ACTION_DOWNLAOD_POSM_MASTER = "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_POSM_MASTER;
	// geo tag
	public static final String METHOD_NAME_DOWNLOAD_GEO = "DownLoadStoreByUser";
	public static final String SOAP_ACTION_DOWNLAOD_GEO = "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_GEO;

	public static final String SOAP_ACTION_COVERAGE_UPLOAD = "http://tempuri.org/"
			+ METHOD_UPLOAD_DR_STORE_COVERAGE;

	
	// CREATING TABLE NAMES GTGSK

	public static final String KEY_NORMAL = "Normal";
	public static final String KEY_SPECIAL = "Special";
	public static final String KEY_PERSON_NAME = "Name";
	public static final String KEY_CONTACT_NUMBER = "Number";

	public static final String TABLE_STORE_DETAIL = "STORE_DETAIL";
	public static final String TABLE_STORE_DATA = "STORE_DATA";
	public static final String TABLE_STORE_LAYOUT = "LAYOUT_MASTER";
	public static final String TABLE_STORE_SIZE = "SIZE_MASTER";
	public static final String TABLE_PROMOTION_NAME_GTGSK = "DOWNLOAD_PROMOTION";
	public static final String TABLE_SKU_MASTER_GTGSK = "SKUMASTER";
	public static final String TABLE_NON_WORKING_MASTER_GTGSK = "NON_WORKING_REASONMASTER";
	public static final String TABLE_NON_WORKING_MASTER_GTGSK_SUBREASON = "NON_WORKING_REASONMASTER_SUBREASON";
	
	public static final String TABLE_COMPLIANCE_MASTER_GTGSK = "COMPLIANCE_MASTER";
	public static final String TABLE_COMPLIANCE_MAPPING_MASTER_GTGSK = "COMPLIANCE_MAPPING_MASTER";
	public static final String TABLE_COMPLIANCE_MAPPING_SPECIAL = "COMPLIANCE_MAPPING_SPECIAL";
	public static final String TABLE_POSM_MASTER_GTGSK = "POSMMASTER";
	public static final String TABLE_SKU_MAPPING = "SKU_MAPPING";
	public static final String TABLE_PLANOGRAM_MAPPING_GTGSK = "PLANOGRAM_MAPPING";
	// CREATING TABLE FOR ABOVE TABLE NAMES
	// Tables



	public static final String KEY_AVAILABILITY = "QUANTITY";
	public static final String TABLE_INSERT_COMPLIANCE_DATA = "COMPLIANCE_DATA_INSERTED";
	public static final String TABLE_INSERT_SUBCOMPLIANCE_DATA = "SUBCOMPLIANCE_DATA_INSERTED";
	public static final String COMMONID = "COMMONID";
	public static final String CATEGORY_BRANDID = "CAT_BRANDID";
	public static final String CREATE_TABLE_COMPLIANCE_DATA = "CREATE TABLE "
			+ TABLE_INSERT_COMPLIANCE_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_STORE_ID + " VARCHAR," + PROMOTION_CD + " VARCHAR,"
			+ PROMOTION_WINDOW + " VARCHAR," + KEY_AVAILABILITY + " VARCHAR,"
			+ KEY_BRAND_CATEGORY + " VARCHAR," + CATEGORY_BRANDID + " VARCHAR,"
			+ KEY_MONTH_HERO + " VARCHAR," + KEY_IMAGE + " VARCHAR)";

	public static final String CREATE_TABLE_SUBCOMPLIANCE_DATA = "CREATE TABLE "
			+ TABLE_INSERT_SUBCOMPLIANCE_DATA
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ COMMONID
			+ " INTEGER,"
			+ COMPLIANCE_ID
			+ " VARCHAR,"
			+ KEY_STORE_ID
			+ " VARCHAR,"
			+ COMPLIANCE + " VARCHAR," + KEY_AVAILABILITY + " VARCHAR)";

	public static final String TABLE_INSERT_COMPLIANCE_DATA_SPECIAL = "COMPLIANCE_DATA_INSERTED_SPECIAL";
	public static final String TABLE_INSERT_SUBCOMPLIANCE_DATA_SPECIAL = "SUBCOMPLIANCE_DATA_INSERTED_SPECIAL";

	public static final String CREATE_TABLE_COMPLIANCE_DATA_SPECIAL = "CREATE TABLE "
			+ TABLE_INSERT_COMPLIANCE_DATA_SPECIAL
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ MID
			+ " INTEGER,"
			+ KEY_STORE_ID
			+ " VARCHAR,"
			+ PROMOTION_CD
			+ " VARCHAR,"
			+ PROMOTION_WINDOW
			+ " VARCHAR,"
			+ KEY_AVAILABILITY
			+ " VARCHAR,"
			+ KEY_IMAGE + " VARCHAR)";

	public static final String CREATE_TABLE_SUBCOMPLIANCE_DATA_SPECIAL = "CREATE TABLE "
			+ TABLE_INSERT_SUBCOMPLIANCE_DATA_SPECIAL
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ COMMONID
			+ " INTEGER,"
			+ COMPLIANCE_ID
			+ " VARCHAR,"
			+ KEY_STORE_ID
			+ " VARCHAR,"
			+ COMPLIANCE + " VARCHAR," + KEY_AVAILABILITY + " VARCHAR)";

	public static final String CREATE_TABLE_SKU_DATA = "CREATE TABLE "
			+ TABLE_INSERT_SKU_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_SKU_STOCK + " VARCHAR," + KEY_SKU_ID + " VARCHAR,"
			+ KEY_SKUNAME + " VARCHAR," + KEY_CORE_SKU + " VARCHAR," + KEY_DOM1
			+ " VARCHAR," + KEY_DOM2 + " VARCHAR," + KEY_DOM3 + " VARCHAR,"  + KEY_IMAGE1
			+ " VARCHAR," + KEY_IMAGE2 + " VARCHAR," + KEY_IMAGE3 + " VARCHAR,"
			+ is_competitor + " VARCHAR," + KEY_SKUFACEUP + " VARCHAR)";

	public static final String CREATE_TABLE_SKU_PACKD = "CREATE TABLE "
			+ TABLE_INSERT_SKU_PACKD + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_SKU_ID + " VARCHAR," + KEY_SKUNAME + " VARCHAR," + KEY_DOM1
			+ " VARCHAR," + KEY_DOM2 + " VARCHAR," + KEY_DOM3 + " VARCHAR,"
			+ KEY_DOM4 + " VARCHAR," + KEY_DOM5 + " VARCHAR)";

	public static final String CREATE_TABLE_SECONDARY_SKU_DATA = "CREATE TABLE "
			+ TABLE_INSERT_SECONDARY_SKU_DATA
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ MID
			+ " INTEGER,"
			+ KEY_SKU_STOCK
			+ " VARCHAR,"
			+ KEY_SKU_ID
			+ " VARCHAR,"
			+ KEY_SKUNAME + " VARCHAR," + KEY_SKUFACEUP + " VARCHAR)";

	public static final String CREATE_TABLE_SKU_MAPPING = "CREATE TABLE "
			+ TABLE_SKU_MAPPING + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + SKU_CD + " VARCHAR,"
			+ storetype_id + " VARCHAR," + SKU_seq + " VARCHAR," + KEY_CORE_SKU
			+ " VARCHAR," + region_id + " VARCHAR)";

	// FOR JCP GT GSK

	public static final String CREATE_TABLE_STORE_DETAIL = "CREATE TABLE "
			+ TABLE_STORE_DETAIL + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_CD
			+ " VARCHAR," + KEY_STORE_NAME + " VARCHAR," + KEY_USER_CITY + " VARCHAR,"
			+ KEY_VISIT_DATE + " VARCHAR," + KEY_CHECKOUT_STATUS + " VARCHAR)";

	// layout Master

	public static final String CREATE_TABLE_LAYOUT_MASTER = "CREATE TABLE "
			+ TABLE_STORE_LAYOUT + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_LAYOUT
			+ " VARCHAR)";

	// Size Master

	public static final String CREATE_TABLE_SIZE_MASTER = "CREATE TABLE "
			+ TABLE_STORE_SIZE + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_SIZE
			+ " VARCHAR)";

	// FOR PROMOTION GT GSK
	public static final String CREATE_TABLE_PROMOTIONAL_MASTER_GTGSK = "CREATE TABLE "
			+ TABLE_PROMOTION_NAME_GTGSK
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ PROMOTION_CD
			+ " VARCHAR," + PROMOTION_WINDOW + " VARCHAR)";
	// FOR SKU GTGSK
	public static final String CREATE_TABLE_SKU_MASTER_GTGSK = "CREATE TABLE "
			+ TABLE_SKU_MASTER_GTGSK + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + SKU_CD + " VARCHAR,"
			+ is_competitor + " VARCHAR," + SKU + " VARCHAR)";
	// FOR NOT WORKING REASON
	public static final String CREATE_TABLE_NONWORKINGREASON_GTGSK = "CREATE TABLE "
			+ TABLE_NON_WORKING_MASTER_GTGSK
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ REASON_ID
			+ " VARCHAR,"
			+ REASON_VALID
			+ " VARCHAR,"
			+ REASON + " VARCHAR)";
	
	
	
	public static final String CREATE_TABLE_NONWORKINGREASON_GTGSK_SUBREASON = "CREATE TABLE "
			+ TABLE_NON_WORKING_MASTER_GTGSK_SUBREASON
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ REASON_ID
			+ " VARCHAR,"
			+ KEY_SUB_REASON_ID
			+ " VARCHAR,"
			+ SUB_REASON + " VARCHAR)";
	
	
	
	
	
	
	// FOR COMPLIANCE MASTER

	public static final String CREATE_TABLE_COMPLIACEMASTER_GTGSK = "CREATE TABLE "
			+ TABLE_COMPLIANCE_MASTER_GTGSK
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ COMPLIANCE_ID
			+ " VARCHAR," + COMPLIANCE + " VARCHAR)";
	// FOR COMPLIANCE MAPPING

	public static final String CREATE_TABLE_PLANOGRAMMAPPING_GTGSK = "CREATE TABLE "
			+ TABLE_PLANOGRAM_MAPPING_GTGSK
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"

			+ PROMOTIONCD
			+ " VARCHAR,"
			+ region_id
			+ " VARCHAR,"
			+ storetype_id
			+ " VARCHAR,"
			+ KEY_BRAND_CATEGORY_ID
			+ " VARCHAR,"
			+ KEY_IMAGE + " VARCHAR," + KEY_CATEGORY_ID + " VARCHAR)";

	// planogram

	public static final String CREATE_TABLE_COMPLIACEMASTERMAPPING_GTGSK = "CREATE TABLE "
			+ TABLE_COMPLIANCE_MAPPING_MASTER_GTGSK
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ COMPLIANCE_CD
			+ " VARCHAR,"
			+ PROMOTIONCD
			+ " VARCHAR,"
			+ region_id
			+ " VARCHAR,"
			+ storetype_id
			+ " VARCHAR,"
			+ KEY_BRAND_CATEGORY_ID
			+ " VARCHAR,"
			+ KEY_MONTH_HERO
			+ " VARCHAR,"
			+ KEY_BRAND_CATEGORY
			+ " VARCHAR,"
			+ KEY_CATEGORY_ID + " VARCHAR)";

	public static final String CREATE_TABLE_COMPLIACE_MAPPING_SPECIAL = "CREATE TABLE "
			+ TABLE_COMPLIANCE_MAPPING_SPECIAL
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ COMPLIANCE_CD
			+ " VARCHAR," + PROMOTIONCD + " VARCHAR)";

	public static final String CREATE_TABLE_POSM_MASTER_GTGSK = "CREATE TABLE "
			+ TABLE_POSM_MASTER_GTGSK + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_POSM_CD
			+ " VARCHAR," + KEY_POSM + " VARCHAR)";

	public static final String TABLE_INSERT_POSM_DATA = "POSM_DATA_INSERTED";
	public static final String CREATE_TABLE_POSM_DATA = "CREATE TABLE "
			+ TABLE_INSERT_POSM_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_STORE_ID + " VARCHAR," + KEY_POSM_CD + " VARCHAR," + KEY_POSM
			+ " VARCHAR," + KEY_AVAILABILITY + " VARCHAR," + KEY_IMAGE
			+ " VARCHAR)";

	public static final String INVOICE_NUMBER = "INVOICE_NUMBER";
	public static final String CHEQUE_NUMBER = "CHEQUE_NUMBER";
	
	public static final String TABLE_GATE_MER_DETAIL = "GATE_MER_DETAIL";
    public static final String Key_EMPID = "EMPID";
	public static final String Key_NAME = "NAME";
	public static final String KEY_PRESENT = "PRESENT";
	public static final String KEY_COMMMON_ID = "COMMONID";

public static final String CREATE_TABLE_GATE_MEETING_MER = "CREATE TABLE "
			+ TABLE_GATE_MER_DETAIL + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + Key_NAME + " VARCHAR,"
			+ Key_EMPID + " VARCHAR," + KEY_PRESENT + " VARCHAR,"
			 + KEY_COMMMON_ID
			+ " VARCHAR)";
	
	
	
	
	

	public static final String TABLE_INSERT_RETAILER_INFO = "RETAILER_INFO";
	public static final String CREATE_TABLE_RETAILER_INFO = "CREATE TABLE "
			+ TABLE_INSERT_RETAILER_INFO + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_STORE_ID + " VARCHAR," + INVOICE_NUMBER + " VARCHAR,"
			+ KEY_IMAGE + " VARCHAR," + CHEQUE_NUMBER + " VARCHAR)";

	// *************** ENDS

	public static final String CREATE_TABLE_GATE_COVERAGE_DATA = "CREATE TABLE  IF NOT EXISTS "
			+ TABLE_GATE_COVERAGE_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + 
			 "USER_ID VARCHAR, " + KEY_IN_TIME + " VARCHAR,"
			+ KEY_OUT_TIME + " VARCHAR," + KEY_VISIT_DATE + " VARCHAR,"
			+ KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE + " VARCHAR,"  + KEY_STATUS + " VARCHAR,"
			
			+ KEY_IMAGE + " VARCHAR," + KEY_IMAGE1 + " VARCHAR," + KEY_IMAGE2
			+ " VARCHAR," + KEY_MEET + " VARCHAR)";

	public static final String CREATE_TABLE_NON_WORKING = "CREATE TABLE "
			+ TABLE_NON_WORKING_REASON + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_REASON_ID
			+ " VARCHAR," + KEY_REASON + " VARCHAR," + KEY_ENTRY + " VARCHAR,"
			+ KEY_IMAGE + " VARCHAR)";

	// Upload Image

	public static final String METHOD_Get_DR_STORE_IMAGES = "GET_StoreLayout_IMAGES";
	public static final String SOAP_ACTION_Get_DR_STORE_IMAGES = "http://tempuri.org/"
			+ METHOD_Get_DR_STORE_IMAGES;

	public static final String METHOD_Get_DR_CHEQUE_IMAGES = "Upload_StoreCheque_IMAGES";
	public static final String SOAP_ACTION_Get_DR_CHEQUE_IMAGES = "http://tempuri.org/"
			+ METHOD_Get_DR_CHEQUE_IMAGES;

	public static final String METHOD_Get_DR_POSM_IMAGES = "GET_PosmDepLoyed_IMAGES";
	public static final String SOAP_ACTION_Get_DR_POSM_IMAGES = "http://tempuri.org/"
			+ METHOD_Get_DR_POSM_IMAGES;

	public static final String METHOD_Get_DR_COMPLIANCE_IMAGES = "GET_Store_SecondaryWindowImage";
	public static final String SOAP_ACTION_Get_DR_COMPLIANCE_IMAGES = "http://tempuri.org/"
			+ METHOD_Get_DR_COMPLIANCE_IMAGES;

	public static final String METHOD_Get_DR_STORE_IMAGES_GEO = "Upload_StoreGeoTag_IMAGES";
	public static final String SOAP_ACTION_DR_STORE_IMAGES_GEO = "http://tempuri.org/"
			+ METHOD_Get_DR_STORE_IMAGES_GEO;

	public static final String CREATE_TABLE_INSERT_GEOTAG = "CREATE TABLE "
			+ TABLE_INSERT_GEO_TAG + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " VARCHAR," + KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE
			+ " VARCHAR," + KEY_STATUS + " VARCHAR," + KEY_IMAGE_PATH1
			+ " VARCHAR," + KEY_IMAGE_PATH2 + " VARCHAR," + KEY_IMAGE_PATH3
			+ " VARCHAR)";

	public static final String CREATE_TABLE_GEO_TAG_CITY = "CREATE TABLE "
			+ TABLE_GEOTAG_CITY + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + "CITY_ID" + " VARCHAR,"
			+ "CITY" + " VARCHAR)";

	public static final String CREATE_TABLE_GEO_TAG_MAPPING = "CREATE TABLE "
			+ TABLE_GEO_TAG_MAPPING + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " VARCHAR," + storetype_id + " VARCHAR," + KEY_CATEGORY_ID
			+ " VARCHAR," + KEY_STORE_NAME + " VARCHAR," + KEY_LATITUDE
			+ " VARCHAR," + KEY_LONGITUDE + " VARCHAR," + KEY_STATUS
			+ " VARCHAR)";
	
	
	public static final String METHOD_NAME_UNIVERSAL_DOWNLOAD = "Download_Universal";
	public static final String SOAP_ACTION_UNIVERSAL = "http://tempuri.org/"
			+ METHOD_NAME_UNIVERSAL_DOWNLOAD;
	
	
	public static final String CREATE_TABLE_COVERAGE_DATA = "CREATE TABLE  IF NOT EXISTS "
			+ TABLE_COVERAGE_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " VARCHAR,USER_ID VARCHAR, " + KEY_IN_TIME + " VARCHAR,"
			+ KEY_OUT_TIME + " VARCHAR," + KEY_VISIT_DATE + " VARCHAR,"
			+ KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE + " VARCHAR,"+ KEY_MERCHANDISER_ID + " VARCHAR,"
		    + KEY_COVERAGE_STATUS + " VARCHAR,"+ KEY_IMAGE + " VARCHAR,"
			
		    + KEY_REASON_ID + " VARCHAR," + KEY_COVERAGE_REMARK
			+ " VARCHAR," + KEY_REASON + " VARCHAR)";
	
	
	
	public static final String CREATE_TABLE_STORE = "CREATE TABLE "
			+ TABLE_STORE_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_MID + " VARCHAR,"
			+ KEY_STORE_ID + " VARCHAR," + KEY_ISSUE + " VARCHAR,"
			+ KEY_STATUS + " VARCHAR," + KEY_IMAGE + " VARCHAR," + KEY_REMARK + " VARCHAR,"
			+ KEY_IMAGE_1 + " VARCHAR," + KEY_IMAGE_2 + " VARCHAR)";
	
	
	
///attendence data
	
	public static final String CREATE_TABLE_ATTENDENCE_DATA = "CREATE TABLE  IF NOT EXISTS "
			+ TABLE_ATTENDENCE_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_DISTRIBUTOR_ID
			+ " VARCHAR,USER_ID VARCHAR, " + KEY_IN_TIME + " VARCHAR,"
			+ KEY_OUT_TIME + " VARCHAR," + KEY_VISIT_DATE + " VARCHAR,"+ KEY_STATUS + " VARCHAR,"
			+ KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE + " VARCHAR,"
		    + KEY_IMAGE_SELFIE + " VARCHAR," + KEY_REASON + " VARCHAR)";
	
	
	public static final String CREATE_TABLE_LIST_HEADER = "CREATE TABLE  IF NOT EXISTS "
			+ TABLE_HEADER_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_CATEGORY_ID
			+ " VARCHAR, " + KEY_CATEGORY + " VARCHAR,"
			+ KEY_STATUS + " VARCHAR," 
		    + KEY_COMMMON_ID + " VARCHAR)";
	
	
	public static final String CREATE_TABLE_LIST_CHILD = "CREATE TABLE  IF NOT EXISTS "
			+ TABLE_CHILD_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_BRAND_ID
			+ " VARCHAR, " + KEY_BRAND + " VARCHAR,"+ KEY_REMARK_STORE + " VARCHAR,"+ KEY_AVAILABILITY + " VARCHAR,"
			+ KEY_STATUS + " VARCHAR," 
		    + KEY_COMMMON_ID + " VARCHAR)";
	
}
