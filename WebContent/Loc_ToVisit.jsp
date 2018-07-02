
<%-- 
    Document   : Loc_ToVisit
    Created on : 10 Mar, 2018, 12:45:15 PM
    Author     : Abhishek Abhinav
--%>
<%@ page import="java.util.*,TravellerBean.*,TravellerDao.*,javax.servlet.http.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
            .travimg{width:100%;margin:0 auto;}
            .travcontainer{background-color:rgb(245,245,245);position:relative;width:90%;margin:0 auto;padding:5px;
            box-shadow: 0px 0px 3px rgba(0,0,0,0.5);border-radius:5px;display:table;padding-bottom:20px;}
            .locname{position:absolute;font-family:"Bahnschrift";left:40px;font-size:larger;margin-top:25px;}
            .locdesc{position:relative;margin-left:35px;margin-top:40px;font-family:"Candara";margin-right:35px;}
            .loctype{position:relative;font-family:"Candara";margin-left:35px;top:60px;}
            .locclimate{position:relative;font-family:"Candara";margin-left:35px;top:20px;}
            .locaddress{position:relative;font-family:"Candara";margin-left:35px;top:20px;}
            .like_count{position:absolute;right:205px;margin-top:5px;font-family:"Bahnschrift"}
            .dislike_count{position:absolute;right:105px;margin-top:5px;font-family:"Bahnschrift"}
            .like{position:absolute;right:200px;font-size:25px;color:darkgray;margin-top:-20px;}
            .dislike{position:absolute;right:100px;font-size:25px;color:darkgray;margin-top:-20px;}
                    .like:active{color:darkgreen;}
        .dislike:active{color:maroon;}    
            .boom{position: absolute;
                            margin-top: 30px;
                            width: 92%;
                            color: rgb(250,250,250);
                            left: 0;
                            right: 0;}
            object{font-weight:bold;}
            .addcomment{
                                    width: 93%;
                                    resize: none;
                                    height: 30px;
                                    margin-left: 3.5%;
                                    border-radius: 15px;
                                    font-size: 15px;
                                    font-family: 'Candara';
                                    text-indent: 15px;box-shadow: inset 0 0 5px black;
                                    border: 1px solid rgb(200,200,200);}
            .thisone{position: relative;
                            margin-top: 30px;
                            width: 92%;
                            color: rgb(250,250,250);
               }
             .postcomment{
             height:30px;
             width:90px;color:white;font-family:"Candara";
             background-color:#0090ff;
             border:1px solid #0090ff;
             float:right;font-size:15px;
             margin-right:3.5%;display:none;
             border-top-right-radius:15px;
             border-bottom-right-radius:15px;
             border-top-left-radius:5px;
             border-bottom-left-radius:5px;}
             .cancelcomment{
                              height:30px;color:black;font-family:"Candara";
             width:90px;display:none;
             background-color:rgb(175,175,175);
             border:1px solid rgb(175,175,175);
             float:right;
             margin-right:15px;
             border-top-right-radius:5px;
             border-bottom-right-radius:5px;
             border-top-left-radius:15px;
             border-bottom-left-radius:15px;font-size:15px;
             }
             .commentbox {
              position: relative;
              display:inline-block;
              background:#ffe8b9;
              text-align: left;
              width: 0;
              top:10px;
              font-family:"Candara";
              word-wrap: break-word;
              margin-bottom:20px;
              padding: 10px 15px;
              border-radius: 6px;
              box-shadow: 0px 2px 2px gray;
              float: left;
              left: 10%;
            }

            .commentbox::before {
              content: '';
              position: absolute;
              top: -1px;
              left: -10px;
              border: 10px solid transparent;
              border-top: 10px solid #ffe8b9;
            
            }
   
            .user_name{
                margin-left:3.5%;
                font-family:fantasy;
                font-weight:normal;
                font-size:15px;
                }
        </style>
    </head>
    <body>
                        <%@include file="header.html" %>
                <div id="my_panel">
                    <%@include file="loginform.html"%>
                    <%@include file="registerform.html" %>
                    <div id="my_container">
                        <%String locationid=request.getParameter("location").toString();
                                locationid=locationid.substring(0, locationid.indexOf('_'));
                                int location_id= Integer.parseInt(locationid);
                                LocationDAO ldao= new LocationDAO();
                                LocationBean loc= ldao.getLocationByLocationId(location_id);
                                LocationCommentsDAO lcd= new LocationCommentsDAO();
                                ArrayList lcmall= lcd.getLocationCommentsByLocationId(location_id);
                                
                        %>
                        <div class="travcontainer">
                        <img src="<%=loc.getLocation_imageurl()%>" class="travimg"><br>
                        <span class="locname"><%=loc.getLocation_name()%></span><br>
                        <span class="like_count"><%=loc.getLocation_likes()%> </span>
                        <i class="fa fa-thumbs-up like" title="Like <%=loc.getLocation_name()%>" value="like"></i>
                        <span class="dislike_count"><%=loc.getLocation_dislikes()%></span>
                        <hr class="boom">
                         <i class="fa fa-thumbs-down dislike" title="Dislike <%=loc.getLocation_name()%>" value="dislike"></i>
                         <span class="loctype"><object style="padding-right:25px;color:indigo;">Type : </object><%=loc.getLocation_type()%></span><br>
                        <span class="locclimate"><object style="padding-right:7px;color:darkorange;">Climate : </object><%=loc.getLocation_climate()%></span><br>
                        <p class="locaddress"><object style="padding-right:2px;color:green;">Address : </object><%=loc.getLocation_address()%></p>
                        <object style="margin-left:35px;font-family: 'Candara';top: 30px;position:relative;">Summary : </object><p class="locdesc"><%=loc.getLocation_desc()%></p>
                        <hr class="thisone">
                        <textarea class="addcomment" placeholder="Add a comment"autocomplete="off"></textarea>
                        <button class="postcomment">Comment</button>
                        <button class="cancelcomment">Cancel</button><hr style="margin-top:50px;color:transparent;" id="pehlahr">
                        <%for(Object lcm:lcmall){
                            LocationCommentsBean lcb= (LocationCommentsBean)lcm;
                            String uid= lcb.getEmailId();
                            UserDAO udo= new UserDAO();
                            UserBean ubean= udo.getUserById(uid);
                        %>
                        <span class="user_name"><%=ubean.getDisplay_name()%></span><br>
                        <div class="commentbox"><%=lcb.getLocationComment()%></div><hr style="margin-top:50px;color:transparent;"><br>
                        <%}%>
                        </div>
                        
                    </div>
                </div>
                    <div id="mask"></div>
                    <%@include file="footer.html" %>
  <script>
            <%@include file="js/header.js" %>     
                <%
                         ArrayList arr;
                        LocationDAO dao=new LocationDAO();
                         arr=dao.getAllLocations();
                         LocationBean lb;                    
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
                           document.querySelector('.addcomment').addEventListener("focus",function(e){
                               <%if(ub==null){%>
                                                                $('#loginform').css('display',"block");
                                                                $('#mask').css('display',"block");
                                                                $('body').css('overflow',"hidden");
                                       <%}else{%>
                              document.querySelector('.addcomment').style.height="60px";
                               document.querySelector('.postcomment').style.display="block";
                               document.querySelector('.cancelcomment').style.display="block";                                           
                                           <%}%>
                           });
                           $('.cancelcomment').click(function(e){
                               document.querySelector('.addcomment').value="";
                               $('.addcomment').css('height',"30px");
                               $('.postcomment').css('display',"none");
                               $(this).css('display',"none");
                           });
                           var widdd= parseInt($('.travcontainer').css('width').slice(0,$('.travcontainer').css('width').indexOf('.')))*0.8+"px";
                           $('.commentbox').css('width',widdd);
                           $(window).resize(function(){
                                var widdd= parseInt($('.travcontainer').css('width').slice(0,$('.travcontainer').css('width').indexOf('.')))*0.8+"px";
                           $('.commentbox').css('width',widdd);
                           });
                          
                $('.like').click(function(e){
                    <%if(ub!=null){%>
                        var action=$(this).attr('value');
                        var locid=<%=Integer.toString(loc.getLocation_id())%>;
                        var action2= $('.dislike').attr('value');
                        if(action2==="undo_dislike")
                            ajaxreq(locid,action2);                       
                        ajaxreq(locid,action);
                     <%}else{%>
                         $('#loginform').css('display',"block");
                         $('#mask').css('display',"block");
                         $('body').css('overflow',"hidden");
                      <%}%>
                });
            

                $('.dislike').click(function(e){
                    //$(this).parent().children().eq(1).val("like");
                    <%if(ub!=null){%>
                        var action=$(this).attr('value');
                        var locid=<%=Integer.toString(loc.getLocation_id())%>;
                        var action2= $('.like').attr('value');
                        if(action2==="undo_like")
                            ajaxreq(locid,action2);
                        ajaxreq(locid,action);
                     <%}else{%>
                         $('#loginform').css('display',"block");
                         $('#mask').css('display',"block");
                         $('body').css('overflow',"hidden");
                      <%}%>
                });
            
            function ajaxreq(locid,action){
                    $.ajax({
                      type: 'POST',
                      url: "likendislike",
                      data: {locid:locid,action:action},
                      async:false,
                      success: function(data){
                           for(i=0;i<$('.like_count').length;i++){
                                   if(action==="like" || action==="undo_like"){
                                        $('.like_count').text(data);
                                        if(action==="like")
                                        {    $('.like').css('color',"darkgreen");
                                              $('.like_count').css('color',"darkgreen");
                                              $('.like').attr('value',"undo_like");
                                        }else if(action==="undo_like"){
                                            $('.like').css('color',"darkgray");
                                              $('.like_count').css('color',"darkgray");
                                              $('.like').attr('value',"like");
                                        }
                                    }
                                    else if(action==="dislike" || action==="undo_dislike"){
                                        $('.dislike_count').text(data);
                                        if(action==="dislike")
                                        {    $('.dislike').css('color',"maroon");
                                              $('.dislike_count').css('color',"maroon");
                                              $('.dislike').attr('value',"undo_dislike");
                                        }else if(action==="undo_dislike"){
                                            $('.dislike').css('color',"darkgray");
                                              $('.dislike_count').css('color',"darkgray");
                                              $('.dislike').attr('value',"dislike");
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
                var loc =<%=Integer.toString(loc.getLocation_id())%>;
                
                $.ajax({
                    type:"POST",
                    async:false, // set async false to wait for previous response
                    url: "getlikendislike",
                    data:{locid:loc},
                    success: function(data)
                    {
                        $('.like').css('display',"");
                        $('.dislike').css('display',"");
                        $('.like_count').css('display',"");
                        $('.dislike_count').css('display',"");      
                        
                        if($.trim(data)==="liked"){
                            $('.like').css('color',"darkgreen");
                            $('.like_count').css('color',"darkgreen");
                            $('.like').attr('value',"undo_like");
                        }else if($.trim(data)==="disliked"){
                            $('.dislike').css('color',"maroon");
                            $('.dislike_count').css('color',"maroon");
                            $('.dislike').attr('value',"undo_dislike");
                        }else if($.trim(data)==="none"){
                            
                        }else{

                        }
                    }
                });
            
            <%}%>                  
                $('.postcomment').click(function(e){
                    <%if(ub==null){%>
                         $('#loginform').css('display',"table");
                         $('#mask').css('display',"block");
                         $('body').css('overflow',"hidden");
                     <%}else{%>
                         var locid=<%=Integer.toString(loc.getLocation_id())%>;
                         var comment=$('.addcomment').val();
                         $('.addcomment').val("");
                            $.ajax({
                                type:"POST",
                                async:false,
                                url:"addcomment",
                                data:{locid:locid,comment:comment},
                                success:function(data){
                                    var username=($.trim(data)).slice(0,($.trim(data)).indexOf('\t'));
                                    var usercomment=($.trim(data)).slice(($.trim(data)).indexOf('\t')+1);
                                    $("<span class=\"user_name\">"+username+"</span><br><div class=\"commentbox\">"+
                                            usercomment+"</div><hr style=\"margin-top:50px;color:transparent;\"><br>").insertAfter("#pehlahr");
                                    var ciddd= parseInt($('.travcontainer').css('width').slice(0,$('.travcontainer').css('width').indexOf('.')))*0.8+"px";
                                    $('.commentbox').css('width',ciddd);
                                }
                            });
                     <%}%>
                });
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
</script>                           
    </body>
</html>
