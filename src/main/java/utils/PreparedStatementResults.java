package utils;

import java.sql.PreparedStatement;

public class PreparedStatementResults {
    private final PreparedStatement stmt;
    private final PreparedStatement insertStmt;

    public PreparedStatementResults(PreparedStatement stmt, PreparedStatement insertStmt) {
        this.stmt = stmt;
        this.insertStmt = insertStmt;
    }

    public PreparedStatement getStmt() {
        return stmt;
    }

    public PreparedStatement getInsertStmt() {
        return insertStmt;
    }
}
