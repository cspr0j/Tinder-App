<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<!--    &lt;!&nd    ash; Bootstrap core CSS &ndash;&gt;-->
<!--    <link href="css/bootstrap.min.css" rel="stylesheet">-->

<!--    &lt;!&ndash; Custom styles for this template &ndash;&gt;-->
<!--    <link rel="stylesheet" href="css/style.css">-->
</head>
<body style="background-color: #f5f5f5;">

<div class="col-4 offset-4">
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center">

                    <#list userList as user>
                        <li>User picture = ${user.pic}</li> <br>
                        <li>User name = ${user.name}</li> <br>
                        <div class="col-12 col-lg-6">
                            <button name="dislike" type="button"formmethod="post" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span> Dislike</button>
                        </div>
                        <div class="col-12 col-lg-6">
                            <button name="like" formmethod="post" class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Like</button>
                        </div>
                    </#list>

                </div>
                <div class="col-12 col-lg-6">
                    <button type="button" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span> Dislike</button>
                </div>
                <div class="col-12 col-lg-6">
                    <button class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Like</button>
                </div>
                <!--/col-->
            </div>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
</div>

</body>
</html>
