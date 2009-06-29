//defautl search by music
//flag nay kiem tra xem dang o dang tim kiem nhac hay phim
//0 la nhac. 1 la phim
var flag_musicOrFilm = 0;

//flag kiem tra xem co tim kiem nang cao ko
//0 la ko. 1 la co
var flag_search_adv=0;

//flag kt xem co tim kiem theo tieu chi ko 
//o la ko, 1 la co
var flag_search_tc = 0;

//ham cat chuoi
//current_str chuoi dua vao . find_str tieu chi cat
function right(current_str,find_str){
	var cL = current_str.length;
	var fL = find_str.length;
	var pos = current_str.lastIndexOf(find_str);
	if(pos+fL>=cL)
	return "";
	else if(pos==-1)
	return current_str;
	else
	return current_str.substring(pos+fL,cL);
	
}

//ham tim kim nang cao
function search_adv()
{	
	//flag_search_tc=0;
	flag_search_adv=1;//bat co search nang cao
	
	//document.getElementById("tc").style.visibility = "hidden";
	//document.getElementById("tc").style.height = "0px";

	//document.getElementById("search_tc").src = "images/search_tc_o.jpg";
	
	if(document.getElementById("adv").style.visibility == "hidden")
	{
		flag_search_adv = 1;
		document.getElementById("adv").style.visibility = "visible";
		document.getElementById("adv").style.height = "auto";			
		
		document.getElementById("search_adv").src = "images/search_adv_c.jpg";
		
	}
	else
	{
		flag_search_adv = 0;
		document.getElementById("adv").style.visibility = "hidden";
		document.getElementById("adv").style.height = "0px";	
	
		document.getElementById("search_adv").src = "images/search_adv_o.jpg";
	}
}

//ham tim kiem ttheo tieu chi
function search_tc()
{	
	//tat flag search nang cao
	flag_search_adv=0;
	
	document.getElementById("adv").style.visibility = "hidden";
	document.getElementById("adv").style.height = "0px";
	document.getElementById("search_adv").src = "images/search_adv_o.jpg";

	//flag_search_tc=1;
	
	if(document.getElementById("tc").style.visibility == "hidden")
	{
		flag_search_tc = 1;
		document.getElementById("tc").style.visibility = "visible";
		document.getElementById("tc").style.height = "auto";			
		
		document.getElementById("search_tc").src = "images/search_tc_c.jpg";
		
	}
	else
	{
		flag_search_tc = 0;
		document.getElementById("tc").style.visibility = "hidden";
		document.getElementById("tc").style.height = "0px";				
		
		document.getElementById("search_tc").src = "images/search_tc_o.jpg";
	}

}

//ham khi nhan button tim kiem
function search()
{
	alert("defalt");
}

//ham khi chon button nhac
function chooseMusic()
{	
	//alert("abc");
	//var t = document.getElementById("search_adv").src;
	//alert(t);
	document.getElementById("search_adv").src = "images/search_adv_o.jpg";
	document.getElementById("search_tc").src = "images/search_tc_o.jpg";
	
	document.getElementById("adv").style.visibility = "hidden";
	document.getElementById("adv").style.height = "0px";	
	
	document.getElementById("tc").style.visibility = "hidden";
	document.getElementById("tc").style.height = "0px";	
	
	var s = document.getElementById("music").src;
	var src = right(s,"/");
	
	if(src != "music_on.jpg")
	{		
		document.getElementById("music").src = "images/music_on.jpg";
		document.getElementById("film").src = "images/film.jpg";		
	}
	flag_musicOrFilm = 0;		

	flag_search_adv=0;
	flag_search_tc=0;

//alert(flag);
}

//ham xu ly khi nhan button film
function chooseFilm()
{
	document.getElementById("search_adv").src = "images/search_adv_o.jpg";
	document.getElementById("search_tc").src = "images/search_tc_o.jpg";
	
	document.getElementById("adv").style.visibility = "hidden";
	document.getElementById("adv").style.height = "0px";	
	
	document.getElementById("tc").style.visibility = "hidden";
	document.getElementById("tc").style.height = "0px";	
	//alert("as");
	var s = document.getElementById("film").src;
	var src = right(s,"/");
	if(src != "film_on.jpg")
	{
		document.getElementById("music").src = "images/music.jpg";
		document.getElementById("film").src = "images/film_on.jpg";
	}
	flag_musicOrFilm = 1;
	flag_search_adv=0;
	flag_search_tc=0;
	//alert(flag);
}


