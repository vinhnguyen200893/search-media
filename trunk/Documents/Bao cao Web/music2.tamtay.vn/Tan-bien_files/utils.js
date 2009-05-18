var isIE  = (navigator.appVersion.indexOf("MSIE") != -1) ? true : false;
var isWin = (navigator.appVersion.toLowerCase().indexOf("win") != -1) ? true : false;
var isOpera = (navigator.userAgent.indexOf("Opera") != -1) ? true : false;
var isMozilla = (navigator.userAgent.indexOf("Mozilla") != -1) ? true : false;


// Requires: prototype.js, scriptaculous.js?load=effects
// Copy content of an element to clipboard, then select all.
// this function properly run if one of the following conditions is met
//    Browser: IE or Opera: alway run (tested with IE 7 and Opera 9)
//             Firefox or Chrome: run if user have Flash Player 9 or below (not run in flashplayer 10)
function copyToClipboard(elt) {
    var urlSwf = "/includes/swf/_clipboard.swf";
    
    //if (isIE || (!DetectFlashVer(10,0,0)))
	if (1)
    {
	    var strMssgBoxId = "hint_notify";
	
	    var parent = Element.up(elt);
	    var eltNotify = document.createElement('div');
	    eltNotify.appendChild(document.createTextNode("Đường link đã được copy. Hãy bấm Ctrl+V để dán"));
	    parent.appendChild(eltNotify);
	    eltNotify.setAttribute('id', strMssgBoxId);
	    eltNotify.setAttribute('style','border: 1px solid; background-color:#FFFFAA');
	
	    elt.onblur =
	        function(e){
	            Element.hide(eltNotify);
	            return true;
	        }
	
	    var cumOffset = Element.cumulativeOffset(elt);
	    var offset = Element.positionedOffset(elt);
	
	    Element.show(eltNotify);
	
		eltNotify.style.left = (offset.left + 2) + 'px';
	    eltNotify.style.top = (offset.top + eltNotify.offsetHeight + 5) + 'px';
	
	    var xEffect = Effect.Fade(eltNotify,
	        {
	            fps: 75,
	            from: 1.9,
	            to: 0.0,
	            duration: 2.5,
	            queue: 'front',
	            afterFinish: function() {
	                Element.remove(eltNotify); 
	            }
	        }
	    );
	}
	
    // Copy the text inside the text box to the user's clipboard
	if (window.clipboardData) {
		window.clipboardData.setData("Text",elt.value);
	} else {

	    var flashcopier = 'flashcopier';
	    if(!$(flashcopier)){
	        var divholder = document.createElement('div');
	        divholder.id = flashcopier;
	        document.body.appendChild(divholder);
	    }
	
	    $(flashcopier).innerHTML = '';
	    var divinfo = '<embed src="' + urlSwf + '" FlashVars="clipboard='+escape(elt.value)+'" width="0" height="0" type="application/x-shockwave-flash"></embed>';
	    $(flashcopier).innerHTML = divinfo;
	}
	    
    elt.select();

    return true;
}

// Flash Player Version Detection - Rev 1.6
// Detect Client Browser type
// Copyright(c) 2005-2006 Adobe Macromedia Software, LLC. All rights reserved.
function ControlVersion()
{
	var version;
	var axo;
	var e;

	// NOTE : new ActiveXObject(strFoo) throws an exception if strFoo isn't in the registry

	try {
		// version will be set for 7.X or greater players
		axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7");
		version = axo.GetVariable("$version");
	} catch (e) {
	}

	if (!version)
	{
		try {
			// version will be set for 6.X players only
			axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.6");
			
			// installed player is some revision of 6.0
			// GetVariable("$version") crashes for versions 6.0.22 through 6.0.29,
			// so we have to be careful. 
			
			// default to the first public version
			version = "WIN 6,0,21,0";

			// throws if AllowScripAccess does not exist (introduced in 6.0r47)		
			axo.AllowScriptAccess = "always";

			// safe to call for 6.0r47 or greater
			version = axo.GetVariable("$version");

		} catch (e) {
		}
	}

	if (!version)
	{
		try {
			// version will be set for 4.X or 5.X player
			axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.3");
			version = axo.GetVariable("$version");
		} catch (e) {
		}
	}

	if (!version)
	{
		try {
			// version will be set for 3.X player
			axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.3");
			version = "WIN 3,0,18,0";
		} catch (e) {
		}
	}

	if (!version)
	{
		try {
			// version will be set for 2.X player
			axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
			version = "WIN 2,0,0,11";
		} catch (e) {
			version = -1;
		}
	}
	
	return version;
}

