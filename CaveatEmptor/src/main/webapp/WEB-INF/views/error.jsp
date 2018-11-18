<%--
  Created by IntelliJ IDEA.
  User: dotinschool3
  Date: 7/16/2018
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            text-align: center;
        }

        #error-title {
            color: #e17686;
            border: 2px solid rgba(206, 65, 76, 0.72);
            border-radius: 5px;
        }

        #error-message-box {
            border: 2px solid rgba(206, 157, 155, 0.72);
            border-radius: 5px;
            display: none;
        }

        #content{
            margin: 15% 1em;
        }

    </style>
    <script>
        function showErrorMessage(txt) {
            document.getElementById("error-message-box").style.display = "block";
        }
    </script>
</head>
<body>
    <div id="content">

    <h2 id="error-title">Error</h2>
    <button onclick='showErrorMessage()'>Read More</button>
    <div id="error-message-box">${message}</div>
    <button onclick="window.location.replace('/navigate-categories/')">Back</button>
    </div>
</body>
</html>
