<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Like page</title>
    <link href="/templates/static/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="/templates/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
          crossorigin="anonymous">
    <!--    &lt;!&nd    ash; Bootstrap core CSS &ndash;&gt;-->
<!--    <link href="css/bootstrap.min.css" rel="stylesheet">-->

<!--    &lt;!&ndash; Custom styles for this template &ndash;&gt;-->
<!--    <link rel="stylesheet" href="css/style.css">-->
</head>
<body style="background-color: #f5f5f5;">

<div class="col-4 offset-4">
    <form action="/users" method="post">
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center">
<#--                    <#list users as user>-->
<#--                        <li>User picture = ${user.pic}</li> <br>-->
<#--                        <li>User name = ${user.name} ${user.surname}</li> <br>-->
<#--                        <li hidden>${user.id}</li>-->
<#--                    </#list>-->
                    <div>
                        <h3>User picture = ${users.photo_url}</h3>
                        <h3>User name = ${users.name} ${users.surname}</h3>
                    </div>

                </div>
                <div class="col-12 col-lg-6">
                    <button type="submit" id="dislike" value="dislike" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span> Dislike</button>
                </div>
                <div class="col-12 col-lg-6">
                    <button type="submit" name="like" value="like" id="like" class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Like</button>
                </div>
                <!--/col-->
            </div>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
    </form>
</div>

</body>
</html>
