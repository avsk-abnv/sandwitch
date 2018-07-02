
<%@ page import="java.util.*,TravellerBean.*,TravellerDao.*,javax.servlet.http.*,java.lang.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Travel Home</title>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <meta name="google-signin-client_id" content="1026790441840-hp4prgmrnqvpt2lv0hlo6l2ltu6dgbkg.apps.googleusercontent.com">
        <style>
            <%@include file="styles/header.css"%>
            <%@include file="styles/footer.css" %>
            <%@include file="styles/loadericon.css" %>
        .img_container{width: 350px;height:300px;overflow: hidden;position: relative;}
        .traveloc:hover .travelimg{transition: 0.5s; transform: scale(1);}
        .traveloc:hover .loctitle{color:#0090ff;}
        .like{position: absolute;color:darkgray;left:10px;top:300px;font-size: 25px;}
        .dislike{position: absolute;color:darkgray;right:10px;top:300px;font-size: 25px;}
        .like_count{position: absolute;color:darkgray;left:10px;top:330px;}
        .dislike_count{position: absolute;color:darkgray;right:10px;top:330px;}
        .like:active{color:darkgreen;}
        .dislike:active{color:maroon;}
        #pagenos{margin: 0 auto;margin-top:50px;display:table;}
        .pgn{float:left;border:1px solid gray;background:white;display:table-cell;border-radius:20px;}
        .pgn>a{padding:8px 16px;display:block;}
        .pgn>span{padding:8px 16px;display:block;}
        @media all and (max-width:1000px){
            .traveloc{width: 100%;height:600px;overflow: hidden;position: relative;}
            .img_container{width: 100%;height:600px;overflow: hidden;position: relative;}
            .like{position: absolute;color:darkgray;left:10px;top:600px;font-size: 25px;}
              .dislike{position: absolute;color:darkgray;right:10px;top:600px;font-size: 25px;}
           .like_count{position: absolute;color:darkgray;left:10px;top:630px;}
             .dislike_count{position: absolute;color:darkgray;right:10px;top:630px;}
             .travelimg{max-width:100%;}
        }
        
        </style>
    </head>
    <body>
        
                <%@include file="header.html" %>
                <div id="my_panel">
                    <%@include file="loginform.html"%>
                    <%@include file="registerform.html" %>
                    
                    <div id="my_container">
                        <%if(request.getParameter("key")!=null){%>
                        <b style="width:100%;float: left;font-family: 'Candara';">Search Result: <%=request.getParameter("key")%> </b>
                        <%}%>
                        <%if(request.getParameter("climate")!=null){%>
                        <b style="width:100%;float: left;font-family: 'Candara';">Filter Climate: <%=request.getParameter("climate")%> </b>
                        <%}%>
                        <%if(request.getParameter("type")!=null){%>
                        <b style="width:100%;float: left;font-family: 'Candara';">Filter Type: <%=request.getParameter("type")%> </b>
                        <%}%>
                        <%if(request.getParameter("state")!=null){%>
                        <b style="width:100%;float: left;font-family: 'Candara';">Filter State: <%=request.getParameter("state")%> </b>
                        <%}%>
                    <%
                        
                         ArrayList arr;
                        LocationDAO dao=new LocationDAO();
                        ArrayList bumba;
                         arr=dao.getAllLocations();
                         bumba=dao.getAllLocations(1);
                         if(request.getParameter("key")!=null)
                            bumba= dao.getLocationsByLocationName(request.getParameter("key"));
                         if(request.getParameter("climate")!=null)
                             bumba=dao.getLocationsByLocationClimate(request.getParameter("climate"));
                         if(request.getParameter("type")!=null)
                             bumba=dao.getLocationsByLocationType(request.getParameter("type"));
                         if(request.getParameter("state")!=null)
                             bumba=dao.getLocationsByLocationState(request.getParameter("state"));
                         if(request.getParameter("page")!=null)
                             bumba=dao.getAllLocations(Integer.parseInt(request.getParameter("page")));
                         LocationBean lb;
                         
                         if(bumba.size()==0){%>
                         <span>No Resutls</span>
                         <%}else{
                         for(Object o: bumba){
                         lb=(LocationBean)o;%>
                         
                         <div class="traveloc" value="<%=lb.getLocation_id()%>">
                             
                             <div class="img_container">
                                 <a href="visit?location=<%=lb.getLocation_id()%>_<%=lb.getLocation_name().replaceAll("\\s","")%>"><img src="" value="<%=lb.getLocation_thumbnail()%>" class="travelimg"></a>
                             </div>
                             
                             <i class="fa fa-thumbs-up like" title="Like <%=lb.getLocation_name()%>" value="like"></i>
                             <span class="like_count"><%=lb.getLocation_likes()%></span><span class="dislike_count"><%=lb.getLocation_dislikes()%></span>
                             <i class="fa fa-thumbs-down dislike" title="Dislike <%=lb.getLocation_name()%>" value="dislike"></i>
                                 <a href="visit?location=<%=lb.getLocation_id()%>_<%=lb.getLocation_name().replaceAll("\\s","")%>" class="loctitle"><%=lb.getLocation_name()%></a>
                         </div>
                    <%}}%>
                </div>
                </div>
                <div id="mask"></div>
                <%
                    String nstr="#";
                    String pstr="#";
                    if(request.getParameter("page")!=null){
                        String pgno = request.getParameter("page");
                        String nextpage= Integer.toString(Integer.parseInt(pgno)+1);
                        if(Integer.parseInt(pgno)>1){
                            String prevpage= Integer.toString(Integer.parseInt(pgno)-1);
                            pstr="?page="+prevpage;
                        }
                        nstr="?page="+nextpage;
                    }
                    else{
                        nstr="?page=2";
                    }
                %>
                <div id="pagenos">
                         <div class="pgn" style="box-shadow: 3px 3px grey;"><a href="<%=pstr%>" style="color:black;font-weight: bolder;"><</a></div>
                         <div class="pgn" id="space" style="background-color:white;border:none;"><span></span></div>
                         <div class="pgn" style="box-shadow: 3px 3px grey;"><a href="<%=nstr%>" style="color:black;font-weight: bolder;">></a></div>
                </div>
                <%@include file="footer.html" %>   
        <script>
            function onSignIn(googleUser) {
      // window.location.href='success.jsp';
      var profile = googleUser.getBasicProfile();
      var imagurl=profile.getImageUrl();
      var name=profile.getName();
      var email=profile.getEmail();
      $('#loginform').css('display',"none");
      $('#Register_form').css('display',"none");
      $('#mask').css('display',"none");
       $.ajax({
                    type:"POST",
                    async:false, // set async false to wait for previous response
                    url: "Authorizegoogle",
                    data:{email:email,name:name},
                    success: function(data){
                        if($.trim(data)==="success"){
                            //document.getElementById("signinas").src = imagurl;
                            if(!$('#user_container').is(":visible"))
                                location.reload();
                        }else if($.trim(data)==="direct"){
                            $('#signin').css('display',"none");
                             $('#user_container').css('display',"table");
                            document.getElementById("signinas").src = imagurl;
                            document.getElementById("user").innerHTML = name;
                        }else{
                            console.log("error");
                        }
                    }
                });
   }
   function signOut() {
      var auth2 = gapi.auth2.getAuthInstance();
      auth2.signOut().then(function () {
        console.log('User signed out.');
        window.location.pathname="/Logout";
      });
    }
    function onLoad() {
      gapi.load('auth2', function() {
        gapi.auth2.init();
      });
    }
      //gapi.auth2.getAuthInstance().disconnect();
      //location.reload();
   
            var picwidth;
$(window).resize(function(){
	var width=$('.traveloc').width();
	//$('.instapic').height(width2);
	var img= document.querySelectorAll('.traveloc');
/*	for(var i=0;i<$('h5').length;i++)
	{
		$('h5').eq(i).css({left:(width-$('h5').eq(i).width())*0.5});
	}*/
	$('.traveloc').height(width);
	$('.img_container').height(width-50);
              $('.like').css('top',width-50+'px');
              $('.like_count').css('top',width-20+'px');
              $('.dislike_count').css('top',width-20+'px');
              $('.dislike').css('top',width-50+'px');
              $('.travelimg').height(width-50);
});
$(window).trigger('resize');
            <%@include file="js/header.js" %>     
                <%
                     for(Object o: arr){
                         lb=(LocationBean)o;
                         %>
                             mylist.push("<%=lb.getLocation_name()%>");
            <%}%>
                <%HttpSession sess= request.getSession();
                        UserBean ub= (UserBean)sess.getAttribute("currentuser");
                %><%if(ub!=null){%>
                                $('#signin').css('display',"none");
                                $('#user_container').css('display',"table");
                                $('#user').text("<%=ub.getDisplay_name()%>");
                       <%}%>
                for(i=0;i<$('.like').length;i++){
                $('.like').eq(i).click(function(e){
                    <%if(ub!=null){%>
                        var action=$(this).attr('value');
                        var locid=$(this).parent().attr('value');
                        var action2= $(this).parent().children().eq(4).attr('value');
                        if(action2==="undo_dislike")
                            ajaxreq(locid,action2);                       
                        ajaxreq(locid,action);
                     <%}else{%>
                         $('#loginform').css('display',"table");
                         $('#mask').css('display',"block");
                         $('body').css('overflow',"hidden");
                      <%}%>
                });
            }
            for(i=0;i<$('.dislike').length;i++){
                $('.dislike').eq(i).click(function(e){
                    //$(this).parent().children().eq(1).val("like");
                    <%if(ub!=null){%>
                        var action=$(this).attr('value');
                        var locid=$(this).parent().attr('value');
                        var action2= $(this).parent().children().eq(1).attr('value');
                        if(action2==="undo_like")
                            ajaxreq(locid,action2);
                        ajaxreq(locid,action);
                     <%}else{%>
                         $('#loginform').css('display',"table");
                         $('#mask').css('display',"block");
                         $('body').css('overflow',"hidden");
                      <%}%>
                });
            }
            function ajaxreq(locid,action){
                    $.ajax({
                      type: 'POST',
                      url: "likendislike",
                      data: {locid:locid,action:action},
                      async:false,
                      success: function(data){
                           for(i=0;i<$('.like_count').length;i++){
                               if($('.like_count').eq(i).parent().attr('value')===locid){
                                   if(action==="like" || action==="undo_like"){
                                        $('.like_count').eq(i).text(data);
                                        if(action==="like")
                                        {    $('.like').eq(i).css('color',"darkgreen");
                                              $('.like_count').eq(i).css('color',"darkgreen");
                                              $('.like').eq(i).attr('value',"undo_like");
                                        }else if(action==="undo_like"){
                                            $('.like').eq(i).css('color',"darkgray");
                                              $('.like_count').eq(i).css('color',"darkgray");
                                              $('.like').eq(i).attr('value',"like");
                                        }
                                    }
                                    else if(action==="dislike" || action==="undo_dislike"){
                                        $('.like_count').eq(i).parent().children().eq(3).text(data);
                                        if(action==="dislike")
                                        {    $('.dislike').eq(i).css('color',"maroon");
                                              $('.dislike_count').eq(i).css('color',"maroon");
                                              $('.dislike').eq(i).attr('value',"undo_dislike");
                                        }else if(action==="undo_dislike"){
                                            $('.dislike').eq(i).css('color',"darkgray");
                                              $('.dislike_count').eq(i).css('color',"darkgray");
                                              $('.dislike').eq(i).attr('value',"dislike");
                                        }
                                    }
                               }
                           }
                    }
                });
            }
            <%if(ub!=null){%>
                   $('.like').css('display',"none");
                    $('.dislike').css('display',"none");
                    $('.like_count').css('display',"none");
                    $('.dislike_count').css('display',"none");
//           for(i=0;i<$('.traveloc').length;i++){
                
            recursiveAjaxCall(0);
            function recursiveAjaxCall(num){
                var loc =$('.traveloc').eq(num).attr('value');
                $.ajax({
                    type:"POST",
                    //async:false, // set async false to wait for previous response
                    url: "getlikendislike",
                    data:{locid:loc},
                    success: function(data)
                    {
                        $('.like').eq(num).css('display',"");
                        $('.dislike').eq(num).css('display',"");
                        $('.like_count').eq(num).css('display',"");
                        $('.dislike_count').eq(num).css('display',"");      
                        
                        if($.trim(data)==="liked"){
                            $('.like').eq(num).css('color',"darkgreen");
                            $('.like_count').eq(num).css('color',"darkgreen");
                            $('.like').eq(num).attr('value',"undo_like");
                        }else if($.trim(data)==="disliked"){
                            $('.dislike').eq(num).css('color',"maroon");
                            $('.dislike_count').eq(num).css('color',"maroon");
                            $('.dislike').eq(num).attr('value',"undo_dislike");
                        }else if($.trim(data)==="none"){
                            
                        }else{

                        }
                       // console.log(num+"   "+$.trim(data));
                        num++;
                        if(num<$('.traveloc').length)
                            recursiveAjaxCall(num);
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown){
                        num++;
                        if(num<$('.traveloc').length)
                            recursiveAjaxCall(num);
                    }
                });
            }
            //}
            <%}%>
            $('#search').bind("cut copy paste select drop", function(e) {
                    e.preventDefault();
                    $('#search').val("");
                    text="";
                    $('#search').css('border',"1px solid red");
                    setTimeout(function(){ $('#search').css('border',"none"); },1000);
                 //alert("You cannot paste into this text field.");
            });
            var downloadingImage=[];
            var i=0;
            var loader="<div class=\"lds-roller\"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div>";
            $('.img_container').append(loader);
            $('.travelimg').css('display',"none");
            $('.travelimg').each(function(index,element){
                downloadingImage.push(new Image());
                downloadingImage[index].src = $(element).attr('value');
                downloadingImage[index].onload=  function(){
                $(element).attr('src',downloadingImage[index].src);
                $(element).css('display',"block");
                $('.lds-roller').eq(index).css('display',"none");
                };
            });
            
           //Synchronous loading
          /* $('.travelimg').each(function(index,element){
               var url=$(element).attr('value');
               $.ajax({
                   url:url,
                   async:false,
                   cache:true,
                   processData:false
               }).always(function(){
                   $(element).attr('src',url).fadeIn();
                   $('.lds-roller').eq(index).css('display',"none");
                   $(element).css('display',"block");
               });
           });*/

            
        </script>
        
    </body>
</html>
