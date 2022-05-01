public class Movie {

    private String title;
    private String urlImage;
    private Float rating;
    private Integer year;


    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public Float getRating() {
        return rating;
    }

    public Integer getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
