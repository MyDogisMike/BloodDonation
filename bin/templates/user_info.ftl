<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>无偿献血</title>
	<#include "headInclude.ftl"/>
	<script type="text/javascript" src="${request.contextPath}/site/js/Jsaddress.js" ></script>
	<script type="text/javascript" src="${request.contextPath}/site/js/user_info.js" ></script>
	<link rel="stylesheet" href="${request.contextPath}/site/css/user_info.css" />
</head>
<body>
	<#include "header.ftl"/>
	<div id="user_info_container">
				<h2 class="user_position">
					当前位置：<a href="/">首页</a>>>个人中心
				</h2>
			<div id="user_left">
				<ul>
					<li>
						<a class="user_item selected" id="user_info" href="javascript:void(0);">用户信息</a>
					</li>
					<li>
						<a class="user_item" id="add_consult" href="javascript:void(0);">添加咨询</a>
					</li>
					<li>
						<a class="user_item" id="manage_consult" href="javascript:void(0);">管理咨询</a>
					</li>
					<li>
						<a class="user_item" href="javascript:void(0);">我的收藏</a>
					</li>
					<li>
						<a class="user_item" id="modify_pass" href="javascript:void(0);">修改密码</a>
					</li>
					<li>
						<a class="user_item" href="/login/out">注销登录</a>
					</li>
				</ul>
			</div>
			<div id="user_right">
				<!-- 用户信息 -->
				<form id="user_info_form">
					<div id="form_div">
						<table cellpadding="0" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th class="w1">参数说明</th>
									<th class="w2">内容</th>
									<th class="w3">说明</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="w1">登录账号:</td>
									<td class="w2">${user.name}</td>
									<td class="w3"></td>
								</tr>
								<tr>
									<td class="w1">昵称:</td>
									<td class="w2"><input id=nickname name="nickname" value="${user.nickname}" style="width:50%"></td>
									<td class="w3"></td>
								</tr>
								<tr>
									<td class="w1">出生日期:</td>
									<input id="birthday" type="hidden" value="${user.birthday}" />
									<td class="w2">
										<select id="birthdayYear">
											<option value="1950">1950</option>
											<option value="1951">1951</option>
											<option value="1952">1952</option>
											<option value="1953">1953</option>
											<option value="1954">1954</option>
											<option value="1955">1955</option>
											<option value="1956">1956</option>
											<option value="1957">1957</option>
											<option value="1958">1958</option>
											<option value="1959">1959</option>
											<option value="1960">1960</option>
											<option value="1961">1961</option>
											<option value="1962">1962</option>
											<option value="1963">1963</option>
											<option value="1964">1964</option>
											<option value="1965">1965</option>
											<option value="1966">1966</option>
											<option value="1967">1967</option>
											<option value="1968">1968</option>
											<option value="1969">1969</option>
											<option value="1970">1970</option>
											<option value="1971">1971</option>
											<option value="1972">1972</option>
											<option value="1973">1973</option>
											<option value="1974">1974</option>
											<option value="1975">1975</option>
											<option value="1976">1976</option>
											<option value="1977">1977</option>
											<option value="1978">1978</option>
											<option value="1979">1979</option>
											<option value="1980">1980</option>
											<option value="1981">1981</option>
											<option value="1982">1982</option>
											<option value="1983">1983</option>
											<option value="1984">1984</option>
											<option value="1985">1985</option>
											<option value="1986">1986</option>
											<option value="1987">1987</option>
											<option value="1988">1988</option>
											<option value="1989">1989</option>
											<option value="1990">1990</option>
											<option value="1991">1991</option>
											<option value="1992">1992</option>
											<option value="1993">1993</option>
											<option value="1994">1994</option>
											<option value="1995">1995</option>
											<option value="1996">1996</option>
											<option value="1997">1997</option>
											<option value="1998">1998</option>
											<option value="1999">1999</option>
											<option value="2000">2000</option>
											<option value="2001">2001</option>
											<option value="2002">2002</option>
											<option value="2003">2003</option>
											<option value="2004">2004</option>
											<option value="2005">2005</option>
											<option value="2006">2006</option>
											<option value="2007">2007</option>
											<option value="2008">2008</option>
											<option value="2009">2009</option>
											<option value="2010">2010</option>
											<option value="2011">2011</option>
											<option value="2012">2012</option>
											<option value="2013">2013</option>
											<option value="2014">2014</option>
											<option value="2015">2015</option>
											<option value="2016">2016</option>
											<option value="2017">2017</option>
											<option value="2018">2018</option>
										</select>
										<select id="birthdayMonth">
											<option value="01">01</option>
											<option value="02">02</option>
											<option value="03">03</option>
											<option value="04">04</option>
											<option value="05">05</option>
											<option value="06">06</option>
											<option value="07">07</option>
											<option value="08">08</option>
											<option value="09">09</option>
											<option value="10">10</option>
											<option value="11">11</option>
											<option value="12">12</option>
										</select>
										<select id="birthdayDay">
											<option value="01">01</option>
											<option value="02">02</option>
											<option value="03">03</option>
											<option value="04">04</option>
											<option value="05">05</option>
											<option value="06">06</option>
											<option value="07">07</option>
											<option value="08">08</option>
											<option value="09">09</option>
											<option value="10">10</option>
											<option value="11">11</option>
											<option value="12">12</option>
											<option value="13">13</option>
											<option value="14">14</option>
											<option value="15">15</option>
											<option value="16">16</option>
											<option value="17">17</option>
											<option value="18">18</option>
											<option value="19">19</option>
											<option value="20">20</option>
											<option value="21">21</option>
											<option value="22">22</option>
											<option value="23">23</option>
											<option value="24">24</option>
											<option value="25">25</option>
											<option value="26">26</option>
											<option value="27">27</option>
											<option value="28">28</option>
											<option value="29">29</option>
											<option value="30">30</option>
											<option value="31">31</option>
										</select>
									</td>
									<td class="w3"></td>
								</tr>
								<tr>
									<td class="w1">性别:</td>
									<input id="gender" type="hidden" value="${user.gender?substring(1)}" />
									<td class="w2"><input id="secret" type="radio" value="0" name="sex" style="margin:0 5px; border:none" checked="checked">保密<input id="man" style="margin:0 5px; border:none" type="radio" value="1" name="sex">男<input id="woman" type="radio" value="2" name="sex" style="margin:0 5px;border:none">女</td>
									<td class="w3"></td>
								</tr>
								<tr>
									<td class="w1">血型:</td>
									<input id="bloodTypeValue" type="hidden" value="${user.bloodType?substring(1)}" />
									<td class="w2">
										<select id="bloodType" name="bloodType">
											<option value="5">保密</option>
											<option value="0">O型血</option>
											<option value="1">A型血</option>
											<option value="2">B型血</option>
											<option value="3">AB型血</option>
											<option value="4">稀有血型</option>
										</select>  
									</td>
									<td class="w3">填写血型后可以收到我们的邮件推送</td>
								</tr>
								
								<tr>
									<td class="w1">所在地区:</td>
									<input id="address" type="hidden" value="${user.address}" />
									<td class="w2">
										<select id="cmbProvince" name="cmbProvince"></select>  
				                        <select id="cmbCity" name="cmbCity"></select>  
				                        <select id="cmbArea" name="cmbArea"></select>  
									</td>
									<td class="w3">请填写所在地区以获取更好的体验</td>
								</tr>
								<tr>
									<td class="w1">邮箱:</td>
									<td class="w2">${user.email}</td>
									<td class="w3"></td>
								</tr>
								<tr>
									<td class="w1">手机号码:</td>
									<td class="w2"><input id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}" style="width:50%"></td>
									<td class="w3"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="user_btn_div">
						<button id="user_update_btn" type="button">保存</button>
					</div>
				</form>
				
				<!-- 修改密码 -->
				<form id="modify_pass_form" style="display: none;">
					<div class="form_div">
						<table cellpadding="0" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th class="w1">参数说明</th>
									<th class="w2">内容</th>
									<th class="w3">说明</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="w1">旧密码:</td>
									<td class="w2"><input id="oldPass" name="oldPass" value="" style="width:50%"><span class="hint" style="color:red;padding:0 8px;"></span></td>
									<td class="w3"></td>
								</tr>
								<tr>
									<td class="w1">新密码:</td>
									<td class="w2"><input id="newPass" name="newPass" value="" style="width:50%"><span class="hint" style="color:red;padding:0 8px;"></span></td>
									<td class="w3"></td>
								</tr>
								<tr>
									<td class="w1">确认新密码:</td>
									<td class="w2"><input id="confirmPass" name="confirmPass" value="" style="width:50%"><span class="hint" style="color:red;padding:0 8px;"></span></td>
									<td class="w3"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="user_btn_div">
						<button id="modify_pass_btn" type="button">保存</button>
					</div>
				</form>
				
				<!-- 添加咨询 -->
				<form id="add_consult_form" style="display: none;">
					<div class="clear"></div>
					<table cellpadding="0" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th style="width:20%;text-align:center;">参数说明</th>
								<th style="width:80%;text-align:center;">内容</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="width:20%; text-align:right; padding-right:5px;">咨询标题</td>
								<td style="width:80%"><input type="text" style="width:50%;" name="title"></td>
							</tr>
							<tr>
								<td style="width:20%; text-align:right;padding-right:5px;">咨询内容</td>
								<td style="width:80%"><textarea style="width:50%;height:150px;" name="content"></textarea></td>
							</tr>
						</tbody>
					</table>
					<div id="user_btn_div">
						<button id="add_consult_btn" type="button">提交</button>
					</div>
				</form>

				<!-- 管理咨询 -->
				<form id="manage_consult_form" style="display: none;">
					<table cellpadding="0" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th style="width:40%;text-align:center;">标题</th>
								<th style="width:20%;text-align:center;">添加时间</th>
								<th style="width:10%;text-align:center;">状态</th>
								<th style="width:30%;text-align:center;"></th>
							</tr>
						</thead>
						<tbody id="consult_table" style="text-align:center">
