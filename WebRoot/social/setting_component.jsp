<%@ page language="java" import="java.util.*,com.data.vo.*,com.process.model.Page" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
try{
%>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link href="css/bootstrap.css" rel="stylesheet"/>
    <link href="css/bootstrap-responsive.css" rel="stylesheet"/>
    <link href="css/main-assets.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="js/js-dashboard.js"></script>
</head>
<body>
	<div class="content-board">
            <div class="content-header">
                <h3>设置</h3>
            </div>
            <br/>
            <div class="content-inner">
                <form class="form-horizontal">
                    <div class="control-group">
                        <label class="control-label">用户名</label>
                        <div class="controls">
                            <input value="" class="input-xlarge"/>
                        </div>
                    </div>
                    <!-- 
                    <div class="control-group">
                        <label class="control-label">Your photo</label>
                        <div class="controls">
                            <input type="file"/>
                        </div>
                    </div>
                     -->
                    <div class="control-group">
                        <label class="control-label">性别</label>
                        <div class="controls">
                            <select>
  								<option value ="male">男</option>
  								<option value ="female">女</option>
  								<option value="not sure">保密</option>
							</select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">E-mail</label>
                        <div class="controls">
                            <input class="input-xlarge" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">所在地</label>
                        <div class="controls">
                            <input class="input-xlarge" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">个人简介</label>
                        <div class="controls">
                            <textarea class="input-xlarge" rows="3"></textarea>
                        </div>
                    </div>
                    <hr/>
                    <p class="help-item">(修改密码)</p>
                    <div class="control-group">
                        <label class="control-label">当前密码</label>
                        <div class="controls">
                            <input type="secret" label="password" class="input-xlarge"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">新密码</label>
                        <div class="controls">
                            <input class="input-xlarge" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">再次确认</label>
                        <div class="controls">
                            <input class="input-xlarge"/>
                        </div>
                    </div>
                    <hr/>
                    <div class="send-action">
                        <button type="submit" class="btn btn-primary">
                        	保存设置
                        </button>
                    </div>
                </form>
            </div>
        </div>
</body>
</html>
<%}catch(NullPointerException e){
 
  return;
}catch(Exception e)
{
   
   return;
}
%>