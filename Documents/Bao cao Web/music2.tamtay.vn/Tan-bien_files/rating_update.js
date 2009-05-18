// All part of the Dynamic Star Rating by Jordan Boesch!
// http://creativecommons.org/licenses/by-nc-nd/2.5/ca/
// PRE-LOAD IMAGES -----------------------------

if (document.images){
  pic1 = new Image(220,19); 
  pic1.src = "/images/rating_loading.gif"; 

  pic2 = new Image(20,70); 
  pic2.src = "/images/rating_star.gif"; 

  pic3 = new Image(20,70); 
  pic3.src = "/images/rating_star_2.gif"; 
  
  pic4 = new Image(16,13); 
  pic4.src = "/images/rating_tick.gif";
  
  pic5 = new Image(14,14); 
  pic5.src = "/images/rating_warning.gif";
}

// AJAX ----------------------------------------

var xmlHttp

function GetXmlHttpObject(){

var xmlHttp = null;

	try {
	  // Firefox, Opera 8.0+, Safari
	  xmlHttp = new XMLHttpRequest();
	  }
	catch (e) {
	  // Internet Explorer
	  try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		}
	  catch (e){
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	  }
	  
	return xmlHttp;

}

// Calculate the rating
function rate(rating,id,uid,show5,showPerc,showVotes, url){


	xmlHttp = GetXmlHttpObject()
	
	if(xmlHttp == null){
		alert ("Your browser does not support AJAX!");
		return;
	  }

	xmlHttp.onreadystatechange = function(){
		
	var uldiv = document.getElementById('ul_'+id);
	
		if (xmlHttp.readyState == 4){ 
			
			//loader.style.display = 'none';
			var res_temp= xmlHttp.responseText;
			
			//alert(res);
			if(res_temp == 'already_voted'){
				
				
			} else {
				percv=res_temp.split(',')[0];
				total=res_temp.split(',')[1];
				rows=res_temp.split(',')[2];
				
				//loader.innerHTML = '<div class="voted">Cám ơn!</div>';

				if(show5 == true){
					var out = document.getElementById('outOfFive_'+id);
					var calculate = res/20;
					out.innerHTML = Math.round(calculate*100)/100; // 3.47;
					//out.innerHTML = Math.round((calculate*2),0)/2; // 3.5;
				} 
				
				if(showPerc == true){
					var perc = document.getElementById('percentage_'+id);
					//var newPerc = Math.round(Math.ceil(res/5))*5;
					var newPerc = total;
					perc.innerHTML = 'Tổng số điểm '+newPerc;
				}
				
				else if(showPerc == false){
					var newPerc = total;
					
				}
				
				if(showVotes == true){
					var votediv = document.getElementById('showvotes_'+id).firstChild.nodeValue;
					document.getElementById('showvotes_'+id).innerHTML = 'Đã có '+rows+' lượt bình chọn';
				}
				
				var ulRater = document.getElementById('rater_'+id);
				ulRater.className = 'star-rating2';
				
				var all_li = ulRater.getElementsByTagName('li');
				
				// start at 1 because the first li isn't a star
				for(var i=1;i<all_li.length;i++){
					
					all_li[i].getElementsByTagName('a')[0].onclick = 'return false;';
					all_li[i].getElementsByTagName('a')[0].setAttribute('href','#');
					
				}
				percv= ''+percv;
				if(navigator.appName == 'Microsoft Internet Explorer'){
					uldiv.style.setAttribute('width',percv+'%'); // IE
				 } else {
					uldiv.setAttribute('style','width: '+percv+'%'); // Everyone else
				 }
				
			}
		} else {
			
		}
	
	}
	//var url = "http://www.ttmusic.vn/includes/rating_process.php";
	
	var params = "sid="+id+"&uid="+uid+"&rating="+rating;
	xmlHttp.open("POST",url,true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.setRequestHeader("Content-length", params.length);
	xmlHttp.setRequestHeader("Connection", "close");
	xmlHttp.send(params);

} 