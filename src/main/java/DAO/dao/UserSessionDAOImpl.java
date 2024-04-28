package DAO.dao;

import DAO.impl.UserSessionDAO;
import model.UserSession;
import utils.Constant;
import utils.DBConnectionManager;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserSessionDAOImpl implements UserSessionDAO {

    private DBConnectionManager dbConnectionManager;

    private static final String ADD_USER_SESSION = "INSERT INTO user_session (session_id, user_name, created_timestamp, is_active) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER_SESSION_ACTIVE_STATUS = "UPDATE user_session SET is_active = ? WHERE id IN ( ? )" ;
    private static final String SELECT_USERSESSION_BY_ID = "SELECT * FROM user_session WHERE id = ?";
    private static final String SELECT_USERSESSION_BY_SESSION_ID = "SELECT * FROM user_session WHERE session_id = ?";
    private static final String SELECT_ALL_USERSESSIONS = "SELECT * FROM user_session";
    private static final String SELECT_ALL_USERSESSIONS_BY_SESSION_ACTIVE_STATUS = "SELECT * FROM user_session where is_active = ?";

    public UserSessionDAOImpl() {
        try {
            dbConnectionManager = DBConnectionManager.getInstance();
        } catch (IOException e) {
            throw new RuntimeException("IOException : ", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ClassNotFoundException : ", e);
        } catch (SQLException e) {
            throw new RuntimeException("SQLException : ", e);
        }
    }

    @Override
    public Integer insertUserSession(UserSession userSession) throws SQLException{
        Integer id = null;
        try(PreparedStatement preparedStatement = dbConnectionManager.getConnection()
                .prepareStatement(ADD_USER_SESSION, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, userSession.getSessionId());
            preparedStatement.setString(2, userSession.getUserName());
            preparedStatement.setString(3, userSession.getCreatedTimestamp());
            preparedStatement.setInt(4, userSession.getIsActive().getKey());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            System.out.println("Item Id: " + id);
        }
        return id;
    }

    @Override
    public Boolean updateUserSessionActiveStatus(Integer activeStatus, List<Integer> ids) throws SQLException {
        try(PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(getUpdateQuery(ids.size()))){
            preparedStatement.setInt(1, activeStatus);
            for (int i = 1; i < ids.size(); i++) {
                preparedStatement.setInt(i + 1, ids.get(i));
            }
            preparedStatement.executeUpdate();
            return true;
        }
    }
    //TO-DO.................
    private String getUpdateQuery(int size) {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE user_session SET is_active = ? WHERE id IN ( ? ) ");
        for (int i = 0; i < size; i++) {
            sqlBuilder.append(" (?)");
            if (i < size - 1) {
                sqlBuilder.append(",");
            }
        }
        return sqlBuilder.toString();
    }

    @Override
    public Optional<UserSession> findUserSessionById(Integer userSessionID) {
        return getUserSession(userSessionID, null);
    }

    @Override
    public Optional<UserSession> findUserSessionBySessionId(String sessionID) {
        return getUserSession(null, sessionID);
    }

    private Optional<UserSession> getUserSession(Integer userSessionID, String sessionID) {
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        UserSession userSession = null;
        if(sessionID == null && userSessionID != null){
            try {
                preparedStatement = this.dbConnectionManager.getConnection().prepareStatement(SELECT_USERSESSION_BY_ID);
                preparedStatement.setInt(1, userSessionID);
                rs = preparedStatement.executeQuery();
                userSession = getUserSessionObject(rs);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                preparedStatement = this.dbConnectionManager.getConnection().prepareStatement(SELECT_USERSESSION_BY_SESSION_ID);
                preparedStatement.setString(1, sessionID);
                rs = preparedStatement.executeQuery();
                userSession = getUserSessionObject(rs);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.ofNullable(userSession);
    }

    private UserSession getUserSessionObject(ResultSet rs) throws SQLException {
        UserSession userSession = new UserSession();
        while (rs.next()) {
            userSession = new UserSession();
            userSession.setId(rs.getInt(Constant.USER_SESSION_TABLE_ID));
            userSession.setSessionId(rs.getString(Constant.USER_SESSION_TABLE_SESSION_ID));
            userSession.setUserName(rs.getString(Constant.USER_SESSION_TABLE_USER_NAME));
            userSession.setCreatedTimestamp(rs.getString(Constant.USER_SESSION_TABLE_CREATED_TIMESTAMP));
            userSession.setIsActive(rs.getInt(Constant.USER_SESSION_TABLE_IS_ACTIVE) == 1 ? UserSession.UserSessionActiveStatus.ACTIVE
                    : UserSession.UserSessionActiveStatus.IN_ACTIVE);
        }
        return userSession;
    }

    @Override
    public Optional<List<UserSession>> findAllUserSessions() throws SQLException {
        return getAllUserSessions(SELECT_ALL_USERSESSIONS, null);
    }

    @Override
    public Optional<List<UserSession>> findUserSessionsBySessionActiveStatus(Integer activeStatus) throws SQLException {
        return getAllUserSessions(SELECT_ALL_USERSESSIONS_BY_SESSION_ACTIVE_STATUS, activeStatus);
    }

    private Optional<List<UserSession>> getAllUserSessions(String queryType, Integer activeStatus) throws SQLException {
        List<UserSession> userSessionList = new ArrayList<>();
        if (SELECT_ALL_USERSESSIONS.equals(queryType)) {
            ResultSet rs = this.dbConnectionManager.executeQuery(queryType);
            while (rs.next()) {
                UserSession userSession = new UserSession();
                userSession.setId(rs.getInt(Constant.USER_SESSION_TABLE_ID));
                userSession.setSessionId(rs.getString(Constant.USER_SESSION_TABLE_SESSION_ID));
                userSession.setUserName(rs.getString(Constant.USER_SESSION_TABLE_USER_NAME));
                userSession.setCreatedTimestamp(rs.getString(Constant.USER_SESSION_TABLE_CREATED_TIMESTAMP));
                userSession.setIsActive(rs.getInt(Constant.USER_SESSION_TABLE_IS_ACTIVE) == 1 ? UserSession.UserSessionActiveStatus.ACTIVE
                        : UserSession.UserSessionActiveStatus.IN_ACTIVE);
                userSessionList.add(userSession);
            }
        } else {
            ResultSet rs = null;
            PreparedStatement preparedStatement = this.dbConnectionManager.getConnection()
                    .prepareStatement(SELECT_ALL_USERSESSIONS_BY_SESSION_ACTIVE_STATUS);
            preparedStatement.setInt(1, activeStatus);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                UserSession userSession = new UserSession();
                userSession.setId(rs.getInt(Constant.USER_SESSION_TABLE_ID));
                userSession.setSessionId(rs.getString(Constant.USER_SESSION_TABLE_SESSION_ID));
                userSession.setUserName(rs.getString(Constant.USER_SESSION_TABLE_USER_NAME));
                userSession.setCreatedTimestamp(rs.getString(Constant.USER_SESSION_TABLE_CREATED_TIMESTAMP));
                userSession.setIsActive(rs.getInt(Constant.USER_SESSION_TABLE_IS_ACTIVE) == 1 ? UserSession.UserSessionActiveStatus.ACTIVE
                        : UserSession.UserSessionActiveStatus.IN_ACTIVE);
                userSessionList.add(userSession);
            }
        }
        return Optional.ofNullable(userSessionList);
    }
}
