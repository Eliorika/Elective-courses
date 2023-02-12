package ru.rsreu.electivecourses.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.rsreu.electivecourses.commands.ActionCommand;

/**
 * Servlet implementation class FrontController
 */

public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of servlet
     */
    public FrontController() {
        super();
    }

    /**
     * Method processing GET methods from JSP pages
     * @param request   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Method processing POST methods from JSP pages
     * @param request   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       processRequest(request, response);
    }

    /**
     * Method processing DELETE methods from JSP pages
     * @param request   the {@link HttpServletRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param response  the {@link HttpServletResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendRequest(request, response);
    }

    /**
     * Forward to the next jsp
     * @param request   the {@link HttpServletRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param response  the {@link HttpServletResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

    /**
     * Send redirect to the next jsp
     * @param request   the {@link HttpServletRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param response  the {@link HttpServletResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     * @throws ServletException
     * @throws IOException
     */

    private void sendRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        String page = command.execute(request);
        response.sendRedirect(page);
    }

    /**
     * Servlet implementation class FrontController
     */
}