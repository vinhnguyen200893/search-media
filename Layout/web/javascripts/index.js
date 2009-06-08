//defautl search by music
var flag = 0;
var flag_search_adv=0;
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
function search_adv()
{	
	//var s = document.getElementById("search_adv").style.visibility;
	//alert(s);
	if(document.getElementById("avd").style.visibility == "hidden")
	{
		flag_search_adv = 1;
		document.getElementById("avd").style.visibility = "visible";
		document.getElementById("avd").style.height = "auto";
		
	}
	else
	{
		flag_search_adv = 0;
		document.getElementById("avd").style.visibility = "hidden";
		document.getElementById("avd").style.height = "0px";		
	}
}
function search()
{
	alert("defalt");
}
function chooseMusic()
{
	var s = document.getElementById("music").src;
	var src = right(s,"/");
	if(src != "music_on.jpg")
	{		
		document.getElementById("music").src = "images/music_on.jpg";
		document.getElementById("film").src = "images/film.jpg";		
	}
	flag = 0;		
	//alert(flag);
}
function chooseFilm()
{
	//alert("as");
	var s = document.getElementById("film").src;
	var src = right(s,"/");
	if(src != "film_on.jpg")
	{
		document.getElementById("music").src = "images/music.jpg";
		document.getElementById("film").src = "images/film_on.jpg";
	}
	flag = 1;
	//alert(flag);
}