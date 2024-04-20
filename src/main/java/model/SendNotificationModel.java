package model;

public class SendNotificationModel {
    private String title;
    private  Type type;
    private String subject;
    private String message;

    public SendNotificationModel(String title, String subject, String message) {

    }

    public SendNotificationModel(String title, Type type, String subject, String message) {
        this.title = title;
        this.type = type;
        this.subject = subject;
        this.message = message;
    }



    public enum Type {
        WATER,
        ELECTRICITY,
        SOLAR
    }
//
//    public SendNotificationModel() {
//        this.title = title;
//        this.type = type;
//        this.subject = subject;
//        this.message = message;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
