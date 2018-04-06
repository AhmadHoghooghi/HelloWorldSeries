/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Ahmad
 */
/*
XmlAccessorType should be set to fild if you want to use XmlElement on filed
otherwise it will throw Exception and say that there are two field with name of title
JAXB finds one with fileds (as result of fild annotaion) and one from public accessors
according to default access type settings: @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
*/
@XmlAccessorType(XmlAccessType.FIELD)
/*
the only mandatory annotation for marshaling. namespace element is optional.
use @XmlRootElement(namespace = "beans") to add prefix and xmlns to tag:
<ns2:book xmlns:ns2="beans">
    ...
</ns2:book>
*/
/*
the only mandatory annotation for marshaling. name element is optional.
*/
//@XmlRootElement(namespace = "beans")

@XmlRootElement//(name = "somethingPublished")//the only mandetory annotation, name is optional
@XmlType(propOrder = {"title","author","isbn"})//optional: sets the order in xml result
public class Book {
    @XmlElement(name = "documentName")//optional this should be on getter or setter
    private String title;
    private String author;
    private String isbn;

    public Book() {
    }

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", author=" + author + ", isbn=" + isbn + '}';
    }
    
    
}