<!-- 							<tr> -->
<!-- 								<td style="width:40%">献血后多吃点什么好呢？</td> -->
<!-- 								<td style="width:20%">2017-11-27 17:11:59</td> -->
<!-- 								<td style="width:10%"> -->
<!-- 									<span>未回复</span> -->
<!-- 								</td> -->
<!-- 								<td style="width:30%"> -->
<!-- 									<a class="check_consult" id="" href="javascript:void(0);">查看</a>| -->
<!-- 									<a href="">删除</a> -->
<!-- 								</td> -->
<!-- 							</tr> -->
						</tbody>
					</table>
				</form>

				<!-- 查看咨询 -->
				<div id="check_consult_div" style="display: none;">
					<table cellpadding="0" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th style="width:20%;text-align:center;">参数说明</th>
								<th style="width:80%;text-align:center;">内容</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="width:20%; text-align:right; padding-right:5px;">咨询标题:</td>
								<td style="width:80%" id="consult_title">献血后多吃点什么好呢？</td>
							</tr>
							<tr>
								<td style="width:20%; text-align:right;padding-right:5px;">咨询时间:</td>
								<td style="width:80%" id="consult_time">2017-11-27 17:11:59</td>
							</tr>
							<tr>
								<td style="width:20%; text-align:right;padding-right:5px;">咨询内容:</td>
								<td style="width:80%" id="consult_content">每次献血后希望能快点恢复身体，多吃点什么食物比较好呢？</td>
							</tr>
							<tr>
								<td style="width:20%; text-align:right;padding-right:5px;">回复:</td>
								<td style="width:80%" id="consult_reply"></td>
							</tr>
						</tbody>
					</table>
				</div>
				
			</div>
		</div>
	<#include "footer.ftl"/>
</body>
</html>