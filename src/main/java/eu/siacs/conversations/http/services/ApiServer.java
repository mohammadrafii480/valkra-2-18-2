package eu.siacs.conversations.http.services;

public class ApiServer {
    public static final String BASE_URL_API = "https://vaa.reg.valkra.net/";  //prod
    public static final String BASE_URL_API_FILE = "https://valkra.net/"; //prod
    public static final String BASE_URL_API_DEV = "https://vaa.m.valkra.net:5280/";  //prod

    public static ApiService getAPIService() {
        return ApiClient.getClientUnsafe(BASE_URL_API).create(ApiService.class);
    }
    public static ApiService getAPIServiceDev() {
        return ApiClient.getClientUnsafe(BASE_URL_API_DEV).create(ApiService.class);
    }

}
