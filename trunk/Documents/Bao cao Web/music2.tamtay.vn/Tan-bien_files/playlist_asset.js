var playlist_asset = {
	pl_dialog: null,
	pl_overlay: null,
	indicator: "pl_dlg_indicator",
	divcontent: "pl_dlg_content",
	
	add_to_playlist: function(url, uid){
		if (uid < 1)
		{
			alert('Bạn cần đăng nhập để thực hiện chức năng này!');
			return;
		}
		
		if (playlist_asset.pl_dialog==null){
			playlist_asset.create_playlist_dialog();
		}
				
		new Ajax.Request(url, { asynchronous:true, evalScripts:true, method: 'get', 
		                        onLoading: Element.show(playlist_asset.indicator), 
		 						onComplete: function(res, js)
		 						{
		 							Element.hide(playlist_asset.indicator);
		 							playlist_asset.update_content(res.responseText);
									Modalbox.show(playlist_asset.pl_dialog,{title: 'Tamtay Music - Playlist', width: 550});
		 						}
		 					  }	
		 				);
	},
	
	validateCreateNew: function()
	{
		var name = $('nplaylist_name').value;
		name = name.replace(/^\s+|\s+$/g, '') ;
		if(name == "")
		{
			alert('Bạn chưa nhập tên cho playlist mới!');
			$('nplaylist_name').value = "";
			$('nplaylist_name').focus();
			return false;
		} else if (!goodString($('nplaylist_name').value, 25, 200)){
			alert('Tên playlist của bạn dài quá 200 ký tự hoặc có một từ có độ dài quá 25 ký tự');
			$('nplaylist_name').focus();
			return false;
		}
		
		if ($('nplaylist_desc').value && !goodString($('nplaylist_desc').value, 25, 200)){
			alert('Phần miêu tả playlist của bạn dài quá 200 ký tự hoặc có một từ có độ dài quá 25 ký tự');
			$('nplaylist_desc').focus();
			return false;
		}
		
		return true;
	},
		
	validateAjaxSubmit: function()
	{
		if($('SelectPlaylist_Old').checked)
		{
			if($('playlist').value == "")
			{
				alert('Bạn chưa chọn playlist!');
				$('playlist').focus();
				
				return false;
			}
		}
		else if($('SelectPlaylist_New').checked)
		{
			
			return playlist_asset.validateCreateNew();
		}	
			
		return true;
	},
	
	startAjaxSubmit: function()
	{
		return true;
	},
	
	completeAjaxSubmit: function(response)
	{
		playlist_asset.update_content(response);
		Modalbox.resizeToContent();
	},
	
	update_content: function(strcontent)
	{
		$(playlist_asset.divcontent).innerHTML=strcontent;
	},
	
	hide_dialog: function()
	{
		playlist_asset.update_content("");
		playlist_asset.pl_overlay.hide();
	},
	
	create_playlist_dialog: function(){
		if (playlist_asset.pl_dialog != null){
			return;
		}
		playlist_asset.pl_dialog = $('pl_dialog');
	}
}