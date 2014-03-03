/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.cphbusiness.bank.view.pageController;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.view.Factory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joachim
 */
@WebServlet(name = "ShowAccountDetail", urlPatterns = {"/ShowAccountDetail"})
public class ShowAccountDetail extends HttpServlet {
    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        BankManager manager = Factory.getInstance().getManager();
        AccountIdentifier accid = AccountDetail.fromString(request.getParameter("accid"));
        AccountDetail adetail = Factory.getInstance().getManager().showAccountHistory(accid);

        RequestDispatcher dispatcher = request.getRequestDispatcher("account-detail.jsp");
        dispatcher.forward(request, response);
    }
}
