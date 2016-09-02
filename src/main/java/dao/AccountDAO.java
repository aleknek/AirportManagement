package dao;

import bin.Account;
import bin.Role;
import database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDAO {

    private static ResultSet rs;

    public boolean checkAccount(int idAccount, int idRole) {

        try {
            String sqlQuery;
            sqlQuery = "SELECT link_role_account_table2.id FROM db_airport.link_role_account AS link_role_account_table1 " +
                    "LEFT JOIN db_airport.link_role_account AS link_role_account_table2 " +
                    "ON link_role_account_table1.id = link_role_account_table2.id AND link_role_account_table2.id_account = '" + idAccount + "' " +
                    "WHERE link_role_account_table1.id_role = '" + idRole + "' AND NOT link_role_account_table2.id IS NULL ";

            PreparedStatement preparedStatement = new DBConnection().getConn().prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account loadAccountByPasswordAndLogin(String login, String password) {

        Account account = new Account();

        try {
            PreparedStatement preparedStatement = new DBConnection().getConn().prepareStatement("SELECT * FROM db_airport.account WHERE login = ? AND password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                account.setId(rs.getInt("id"));
                account.setUsername(rs.getString("login"));
                account.setPassword(rs.getString("password"));
                List<Role> roles = new RoleDAO().loadRoleByIdAccount(rs.getInt("id"));
                account.setRoles(roles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
