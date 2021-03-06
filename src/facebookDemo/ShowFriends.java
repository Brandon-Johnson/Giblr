package facebookDemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.Like;
import facebook4j.Music;
import facebook4j.ResponseList;

/**
 * Servlet implementation class ShowFriends
 */
@WebServlet("/ShowFriends")
public class ShowFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowFriends() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Facebook facebook = (Facebook)request.getSession().getAttribute("facebook");
    		
    	PrintWriter out = response.getWriter();
    		
    	try {
    		out.write("Your name is: " + facebook.getName() + "\n\n");
    			
    		ResponseList<Music> list = facebook.getMusic();
    			
    		for (Music like : list) {
    			out.write(like.getName() + "\n");
    		}
    			
    	} catch (IllegalStateException e) {
    		e.printStackTrace();
    	} catch (FacebookException e) {
    		e.printStackTrace();
    	}
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
