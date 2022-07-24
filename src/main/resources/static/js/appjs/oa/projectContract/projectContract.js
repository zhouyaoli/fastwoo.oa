var prefix = "/oa/projectContract"
$(function() {
	load();
});

function load() {
	$('#exampleTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : prefix + "/list", // 服务器数据的加载地址
		// showRefresh : true,
		// showToggle : true,
		// showColumns : true,
		iconSize : 'outline',
		toolbar : '#exampleToolbar',
		striped : true, // 设置为true会有隔行变色效果
		dataType : "json", // 服务器返回的数据类型
		pagination : true, // 设置为true会在底部显示分页条
		// queryParamsType : "limit",
		// //设置为limit则会发送符合RESTFull格式的参数
		singleSelect : false, // 设置为true将禁止多选
		// contentType : "application/x-www-form-urlencoded",
		// //发送到服务器的数据编码类型
		pageSize : 10, // 如果设置了分页，每页数据条数
		pageNumber : 1, // 如果设置了分布，首页页码
		// search : true, // 是否显示搜索框
		showColumns : false, // 是否显示内容下拉框（选择显示的列）
		sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
		queryParams : function(params) {
			return searchParam(params);
		},
		// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
		// queryParamsType = 'limit' ,返回参数必须包含
		// limit, offset, search, sort, order 否则, 需要包含:
		// pageSize, pageNumber, searchText, sortName,
		// sortOrder.
		// 返回false将会终止请求
		columns : [ {
			checkbox : true
		}, {

			visible : false,
			field : 'id',
			title : '主键'
		}, {
			field : 'no',
			title : '合同编号'
		}, {
			field : 'name',
			title : '合同名称'
		}, {
			visible : false,
			field : 'projectId',
			title : '项目id'
		}, {
			field : 'projectName',
			title : '项目名称'
		}, {
			field : 'amount',
			title : '合同金额'
		}, {
			field : 'payAmount',
			title : '已(收/付)款金额'
		}, {
			field : 'billAmount',
			title : '已开票金额'
		}, {
			field : 'billType',
			title : '开票类型',
			formatter : function(value, row, index) {
				if (1 == value) {
					return "增值税发票";
				} else if (2 == value) {
					return "普通发票";
				}
				return "";
			}
		}, {
			field : 'person',
			title : '销售人员'
		}, {
			field : 'oneCompany',
			title : '甲方公司'
		}, {
			field : 'oneLeader',
			title : '甲方负责人'
		}, {
			field : 'onePhone',
			title : '甲方联系人'
		}, {
			field : 'twoCompany',
			title : '乙方名称'
		}, {
			field : 'twoLeader',
			title : '乙方负责人'
		}, {
			field : 'twoPhone',
			title : '乙方联系电话'
		}, {
			field : 'state',
			title : '合同状态',
			formatter : function(value, row, index) {
				if (1 == value) {
					return "未立项";
				} else if (2 == value) {
					return "立项成立";
				} else if (4 == value) {
					return "结束";
				}
				return "";
			}
		}, {
			field : 'payWay',
			title : '付款方式',
			formatter : function(value, row, index) {
				if (1 == value) {
					return "阶段付款";
				} else if (2 == value) {
					return "比例付款";
				} else if (3 == value) {
					return "全额付款";
				}
				return "";
			}
		}, {
			field : 'signDate',
			title : '合同签署日期'
		}, {
			field : 'validDate',
			title : '合同生效日期'
		}, {
			field : 'limitDate',
			title : '合同截止日期'
		}, {
			field : 'type',
			title : '合同类型',
			formatter : function(value, row, index) {
				if (1 == value) {
					return "收入合同";
				} else if (2 == value) {
					return "支出合同";
				}
				return "";
			}
		}, {
			visible : false,
			field : 'contractNum',
			title : '合同数'
		}, {
			title : '操作',
			field : 'operator',
			align : 'center',
			formatter : function(value, row, index) {
				var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i></a> ';
				var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a> ';
				var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\'' + row.id + '\')"><i class="fa fa-key"></i></a> ';
				return e + d;
			}
		} ],
		onLoadError : function(e, s) { // 加载失败时执行
			console.log(e);
			console.log(s);
		},
		onLoadSuccess : function(data) { // 加载失败时执行
			if (data && data.code && 403 == data.code) {
				windows.location.href = data.loginUrl
			}
		}
	});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function importExcel() {
	layer.open({
		type : 2,
		title : '导入',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '400px', '220px' ],
		content : '/common/excelModel/importExcel?submitUrl=' + prefix + '/saveImportExcel&modelUrl=/excelModel/oa/projectContract.xlsx' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {

		sendAjaxRequest(prefix + "/remove", {
			'id' : id
		}, false, false, false, function(r) {
			if (r.code == 0) {
				layer.msg(r.msg);
				reLoad();
			} else {
				layer.msg(r.msg);
			}
		});

		// $.ajax({
		// url : prefix+"/remove",
		// type : "post",
		// data : {
		// 'id' : id
		// },
		// success : function(r) {
		// if (r.code==0) {
		// layer.msg(r.msg);
		// reLoad();
		// }else{
		// layer.msg(r.msg);
		// }
		// }
		// });
	})
}
function searchParam(params) {
	let
	reParam = {
		searchKey : $('#searchKey').val()
	};
	if (params) {
		// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
		reParam.limit = params.limit;
		reParam.offset = params.offset;
	}
	return reParam;
}
function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});

		sendAjaxRequest(prefix + '/batchRemove', {
			"ids" : ids
		}, false, false, false, function(r) {
			if (r.code == 0) {
				layer.msg(r.msg);
				reLoad();
			} else {
				layer.msg(r.msg);
			}
		});
		// $.ajax({
		// type : 'POST',
		// data : {
		// "ids" : ids
		// },
		// url : prefix + '/batchRemove',
		// success : function(r) {
		// if (r.code == 0) {
		// layer.msg(r.msg);
		// reLoad();
		// } else {
		// layer.msg(r.msg);
		// }
		// }
		// });
	}, function() {

	});
}