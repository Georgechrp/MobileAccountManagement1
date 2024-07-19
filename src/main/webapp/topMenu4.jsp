<div id="top-menu">
    <ul>
        <li>
            <form id="top" action="AdminMain.jsp" method="get">
                <input class="top" type="submit" value="Main Page">
            </form>
        </li>
        <li>
            <form id="top" action="RegistrationSeller2.jsp" method="get">
                <input class="top" type="submit" name="action" value="New Seller">
            </form>
        </li>
        <li>
            <form id="top" action="RegistrationClient3.jsp" method="get">
                <input class="top" type="submit" name="action" value="New Client">
            </form>
        </li>
        <li>
            <form id="top" action="RegistrationAdmin.jsp" method="get">
                <input class="top" type="submit" name="action" value="New Admin">
            </form>
        </li>
        <li>
            <form id="top" action="CreatePrograms.jsp" method="post">
                <input class="top" type="submit" name="action" value="Create Programs">
            </form>
        </li>
        <li>
            <form id="top" action="<%=request.getContextPath()%>/AdminServlet" method="post">
                <input class="top" type="submit" name="action" value="Edit Programs">
            </form>
        </li>
         <li>
            <form id="top" action="<%=request.getContextPath()%>/UserServlet" method="post">
                <input class="top" type="submit" name="action" value="Logout">
            </form>
        </li>
    </ul>
</div>
<style>
    body {
    margin: 0;
    padding: 0;
}

#top-menu {
    width: 100%;
    background-color: #333;
    overflow-x: auto; /* Adds horizontal scrolling if necessary */
    overflow-y: hidden;
    position: fixed; /* Fixes the menu at the top of the page */
    top: 0;
    left: 0;
    z-index: 1000; /* Ensures the menu is on top of other elements */
    white-space: nowrap; /* Prevents items from wrapping to the next line */
}

#top-menu ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    display: inline-block;
}

#top-menu li {
    display: inline-block;
}

#top-menu li input {
    display: inline-block;
    color: white;
    text-align: center;
    padding: 10px 10px; /* Adjusted padding for better fit */
    text-decoration: none;
    background-color: #333;
    border: none;
    cursor: pointer;
    font-family: inherit;
    font-size: inherit;
    white-space: nowrap; /* Ensures the button text doesn't wrap */
}

#top-menu li input:hover {
    background-color: #111;
}

.content {
    padding-top: 60px; /* Adjusts space to account for the fixed menu */
}

#top {
    background-color: #333;
    color: white;
    text-align: center;
    padding: 10px 10px; /* Adjusted padding for better fit */
    text-decoration: none;
    border: none;
    cursor: pointer;
    font-family: inherit;
    font-size: inherit;
}
</style>	