//nhan chon button dong lai
function btclose()
{
	document.getElementById("search_adv").src = "images/search_adv_o.jpg";
	//document.getElementById("search_tc").src = "images/search_tc_o.jpg";
	
	document.getElementById("adv").style.visibility = "hidden";
	document.getElementById("adv").style.height = "0px";	
	
	//document.getElementById("tc").style.visibility = "hidden";
	//document.getElementById("tc").style.height = "0px";	
	 flag_search_adv=0;
}

function query()
{	
	window.location('show_result.htm');
	
}

//lyric

function divLyric(){
	var il = document.getElementsByName("imgLyric") ;	
	var dl = document.getElementsByName("divLyric") ;	

    //alert(dl[0].style.height);
	if(dl[0].style.height != '40px')
	{
		for( j = 0; j < il.length; j++)
		{
			il[j].src = "images/move-down.png";
		}
		for( j = 0; j < dl.length; j++)
		{
			dl[j].style.height = "40px";
		}
			
	}
	else
	{
		for( j = 0; j < il.length; j++)
		{
			il[j].src = "images/move-top.png";
		}		
		for( j = 0; j < dl.length; j++)
		{
			dl[j].style.height = "auto";
		}				
	}
	
}

////////////////////////////
//play accordian

function playacc(evt)
{
	var t;
	//alert("asdf");
	var evt1 = evt || window.event;
	var obj = evt1.target || window.event.srcElement;	
	
	//alert(obj.style.height);
	var ControlList = document.getElementsByName("imgacc");
	for(i = 0; i < ControlList.length; i++)
	{
		ControlList[i].src = "images/play.jpg";
	}

	var a = obj.firstChild;
	alert(a);
	if(a == null)
	{
		alert("aa");
		obj.removeChild(obj.firstChild);
		t = right(obj.firstChild.src,"/");	
		//alert(t);
		if(t == "play.jpg")
		{
		//	obj.removeChild(obj.firstChild);
			obj.firstChild.src = "images/pause.jpg";
		}
		return;
	}
	//alert(a.length);
	//var a = a.text;
	//kiem tra object text	
	if(a.length != null)
	{
		alert("bb");
		return;	
	}
	
	if(!a.src )
	{
		alert("cc");
		obj.removeChild(obj.firstChild);		
		t = right(obj.firstChild.src,"/");	
		//alert(t);
		if(t == "play.jpg")
		{
			//	obj.removeChild(obj.firstChild);
			obj.firstChild.src = "images/pause.jpg";
		}
		return ;
	}
		/*
	var imgNode = document.createElement("img");
	imgNode.src = "images/play.jpg";
	imgNode.height = "40";
	imgNode.width = "40";
	imgNode.name = "imgacc";
	imgNode.border = "0";
		
		
	obj.insertBefore(imgNode,obj.firstChild);
	*/
}
function play_acc(obj)
{
	var t,j,i;

	var il = document.getElementsByName("imgLyric") ;	
	for( j = 0; j < il.length; j++)
	{
		il[j].src = "images/move-down.png";
	}
		
	var dl = document.getElementsByName("divLyric") ;	
	for( j = 0; j < dl.length; j++)
	{
		dl[j].style.height = "40px";
	}
	
	//alert(obj.style.height);
	var ControlList = document.getElementsByName("imgacc");
	for( i = 0; i < ControlList.length; i++)
	{
		ControlList[i].src = "images/play.jpg";
	}

//	alert("cc");
	if(!obj.firstChild.src)
		obj.removeChild(obj.firstChild);		
	
	t = right(obj.firstChild.src,"/");	
	//alert(t);
	if(t == "play.jpg")
	{
		//	obj.removeChild(obj.firstChild);
		obj.firstChild.src = "images/pause.jpg";
	}
	//ssreturn ;
		
}
function onload_acc()
{
    var ControlList = document.getElementsByName("imgacc");
    ControlList[0].src = "images/pause.jpg";

}
function chooseObj(obj)
{
	var src = obj.src;
	src = right(src,"/");	
	
	var pos = src.lastIndexOf(".");		
	src = src.substring(0,pos);
	//alert(src);
	
	var ControlList = document.getElementsByName("objSearch");  
	ControlList[0].src = "images/t_mp3.jpg";
	ControlList[1].src = "images/t_video.jpg";
	ControlList[2].src = "images/t_album.jpg";
	ControlList[3].src = "images/t_lyric.jpg";
	
	
	obj.src = "images/" + src + "_o.jpg";
	//alert();
	
}