package dao;

import bean.Role;
import database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoleDAO {

    private static ResultSet rs;

    public List<Role> loadAllRoles() {

        List<Role> roles = new LinkedList<Role>();

        try {
            String sqlQuery;
            sqlQuery = "SELECT * FROM role";

            PreparedStatement preparedStatement = DBConnection.getInstance().getConn().prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setName(rs.getString("name"));
                role.setId(rs.getInt("id"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public List<Role> loadRoleByIdAccount(int idAccount) {

        List<Role> roles = new ArrayList<Role>();
        try {
            String sqlQuery;
            sqlQuery = "SELECT role.id AS idRole, role.name AS nameRole FROM db_airport.link_role_account AS link_role_account " +
                    "LEFT JOIN db_airport.role AS role " +
                    "ON link_role_account.id_role = role.id " +
                    "WHERE link_role_account.id_account = '" + idAccount + "'";

            PreparedStatement preparedStatement =  DBConnection.getInstance().getConn().prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("idRole"));
                role.setName(rs.getString("nameRole"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }
}
