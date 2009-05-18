function doShowHidePanel(sid) 
		{
			titleid = "t_" + sid;
			contentid = "c_" + sid;
			if (document.getElementById(contentid).className=='content_block')
				{document.getElementById(contentid).className='content_none';
				document.getElementById(titleid).className='title_none';}
			else
				{document.getElementById(contentid).className='content_block';
				document.getElementById(titleid).className='title_block';}
}

//Change class name:
function ChangeClass(sid,sclassname) {
	document.getElementById(sid).className = sclassname;
}
			
function insertSymbol(symbol){
	var obj=$('comment_content');
	if (document.selection)
	{
		obj.focus();
		sel = document.selection.createRange();
		sel.text = symbol;
	}
	else if (obj.selectionStart || obj.selectionStart == 0)
	{
		var startPos = obj.selectionStart;
		var endPos = obj.selectionEnd;
		obj.value = obj.value.substring(0, startPos)
					+ symbol
					+ obj.value.substring(endPos, obj.value.length);
	} 
	else
	{
		obj.value += symbol;
	}
}

function ShowFullLyric() {
	ChangeClass('lyric-short','lyric-short display-none');
	ChangeClass('lyric-full','lyric-full display-block');
	ChangeClass('cmd_view_full','display-none');
	ChangeClass('cmd_view_short','display-block');					
}

function ShowShortLyric() {
	ChangeClass('lyric-short','lyric-short display-block');
	ChangeClass('lyric-full','lyric-full display-none');
	ChangeClass('cmd_view_full','display-block');
	ChangeClass('cmd_view_short','display-none');					
}

function show_embed_dialog(elt, skin){
	elt=$('eanchor');
	$('embed_image').src = '';
	var offset = Element.positionedOffset(elt);
	$('embed_dialog').style.top = (offset.top + 30) + 'px';
	$('embed_dialog').style.left = (offset.left-20)+'px';
	
	$('embed_image').src = '/images/fplayer'+skin+'_preview.gif';
	for (i = 0; i < 2; i++)
	{
		if ($('embed_text_'+i) != null)
		{
			$('embed_text_'+i).style.display = "none";
		}
	}
	$('embed_text_'+skin).style.display = "";
	
	Effect.Appear('embed_dialog', { duration: 0.5 });
}

function close_embed_dialog(){
	Effect.BlindUp('embed_dialog', { duration: 0.8 });			
	$('embed_image').src = '';
	for (i = 0; i < 4; i++)
	{
		if ($('embed_text_'+i) != null)
		{
			$('embed_text_'+i).style.display = "none";
		}
	}
}

function toggle_selectall(checkname, exby){
  for (i = 0; i < checkname.length; i++)
  	if (checkname[i].type=='checkbox' && checkname[i].name != 'all')
  		checkname[i].checked = exby.checked? true:false
}

function mymusic_submit()
{
	var checkname = document.admin_form;
	var havecheck = false;
	for (i = 0; i < checkname.length; i++)
  		if (checkname[i].type=='checkbox' && checkname[i].checked)
  		{
  			havecheck = true;
  			break;
  		}
  	if (!havecheck || (typeof(checkname.admin_action)!='undefined' && checkname.admin_action.value==0))
  	{
  		alert('Bạn phải chọn 1 hành động và ít nhất 1 bài hát để thực hiện!');
  		return false;
  	}
  	else
  	{
  		if (confirm('Bạn có chắc chắn muốn thực hiện hành động này?')){
  			return true;
  		} else {
  			return false;
  		}
  	}
}

function setHomepage()
{
if (document.all)
{
document.body.style.behavior='url(#default#homepage)';
document.body.setHomePage('http://www.tamtay.vn');

}
else if (window.sidebar)
{
if(window.netscape)
{
try
{
netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
}
catch(e)
{
alert("Trình duyệt của bạn không hỗ trợ thao tác này. Nếu bạn muốn thực hiện tiếp, hãy làm theo hướng dẫn sau:\ngõ about:config vào thanh địa chỉ, sau đó thay đổi giá trị của tham số signed.applets.codebase_principal_support thành true");
}
}
var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components. interfaces.nsIPrefBranch);
prefs.setCharPref('browser.startup.homepage','http://www.tamtay.vn');
}
} 

function myCookie(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        // CAUTION: Needed to parenthesize options.path and options.domain
        // in the following expressions, otherwise they evaluate to undefined
        // in the packed version for some reason...
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = name+'='+ encodeURIComponent(value)+expires+path+domain+secure;
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = cookies[i];
                cookie = cookie.replace(/^\s+|\s+$/g, '') ;
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
}

function toggle_popup()
{
	var popup = $('popupcheck').checked;
	myCookie('play_popup',null);
	myCookie('play_popup',popup,{ expires: 7, path: '/', domain: 'tamtay.vn', secure: false });
}

function get_popup_cookie(){
	var popup = myCookie('play_popup');
	if (popup==null){
		popup = true;
		myCookie('play_popup',popup,{ expires: 7, path: '/', domain: 'tamtay.vn', secure: false });
	}
	if (popup=='true')
		$('popupcheck').checked = true;
	else
		$('popupcheck').checked = false;		
}

function resizeImage(img,nsize)
{
	var nWidth=img.width;
	var	nHeight=img.height;	
	nScale = nWidth/nHeight;		
	//Check:
	if (nScale > 1) {
		nWidth = nsize;		
		nHeight = (nWidth*img.height)/img.width;
		//fix vertical-align:	
		nMargin = (nWidth - nHeight)/2;
		//Set style to image:
		img.width = nWidth;
		img.height = nHeight;
		img.style.margin = nMargin + 'px 0px';
		}
	else {
		//Return size:
		nHeight = nsize;
		nWidth = (nHeight*img.width)/img.height;
		//fix align:	
		nMargin = (nHeight - nWidth)/2;	
		//Set style to image:	
		img.width = nWidth;
		img.height = nHeight;
		img.style.margin = '0px ' + nMargin + 'px';
	}	
}