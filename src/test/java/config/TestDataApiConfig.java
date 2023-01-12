package config;

public class TestDataApiConfig {

    public static String ApiTestRequestPath = "src/test/java/testDataApi/requests/";
    public static String BaseAddress = "https://testdata-api.test.probation.service.justice.gov.uk/";

    public enum Endpoints {
        OFFENDER ("offender/", "offender/update/"),
        EVENT ("event/", "event/update/"),
        CONTACT ("contact/", "contact/update/");


        private final String insertEndpointName;
        private final String updateEndpointName;

        Endpoints(String insertEndpointName, String updateEndpointName) {
            this.insertEndpointName = insertEndpointName;
            this.updateEndpointName  = updateEndpointName;
        }

        public String getInsertEndpointName() {
            return insertEndpointName;
        }
        public String getUpdateEndpointName() { return updateEndpointName; }
    }
}
