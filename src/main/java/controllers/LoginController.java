package controllers;

import constants.Constants;
import dao.AccountDAO;
import dao.RoleDAO;
import bean.Account;
import bean.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginController", urlPatterns = {"/login", "/login/"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        setAttributeRoles(req);
        req.setAttribute("currentPage", "login.jsp");
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Integer idRole = Integer.parseInt(req.getParameter("role"));

        if (idRole == null) {
            req.setAttribute("ErrorMessage", "roleIsEmpty");
            req.setAttribute("currentPage", "login.jsp");
            req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
        }
        if (username == null || username.isEmpty() && (password == null || password.isEmpty())) {
            req.setAttribute("ErrorMessage", "dataIsEmpty");
            req.setAttribute("currentPage", "login.jsp");
            req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
        }

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.loadAccountByPasswordAndLogin(username, password);

        if (account.getUsername() != null) {

            if (!accountDAO.checkAccount(account.getId(), idRole)) {
                setAttributeRoles(req);
                req.setAttribute("ErrorMessage", "incorrectRole");
                req.setAttribute("currentPage", "login.jsp");
                req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
            }

            if (idRole == 1) {
                req.getSession().setAttribute(Constants.CURRENT_MAPPING, "/staff");
            } else {
                req.getSession().setAttribute(Constants.CURRENT_MAPPING, "/passenger");
            }
            req.getSession().setAttribute(Constants.CURRENT_ROLE, idRole);
            if (idRole == 1) {
                resp.sendRedirect("/staff/home");
            } else {
                resp.sendRedirect("/passenger/home");
            }
        } else {
            setAttributeRoles(req);
            req.setAttribute("ErrorMessage", "accountNotFound");
            req.setAttribute("currentPage", "login.jsp");
            req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
        }
    }

    protected void setAttributeRoles(HttpServletRequest req) {
        List<Role> roles = new RoleDAO().loadAllRoles();
        req.setAttribute("roles", roles);
    }
}


