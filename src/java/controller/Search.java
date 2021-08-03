/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ArticleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class Search extends BaseController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArticleDAO articleDAO = new ArticleDAO();
            String p_size = getServletContext().getInitParameter("pageSize");
            int pagesize = Integer.parseInt(p_size);
            String keyword = request.getParameter("txtSearch");
            //get total searched article 
            int total_article = articleDAO.countArticleBySearch(keyword);
            //get total page to paging
            int totalPage = (total_article % pagesize == 0) ? (total_article / pagesize) 
                    : (total_article / pagesize) + 1;
            
            ArrayList<Article> articles = new ArrayList<>();
            String p_index = request.getParameter("pageindex");
            int pageindex = 0;
            //set p_index for the first access
            if (p_index == null) {
                p_index = "1";
            }
            //handle when pageindex not number
            try {
                pageindex = Integer.parseInt(p_index);
            } catch (Exception ex) {
                request.setAttribute("result", "Invalid");
                request.getRequestDispatcher("search.jsp").forward(request, response);
            }

            //handle when pageindex out of size
            if (pageindex > totalPage || pageindex < 1) {
                request.setAttribute("result", "Not Found");
            } else {
                articles = articleDAO.getListArticleSearch(pageindex, pagesize, keyword);
            }

            request.setAttribute("keyword", keyword);
            request.setAttribute("pageindex", pageindex);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("articles", articles);
            request.getRequestDispatcher("search.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "Sorry! There is an error now");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
