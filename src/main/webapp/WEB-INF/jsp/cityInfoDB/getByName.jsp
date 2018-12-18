<!DOCTYPE html>

<html lang="en">
<head>
    <title>JSP</title>
</head>
<body>

<div>
    <h2>JSP GET_BY_NAME Page</h2>
</div>

<form class="form-signin" role="form" action="getByName" method="POST">
    <input type="text" name="name" class="form-control" placeholder="Name" required autofocus>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Get city</button>
</form>
<div>
    <div> ${getByName} - is add in database</div>
</div>
<div>
    <a href="/">back</a>
</div>
</body>
</html>