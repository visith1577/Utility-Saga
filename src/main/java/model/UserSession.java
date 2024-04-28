package model;

public class UserSession {
    private Integer id;
    private String sessionId;
    private String userName;
    private String createdTimestamp;
    private Integer isActive; // default 1

    public enum UserSessionActiveStatus {
        ACTIVE(1), IN_ACTIVE(0);
        private final Integer key;
        UserSessionActiveStatus(Integer key) {
            this.key = key;
        }
        public Integer getKey() {
            return key;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public UserSessionActiveStatus getIsActive() {
        return isActive == 1 ? UserSessionActiveStatus.ACTIVE : UserSessionActiveStatus.IN_ACTIVE;
    }

    public void setIsActive(UserSessionActiveStatus activeStatus) {
        this.isActive = activeStatus.getKey();
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", userName='" + userName + '\'' +
                ", createdTimestamp='" + createdTimestamp + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
