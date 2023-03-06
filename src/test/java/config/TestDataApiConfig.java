package config;

public class TestDataApiConfig {

    public static String apiRequestPath = "src/test/java/testDataApi/requests/";
    public static String apiBaseAddress = "<URL TO TEST DATA API>";

    public enum Endpoints {
        OFFENDER ("offender/", "offender/update/"),
        EVENT ("event/", "event/update/"),
        CONTACT ("contact/", "contact/update/");


        private final String endpointName;
        private final String updateEndpointName;

        Endpoints(String endpointName, String updateEndpointName) {
            this.endpointName = endpointName;
            this.updateEndpointName  = updateEndpointName;
        }

        public String getEndpointName() {
            return endpointName;
        }
        public String getUpdateEndpointName() { return updateEndpointName; }
    }
}
