<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!<br>

<#if name == "黑马">
    判断语句
</#if>

<#-- 取出Map数据-->
姓名: ${map['s1'].name}<br>
年龄: ${map.s1.age}<br>
<table>
    <tr>
        <td <#if name == "黑马">style="background: pink" </#if>>序号</td>
        <td>姓名</td>
        <td>年龄</td>
    </tr>
    <#list friends as stu>
        <tr>
            <td>${stu_index + 1}</td>
            <td>${stu.name}</td>
            <td>${stu.age}</td>
        </tr>
    </#list>
    <br>
    <#list map?keys as k>
        <tr>


        <td>${k_index + 1}</td>
        <td>${map[k].name}</td>
        <td>${map[k].age}</td>
        <td >${map[k].money}</td>
        </tr>
    </#list>

</table>

</body>
</html>