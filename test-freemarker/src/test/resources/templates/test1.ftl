<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!
<br/>
遍历数据模型中的List中的学生信息(数据模型中的Stus)
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>金额</td>
        <td>出生日期</td>
        <#--    <td>朋友</td>-->
        <#--        <td>最好的朋友</td>-->
    </tr>
    <#if stus??>
        <#list stus as stu>
            <tr <#if (stu.age >= 18)>style="background: green"></#if>>
                <td>${stu_index+ 1}</td>
                <td>${stu.name}</td>
                <td<#if stu.age<18> style="background: red;"></#if>${stu.age}</td>
                <td>${stu.money}</td>
                <#--<td>${stu.birthday?date}</td>-->
                <td>${(stu.birthday?date)!''}</td>
                <#--        <td>朋友</td>-->
                <#--        <td>最好的朋友</td>-->
            </tr>
        </#list>
    </#if>
</table>
学生个数:${stus?size}
<br/>

第一种方式:遍历数据模型中的map数据,在中括号[]中填写map的Key
<br/>
<#--序号:${stuMap['stu1']}<br/>-->
姓名:${stuMap['stu1'].name}<br/>
年龄:${stuMap['stu1'].age}<br/>
金额:${stuMap['stu1'].money}<br/>
第二种方式:遍历数据模型中的map数据,在map后边直接加".",再写key,
<br/>
姓名:${stuMap.stu1.name}<br/>
年龄:${stuMap.stu1.age}<br/>
金额:${stuMap.stu1 .money}<br/>
第三种方式:遍历数据模型中的map的key,stuMap?keys,这种结构就是拿出stuMap中所有的key,
注意stuMap后面没有".",切key 只能放在[]里面
<br/>
<#list stuMap?keys as key>
    姓名:${stuMap[key].name}<br/>
    年龄:${stuMap[key].age}<br/>
    金额:${stuMap[key].money}<br/>
</#list>
</body>
</html>