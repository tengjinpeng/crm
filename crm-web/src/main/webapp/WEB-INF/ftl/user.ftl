<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        table{
            width: 600px;
            text-align: center;
            border-collapse: collapse;
            margin-top: 50px;
        }
        th,td{
            border: 1px solid  #cccccc;
            line-height: 40px;
        }
    </style>
</head>
<body>
<table align="center">
    <tr>
        <th>用户名</th>
        <th>密码</th>
    </tr>
    <tr>
        <td>${userName}</td>
        <td>${password}</td>
    </tr>

</table>
</body>
</html>