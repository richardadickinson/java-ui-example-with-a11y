package data;

public class SessionData {

    private static SessionData sessionData = new SessionData();
    private ThreadLocal<SessionData> threadLocal = new ThreadLocal<>();

    private void setSessionData(){
        threadLocal.set(sessionData);
    }


    private String Crn;

    public String getCrn() {
        return Crn;
    }

    public void setCrn(String crn) {
        this.Crn = crn;
    }


}
