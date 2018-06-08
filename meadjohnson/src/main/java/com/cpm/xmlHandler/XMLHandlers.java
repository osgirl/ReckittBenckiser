package com.cpm.xmlHandler;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.cpm.xmlGetterSetter.AssetMasterGetterSetter;
import com.cpm.xmlGetterSetter.CategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.ColdroomClosingGetterSetter;
import com.cpm.xmlGetterSetter.CompanyGetterSetter;
import com.cpm.xmlGetterSetter.ComplianceByMappingGetterSetter;
import com.cpm.xmlGetterSetter.ComplianceGetterSetter;
import com.cpm.xmlGetterSetter.DeepFreezerGetterSetter;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlGetterSetter.JCPGetterSetter;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.cpm.xmlGetterSetter.LoginGetterSetter;
import com.cpm.xmlGetterSetter.MappingAssetGetterSetter;
import com.cpm.xmlGetterSetter.MappingAvailabilityGetterSetter;
import com.cpm.xmlGetterSetter.MappingPromotionGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingReasonGetterSetter;
import com.cpm.xmlGetterSetter.NonWrkingMasterGetterSetter;
import com.cpm.xmlGetterSetter.PerformanceGetterSetter;
import com.cpm.xmlGetterSetter.PosmGetterSetter;
import com.cpm.xmlGetterSetter.PromotionalMasterGettersetter;
import com.cpm.xmlGetterSetter.QuestionGetterSetter;
import com.cpm.xmlGetterSetter.SkuMappingGetterSetter;
import com.cpm.xmlGetterSetter.SkuMasterGetterSetter;


public class XMLHandlers {

	// LOGIN XML HANDLER
	public static LoginGetterSetter loginXMLHandler(XmlPullParser xpp,
			int eventType) {
		LoginGetterSetter lgs = new LoginGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("RESULT")) {
						lgs.setResult(xpp.nextText());
					}
					if (xpp.getName().equals("APP_VERSION")) {
						lgs.setVERSION(xpp.nextText());
					}
					if (xpp.getName().equals("APP_PATH")) {
						lgs.setPATH(xpp.nextText());
					}
					if (xpp.getName().equals("CURRENTDATE")) {
						lgs.setDATE(xpp.nextText());
					}
					
