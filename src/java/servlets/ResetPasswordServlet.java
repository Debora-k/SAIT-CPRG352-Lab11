
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.AccountService;

public class ResetPasswordServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // this "uuid" is from a link
        String uuid = request.getParameter("uuid");
        // hiddenuuid is from jsp
        request.setAttribute("hiddenuuid", uuid);
        
        if(uuid != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        }
      
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        // to reset page
        String url = request.getRequestURL().toString();
        
        AccountService as = new AccountService();
        String path = getServletContext().getRealPath("/WEB-INF");
        as.resetPassword(email, path, url);
    }
}
