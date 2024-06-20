<div id="top-menu">
    <ul>
        <li><a href="ShowPrograms.jsp">Show programs</a></li>
        <li><a href="RegistrationClient.jsp">Register a new client</a></li>
        <li><a href="RegistrationClient.jsp">Match a client to a program</a></li>
        <li><a href="RegistrationPage.jsp">Logout</a></li>
    
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
        overflow: hidden;
        position: fixed; /* Fixes the menu at the top of the page */
        top: 0;
        left: 0;
        z-index: 1000; /* Ensures the menu is on top of other elements */
    }
    #top-menu ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center; /* Centers the menu items horizontally */
    }
    #top-menu li {
        float: left;
    }
    #top-menu li a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }
    #top-menu li a:hover {
        background-color: #111;
    }
    .content {
        padding-top: 5px; /* Adds space to account for the fixed menu */
    }
</style>