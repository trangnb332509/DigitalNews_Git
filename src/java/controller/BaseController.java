/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ArticleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Article;

/**
 *
 * @author Dell Inc
 */
public abstract class BaseController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArticleDAO articleDAO = new ArticleDAO();
            //get top 1 article
            Article mostRecentArticle = articleDAO.getTop1();
            //get top 5 articles
            ArrayList<Article> fiveRecentArticle = articleDAO.get5RecentNews();
            request.setAttribute("mostRecentArticle", mostRecentArticle);
            request.setAttribute("fiveRecentArticle", fiveRecentArticle);
            processGet(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "Sorry! There is an error now");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processPost(request, response);
    }
    
    protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
    

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
