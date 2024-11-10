package com.qp.assessment.gsms.util;


public interface CommonConstants {
	
	public interface Pagination {
		int DEFAULT_PAGE_NUMBER = 0;
		int DEFAULT_PAGE_SIZE = 10;
	}
	public interface Pagination1 {
		int DEFAULT_PAGE_NUMBER = 0;
		int DEFAULT_PAGE_SIZE = 100;
	}

	public interface ErrorConstants {
		String RESOURCE_ALREADY_IN_USE = "resource.already.in.use";
		String RESOURCE_ALREADY_EXISTS = "resource.already.exists";
		String RESOURCE_FOR_NOT_FOUND = "resource.for.not.found";
		String RESOURCE_FOR_NOT_REQUIRED = "resource.not.found";
		String REGISTRATION_FAILED = "registration.failed";
		String REQUIRED = "error.required";
		String MAXLENGTH = "error.maxlength";
		String MINLENGTH = "error.minlength";
		String BETWEENLENGTH = "error.betweenlength";
		String PASSWORD = "error.password";
		String VALID = "error.valid";
		String POSITIVE = "error.positive";
		String GREATER_THAN_OR_EQUAL_TO = "error.date.validation";
		
	}
	
	public interface LOGIN {
		public interface LABELS {
			String BASE = "label.login";
			String MOBILE_EMAILID = BASE+".mobileemailid";
			String PASSWORD = BASE+".password";
			String OTP = BASE+".otp";
			String EXPO_PUSH_TOKEN = BASE+".expopushtoken";
			String OTPMESSAGE = BASE+".otpmessage";
			String MOBILENOTFOUNDMESSAGE = BASE+".mobilenotfoundmessage";
			String APPNOTFOUNDMESSAGE = BASE+".appnotfoundmessage";
			String OTPEXPIRED = BASE+".otpexpired";
			String INVALIDOTP = BASE+".invalidotp";
			String REFRESHTOKENINVALID = BASE+".refreshtokeninvalid";
		}
		
		public interface VALIDATION {
			String OTP_REQUIRED = LABELS.OTP+" "+ErrorConstants.REQUIRED;
			String EXPO_PUSH_TOKEN_REQUIRED = LABELS.EXPO_PUSH_TOKEN+" "+ErrorConstants.REQUIRED;
			String PASSWORD_REQUIRED = LABELS.PASSWORD+" "+ErrorConstants.REQUIRED;
			String PASSWORD_BETWEENLENGTH = LABELS.PASSWORD+" "+ErrorConstants.BETWEENLENGTH;
			String PASSWORD_VALID = LABELS.PASSWORD+" "+ErrorConstants.VALID;
		}
	}
	
	public interface USER {
		public interface LABELS {
			String BASE = "label.user";
			String USER_NAME = "{"+BASE+".userName"+"}";
			String FISRT_NAME = "{"+BASE+".firstName"+"}";
			String LAST_NAME = "{"+BASE+".lastName"+"}";
			String ROLES = "{"+BASE+".roles"+"}";
			String MOBILENUMBER = "{"+BASE+".mobileNumber"+"}";
			String EMAILID = "{"+BASE+".emailId"+"}";
		}
		
		public interface VALIDATION {
			String FISRT_NAME_REQUIRED = LABELS.FISRT_NAME+" "+"{"+ErrorConstants.REQUIRED+"}";
			String FIRST_NAME_MAXLENGTH = LABELS.FISRT_NAME+" "+"{"+ErrorConstants.MAXLENGTH+"}";
			String LAST_NAME_REQUIRED = LABELS.LAST_NAME+" "+"{"+ErrorConstants.REQUIRED+"}";
			String LAST_NAME_MAXLENGTH = LABELS.LAST_NAME+" "+"{"+ErrorConstants.MAXLENGTH+"}";
			String USER_NAME_REQUIRED = LABELS.USER_NAME+" "+"{"+ErrorConstants.REQUIRED+"}";
			String USER_NAME_MAXLENGTH = LABELS.USER_NAME+" "+"{"+ErrorConstants.MAXLENGTH+"}";
			String ROLES_REQUIRED = LABELS.ROLES+" "+"{"+ErrorConstants.REQUIRED+"}";
			String MOBILENUMBER_REQUIRED = LABELS.MOBILENUMBER+" "+"{"+ErrorConstants.REQUIRED+"}";
			String MOBILENUMBER_BETWEENLENGTH = LABELS.MOBILENUMBER+" "+"{"+ErrorConstants.BETWEENLENGTH+"}";
			String EMAILID_REQUIRED = LABELS.EMAILID+" "+"{"+ErrorConstants.REQUIRED+"}";
			String EMAILID_MAXLENGTH = LABELS.EMAILID+" "+"{"+ErrorConstants.MAXLENGTH+"}";
		}
	}

	public interface ITEMS {
		public interface LABELS {
			String BASE = "label.items";
			String NAME = "{"+BASE+".name"+"}";
			String DESCRIPTION = "{"+BASE+".description"+"}";
			String PRICE = "{"+BASE+".price"+"}";
			String QUANTITY = "{"+BASE+".quantity"+"}";
			String FLAG = "{"+BASE+".flag"+"}";
		}
		
		public interface VALIDATION {
			String NAME_REQUIRED = LABELS.NAME+" "+"{"+ErrorConstants.REQUIRED+"}";
			String DESCRIPTION_REQUIRED = LABELS.DESCRIPTION+" "+"{"+ErrorConstants.REQUIRED+"}";
			String PRICE_REQUIRED = LABELS.PRICE+" "+"{"+ErrorConstants.REQUIRED+"}";
			String QUANTITY_REQUIRED = LABELS.QUANTITY+" "+"{"+ErrorConstants.REQUIRED+"}";
			String QUANTITY_BETWEENLENGTH = LABELS.QUANTITY+" "+"{"+ErrorConstants.BETWEENLENGTH+"}";
			String FLAG_REQUIRED = LABELS.FLAG+" "+"{"+ErrorConstants.REQUIRED+"}";
		}
	}
	
	public interface ValidationMessages {
	    public static final String USER_ID_REQUIRED = "User ID is required";
	    public static final String ITEMS_REQUIRED = "Items are required in the order";
	    public static final String ITEM_ID_REQUIRED = "Each order item must have an item ID";
	    public static final String QUANTITY_MINIMUM = "Quantity must be at least 1";
	}

	
}
