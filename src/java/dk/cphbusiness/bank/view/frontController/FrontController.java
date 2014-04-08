package dk.cphbusiness.bank.view.frontController;

import dk.cphbusiness.bank.view.Factory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joachim
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    private int PORT_NON_SSL;
    private int PORT_SSL;
    private boolean useSSL = true;
    private boolean autoLogon = false;
    private String autoLogonUser;
    private String autoLogonPassword;

    public FrontController() {

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cmdStr = request.getParameter("command");
        cmdStr = cmdStr != null ? cmdStr : "main";
        //Provides auto login. See LoginCommand for the full story
        if (request.getRemoteUser() == null) {
            cmdStr = "login";
//            request.setAttribute("autoLogon", "YES");
//            request.setAttribute("username", autoLogonUser);
//            request.setAttribute("password", autoLogonPassword);
        }

        Command command = Factory.getInstance().getCommand(cmdStr, request);

        String path = command.execute(request);

        if (useSSL) { //So we can disable the SSL switch
            SSL_Switch(command, request, response, path);
        } else {
            request.getRequestDispatcher(path).forward(request, response);
        }

    }

    private void SSL_Switch(Command command, HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        if (command instanceof ShowLoginCommand && !request.isSecure()) {
            String SSL = "https://" + request.getServerName() + ":" + PORT_SSL + request.getRequestURI() + "?command=showlogin";
            System.out.println("SSL redirect: " + SSL);
            response.sendRedirect(SSL);
        } else if (command instanceof LogoutCommand) {
            String nonSSL = "http://" + request.getServerName() + ":" + PORT_NON_SSL + request.getRequestURI();
            System.out.println("Non SSL redirect: " + nonSSL);
            response.sendRedirect(nonSSL);
        } else {
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        PORT_NON_SSL = Integer.parseInt(getServletContext().getInitParameter("portNonSSL"));
        PORT_SSL = Integer.parseInt(getServletContext().getInitParameter("portSSL"));
        useSSL = Boolean.parseBoolean(getServletContext().getInitParameter("useSSL"));
        autoLogon = Boolean.parseBoolean(getServletContext().getInitParameter("autoLogon"));
        autoLogonUser = getServletContext().getInitParameter("autoLogonUser");
        autoLogonPassword = getServletContext().getInitParameter("autoLogonPassword");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
