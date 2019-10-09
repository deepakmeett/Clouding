package com.example.clouding;

public class Model_Clouding {

    public String book_one, book_two;

    public Model_Clouding(String book_one, String book_two) {
        this.book_one = book_one;
        this.book_two = book_two;

    }

    public Model_Clouding(){

    }

    public String getBook_one() {
        return book_one;
    }

    public void setBook_one(String book_one) {
        this.book_one = book_one;
    }

    public String getBook_two() {
        return book_two;
    }

    public void setBook_two(String book_two) {
        this.book_two = book_two;
    }
}
