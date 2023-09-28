package com.HRMS.constants;

public class RestApiList {
    public static final String API="/api";
    public static final String VERSION="/v1";
    public static final String USER=API+VERSION+"/user";
    public static final String DEBT=API+VERSION+"/debt";
    public static final String VACATION=API+VERSION+"/vacation";

    public static final String VIEWALLEMPLOYEE = USER+"/viewallemployee";

    public static final String CREATEVOCATION = USER + "/createvocation";
    public static final String FINDALLVOCATION = USER + "/findallvocation";

    public static final String FINDALLVOCATIONPENDING = USER + "/findallvocationpending";
    public static final String APPROVEVOCATIONREQUEST = USER + "/approvevocationrequest";
    public static final String REJECTVOCATIONREQUEST = USER + "/rejectvocationrequest";
    public static final String FINDALLBYSORT = USER + "/findallbyshort";
    public static final String ADVANCE = USER + "/advance";
    public static final String ALLDEBTS = USER + "/alldebts";
}
