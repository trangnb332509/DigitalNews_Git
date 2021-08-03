/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ArticleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Article;

/**
 *
 * @author Dell Inc
 */
public class Detail extends BaseController {




    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArticleDAO articleDAO = new ArticleDAO();            
            String raw_id = request.getParameter("id");
            Article article = null;
            try{
                int id = Integer.parseInt(raw_id);
                article = articleDAO.getArticleById(id);
                //do not find article
                if(article==null){
                    request.setAttribute("result", "Not Found");
                }
            }catch(NumberFormatException nf){
                //raw_id is not number then return message
                request.setAttribute("result", "Invalid");
            }
            request.setAttribute("article", article);
            request.getRequestDispatcher("detail.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "Sorry! There is an error now");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }



}
