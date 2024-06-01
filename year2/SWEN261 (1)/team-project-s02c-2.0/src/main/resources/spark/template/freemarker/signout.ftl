<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>Web Checkers | ${title}</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <h1>${title}</h1>

    <div class="body">
        <#include "message.ftl" />

        <form action="./signout" method="POST">
            <label for="fname">Yes or no:</label><br>
            <!-- <input type="text" id="fname" answer="answer"> -->
            <input name="answer" />
            <br/><br/>
            <button type="submit">Submit</button>
            <!-- <input type="submit" value="Submit"></button> -->
        </form>
    </div>
</body>
</html>