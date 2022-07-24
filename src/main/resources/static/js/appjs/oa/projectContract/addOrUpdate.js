$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});


function update() {
	sendAjaxRequest("/oa/projectContract/saveOrUpdate", $('#signupForm').serialize(), false, false, false, function(data) {
		debugger
		if (data.code == 0) {
			parent.layer.msg("操作成功");
			parent.reLoad();
			var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
			parent.layer.close(index);

		} else {
			parent.layer.alert(data.msg)
		}

	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			no : {
				required : true,
				minlength : 2,
				remote : {
					url : "/oa/projectContract/exit", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						no : function() {
							return $("#no").val();
						},
						id : function() {
							return $("#id").val();
						}
					}
				}
			},
			name : {
				required : true
			},
			projectName : {
				required : true
			},
			signDate : {
				beforTo : "#validDate"
			},
			validDate : {
				afterTo : "#signDate",
				beforTo : "#limitDate"
			},
			limitDate : {
				afterTo : "#validDate"
			},
		},
		messages : {
			name : {
				required : icon + "请输入合同名称"
			},
			projectName : {
				required : icon + "请选择项目名称"
			},
			no : {
				required : icon + "请输入您的合同编号",
				minlength : icon + "合同编号必须两个字符以上",
				remote : icon + "合同编号已经存在"
			},
			signDate : {
				beforTo : "合同签订日期必须小于合同生效日期"
			},
			validDate : {
				afterTo : "合同生效日期必须大于于合同签订日期",
				beforTo : "合同生效日期必须小于合同截止日期"
			},
			limitDate : {
				afterTo : "合同截止日期必须大于于合同生效日期"
			},
		}
	})
}