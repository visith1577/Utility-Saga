package DAO.impl;

import model.UserSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserSessionDAO {

    Integer insertUserSession(UserSession userSession) throws SQLException;
    Boolean updateUserSessionActiveStatus(Integer activeStatus, List<Integer> ids) throws SQLException;
   // Boolean inActiveUserSessionById(Integer id) throws SQLException;
    Optional<UserSession> findUserSessionById(Integer userSessionID) throws SQLException;
    Optional<UserSession> findUserSessionBySessionId(String sessionID) throws SQLException;
    Optional<List<UserSession>> findAllUserSessions() throws SQLException;
    Optional<List<UserSession>> findUserSessionsBySessionActiveStatus(Integer activeStatus) throws SQLException;
}
