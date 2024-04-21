package model;

import java.time.LocalDateTime;

public class SendNotificationModel {
    private String title;
    private  Type type;
    private String subject;
    private String message;

    private RecipientType recipientType;
    private String recipientId;
    private LocalDateTime date;

    public SendNotificationModel(String title, String subject, String message) {

    }

    public SendNotificationModel(String title, Type type, String subject, String message) {
        this.title = title;
        this.type = type;
        this.subject = subject;
        this.message = message;
    }

    public SendNotificationModel() {
        this.title = title;
        this.recipientType = recipientType;
        this.recipientId = recipientId;
        this.date = date;
        this.subject = subject;
        this.message = message;
    }

    public SendNotificationModel(String title, RecipientType recipientType, String recipentId, String subject, String message) {
    }

    public SendNotificationModel(String title, RecipientType recipientType, String recipentId, String date, String subject, String message) {
    }

    public enum RecipientType {
        ALL,
        SPECIFIC
    }


    public enum Type {
        WATER,
        ELECTRICITY,
        SOLAR
    }

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

    public RecipientType getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(RecipientType recipientType) {
        this.recipientType = recipientType;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

