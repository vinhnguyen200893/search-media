function mylog(message) {
    if (!mylog.window_ || mylog.window_.closed) {
        var win = window.open("", null, "width=400,height=200," +
                              "scrollbars=yes,resizable=yes,status=no," +
                              "location=no,menubar=no,toolbar=no");
        if (!win) return;
        var doc = win.document;
        doc.write("<html><head><title>Debug Log</title></head>" +
                  "<body></body></html>");
        doc.close();
        mylog.window_ = win;
    }
    var logLine = mylog.window_.document.createElement("div");
    logLine.appendChild(mylog.window_.document.createTextNode(message));
    mylog.window_.document.body.appendChild(logLine);
}

//require prototype
function HotPlay(url){
	new Ajax.Request(url, { asynchronous:true, evalScripts:true, method: 'get', 
	                        onLoading: Element.show('indicator'), 
	 						onComplete: function(res, js)
	 						{
	 							Element.hide('indicator');
	 							$('hotplay_container').innerHTML = res.responseText;
	 						}
	 					  }	
	 				);
	 Element.show('hotplay_container');
}

function ArtistListenAll(url){
	new Ajax.Request(url, { asynchronous:true, evalScripts:true, method: 'get', 
	 						onComplete: function(res, js)
	 						{
	 							$('hotplay_container').innerHTML = res.responseText;
	 						}
	 					  }	
	 				);	
	Element.show('hotplay_container');
}

function CreateWMPlayer(cName, cURL, pWidth, pHeight, autoStart)
{
	c = document.getElementById(cName);
	if (!pWidth)
		pWidth = 480;
	if (!pHeight)
		pHeight = 44;
	if ((autoStart===0)||(autoStart===false)){
		eautoStart = 0;
	}
	else {
		eautoStart = 1;
	}
	var spec = '';
    if(-1 != navigator.userAgent.indexOf("MSIE")){
    	spec = '  classid="clsid:6BF52A52-394A-11d3-B153-00C04F79FAA6"';
    }
	else {
		spec = '  type="application/x-mplayer2"';		
	}
	
	document.write('<object id="Player" width="'+pWidth+'" height="'+pHeight+'" standby="Loading Microsoft Windows Media Player components..." '+spec+' >');
	document.write('  <param name="Url" value="'+cURL+'" />');
	document.write('  <param name="ShowControls" value="true" />');
	document.write('  <param name="PlayCount" value="1" />');
	document.write('  <param name="AutoStart" value="'+eautoStart+'" />');
	document.write('  <param name="Volume" value="100" />');
	document.write('  <param name="ShowStatusbar" value="true" />');
	document.write('  <param name="EnableContextMenu" value="true" />');
	document.write('  <param name="uiMode " value="full" />');
	document.write('</object>');
}

function PlayerJump(container, txt_search, stt)
{
	var str = orig_player.replace(txt_search, txt_search+'/st/'+stt);
	str = str.replace('src="'+txt_search+'"', 'src="'+txt_search+'/st/'+stt+'"')
	$(container).innerHTML = str;
}