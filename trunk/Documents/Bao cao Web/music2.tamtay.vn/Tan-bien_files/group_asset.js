var group_asset=
{
	firstTime: true,
	oldBlock: "",
	elm: "",
	add_event: function(url)
	{
		if (group_asset.firstTime)
		{
			group_asset.firstTime=false;
			group_asset.oldBlock=$(group_asset.elm).innerHTML;
		}
		
		new Ajax.Request(url, { asynchronous:true, evalScripts:true, method: 'get', 
		                        onLoading: $(group_asset.elm).innerHTML = " Đang tải ...", 
		 						onComplete: function(res, js)
		 						{
		 							$(group_asset.elm).innerHTML = res.responseText;
		 						}
		 					  }	
		 				);

		return false;		
	},
	add_event_send: function(url)
	{
		new Ajax.Request(url, { asynchronous:true, evalScripts:true, 
					  			parameters: 'eid='+$('eid').value,
		                        onLoading: $(group_asset.elm).innerHTML = " Đang gửi ...", 
		 						onComplete: function(res, js)
		 						{
		 							$(group_asset.elm).innerHTML = res.responseText;
		 						}						
						});
	},
	invite_group: function(url)
	{
		if (group_asset.firstTime)
		{
			group_asset.firstTime=false;
			group_asset.oldBlock=$(group_asset.elm).innerHTML;
		}
		
		new Ajax.Request(url, { asynchronous:true, evalScripts:true, method: 'get', 
		                        onLoading: $(group_asset.elm).innerHTML = " Đang tải ...", 
		 						onComplete: function(res, js)
		 						{
		 							$(group_asset.elm).innerHTML = res.responseText;
		 						}
		 					  }	
		 				);

		return false;
	},
	restore: function ()
	{
		$(group_asset.elm).innerHTML = group_asset.oldBlock;
	},
	invite_send: function (url,invited,inviting)
	{
		if (!confirm('Bạn chắc chắn gửi thư mời tham gia nhóm'))
		{
			return;
		}
		gid = $('gid').value;
		new Ajax.Request(url, { asynchronous:true, evalScripts:true, method: 'post',
		                        onLoading: $(group_asset.elm).innerHTML = " Đang tải ...", 
					  			parameters: 'gid='+gid+'&invited='+invited+'&inviting='+inviting,
		 						onComplete: function(res, js)
		 						{
		 							$(group_asset.elm).innerHTML = res.responseText;
		 						}}); 
	},
	add_group: function(url)
	{
		if (group_asset.firstTime)
		{
			group_asset.firstTime=false;
			group_asset.oldBlock=$(group_asset.elm).innerHTML;
		}
		
		new Ajax.Request(url, { asynchronous:true, evalScripts:true, method: 'get', 
		                        onLoading: $(group_asset.elm).innerHTML = " Đang tải ...", 
		 						onComplete: function(res, js)
		 						{
		 							$(group_asset.elm).innerHTML = res.responseText;
		 						}
		 					  }	
		 				);

		return false;
	},
	add_group_send: function(url)
	{
		new Ajax.Request(url, { asynchronous:true, evalScripts:true, 
					  			parameters: 'group_id='+$('gid').value,
		                        onLoading: $(group_asset.elm).innerHTML = " Đang gửi ...", 
		 						onComplete: function(res, js)
		 						{
		 							$(group_asset.elm).innerHTML = res.responseText;
		 						}						
						});
	}
}