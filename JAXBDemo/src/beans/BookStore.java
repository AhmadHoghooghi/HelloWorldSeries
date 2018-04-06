/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmad
 */
/*
this is mandatory if you want to use XmlElementWrapper and
XmlElement to field, not to public accessors.
*/
@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement//this is mandatory
public class BookStore {

    private String name;

    /*
    @XmlElementWrapper shoul be set to change marshalling of list and array
    and collection from
    <bookList> ... </bookList>
    <bookList> ... </bookList>
    to 
    <bookList>
        <book></book>
        <book></book>
    </bookList>
     */
    @XmlElementWrapper(name = "bookList")
    /*
    @XmlElement sets name of each sub-element for <bookList>
    if you omit it, sub element name would be same as wrapper element.
    */
    @XmlElement(name = "book")
    private List<Book> bookList;
    private String location;

    public BookStore() {
    }

    public BookStore(String name, List<Book> bookList, String location) {
        this.name = name;
        this.bookList = bookList;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    /*
    These two elements shuld be on getter or setter method.
    there is an option to put them on field. but...
    In general, JAXB looks for public accessor methods.
    If you are going to place the annotation on a field,
    make sure that you mention the access type 
    as this @XmlAccessorType(XmlAccessType.FIELD).
    this is default for class: @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BookStore{" + "name=" + name + ", location=" + location+"\n");
        bookList.forEach(book-> sb.append("  "+book+"\n"));
        sb.append("}");
        return sb.toString();
                
    }
    
    
}
