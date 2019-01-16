<!DOCTYPE html>

<html lang="en">
<head>
    <title>JSP</title>
</head>
<body>

<div>
    <h2>JSP SAVE_BY_NAME Page</h2>
</div>


<form class="form-signin" role="form" action="saveName" method="POST">
    <input type="text" name="name" class="form-control" placeholder="Name" required autofocus>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Save city</button>
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