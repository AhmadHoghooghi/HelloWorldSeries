/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import beans.Book;
import beans.BookStore;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Ahmad
 */
public class JAXBDemo {

    private static File bookFileXml = new File("./book-jaxb.xml");
    private static File bookStoreFileXml = new File("./bookStore-jaxb.xml");

    public static void main(String[] args) throws JAXBException {
        //>>>play with book
        Book book = createABook();
        writeABookToConsole(book);
        //writeABookToFile(book, bookFileXml);
        //readBookFromFileToConsole(bookFileXml);
        
        
        //>>>play with bookStore
        //BookStore bookStore = createABookStore();
        //writeABookStoreToConsole();
        //writeBookStoreToFile(bookStore,bookStoreFileXml);
        //readBookStoreFromFileToConsole(bookStoreFileXml);

    }

    public static void writeABookToConsole(Book book) throws JAXBException {

        //create context
        JAXBContext context = JAXBContext.newInstance(Book.class);
        //create Marshaller
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //thre are different types of marshall
        //the second param can be different for example File, Node, Writer and ...
        marshaller.marshal(book, System.out);

    }

    private static void writeABookStoreToConsole(BookStore bookStore) throws JAXBException {

        //crate context
        JAXBContext context = JAXBContext.newInstance(BookStore.class);
        //create Marshaller
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //write
        marshaller.marshal(bookStore, System.out);

    }

    private static void writeABookToFile(Book book, File xmlFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(book, xmlFile);
        System.out.println("book is written to "+xmlFile.getName());
    }

    private static Book createABook() {
        //create object
        return new Book("bookTitle", "bookAuthor", "123456");
    }

    private static BookStore createABookStore() {
        //Create bookStore
        Book book1 = new Book("book1", "author1", "1");
        Book book2 = new Book("book2", "author2", "3");
        Book book3 = new Book("book3", "author3", "3");
        Book[] bookArray = {book1, book2, book3};

        return new BookStore("bestBS", Arrays.asList(bookArray), "NY");
    }

    private static void readBookFromFileToConsole(File bookFileXml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Book.class);
        Unmarshaller unMarshaller = context.createUnmarshaller();
        Book book = (Book)unMarshaller.unmarshal(bookFileXml);
        System.out.println(book);
    }

    private static void writeBookStoreToFile(BookStore bookStore, File bookStoreFileXml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(BookStore.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(bookStore, bookStoreFileXml);
        System.out.println("bookStore is written to "+bookStoreFileXml.getName());
                
    }

    private static void readBookStoreFromFileToConsole(File bookStoreFileXml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(BookStore.class);
        Unmarshaller unMarshaller = context.createUnmarshaller();
        BookStore bookStore = (BookStore) unMarshaller.unmarshal(bookStoreFileXml);
        System.out.println(bookStore);
    }

}