					if (xpp.getName().equals("RIGHTNAME")) {
						lgs.setRIGHTNAME(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lgs;
	}

	// FAILURE XML HANDLER
	public static FailureGetterSetter failureXMLHandler(XmlPullParser xpp,
			int eventType) {
		FailureGetterSetter failureGetterSetter = new FailureGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("STATUS")) {
						failureGetterSetter.setStatus(xpp.nextText());
					}
					if (xpp.getName().equals("ERRORMSG")) {
						failureGetterSetter.setErrorMsg(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return failureGetterSetter;
	}

	// JCP XML HANDLER
	public static JourneyPlanGetterSetter JCPXMLHandler(XmlPullParser xpp, int eventType) {
		JourneyPlanGetterSetter jcpGetterSetter = new JourneyPlanGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setTable_journey_plan(xpp.nextText());
					}
					
					if (xpp.getName().equals("STORE_CD")) {
						jcpGetterSetter.setStore_cd(xpp.nextText());
					}
					if (xpp.getName().equals("EMP_CD")) {
						jcpGetterSetter.setEmp_cd(xpp.nextText());
					}
					if (xpp.getName().equals("VISIT_DATE")) {
						jcpGetterSetter.setVISIT_DATE(xpp.nextText());
					}
					if (xpp.getName().equals("KEYACCOUNT")) {
						jcpGetterSetter.setKey_account(xpp.nextText());
					}
					if (xpp.getName().equals("STORENAME")) {
						jcpGetterSetter.setStore_name(xpp.nextText());
					}
					if (xpp.getName().equals("CITY")) {
						jcpGetterSetter.setCity(xpp.nextText());
					}
					if (xpp.getName().equals("STORETYPE")) {
						jcpGetterSetter.setStore_type(xpp.nextText());
					}
					if (xpp.getName().equals("UPLOAD_STATUS")) {
						jcpGetterSetter.setUploadStatus(xpp.nextText());
					}
					/*if (xpp.getName().equals("CATEGORY_TYPE")) {
						jcpGetterSetter.setCategory_type(xpp.nextText());
					}*/
					if (xpp.getName().equals("CHECKOUT_STATUS")) {
						jcpGetterSetter.setCheckOutStatus(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}

	public static JCPGetterSetter LayoutmasterHandler(XmlPullParser xpp,
			int eventType) {
		JCPGetterSetter pgs = new JCPGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("STORE_LAYOUT")) {
						pgs.setStore_layout(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pgs;
	}

	public static JCPGetterSetter SizemasterHandler(XmlPullParser xpp,
			int eventType) {
		JCPGetterSetter pgs = new JCPGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("STORE_SIZE")) {
						pgs.setStore_size(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pgs;
	}

	public static ComplianceByMappingGetterSetter PlanogramMapping(
			XmlPullParser xpp, int eventType) {
		ComplianceByMappingGetterSetter Mappingcompliance = new ComplianceByMappingGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("PROMOTION_CD")) {
						Mappingcompliance.setMappingCompliancePromotion(xpp
								.nextText());
					}
					if (xpp.getName().equals("REGION_CD")) {
						Mappingcompliance.setRegion_id(xpp.nextText());
					}

					if (xpp.getName().equals("STORETYPE_CD")) {
						Mappingcompliance.setStore_typeid(xpp.nextText());
					}

					if (xpp.getName().equals("CATEGORY_CD")) {
						Mappingcompliance.setCATEGORY_ID(xpp.nextText());
					}

					if (xpp.getName().equals("BRAND_CATEGORY_CD")) {
						Mappingcompliance.setBRAND_CATEGORY_ID(xpp.nextText());
					}

					if (xpp.getName().equals("PLANOGRAM_IMAGE")) {
						Mappingcompliance.setPlanogram_image(xpp.nextText());
					}
					if (xpp.getName().equals("IMAGE_PATH")) {
						Mappingcompliance.setPath(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Mappingcompliance;

	}

	public static PromotionalMasterGettersetter PromotionalmasterHandler(
			XmlPullParser xpp, int eventType) {
		PromotionalMasterGettersetter pgs = new PromotionalMasterGettersetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("PROMOTION_CD")) {
						pgs.setpromotionid(xpp.nextText());
					}
					if (xpp.getName().equals("PROMOTION")) {
						pgs.setpromotionname(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pgs;
	}

	public static SkuMasterGetterSetter SkuMaster(XmlPullParser xpp,
			int eventType) {
		SkuMasterGetterSetter skumaster = new SkuMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("SKU_CD")) {
						skumaster.setSku_cd(xpp.nextText());
					}
					if (xpp.getName().equals("SKU")) {
						skumaster.setSku(xpp.nextText());
					}
					if (xpp.getName().equals("BRAND_CD")) {
						skumaster.setBrand_cd(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return skumaster;
	}

	// non working reason handler

	public static NonWrkingMasterGetterSetter nonwrking(XmlPullParser xpp,
			int eventType) {
		NonWrkingMasterGetterSetter non_wrking_reason = new NonWrkingMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("REASON_ID")) {
						non_wrking_reason
								.setNon_WorkingReasonId(xpp.nextText());
					}
					if (xpp.getName().equals("REASON")) {
						non_wrking_reason.setNon_WorkingReasonName(xpp
								.nextText());
					}
					if (xpp.getName().equals("IMAGE_ALLOW")) {
						non_wrking_reason.setNon_WorkingReasonValid(xpp
								.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return non_wrking_reason;

	}
	
	
	public static NonWrkingMasterGetterSetter nonwrkingSubReason(XmlPullParser xpp,
			int eventType) {
		NonWrkingMasterGetterSetter non_wrking_reason = new NonWrkingMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("SUB_REASON_ID")) {
						non_wrking_reason
								.setSubReason_id(xpp.nextText());
					}
					if (xpp.getName().equals("REASON_ID")) {
						non_wrking_reason.setNon_WorkingReasonId(xpp
								.nextText());
					}
					if (xpp.getName().equals("SUB_REASON")) {
						non_wrking_reason.setSub_reason(xpp
								.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return non_wrking_reason;

	}

	public static SkuMappingGetterSetter sku_mapping(XmlPullParser xpp,
			int eventType) {
		SkuMappingGetterSetter non_wrking_reason = new SkuMappingGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("SKU_CD")) {
						non_wrking_reason.setSku_id(xpp.nextText());
					}
					if (xpp.getName().equals("STORETYPE_CD")) {
						non_wrking_reason.setStoretype_id(xpp.nextText());
					}
					if (xpp.getName().equals("REGION_CD")) {
						non_wrking_reason.setREGION_CD(xpp.nextText());
					}
					if (xpp.getName().equals("SKU_SEQUENCE")) {
						non_wrking_reason.setSKU_SEQUENCE(xpp.nextText());
					}
					if (xpp.getName().equals("CORE_SKU")) {
						non_wrking_reason.setCore_sku(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return non_wrking_reason;

	}

	// complianceXML handler

	public static ComplianceGetterSetter ComplianceXMLHandler(
			XmlPullParser xpp, int eventType) {
		ComplianceGetterSetter compliance = new ComplianceGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("COMPLIANCE_CD")) {
						compliance.setComplianceID(xpp.nextText());
					}
					if (xpp.getName().equals("COMPLIANCE")) {
						compliance.setComplianceName(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return compliance;

	}

	// XML handler for mapping compliance

	public static ComplianceByMappingGetterSetter ComplianceByMapping(
			XmlPullParser xpp, int eventType) {
		ComplianceByMappingGetterSetter Mappingcompliance = new ComplianceByMappingGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("COMPLIANCE_CD")) {
						Mappingcompliance
								.setMappingComplianceId(xpp.nextText());
					}
					if (xpp.getName().equals("PROMOTION_CD")) {
						Mappingcompliance.setMappingCompliancePromotion(xpp
								.nextText());
					}
					if (xpp.getName().equals("REGION_CD")) {
						Mappingcompliance.setRegion_id(xpp.nextText());
					}

					if (xpp.getName().equals("STORETYPE_CD")) {
						Mappingcompliance.setStore_typeid(xpp.nextText());
					}

					if (xpp.getName().equals("CATEGORY_CD")) {
						Mappingcompliance.setCATEGORY_ID(xpp.nextText());
					}

					if (xpp.getName().equals("BRAND_CATEGORY_CD")) {
						Mappingcompliance.setBRAND_CATEGORY_ID(xpp.nextText());
					}
					if (xpp.getName().equals("BRAND_CATEGORY")) {
						Mappingcompliance.setBRAND_CATEGORY(xpp.nextText());
					}
					if (xpp.getName().equals("MONTH_HERO")) {
						Mappingcompliance.setMONTH_HERO(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Mappingcompliance;

	}

	public static ComplianceByMappingGetterSetter ComplianceByMappingSpecial(
			XmlPullParser xpp, int eventType) {
		ComplianceByMappingGetterSetter Mappingcompliance = new ComplianceByMappingGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("COMPLIANCE_CD")) {
						Mappingcompliance
								.setMappingComplianceId(xpp.nextText());
					}
					if (xpp.getName().equals("PROMOTION_CD")) {
						Mappingcompliance.setMappingCompliancePromotion(xpp
								.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Mappingcompliance;

	}

	// Posm Master
	// XML handler for mapping compliance

	public static PosmGetterSetter Posm(XmlPullParser xpp, int eventType) {
		PosmGetterSetter Mappingcompliance = new PosmGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("POSM_CD")) {
						Mappingcompliance.setPosmId(xpp.nextText());
					}
					if (xpp.getName().equals("POSM")) {
						Mappingcompliance.setPosm(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Mappingcompliance;

	}

	public static JCPGetterSetter GeoTagXMLHandler(XmlPullParser xpp,
			int eventType) {
		JCPGetterSetter jcpGetterSetter = new JCPGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("STORE_CD")) {
						jcpGetterSetter.setStoreid(xpp.nextText());
					}
					if (xpp.getName().equals("STORENAME")) {
						jcpGetterSetter.setStorename(xpp.nextText());
					}
					if (xpp.getName().equals("KEYACCOUNT")) {
						jcpGetterSetter.setKey_account(xpp.nextText());
					}
					if (xpp.getName().equals("STORETYPE")) {
						jcpGetterSetter.setStore_typeid(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY")) {
						jcpGetterSetter.setCATEGORY_ID(xpp.nextText());
					}
					if (xpp.getName().equals("GEO_TAG_STATUS")) {
						jcpGetterSetter.setStatus(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	
	
	public static SkuMasterGetterSetter storeListXML(XmlPullParser xpp,
			int eventType) {
		SkuMasterGetterSetter skumaster = new SkuMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						skumaster.setSku_master_table(xpp.nextText());
					}
					if (xpp.getName().equals("SKU_CD")) {
						skumaster.setSku_cd(xpp.nextText());
					}
					if (xpp.getName().equals("SKU")) {
						skumaster.setSku(xpp.nextText());
					}
					if (xpp.getName().equals("BRAND_CD")) {
						skumaster.setBrand_cd(xpp.nextText());
					}
					if (xpp.getName().equals("BRAND")) {
						skumaster.setBrand(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY")) {
						skumaster.setCategory(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY_CD")) {
						skumaster.setCategory_cd(xpp.nextText());
					}
					/*if (xpp.getName().equals("MRP")) {
						skumaster.setMrp(xpp.nextText());
					}*/
				/*	if (xpp.getName().equals("CATEGORY_TYPE")) {
						skumaster.setCategory_type(xpp.nextText());
					}
					
					if (xpp.getName().equals("PACKING")) {
						skumaster.setPacking_size(xpp.nextText());
					}*/
					
					if (xpp.getName().equals("SKU_SEQUENCE")) {
						skumaster.setSku_sequence(xpp.nextText());
					}
					
					if (xpp.getName().equals("BRAND_SEQUENCE")) {
						skumaster.setBrand_sequence(xpp.nextText());
					}
					
					if (xpp.getName().equals("CATEGORY_SEQUENCE")) {
						skumaster.setCategory_sequence(xpp.nextText());
					}
					
					
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return skumaster;
	}
	
	
	public static MappingAvailabilityGetterSetter mappingavailXML(XmlPullParser xpp,
			int eventType) {
		MappingAvailabilityGetterSetter mappingavail = new MappingAvailabilityGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						mappingavail.setMapping_avail_table(xpp.nextText());
					}
					if (xpp.getName().equals("STORE_CD")) {
						mappingavail.setStore_cd(xpp.nextText());
					}
					if (xpp.getName().equals("SKU_CD")) {
						mappingavail.setSku_cd(xpp.nextText());
					}
					/*if (xpp.getName().equals("CATEGORY_SEQUENCE")) {
						mappingavail.setCategory_sequence(xpp.nextText());
					}
					if (xpp.getName().equals("BRAND_SEQUENCE")) {
						mappingavail.setBrand_sequence(xpp.nextText());
					}
					if (xpp.getName().equals("SKU_SEQUENCE")) {
						mappingavail.setSku_sequence(xpp.nextText());
					}*/
					
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mappingavail;
	}
	
	
	public static MappingPromotionGetterSetter mappingpromotXML(XmlPullParser xpp,
			int eventType) {
		MappingPromotionGetterSetter mappingpromo = new MappingPromotionGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						mappingpromo.setMapping_promotion_table(xpp.nextText());
					}
					if (xpp.getName().equals("STORE_CD")) {
						mappingpromo.setStore_cd(xpp.nextText());
					}
					if (xpp.getName().equals("PID")) {
						mappingpromo.setPid(xpp.nextText());
					}
					if (xpp.getName().equals("PROMOTION")) {
						mappingpromo.setPromotion(xpp.nextText());
					}
					if (xpp.getName().equals("BRAND_CD")) {
						mappingpromo.setBrand_cd(xpp.nextText());
					}
					/*if (xpp.getName().equals("SKU_SEQUENCE")) {
						mappingpromo.setSku_sequence(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY_TYPE")) {
						mappingpromo.setCategory_type(xpp.nextText());
					}*/
					
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mappingpromo;
	}
	
	
	public static MappingAssetGetterSetter mappingassetXML(XmlPullParser xpp,
			int eventType) {
		MappingAssetGetterSetter mappingasset = new MappingAssetGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						mappingasset.setMapping_asset_table(xpp.nextText());
					}
					if (xpp.getName().equals("STORE_CD")) {
						mappingasset.setStore_cd(xpp.nextText());
					}
					if (xpp.getName().equals("BRAND_ID")) {
						mappingasset.setBrand_id(xpp.nextText());
					}
					if (xpp.getName().equals("ASSET_CD")) {
						mappingasset.setAsset_cd(xpp.nextText());
					}
					
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mappingasset;
	}
	
	
	public static DeepFreezerGetterSetter deepfreezXML(XmlPullParser xpp,
			int eventType) {
		DeepFreezerGetterSetter deepfreez = new DeepFreezerGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						deepfreez.setDeepfreeztable(xpp.nextText());
					}
					if (xpp.getName().equals("FID")) {
						deepfreez.setFid(xpp.nextText());
					}
					if (xpp.getName().equals("DEEP_FREEZER")) {
						deepfreez.setDeep_frrezer(xpp.nextText());
					}
					if (xpp.getName().equals("FREEZER_TYPE")) {
						deepfreez.setType(xpp.nextText());
					}
					if (xpp.getName().equals("SEQUENCE")) {
						deepfreez.setSequence(xpp.nextText());
					}
					
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deepfreez;
	}
	
	
	public static AssetMasterGetterSetter assetMasterXML(XmlPullParser xpp,
			int eventType) {
		AssetMasterGetterSetter assetmaster = new AssetMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						assetmaster.setAsset_master_table(xpp.nextText());
					}
					if (xpp.getName().equals("ASSET_CD")) {
						assetmaster.setAsset_cd(xpp.nextText());
					}
					if (xpp.getName().equals("ASSET")) {
						assetmaster.setAsset(xpp.nextText());
					}
					
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assetmaster;
	}



	public static CompanyGetterSetter comapnyMasterXML(XmlPullParser xpp,
														 int eventType) {
		CompanyGetterSetter assetmaster = new CompanyGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						assetmaster.setCompany_master_table(xpp.nextText());
					}
					if (xpp.getName().equals("COMPANY_CD")) {
						assetmaster.setCompany_cd(xpp.nextText());
					}
					if (xpp.getName().equals("COMPANY")) {
						assetmaster.setCompany(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assetmaster;
	}

	//Performance
	
	public static PerformanceGetterSetter performanceXML(XmlPullParser xpp,
			int eventType) {
		PerformanceGetterSetter performanceGetterSetter = new PerformanceGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						performanceGetterSetter.setPerformance_table(xpp.nextText());
					}
					if (xpp.getName().equals("STORE_CD")) {
						performanceGetterSetter.setStore_cd(xpp.nextText());
					}
					if (xpp.getName().equals("TARGET")) {
						performanceGetterSetter.setMonthly_target(xpp.nextText());
					}
					
					if (xpp.getName().equals("SALE")) {
						performanceGetterSetter.setMtd_sales(xpp.nextText());
					}
					
					if (xpp.getName().equals("ACH")) {
						performanceGetterSetter.setAchievement(xpp.nextText());
					}
					
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return performanceGetterSetter;
	}
	
	
	//Closing Cold
	
		public static ColdroomClosingGetterSetter closincoldXML(XmlPullParser xpp,
				int eventType) {
			ColdroomClosingGetterSetter closingcoldGetterSetter = new ColdroomClosingGetterSetter();

			try {
				while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
					if (xpp.getEventType() == XmlPullParser.START_TAG) {

						if (xpp.getName().equals("META_DATA")) {
							closingcoldGetterSetter.setClosing_cold_table(xpp.nextText());
						}
						if (xpp.getName().equals("STORE_CD")) {
							closingcoldGetterSetter.setStore_cd(xpp.nextText());
						}
						if (xpp.getName().equals("SKU_CD")) {
							closingcoldGetterSetter.setSku_cd(xpp.nextText());
						}
						
						if (xpp.getName().equals("CLOSING_STOCK_COLD")) {
							closingcoldGetterSetter.setClosing_cold(xpp.nextText());
						}
					
						
					}
					xpp.next();
				}
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return closingcoldGetterSetter;
		}
	
	
	
	public static NonWorkingReasonGetterSetter nonWorkinReasonXML(XmlPullParser xpp,
			int eventType) {
		NonWorkingReasonGetterSetter nonworking = new NonWorkingReasonGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						nonworking.setNonworking_table(xpp.nextText());
					}
					if (xpp.getName().equals("REASON_CD")) {
						nonworking.setReason_cd(xpp.nextText());
					}
					if (xpp.getName().equals("REASON")) {
						nonworking.setReason(xpp.nextText());
					}
					if (xpp.getName().equals("ENTRY_ALLOW")) {
						nonworking.setEntry_allow(xpp.nextText());
					}
					
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nonworking;
	}
	
	
	public static CategoryMasterGetterSetter categoryMasterXML(XmlPullParser xpp,
			int eventType) {
		CategoryMasterGetterSetter category = new CategoryMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						category.setCategory_master_table(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY_CD")) {
						category.setCategory_cd(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY")) {
						category.setCategory(xpp.nextText());
					}
					
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}


	//Question Data

	// JCP XML HANDLER
	public static QuestionGetterSetter QuestionXMLHandler(XmlPullParser xpp, int eventType) {
		QuestionGetterSetter qnsGetterSetter = new QuestionGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						qnsGetterSetter.setTable_question_today(xpp.nextText());
					}

					if (xpp.getName().equals("QUESTION_ID")) {
						qnsGetterSetter.setQuestion_cd(xpp.nextText());
					}
					if (xpp.getName().equals("QUESTION")) {
						qnsGetterSetter.setQuestion(xpp.nextText());
					}
					if (xpp.getName().equals("ANSWER_ID")) {
						qnsGetterSetter.setAnswer_cd(xpp.nextText());
					}
					if (xpp.getName().equals("ANSWER")) {
						qnsGetterSetter.setAnswer(xpp.nextText());
					}
					if (xpp.getName().equals("RIGHT_ANSWER")) {
						qnsGetterSetter.setRight_answer(xpp.nextText());
					}
					if (xpp.getName().equals("STATUS")) {
						qnsGetterSetter.setStatus(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qnsGetterSetter;
	}

	/*public static StoreListGetterSetter storeStruct(XmlPullParser xpp,
			int eventType) {
		StoreListGetterSetter skumaster = new StoreListGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						skumaster.setStoreTable(xpp.nextText());
					}
			
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return skumaster;
	}

	
	
	//data
	
	public static NonWrkingMasterGetterSetter nonworkingData(XmlPullParser xpp, int eventType) {
		NonWrkingMasterGetterSetter jcpGetterSetter = new NonWrkingMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setNonworkingtable(xpp.nextText());
					}
					
					if (xpp.getName().equals("REASON_ID")) {
						jcpGetterSetter.setNon_WorkingReasonId(xpp.nextText());
					}
					if (xpp.getName().equals("REASON")) {
						jcpGetterSetter.setNon_WorkingReasonName(xpp.nextText());
					}
					if (xpp.getName().equals("SUB_REASON_ID")) {
						jcpGetterSetter.setSubReason_id(xpp.nextText());
					}
					if (xpp.getName().equals("SUB_REASON")) {
						jcpGetterSetter.setSub_reason(xpp.nextText());
					}
					
					if (xpp.getName().equals("IMAGE_ALLOW")) {
						jcpGetterSetter.setImageAllow(xpp.nextText());
					}
					if (xpp.getName().equals("ENTRY_NOT_ALLOW")) {
						jcpGetterSetter.setEntryAlow(xpp.nextText());
					}
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	 
	// supervior download
	
	
	public static SupervisorGetterSetter distributorName(XmlPullParser xpp, int eventType) {
		SupervisorGetterSetter jcpGetterSetter = new SupervisorGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setSupervisortable(xpp.nextText());
					}
					
					if (xpp.getName().equals("KEYACCOUNT_CD")) {
						jcpGetterSetter.setSupervisorId(xpp.nextText());
					}
					if (xpp.getName().equals("KEYACCOUNT")) {
						jcpGetterSetter.setSupervisorname(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	// supervisor journey
	
	public static JourneyPlanGetterSetter supervisorJourney(XmlPullParser xpp, int eventType) {
		JourneyPlanGetterSetter jcpGetterSetter = new JourneyPlanGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setTablemerchJourney(xpp.nextText());
					}
					
					if (xpp.getName().equals("MERCHANDISER_CD")) {
						jcpGetterSetter.setMerchanId(xpp.nextText());
					}
					if (xpp.getName().equals("VISIT_DATE")) {
						jcpGetterSetter.setVisitdate(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	//designation
	
	public static DesignationGetterSetter designation(XmlPullParser xpp, int eventType) {
		DesignationGetterSetter jcpGetterSetter = new DesignationGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setTabledesig(xpp.nextText());
					}
					
					if (xpp.getName().equals("DESIGNATION_CD")) {
						jcpGetterSetter.setDesigId(xpp.nextText());
					}
					if (xpp.getName().equals("DESIGNATION")) {
						jcpGetterSetter.setDesig(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	
	
	public static Deviation_Reason deviationReason(XmlPullParser xpp, int eventType) {
		Deviation_Reason jcpGetterSetter = new Deviation_Reason();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.settabledeviation(xpp.nextText());
					}
					
					if (xpp.getName().equals("DREASON_ID")) {
						jcpGetterSetter.setdevi_id(xpp.nextText());
					}
					if (xpp.getName().equals("DREASON")) {
						jcpGetterSetter.setdevi_name(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	
	
	public static Sup_Window supWindow(XmlPullParser xpp, int eventType) {
		Sup_Window jcpGetterSetter = new Sup_Window();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setSupWindowTable(xpp.nextText());
					}
					
					if (xpp.getName().equals("CATEGORY_CD")) {
						jcpGetterSetter.setCat_id(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY")) {
						jcpGetterSetter.setCat_name(xpp.nextText());
					}
					
					if (xpp.getName().equals("BRAND_CD")) {
						jcpGetterSetter.setBrand_id(xpp.nextText());
					}
					if (xpp.getName().equals("BRAND")) {
						jcpGetterSetter.setBrand_name(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	
	
	public static Sup_Performance supPerformance(XmlPullParser xpp, int eventType) {
		Sup_Performance jcpGetterSetter = new Sup_Performance();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setSupPerformanceTable(xpp.nextText());
					}
					
					if (xpp.getName().equals("MANDAYS")) {
						jcpGetterSetter.setMandays(xpp.nextText());
					}
					if (xpp.getName().equals("COVERAGE")) {
						jcpGetterSetter.setCoverage(xpp.nextText());
					}
					
					if (xpp.getName().equals("MERCHANDISED")) {
						jcpGetterSetter.setMerchandised(xpp.nextText());
					}
					if (xpp.getName().equals("COMPLIANCE")) {
						jcpGetterSetter.setCompliance(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	
	public static Sup_Merchandiser supMerchandise(XmlPullParser xpp, int eventType) {
		Sup_Merchandiser jcpGetterSetter = new Sup_Merchandiser();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setSupMerchandiserTable(xpp.nextText());
					}
					
					if (xpp.getName().equals("MERCHANDISER")) {
						jcpGetterSetter.setMerchandiser(xpp.nextText());
					}
					
					if (xpp.getName().equals("MANDAYS")) {
						jcpGetterSetter.setMandays(xpp.nextText());
					}
					if (xpp.getName().equals("COVERAGE")) {
						jcpGetterSetter.setCoverage(xpp.nextText());
					}
					
					if (xpp.getName().equals("MERCHANDISED")) {
						jcpGetterSetter.setMerchandised(xpp.nextText());
					}
					if (xpp.getName().equals("COMPLIANCE")) {
						jcpGetterSetter.setCompliance(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}*/

	
}
