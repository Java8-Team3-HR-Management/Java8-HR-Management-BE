package com.HRMS.constants;

public class RestApiList {
    //Response Dto's constants

    public static int statusFailed=100;
    public static int statusSuccess=200;

    public static String messageFailed="This service is not unavailable now. Please try again later";
    public static String messageSuccess="Success!";

// End points constants

    public static final String API = "/api";
    public static final String VERSION = "/v1";
    public static final String COMPANY = API + VERSION + "/company";
    public static final String COMMENT = API + VERSION + "/comment";
    public static final String ADDCOMPANY = "/addCompany";
    public static final String GETALLCOMPANY = "/getAllCompany";
    public static final String GETALLCOMPANYAPPROVAL = "/getAllCompanyApproval";
    public static final String UPDATECOMPANY = "/updateCompany";
    public static final String GETCOMPANYBYNAME = "/getCompanyByName";
    public static final String EXPENSE= "/expense";
}