// JavaScript helper required to detect Flash Player PlugIn version information
function GetSwfVer(){
	// NS/Opera version >= 3 check for Flash plugin in plugin array
	var flashVer = -1;
	
	if (navigator.plugins != null && navigator.plugins.length > 0) {
		if (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]) {
			var swVer2 = navigator.plugins["Shockwave Flash 2.0"] ? " 2.0" : "";
			var flashDescription = navigator.plugins["Shockwave Flash" + swVer2].description;
			var descArray = flashDescription.split(" ");
			var tempArrayMajor = descArray[2].split(".");			
			var versionMajor = tempArrayMajor[0];
			var versionMinor = tempArrayMajor[1];
			var versionRevision = descArray[3];
			if (versionRevision == "") {
				versionRevision = descArray[4];
			}
			if (versionRevision[0] == "d") {
				versionRevision = versionRevision.substring(1);
			} else if (versionRevision[0] == "r") {
				versionRevision = versionRevision.substring(1);
				if (versionRevision.indexOf("d") > 0) {
					versionRevision = versionRevision.substring(0, versionRevision.indexOf("d"));
				}
			}
			var flashVer = versionMajor + "." + versionMinor + "." + versionRevision;
		}
	}
	// MSN/WebTV 2.6 supports Flash 4
	else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.6") != -1) flashVer = 4;
	// WebTV 2.5 supports Flash 3
	else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.5") != -1) flashVer = 3;
	// older WebTV supports Flash 2
	else if (navigator.userAgent.toLowerCase().indexOf("webtv") != -1) flashVer = 2;
	else if ( isIE && isWin && !isOpera ) {
		flashVer = ControlVersion();
	}	
	return flashVer;
}

// When called with reqMajorVer, reqMinorVer, reqRevision returns true if that version or greater is available
function DetectFlashVer(reqMajorVer, reqMinorVer, reqRevision)
{
	versionStr = GetSwfVer();
	if (versionStr == -1 ) {
		return false;
	} else if (versionStr != 0) {
		if(isIE && isWin && !isOpera) {
			// Given "WIN 2,0,0,11"
			tempArray         = versionStr.split(" "); 	// ["WIN", "2,0,0,11"]
			tempString        = tempArray[1];			// "2,0,0,11"
			versionArray      = tempString.split(",");	// ['2', '0', '0', '11']
		} else {
			versionArray      = versionStr.split(".");
		}
		var versionMajor      = versionArray[0];
		var versionMinor      = versionArray[1];
		var versionRevision   = versionArray[2];

        	// is the major.revision >= requested major.revision AND the minor version >= requested minor
		if (versionMajor > parseFloat(reqMajorVer)) {
			return true;
		} else if (versionMajor == parseFloat(reqMajorVer)) {
			if (versionMinor > parseFloat(reqMinorVer))
				return true;
			else if (versionMinor == parseFloat(reqMinorVer)) {
				if (versionRevision >= parseFloat(reqRevision))
					return true;
			}
		}
		return false;
	}
}

var share_overlay = {
	show: function()
	{
//		if ($('player_container') != null){
//			$('player_container').style.display = 'none';
//		}
		Modalbox.show($('share_dialog'), {title: 'Chia sẻ với bạn bè địa chỉ trang web này', width: 500});
	},
	hide: function()
	{
//		if ($('player_container') != null){
//			$('player_container').style.display = '';
//		}		
	}
}

