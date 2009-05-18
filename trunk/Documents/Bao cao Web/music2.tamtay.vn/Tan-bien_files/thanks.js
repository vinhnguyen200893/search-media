var thanks_js = {
	doing: false,
	loading_div: 'promoteLoading',
	load_thanks: function(url)
	{
		Element.show(thanks_js.loading_div);
		new Ajax.Request(url, { asynchronous:true, evalScripts:true, method: 'get', 
		 						onComplete: function(res)
		 						{
		 							Element.hide(thanks_js.loading_div);
		 							json = res.responseJSON;
		 							if (json[0]==0) { //error
		 								Element.hide('do_mark');
		 								Element.hide('thanks_info');
		 								Element.show('service_err');		 								
		 							} else {
		 								Element.update('thanks_count',json[1]);
		 								Element.show('thanks_info');
		 								if (json[2]!=0){
		 									thanks_js.show_thanked(json[2]);
		 								}
		 							}
		 						}
		 					  }	
		 				);
	},
	
	show_thanked: function(mark){
		if (mark==1){
			$('addMark').addClassName('on');
			$('minusMark').removeClassName('on');
		} else if (mark==-1) {
			$('minusMark').addClassName('on');
			$('addMark').removeClassName('on');			
		}
	},
	
	addMark: function(uid, nid)
	{
		if (thanks_js.doing)
			return;
		thanks_js.doing = true;
		url = '/main/thanks/uid/'+uid+'/nid/'+nid;
		Element.show(thanks_js.loading_div);
		new Ajax.Request(url, { asynchronous:true, evalScripts:true, method: 'post', 
		                        parameters: {submit: "yes", mark: 1},
		 						onComplete: function(res)
		 						{
		 							thanks_js.doing = false;
		 							Element.hide(thanks_js.loading_div);
		 							json = res.responseJSON;
		 							if (json[0]==0) { //error
		 								Element.hide('do_mark');
		 								Element.hide('thanks_info');
		 								Element.show('service_err');		 								
		 							} else {
		 								Element.update('thanks_count',json[1]);
		 								if (json[2]!=0){
		 									thanks_js.show_thanked(json[2]);
		 								}
		 							}
		 						}
		 					  }	
		 				);
	},
	minusMark: function(uid, nid)
	{
		if (thanks_js.doing)
			return;
		thanks_js.doing = true;
		url = '/main/thanks/uid/'+uid+'/nid/'+nid;
		Element.show(thanks_js.loading_div);
		new Ajax.Request(url, { asynchronous:true, evalScripts:true, method: 'post', 
		                        parameters: {submit: "yes", mark: -1},
		 						onComplete: function(res)
		 						{
		 							thanks_js.doing = false;
		 							Element.hide(thanks_js.loading_div);
		 							json = res.responseJSON;
		 							if (json[0]==0) { //error
		 								Element.hide('do_mark');
		 								Element.hide('thanks_info');
		 								Element.show('service_err');		 								
		 							} else {
		 								Element.update('thanks_count',json[1]);
		 								if (json[2]!=0){
		 									thanks_js.show_thanked(json[2]);
		 								}
		 							}
		 						}
		 					  }	
		 				);
	}
}