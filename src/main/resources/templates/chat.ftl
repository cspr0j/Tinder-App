<!DOCTYPE html>
<html lang="en">
<head>
    <title>Chat</title>

    <link href="/templates/static/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="/templates/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
          crossorigin="anonymous">

</head>
<body>


<div class="container-fluid h-100">
    <div style="display: flex; justify-content: space-between; margin-top: 20px;">
        <a href="/liked" role="button" class="btn btn-primary" style="display: block;width:130px;">Liked</a>
        <a href="/logout" role="button" class="btn btn-danger" style="display: block;width:130px;">Log out</a>
    </div>
    <div class="row justify-content-center h-100">
        <div class="col-md-8 col-xl-6 chat">
            <div class="card">
                <div class="card-header msg_head">
                    <div class="d-flex bd-highlight combinedHeader">
                        <div>
                            <span>Chat with ${receiver.name} </span>
                        </div>
                    </div>
                </div>
                <div class="card-body msg_card_body">
                    <#list messages as message>
                        <#if message.userId == sender.id>
                        <#--                        &lt;#&ndash;                        TODO:  think about of deleting this: &ndash;&gt;-->
                        <#--                            <div class="sender-img">-->
                        <#--                                <img src="${sender.photo_url}" alt="" class="float-right">-->
                        <#--                            </div>-->
                            <div class="send-msg float-right mb-2">
                                <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                    ${message.text}
                                </p>
                                <span class="receive-msg-time">${message.date}</span>
                            </div>
                        <#--receive message-->
                        <#else>
                            <div class="sender-img">
                                <img src="${receiver.photo_url}" alt="" class="float-left">
                            </div>
                            <div class="send-msg float-left ml-2">
                                <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                    ${message.text}
                                </p>
                                <span class="receive-msg-time">${message.date}</span>
                            </div>
                        </#if>
                        <br>
                        <br>
                    </#list>
                </div>
                <div class="card-footer">
                    <div class="input-group">
                        <form method="post" style="width: 100%">
                            <div class="input-group mb-3">
                                <input type="text" name="message" class="form-control"
                                       placeholder="Type your message..." autocomplete="off" aria-label="Message Input">
                                <div class="input-group-append">
                                    <button id="send" class="btn btn-primary" type="button">Send</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>