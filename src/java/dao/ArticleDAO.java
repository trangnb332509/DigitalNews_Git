/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modal.Article;

/**
 *
 * @author Dell Inc
 */
public class ArticleDAO {

    public ArrayList<Article> get5RecentNews() throws Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        ArrayList<Article> listArticles = new ArrayList<>();
        try {
            String sql = "SELECT top 5 [id]\n"
                    + "      ,[title]\n"
                    + "      ,[image]\n"
                    + "      ,[content]\n"
                    + "      ,[author]\n"
                    + "      ,[date]\n"
                    + "  FROM [Digital_News].[dbo].[Article]\n"
                    + "  order by date desc";
            db = new DBContext();
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setImage(rs.getString("image"));
                article.setContent(rs.getString("content"));
                article.setAuthor(rs.getString("author"));
                article.setDate(rs.getTimestamp("date"));
                article.setDescription(article.getContent());
                listArticles.add(article);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(con, pre, rs);
        }
        return listArticles;
    }

    public Article getArticleById(int id) throws Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[title]\n"
                    + "      ,[image]\n"
                    + "      ,[content]\n"
                    + "      ,[author]\n"
                    + "      ,[date]\n"
                    + "  FROM [Digital_News].[dbo].[Article]\n"
                    + "  where id = ?";
            db = new DBContext();
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();
            if (rs.next()) {
                Article article = new Article();
                article.setId(id);
                article.setTitle(rs.getString("title"));
                article.setImage(rs.getString("image"));
                article.setContent(rs.getString("content"));
                article.setAuthor(rs.getString("author"));
                article.setDate(rs.getTimestamp("date"));
                article.setDescription(rs.getString("content"));
                return article;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(con, pre, rs);
        }
        return null;
    }

    public int countArticleBySearch(String keyWord) throws Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(id) as number FROM Article \n"
                    + "WHERE content\n"
                    + "LIKE ? OR title LIKE ?";
            db = new DBContext();
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            keyWord = "%" + keyWord + "%";
            pre.setString(1, keyWord);
            pre.setString(2, keyWord);
            rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt("number");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(con, pre, rs);
        }
        return 0;
    }

    public ArrayList<Article> getListArticleSearch(int pageIndex, int pageSize, String keyWord) throws Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        ArrayList<Article> listArticles = new ArrayList<>();
        try {
            String sql = "select [id],[title],[image],[content],[author],[date] from\n"
                    + "(select ROW_NUMBER() over (order by date desc) \n"
                    + "as rid, [id]\n"
                    + "	  ,[title]\n"
                    + "      ,[image]\n"
                    + "      ,[content]\n"
                    + "      ,[author]\n"
                    + "      ,[date] from Article\n"
                    + "where content\n"
                    + "LIKE ? OR title LIKE ?) tbl\n"
                    + "where rid between ? and ?";
            db = new DBContext();
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            keyWord = "%" + keyWord + "%";
            pre.setString(1, keyWord);
            pre.setString(2, keyWord);
            int first = (pageIndex-1)*pageSize+1;
            int last = pageIndex*pageSize;
            pre.setInt(3, first);
            pre.setInt(4, last);
            rs = pre.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setImage(rs.getString("image"));
                article.setContent(rs.getString("content"));
                article.setAuthor(rs.getString("author"));
                article.setDate(rs.getTimestamp("date"));
                article.setDescription(article.getContent());
                listArticles.add(article);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(con, pre, rs);
        }
        return listArticles;
    }

    public Article getTop1() throws Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        String sql = "SELECT top 1 [id]\n"
                + "		,[title]\n"
                + "      ,[image]\n"
                + "      ,[content]\n"
                + "      ,[author]\n"
                + "      ,[date]\n"
                + "FROM [Digital_News].[dbo].[Article]\n"
                + "order by date desc";
        try {
            db = new DBContext();
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            rs = pre.executeQuery();
            //return recent article
            if(rs.next()){
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setImage(rs.getString("image"));
                article.setContent(rs.getString("content"));
                article.setAuthor(rs.getString("author"));
                article.setDate(rs.getTimestamp("date"));
                article.setDescription(article.getContent());
                return article;
            }
        } catch (Exception ex) {
            throw ex;
        }finally{
            db.closeConnection(con, pre, rs);
        }
        return null;
    }
}
