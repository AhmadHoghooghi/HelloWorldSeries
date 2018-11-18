<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ahmad
  Date: 2018-06-21
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories</title>
    <link href="/resources/styles/main.css" rel="stylesheet">
    <link href="/resources/styles/category.css" rel="stylesheet">
    <link href="/resources/styles/item.css" rel="stylesheet">
    <script src="/resources/scripts/main.js"></script>
</head>
<body>
    <div id="categories">
        <div id="categories-header">
            <c:choose>
                <c:when test="${parent==null}">
                    <label id="parent-category-name">Top Level Categories</label>
                    <button class="upper-level-category-button" disabled>Upper Level Categories</button>
                </c:when>

                <c:otherwise>
                    <label id="parent-category-name">Sub-Categories of: ${parent.name}</label>
                    <form action="/navigate-categories/${parent.parent.name}">
                        <input class="upper-level-category-button" type="submit" value="Upper Level Categories">
                    </form>
                </c:otherwise>
            </c:choose>
        </div>

        <div id="category-list">
            <ul>
                <c:forEach items="${categories}" var="category">
                    <li>
                        <form id="delete-form" action="/delete-category/${category.name}" method="post">
                            <input type="hidden" name="parent-category-name" value="${category.parent.name}">
                            <input type="submit" value="Delete">
                        </form>
                        <button id="edit-button-${category.name}" class="edit-button"
                                onclick="createEditForm('${category.name}','${category.parent.name}')">Edit
                        </button>
                        <div id="edit-div-${category.name}" class="edit-div" style="display: inline">
                        </div>
                        <a id="category-link-${category.name}" class="category-link"
                           href="/navigate-categories/${category.name}">${category.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div id="add-category">
            <form action="/add-category" method="post" id="add-category-form">
                <label>New Category Name:</label>
                <input type="hidden" name="parent-category-name" value="${parent.name}">
                <input type="text" id="new-category-text" name="new-category-name"/>
                <input type="submit" value="Add"/>
            </form>
        </div>
    </div>
    <div id="items">
        <div id="items-header">
            <c:if test="${parent==null}">

                <label>All Items:</label>
            </c:if>
            <c:if test="${parent !=null}">
                <label>Items in: ${parent.name}</label>
            </c:if>
        </div>

        <div id="item-list">
            <c:forEach items="${items}" var="item">
                <div id="item-${item.name}-div" class="item-div">
                    <table idd="item-table">
                        <td>
                            <form action="/item-update-bidding/${item.id}" method="post">
                                <input name="category-name" type="hidden" value="${parent.name}">
                                <c:if test="${item.biddable == true}">
                                    <input type="submit" value="Close Bidding">
                                </c:if>
                                <c:if test="${item.biddable == false}">
                                    <input type="submit" value="Open Bidding">
                                </c:if>
                            </form>
                            <form action="/item-update-add-category/${item.id}" method="post">
                                <label>Add Category:</label>
                                <input type="text" name="name-of-category-to-add"><br/>
                                <input name="category-name" type="hidden" value="${parent.name}">
                                <input type="submit" value="Add Category">
                            </form>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>Name:</td>
                                    <td>${item.name}</td>
                                    <td>Auction End:</td>
                                    <td>${item.auctionEnd}</td>
                                </tr>
                                <tr>
                                    <td>Seller Name:</td>
                                    <td>${item.seller.firstName} ${item.seller.lastName}</td>
                                    <td>Open to Bid:</td>
                                    <td>${item.biddable}</td>
                                </tr>
                                <tr>
                                    <td>Categories:</td>
                                    <td>
                                        <c:forEach items="${item.categories}" var="cat">
                                            <span class="item-category">${cat.name}</span>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </table>
                </div>
            </c:forEach>
        </div>

        <div id="add-item">
            <form action="/item" method="post">
                <table id="add-item-table">
                    <tr>
                        <td>Item Name</td>
                        <td><input type="text" name="item-name"><br/></td>

                        <td>Auction End</td>
                        <td><input type="datetime-local" name="auction-end"><br/></td>
                    </tr>
                    <tr>
                        <td>Seller user-name:</td>
                        <td><input type="text" name="user-name"><br/></td>
                        <td> Initial price:</td>
                        <td><input type="number" placeholder="$" step="0.1" name="initial-price"><br/></td>
                    </tr>
                </table>
                <input type="hidden" name="category-name" value="${parent.name}">
                <input type="submit" value="Add"><br/>
            </form>
        </div>
    </div>
</body>
</body>
</html>