//
//function DialogOverlay(content, container) {
//
//	// Manage arguments and assign defaults, 
//	if (typeof container == 'undefined' ) container = document.body;
//	if (null == (this.container = $(container))) throw("container is not valid");
//
//	// Assign instance variables
//	this.content = content;
//	this.overlay = new Element('div', { 'class': 'overlay' }).hide();
//	this.dialog = new Element('div', { 'class': 'dialog' }).hide();
//
//	// Hide the overlay when clicked. Ignore clicks on the dialog.
//	Event.observe(this.overlay, 'click', this.hide.bindAsEventListener(this));
//	//Event.observe(this.dialog, 'click',  function(event) { Event.stop(event) });
//	
//	// Insert the elements into the DOM
//	this.dialog.insert(this.content);
//	this.container.insert(this.overlay);
//	this.container.insert(this.dialog);
//
//	// Content may have been hidden if it is embedded in the page
//	content.show();
//	this.dialog.hide();
//}
//
//DialogOverlay.prototype.show = function() {
//	if ($('player_container') != null){
//		$('player_container').style.display = 'none';
//	}
//	new Effect.Appear(this.overlay, { duration: 0.7,  to: 0.8 });
//	this.dialog.show();
//	return this;
//};
//
//DialogOverlay.prototype.hide = function(event) {
//	this.dialog.hide();
//	this.overlay.hide();
//	if ($('player_container') != null){
//		$('player_container').style.display = '';
//	}
//	return this;
//};
//
function report_bad(url)
{
	var width = 500;
	var height = 250;
	var left = parseInt((screen.availWidth/2) - (width/2));
	var top = parseInt((screen.availHeight/2) - (height/2));
	var windowFeatures = "width=" + width + ", height=" + height + ", left=" + left + ", top=" + top + ", screenX=" + left + ",screenY=" + top + ", resizable=no, status=no, menubar=no, scrollbars=no";
	window.open(url, "subWind", windowFeatures);
	return false;	
}

function Select_Type()
{
	if($('SelectPlaylist_Old').checked)
	{
		$('nplaylist_name').disabled = true;
		$('playlist').disabled = false;
		Element.hide('addmore');
		Modalbox.resizeToContent();
	}
	else if($('SelectPlaylist_New').checked)
	{
		$('nplaylist_name').disabled = false;
		$('playlist').disabled =true;
		Modalbox.resizeToInclude('addmore', {afterResize: function(){Element.show('addmore')}});
	}
}

/*
* function limitTextLength: limit the  content less than a given value
* t: text control object ( HTML)
* l: maximum text length
* d: div control, to display the status message
* msg : the message, need to use this for multilang prupose, for example :  "Con lai [n] ky tu"
*/
function limitTextLength(t,l,d,msg)
{
  if (t.value.length>l) t.value=t.value.substring(0,l);
  d = document.getElementById(d);
  l=(l-t.value.length);
  d.innerHTML=msg.replace('[n]',l);
}

/*
*  function goodString: check the string is good or not
*  a good string is a string with length less than or equal to strlength and 
*  length of each word in a string is less than or equal to wordlength.
*  parameters:
*               string str:        the string to check
*			    int    wordlength: maximum length of each word
*               int    strlength: maximum length of string
*/
function goodString(str, wordlength, strlength)
{
	if (str.length > strlength){
		return false;
	}
	
	var array_str = str.split(" ");
	var flag = true;
	for (i in array_str)
	{
		if (array_str[i].length > wordlength)
		{
			flag = false;
			break;
		}
	}
	
	if (flag == false)		
		return false;	

	return true;
}

function openWindow(url, winname) {	
	var features, top, left, width, height;
	var reOpera = /opera/i ;
	var winnameRequired = ((navigator.appName == "Netscape" && parseInt(navigator.appVersion) == 4) || reOpera.test(navigator.userAgent));

	width= window.screen.width;
	height = window.screen.height;
	left = 0;
	top = 0;	
		features = "width=" + width + ",height=" + height + ",top=" + top + ",left=" + left + ",status=1,location=1,menubar=1,toolbar=1,scrollbars=1";
	newwindow = window.open(url, winname, features);
	newwindow.focus();
	return false;
}
