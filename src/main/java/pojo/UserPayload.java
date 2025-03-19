package pojo;

public class UserPayload {
    /*
    {
    "name": "Tom Wills",
    "gender": "male",
    "email": "will.wils@mail.com",
    "status": "active"
}
     */

    private String name;
    private String gender;
    private String email;
    private String status;





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public void setName(String name){
        this.name = name;

    }
    public String getName(){
        return name;
    }

    public void setGender(String gender){
        this.gender = gender;

    }

    public String getGender(){
        return gender;
    }

}
