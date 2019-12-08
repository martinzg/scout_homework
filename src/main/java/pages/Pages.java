package pages;

public enum Pages {

    HOUSING("https://helsinki.craigslist.org/d/housing/search/hhh?lang=en&cc=gb");

    private String url;

    Pages(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}