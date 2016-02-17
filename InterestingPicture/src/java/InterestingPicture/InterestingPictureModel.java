package InterestingPicture;

/*
 * @author Joe Mertz
 * 
 * This file is the Model component of the MVC, and it models the business
 * logic for the web application.  In this case, the business logic involves
 * making a request to flickr.com and then screen scraping the HTML that is
 * returned in order to fabricate an image URL.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InterestingPictureModel {

    private String pictureTag; // The search string of the desired picture
    private String pictureURL; // The URL of the picture image

    /**
     * Arguments.
     *
     * @param searchTag The tag of the photo to be searched for.
     */
    public void doFlickrSearch(String searchTag) {
        pictureTag = searchTag;
        String response = "";

        // Create a URL for the desired page
        String flickrURL = "https://www.flickr.com/photos/tags/" + searchTag + "/interesting/";
        response = fetch(flickrURL);

        //System.out.println(response);

        /*
         * Find the picture URL to scrape
         *
         * Screen scraping is an art that requires looking at the HTML
         * that is returned creatively and figuring out how to cut out
         * the data that is important to you.
         * 
         * First find the src target that starts with farm
         */
        int cutLeft = response.indexOf("src=\"https://farm");
        // If not found, then no such photo is available.
        if (cutLeft == -1) {
            pictureURL = pictureTag = null;
            return;
        }
        // I don't want the src=" part, so skip 5 characters
        cutLeft += 5;

        // Look for the close quote 
        int cutRight = response.indexOf("\"", cutLeft);

        // Now snip out the part from positions cutLeft to cutRight
        pictureURL = response.substring(cutLeft, cutRight);
        System.out.println("pictureURL= " + pictureURL);

    }

    /*
     * Return a URL of an image of appropriate size
     * 
     * Arguments
     * @param picsize The string "mobile" or "desktop" indicating the size of
     * photo requested.
     * @return The URL an image of appropriate size.
     */
    public String interestingPictureSize(String picsize) {
        int finalDot = pictureURL.lastIndexOf(".");
        /*
         * From the flickr online documentation, the last letter before the
         * final "." and file extension is a size indicator.  "m" for small
         * and "z" for big.
         */
        String sizeLetter = (picsize.equals("mobile")) ? "m" : "z";
        return (pictureURL.substring(0, finalDot - 1) + sizeLetter
                + pictureURL.substring(finalDot));
    }

    /*
     * Return the picture tag.  I.e. the search string.
     * 
     * @return The tag that was used to search for the current picture.
     */
    public String getPictureTag() {
        return (pictureTag);
    }

    /*
     * Make an HTTP request to a given URL
     * 
     * @param urlString The URL of the request
     * @return A string of the response from the HTTP GET.  This is identical
     * to what would be returned from using curl on the command line.
     */
    private String fetch(String urlString) {
        String response = "";
        try {
            URL url = new URL(urlString);
            /*
             * Create an HttpURLConnection.  This is useful for setting headers
             * and for getting the path of the resource that is returned (which 
             * may be different than the URL above if redirected).
             * HttpsURLConnection (with an "s") can be used if required by the site.
             */
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            // Read each line of "in" until done, adding each to "response"
            while ((str = in.readLine()) != null) {
                // str is one line of text readLine() strips newline characters
                response += str;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Eeek, an exception");
            // Do something reasonable.  This is left for students to do.
        }
        return response;
    }
}
