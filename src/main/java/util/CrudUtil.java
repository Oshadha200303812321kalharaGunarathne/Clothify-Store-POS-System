package util;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CrudUtil {

    public static ResultSet execute(String sql, Object... params) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            pstm.setObject(i + 1, params[i]);
        }

        if (sql.trim().toUpperCase().startsWith("SELECT")) {
            return pstm.executeQuery();
        }

        pstm.executeUpdate();
        return null;
    }
}
