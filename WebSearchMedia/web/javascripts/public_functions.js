
function show_hide(show,hide)
{
    if(show!='')
    {
    	document.getElementById(show).style.display = 'block';
    }
    if(hide!='')
    {
    	document.getElementById(hide).style.display = 'none';
    }
}

function show_processing(dest)
{
	document.getElementById(dest).innerHTML = '<span class = "processing"> System is processing ...</span>';
}

function show_lightbox(caption,method,width,height)
{
        return GB_showCenter(caption,method,height,width);
}
function show_content(element_id,method,show_element_id,hide_element_id)
{
    show_hide(show_element_id,hide_element_id);
	show_processing(element_id);
	new Ajax.Updater (element_id,root_url + method);
}