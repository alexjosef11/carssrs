package user.registration.model;

import java.util.Objects;

public class Feedback {
    private String response1;
    private String response2;
    private String response3;
    private String response4;

    public Feedback(String response1, String response2, String response3, String response4){
        this.response1=response1;
        this.response2=response2;
        this.response3=response3;
        this.response4=response4;
    }
    public Feedback(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(response1, feedback.response1) &&
                Objects.equals(response2, feedback.response2) &&
                Objects.equals(response3, feedback.response3) &&
                Objects.equals(response4, feedback.response4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(response1, response2, response3, response4);
    }

    public String getResponse1() {
        return response1;
    }

    public void setResponse1(String response1) {
        this.response1 = response1;
    }

    public String getResponse2() {
        return response2;
    }

    public void setResponse2(String response2) {
        this.response2 = response2;
    }

    public String getResponse3() {
        return response3;
    }

    public void setResponse3(String response3) {
        this.response3 = response3;
    }

    public String getResponse4() {
        return response4;
    }

    public void setResponse4(String response4) {
        this.response4 = response4;
    }

    public String toString(){
        return this.response1+" "+ " "+this.response2+" "+this.response3+" "+this.response4;
    }

}
