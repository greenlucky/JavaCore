package com.lamdevops.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.lamdevops.stream.FilterTest.print;

/**
 * Created by lam.nm on 7/26/2017.
 */
public class FlatMap {

    @Test
    public void flatMap() throws Exception {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        //Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);
        Stream<String> words = temp.flatMap(w -> Arrays.stream(w));

        print(words);
    }

    @Test
    public void flatMapPlus() throws Exception {
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)) // Stream of List<Integer>
                .flatMap(List::stream)
                .map(integer -> integer + 1)
                .collect(Collectors.toList());
        print(together.stream());
    }

    /**
     * This demo FlatMap with list. The test have two entity
     * Writer and Book. We build the a  List Writers each writer
     * will contains list of Book. Then use FlatMap get list of book
     * from list Writers.
     *
     * @throws Exception
     */
    @Test
    public void testFlatMapWithList() throws Exception {

        List<Book> books1 = Arrays.asList(
                new Book(10, "Mastering Java 8"),
                new Book(22, "Spring Microservices in Action"));
        List<Book> books2 = Arrays.asList(
                new Book(12, "Java 8 with Stream, Optional"),
                new Book(17, "Spring in Action"));

        Writer writer1 = new Writer("Mohan", books1);
        Writer writer2 = new Writer("Sohan", books2);
        List<Writer> writers = Arrays.asList(writer1, writer2);

        System.out.println(writers);

        //print all current books.
        List<Book> booksResult = writers.stream().flatMap(writer -> writer.getBooks().stream()).collect(Collectors.toList());
        System.out.println("List of books");
        booksResult.stream().forEach(System.out::println);

        long start = System.nanoTime();
        //find max price of book
        Book maxPriceBook = writers.stream().flatMap(writer -> writer.getBooks().stream()).reduce((b1, b2) -> b1.getPrice() > b2.getPrice() ? b1 : b2).get();
        System.out.println("Max price:");
        System.out.println(maxPriceBook);
        long end = ((System.nanoTime() - start)/1_000_000);
        System.out.println(end + " msecs");


    }

    @Test
    public void testFlatMapWithList2() throws Exception {

        List<Book> books1 = Arrays.asList(
                new Book(10, "Mastering Java 8"),
                new Book(22, "Spring Microservices in Action"));
        List<Book> books2 = Arrays.asList(
                new Book(12, "Java 8 with Stream, Optional"),
                new Book(17, "Spring in Action"));

        Writer writer1 = new Writer("Mohan", books1);
        Writer writer2 = new Writer("Sohan", books2);
        List<Writer> writers = Arrays.asList(writer1, writer2);

        long start = System.nanoTime();
        //find max price of book
        Book maxPriceBook = writers.stream().flatMap(writer -> writer.getBooks().stream()).max(new BookComparator()).get();
        System.out.println("Max price:");
        System.out.println(maxPriceBook);
        long end = ((System.nanoTime() - start)/1_000_000);
        System.out.println(end + " msecs");
    }

    private static class Book {
        private int price;
        private String name;

        public Book(int price, String name) {
            this.price = price;
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "price=" + price +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    private static class Writer {
        private String author;
        private List<Book> books;

        public Writer(String author, List<Book> books) {
            this.author = author;
            this.books = books;
        }

        public List<Book> getBooks() {
            return books;
        }


        @Override
        public String toString() {
            return "Writer{" +
                    "author='" + author + '\'' +
                    ", books=" + books +
                    '}';
        }
    }

    private static class BookComparator implements Comparator<Book> {

        @Override
        public int compare(Book b1, Book b2) {
            return b2.getPrice() - b1.getPrice();
        }
    }
}
