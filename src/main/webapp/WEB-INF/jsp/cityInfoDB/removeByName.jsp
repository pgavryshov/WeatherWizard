<!DOCTYPE html>

<html lang="en">
<head>
    <title>JSP</title>
</head>
<body>

<div>
    <h2>JSP REMOVE_BY_NAME Page</h2>
</div>

<form class="form-signin" role="form" action="removeByName" method="POST">
    <input type="text" name="name" class="form-control" placeholder="Name" required autofocus>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Remove city</button>
</form>
<div>
    <div> ${cityStatus} </div>
</div>
<div>
    <a href="/">back</a>
</div>

<div>${errorMessage}</div>

</body>
</html>