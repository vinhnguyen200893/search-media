//defautl search by music
var flag = 0;

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
	//var _img = document.getElementsById("search_adv");
	var s = document.getElementById("search_adv").src;
	var f = right(s,"/");
	alert(f);
	
	var s1 = s.
	var adv = document.getElementsById("adv");	
	
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