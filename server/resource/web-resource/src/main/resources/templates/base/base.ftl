<!DOCTYPE html>
<html lang="en">
<head>
    <META charset="UTF-8">
    <META HTTP-EQUIV="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="X-UA-Compatible" charset="IE=Edge"/>
    <title>
        <@block name="title"></@block>
    </title>

    <@block name="css"></@block>

    <script type="text/javascript" src="${static}/js/3rd/jquery-3.4.1.min.js"></script>
</head>

<body>
    <@block name="content"></@block>
    <@block name="js"></@block>
</body>
</html>