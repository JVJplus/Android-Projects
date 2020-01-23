package com.jvjplus.booklistingapp;

import java.util.ArrayList;

public class BookDetails {
    String title,publisher,publishDate,description,smallThumbnailLink,thumbnailLink,language,previewLink,infoLink,buyLink;
    double amount=-1,avgRating=-1,ratingsCount=-1;
    boolean saleability=false;
    int pageCount=-1;
    ArrayList<String> categories,authors;


    @Override
    public String toString() {
        return "BookDetails{" +
                "title='" + title + '\'' +
                ",\n\n publisher='" + publisher + '\'' +
                ",\n\n publishDate='" + publishDate + '\'' +
                ",\n\n description='" + description + '\'' +
                ",\n\n smallThumbnailLink='" + smallThumbnailLink + '\'' +
                ",\n\n thumbnailLink='" + thumbnailLink + '\'' +
                ",\n\n language='" + language + '\'' +
                ",\n\n previewLink='" + previewLink + '\'' +
                ",\n\n infoLink='" + infoLink + '\'' +
                ",\n\n buyLink='" + buyLink + '\'' +
                ",\n\n amount=" + amount +
                ",\n\n avgRating=" + avgRating +
                ",\n\n ratingsCount=" + ratingsCount +
                ",\n\n saleability=" + saleability +
                ",\n\n pageCount=" + pageCount +
                ",\n\n categories=" + categories +
                ",\n\n authors=" + authors +
                '}';
    }
}
