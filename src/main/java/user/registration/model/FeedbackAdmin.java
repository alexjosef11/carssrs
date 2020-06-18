package user.registration.model;

public class FeedbackAdmin {

    public static String Responses;

    public static String getResponses() {
        return Responses;
    }

    public void setResponses(String Responses) {
        this.Responses = Responses;
    }

    public FeedbackAdmin(String Responses){
        this.Responses=Responses;
    }

    public FeedbackAdmin(){

    }


}
