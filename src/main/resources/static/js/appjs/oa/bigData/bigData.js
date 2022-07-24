$(function() {

	// 注册wrap事件
	// registerWrapEvent();
	setSize();
	initProjectRender();
});

$(window).resize(function() {
	setSize();
});

function setSize() {
	let
	h = $(window).height();
	let
	t = 90;
	let
	c = (h - t);
	$('#center-tr>td .box').css('height', c * 0.45);
	$('#center-tr>td:FIRST-CHILD .box').css('height', c * 0.44);
	// $('#project-list').css('height', c*0.88+30 );
	$('#footer-tr>td .box').css({
		'height' : c * 0.44
	});
	$('body').css('height', $(document).height());
};

var timer = null;

/**
 * 初始化渲染
 */
function initProjectRender() {
//	if (timer) {
//		window.clearInterval(timer);
//	}

//	timer = window.setInterval(function() {
//		loadProjectRender()
//	}, 2 * 60 * 1000);

	loadProjectRender();
}

function loadProjectRender() {

	try {
		getDistributeStstus();
	} catch (e) {

	}

	try {
		getSampleCollectState();
	} catch (e) {

	}
	try {
		getCheckState();
	} catch (e) {

	}
	try {
		getMakeOrgAcceptState();
	} catch (e) {

	}
	try {
		getMoveState();
	} catch (e) {
		
	}
}
