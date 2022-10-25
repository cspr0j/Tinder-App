<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tinder</title>

    <link href="/templates/static/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="/templates/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
          crossorigin="anonymous">

</head>
<body style="background-color: #f5f5f5;">
<div class="col-4 offset-4">
    <form class="form-users" action="/users" method="post">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-12 col-lg-12 col-md-12 text-center">
                        <img class="img-fluid" alt="" src=${user.photo_url}>
                        <h3>${user.name} ${user.surname}</h3>
                        <h4>Java Developer</h4>
                        <h5>Personal Information:</h5>
                        <p>Age:<i class="lnr lnr-calendar-full"></i> ${user.age} years old</p>
                        <p>Email:<i class="lnr lnr-envelope"></i> ${user.email}</p>
                        <p>Address:<i class="lnr lnr-home"></i> Baku, Azerbaijan</p>
                    </div>

                    <input type="hidden" name="userId" value=${user.id}>
                    <div class="col-12 col-lg-6">
                        <button name="dislike" type="submit" value='false'
                                class="btn btn-outline-danger btn-block">
                            <span class="fa fa-times"></span>
                            Dislike
                        </button>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button name="like" type="submit" value="true" class="btn btn-outline-success btn-block">
                            <span class="fa fa-heart"></span>
                            Like
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div style="display: flex; justify-content: space-between; margin-top: 20px;">
            <a href="/liked" role="button" class="btn btn-primary" style="display: block;width:130px;">Liked</a>
            <a href="/logout" role="button" class="btn btn-danger" style="display: block;width:130px;">Log out</a>
        </div>
    </form>
</div>
</body>
</html>