package facebookDemo;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.obliquid.helpers.RecursiveDump;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Spotify {

   public Spotify() {
      //
   }
   
   /**
    * Sends the information to Spofity
    * 
    * @param pQuery
    * @return
    */
   private Map<String, Object> querySpotify(String pQuery) {
      URL url = null;
      
      try {
         url = new URL("https://api.spotify.com"+pQuery);
         URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
         url = uri.toURL();
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      ObjectMapper om = new ObjectMapper();
      Map<String, Object> res = null;
      try {
         res = om.readValue(url, Map.class);
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return res;
   }
   
   
   /**
    * Get the id of an artist
    * 
    * @param pName
    * @return id of the artist
    */
   public SpotifyData getArtist(String pName) {
      Map<String, Object> res = querySpotify("/v1/search?type=artist&q="+pName);
      
      //System.out.println(RecursiveDump.dump(res));
      
      //Spotify Artist Data
      SpotifyData sad = null;
      
      //Retrieve the id
      try {
         //Get the artists tag
         Map<String, Object> artists = (Map<String, Object>) res.get("artists");
         //Get the items tag
         ArrayList<Object> items = (ArrayList<Object>) artists.get("items");
         //Get the first artist
         Map<String, Object> item = (Map<String, Object>) items.get(0);
         //
         List<Object> images = (List<Object>) item.get("images");
         String image = (String) ((Map<String, Object>) images.get(0)).get("url");
         //Get the id
         sad = new SpotifyData((String) item.get("id"), (String) item.get("name"), image);
      } catch (Exception e) {}
      
      //return the id
      return sad;
   }
   
   
   /**
    * Gets a list of albums for a given artist
    * 
    * @param pArtistId the id of the artist
    * @return A list of id's of albums for the artist
    */
   public List<SpotifyData> getAlbums(String pArtistId) {
      Map<String, Object> res = querySpotify("/v1/artists/"+pArtistId+"/albums");
      
      List<SpotifyData> albums = new ArrayList<SpotifyData>();
      try {
         List<Object> items = (List<Object>) res.get("items");
         SpotifyData sd;
         for (Object item : items) {
            Map<String, Object> i = (Map<String, Object>) item;
            List<Object> images = (List<Object>) i.get("images");
            String image = (String) ((Map<String, Object>) images.get(0)).get("url");
            sd = new SpotifyData((String) i.get("id"), (String) i.get("name"), image);
            albums.add(sd);
         }
      } catch (Exception e) {}
      
      return albums;
   }
   
   /**
    * Gets a list of tracks for a given album
    * 
    * @param pAlbumId the id of the album
    * @return A list of tracks for a given album
    */
   public List<SpotifyData> getAlbumTracks(String pAlbumId) {
      Map<String, Object> res = querySpotify("/v1/albums/"+pAlbumId+"/tracks");
      
      List<SpotifyData> tracks = new ArrayList<SpotifyData>();
      SpotifyData sd;
      try {
         List<Object> items = (List<Object>) res.get("items");
         for (Object item : items) {
            Map<String, Object> i = (Map<String, Object>) item;
            sd = new SpotifyData((String) i.get("id"), (String) i.get("name"), "");
            tracks.add(sd); // or id
         }
      } catch (Exception e) {}
      
      return tracks;
   }
   
   
   
   /**
    * for testing
    * 
    * @param args
    */
   /*
    public static void main(String args[]) {
      Spotify spotify = new Spotify();
      SpotifyData artist = spotify.getArtist("Carly Rae Jepsen");
      
      System.out.println("Artist: "+artist.name+"\n"+artist.image);
      System.out.println("----------------");
      
      List<SpotifyData> albums = spotify.getAlbums(artist.id);
      for (SpotifyData s : albums) {
         System.out.println("\n\tAlbum: "+s.name + "\n\t["+s.image+"]");
         List<SpotifyData> tracks = spotify.getAlbumTracks(s.id);
         for (SpotifyData t : tracks) {
            System.out.println("\t\tTrack: " + t.name);
         }
      }
      
      System.out.println("----------------");
      
   }/**/
   
}