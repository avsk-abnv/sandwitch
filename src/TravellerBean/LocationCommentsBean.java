
package TravellerBean;

//creating class

public class LocationCommentsBean {
    
    private int location_comment_id;
    private String location_comment;
    private int location_id;
    private String email_id;
    
    //setter methods
    
    public void setLocationCommentId(int location_comment_id){
        this.location_comment_id=location_comment_id;
    }
    
    public void setLocationComment(String location_comment){
        this.location_comment=location_comment;
    }
    
    public void setLocationId(int location_id){
        this.location_id=location_id;
    }
    
    public void setEmailId(String email_id){
        this.email_id=email_id;
    }
    
    //getter methods
    
    public int getLocationCommentId(){
        return location_comment_id;
    }
    
    public String getLocationComment(){
        return location_comment;
    }
    
    public int getLocationId(){
        return location_id;
    }
    
    public String getEmailId(){
        return email_id;
    }
    
}
