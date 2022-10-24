<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="/templates/static/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="/templates/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
          crossorigin="anonymous">

    <title>People list</title>
</head>
<body>


<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list users as user>
                                <tr>
                                    <td width="10">
                                        <div class="avatar-img">
                                            <#--<img class="img-circle" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxhcCYW4QDWMOjOuUTxOd50KcJvK-rop9qE9zRltSbVS_bO-cfWA"  alt=""/>-->
                                            <img class="img-circle" src="${user.photo_url}" alt=""/>
                                        </div>
                                    </td>

                                    <td class="align-middle">
                                        <small class="minitext">Full Name</small><br>
                                        <p class="normaltext">${user.name} ${user.surname}</p>
                                    </td>
                                    <td class="align-middle">
                                        <small class="minitext">Age</small><br>
                                        <p class="normaltext">${user.age}</p>
                                    </td>
                                    <td class="align-middle">
                                        <small class="minitext">E-mail</small><br>
                                        <p class="normaltext">${user.email}</p>
                                    </td>
                                    <td class="align-middle">
                                        <form method="post">
                                            <button type="submit" name="id" value='${user.id}'
                                                    class="btn btn-primary"><i class="fa fa-paper-plane">
                                                    Message</i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div style="display: flex; justify-content: space-between; margin-top: 20px;">
                <a href="/users" role="button" class="btn btn-primary">Like page</a>
                <a href="/logout" role="button" class="btn btn-danger">Log out</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
