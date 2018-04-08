package murach.cart;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import murach.business.Cart;
import murach.business.LineItem;

import murach.data.ProductIO;
import murach.business.Product;

public class ProductShowSerlvet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        //find products in cart
        ArrayList<Product> productsInCart = new ArrayList<>();
        cart.getItems().forEach((lineItem) -> {
            productsInCart.add(lineItem.getProduct());
        });
        
        //find buyable products
        String path = getServletContext().getRealPath("/WEB-INF/products.txt");
        ArrayList<Product> products = ProductIO.getProducts(path);
        ArrayList<Product> productsNotInCart = new ArrayList<>();
        products.stream()
                .filter((p) -> (!productsInCart.contains(p)))
                .forEach((p) -> {productsNotInCart.add(p);});
        
        session.setAttribute("buyableProducts", productsNotInCart);
        String url = "/index.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
