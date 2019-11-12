<@override name="title">资源上传页</@override>

<@override name="content">

    <form action="/resource/upload/excel" method="post" enctype="multipart/form-data">
        <input type="file" name="upload-excel">
        <input type="submit" value="提交">
    </form>

</@override>

<@override name="js">

</@override>

<@extends name="../base/base.ftl"/>