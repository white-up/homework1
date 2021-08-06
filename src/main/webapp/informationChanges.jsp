<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <title>信息修改</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

    <script type="application/x-javascript">
        addEventListener("load", function() {
            setTimeout(hideURLbar, 0);

        }, false);
        function hideURLbar(){ window.scrollTo(0,1);}

    </script>
    <script>
        function checkGenderInput(){
            if("男"===$("#userGender")||"女"===$("#userGender")){
                return true;
            }else {
                return false;
            }
        }

        function updateUserPersonalInformation(){
            if(confirm("您确定要修改吗")){
                $.get("updateUserServlet",{
                    "id":${user.id},
                    "property":encodeURI("UserPersonalInformation"),
                    "name":encodeURI($("#userName").val()),
                    "gender":encodeURI($("#userGender").val())
                },function (error){
                    if(error.error==null){
                        alert("修改成功,请刷新页面!");
                    }else {
                        alert(error.error);
                    }

                });
            }
        }
        function updateUserMoneyInformation(){
            if(confirm("您确定要修改吗")){
                $.get("updateUserServlet",{
                    "id":${user.id},
                    "property":encodeURI("UserMoneyInformation"),
                    "basePay":encodeURI($("#userBasePay").val()),
                    "jobSalary":encodeURI($("#userJobSalary").val()),
                    "jobSubsidy":encodeURI($("#userJobSubsidy").val()),
                    "medicalInsurance":encodeURI($("#userMedicalInsurance").val()),
                    "accumulationFund":encodeURI($("#userAccumulationFund").val())
                },function (error){
                    if(error.error==null){
                        alert("修改成功,请刷新页面!");
                    }else {
                        alert(error.error);
                    }
                    }
                );
            }
        }
        $(function (){
            $("#PersonalInformationButton").click(function (){
                updateUserPersonalInformation();
            });
            $("#moneyInformationButton").click(function (){
                updateUserMoneyInformation();
            });
        });
    </script>
    <link href="css/style.css" rel='stylesheet' type='text/css' media="all" />
    <!-- /css files -->
</head>
<body>
<h1>信息修改<span class="label label-success">id:${user.id}</span></h1>
<div class="log">
    <div class="content1">
        <h2>个人信息</h2>
        <div><label style="color: #1DC7EA">姓名　:　</label>
            <input type="text" name="username" value="${user.name}" id="userName" onblur="if (this.value == '') {this.value = '${user.name}';}">
        </div>
        <div><label style="color: #1DC7EA">性别　:　</label>
            <input type="text" name="userGender" value="${user.gender}" id="userGender" onblur="if (this.value == '') {this.value = '${user.gender}';}">
        </div>
            <button type="button"  class="register" id="PersonalInformationButton">修改</button>
            <div class="clear"></div>
        <!--</form>-->
    </div>
    <div class="content2">
        <h2>收入情况</h2>
            <div>
                <label style="color: #1DC7EA">基本工资　:　</label>
                <input type="text" id="userBasePay" name="userBasePay" value="${user.basePay}" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '${user.basePay}';}">
            </div>
            <div>
                <label style="color: #1DC7EA">职务工资　:　</label>
                <input type="text" id="userJobSalary" name="userJobSalary" value="${user.jobSalary}" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '${user.jobSalary}';}">
            </div>
            <div>
                <label style="color: #1DC7EA">岗位津贴　:　</label>
                <input type="text" id="userJobSubsidy" name="userJobSubsidy" value="${user.jobSubsidy}" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '${user.jobSubsidy}';}">
            </div>
            <div>
                <label style="color: #1DC7EA">医疗保险　:　</label>
                <input type="text" id="userMedicalInsurance" name="medicalInsurance" value="${user.medicalInsurance}" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '${user.medicalInsurance}';}">
            </div>
            <div>
                <label style="color: #1DC7EA">公积金　　:　</label>
                <input type="text" id="userAccumulationFund" name="accumulationFund" value="${user.accumulationFund}" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '${user.accumulationFund}';}">
            </div>
            <button type="button"  class="register" id="moneyInformationButton">修改</button>
            <div class="clear"></div>
    </div>

</div>
<div class="footer">
    <p>Copyright &copy; 2021.Company name All rights reserved.<a target="_blank" ></a><a target="_blank" ></a></p>
</div>

</body>
</html>