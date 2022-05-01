import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Filmes {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI("https://imdb-api.com/en/API/Top250Movies/" + System.getenv("apiKey")))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        String[] arrayMovies = parseMovies(json);
//        String[] arrayAttributes = parseAttributes(arrayMovies);
        for (Movie movie : getMovies(arrayMovies)){
            System.out.println(movie.getTitle());
            System.out.println(movie.getUrlImage());
            System.out.println(movie.getRating());
            System.out.println(movie.getYear());
        }
    }

    public static String[] parseMovies(String json){
       return json.split("},");
    }

    public static String[] parseAttributes(String[] movies){
        String attribute = Arrays.toString(movies);
            return attribute.split(",");
        }

    public static String getAttribute(String attributes, String find) {
        String [] att = attributes.split(",");
        for (String attribute : att) {
            if (attribute.contains(find)) {
                int i = attribute.indexOf(find);
                attributes = (attribute.substring(i));
            return attributes;
            }
        }
        return attributes;
    }

    public static String getRating(String rating, String find) {
      String[] spt = rating.split(",");
        for (String rt : spt) {
            if (rt.contains(find)) {
                int i = rt.indexOf(find) + 13;
                int f = rt.indexOf(find) + 16;
                rating = (rt.substring(i, f));
                return rating;
            }
        }
return rating;
    }

    public static String getYear(String year, String find) {
        String[] spt = year.split(",");
        for (String yr : spt) {
            if (yr.contains(find)) {
                int i = yr.indexOf(find) + 7;
                int f = yr.indexOf(find) + 11;
                year = (yr.substring(i, f));
                return year;
            }
        }
        return year;
    }

    public static List<Movie> getMovies(String[] parseMovie) {

        List<Movie> mv = new ArrayList<>();
        for (String mve : parseMovie ){
            Movie movie = new Movie();
            movie.setTitle(getAttribute(mve, "title"));
            movie.setUrlImage(getAttribute(mve,"image" ));
            Float rt = Float.parseFloat(getRating(mve, "imDbRating\""));
            movie.setRating(rt);
            Integer yr = Integer.parseInt(getYear(mve,"year"));
            movie.setYear(yr);
            mv.add(movie);
        }
        return mv;
    }
}
