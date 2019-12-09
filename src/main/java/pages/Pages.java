package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Pages {

    HOUSING("https://helsinki.craigslist.org/d/housing/search/hhh?lang=en&cc=gb");

    private final String url;
}