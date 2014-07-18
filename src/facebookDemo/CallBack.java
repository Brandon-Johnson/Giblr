package facebookDemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Music;
import facebook4j.ResponseList;

/**
 * Servlet implementation class CallBack
 */
@WebServlet("/CallBack")
public class CallBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallBack() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      Facebook facebook = (Facebook)request.getSession().getAttribute("facebook");
         Spotify spotify = new Spotify();
         List<SpotifyData> sp = new ArrayList<SpotifyData>();
         
         try {
            ResponseList<Music> list = facebook.getMusic();
               
            for (Music like : list) {
               sp.add(spotify.getArtist(like.getName()));
            }
               
         } catch (IllegalStateException e) {
            e.printStackTrace();
         } catch (FacebookException e) {
            e.printStackTrace();
         }
			
         //System.out.println(sp.size());
         
         request.setAttribute("sp", sp);
         request.getRequestDispatcher("artistList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
