            var mylist=[];
            
            var myinp= document.querySelector("#search");
            var hovered_at=-1;
            var mouseactivity=false;
            var firsttime_excep=true;
            var event = new Event('selected');
            myinp.value="";
            var text=myinp.value;
                    myinp.addEventListener("keypress",function(event){
                        if(event.which!==8){
                            if(event.which!==0 && event.which!==13)
                                text+=event.key;
                            //console.log(event.which);
                        }
                        else
                            text=text.slice(0,-1);
                        if(event.key.toLowerCase()==="arrowdown"){
                            mouseactivity=false;
                              if(hovered_at===document.querySelectorAll("ul>li").length-1){
                                    //console.log(0);
                                    hovered_at=0;
                              }else{
                                  //console.log(hovered_at+1);
                                  hovered_at++;
                              }
                              myinp.value=document.querySelectorAll("ul>li")[hovered_at].textContent;
                              text=myinp.value;
                              colorize(hovered_at);
                        }
                        if(event.key.toLowerCase()==="arrowup"){
                            mouseactivity=false;
                                if(hovered_at<=0){
                                    //console.log(document.querySelectorAll("ul>li").length-1);
                                    hovered_at=document.querySelectorAll("ul>li").length-1;
                                    
                              }else{
                                  //console.log(hovered_at-1);
                                  hovered_at--;
                              }
                              myinp.value=document.querySelectorAll("ul>li")[hovered_at].textContent;
                              text=myinp.value;
                              colorize(hovered_at);
                        }
                        if(event.key.toLowerCase()!=="arrowup" && event.key.toLowerCase()!=="arrowdown")
                            dothiswork();
                        
            });
            myinp.addEventListener("focus",function(){
                text=myinp.value;
            });
            function dothiswork(){
                document.querySelector("ul").innerHTML="";
                hovered_at=-1;
                var blax;
                firsttime_excep=true;
                var index=0;
                var crax=text.toLocaleUpperCase();
                mylist.forEach(function(x){
                    index++;
                    blax=x.slice(x.indexOf('(')+1);
                        if((x.slice(0,text.length).toUpperCase()===crax || blax.slice(0,text.length).toUpperCase()===crax) && text.length!==0 ){
                                    document.querySelector("ul").innerHTML+="<li>"+x+"</li>"; 
                        }
                     });
            }
            
            document.querySelector('body').addEventListener('click', function(event) {
                   if(event.target.tagName.toLowerCase()!=='ul'){
                       document.querySelector("ul").innerHTML="";
                       hovered_at=-1;
                   }
                    
            });
            var flg=false;
            document.querySelector("#navbtn").addEventListener("click",function(event){
                if(!flg){
                       document.querySelector("#item1").style.display="block";
                       document.querySelector("#item2").style.display="block";
                       document.querySelector("#item3").style.display="block";
                      if(document.querySelector("#my_panel")!==null)
                            document.querySelector("#my_panel").style.marginTop="225px";
                   }else{
                       document.querySelector("#item1").style.display="none";
                       document.querySelector("#item2").style.display="none";
                       document.querySelector("#item3").style.display="none";
                       if(document.querySelector("#my_panel")!==null)
                            document.querySelector("#my_panel").style.marginTop="120px";
                   }
                   flg=!flg;
                   
            });
            document.querySelector("ul").addEventListener("mouseover",function(event){
                for(i=0;i<document.querySelectorAll("ul>li").length;i++){
                    if(document.querySelectorAll("ul>li")[i]===event.target){
                            //console.log(i);
                            if(mouseactivity || firsttime_excep){
                                hovered_at=i;
                                firsttime_excep=false;
                                colorize(hovered_at);
                            }
                    }
                }
            });
            document.querySelector("ul").addEventListener("click",function(event){
                 //console.log(event.target.textContent);
                 myinp.value=event.target.textContent;
            });
            function colorize(hovered_at){
                for(i=0;i<document.querySelectorAll("ul>li").length;i++){
                    if(i===hovered_at){
                        document.querySelectorAll("ul>li")[i].style.backgroundColor="skyblue";
                        //console.log("did it");
                    }else{
                        document.querySelectorAll("ul>li")[i].style.backgroundColor="";
                    }
                }
            }
            document.querySelector("ul").addEventListener("mousemove",function(event){
                mouseactivity=true;
            });
            function eventFire(el, etype){
                if (el.fireEvent) {
                  el.fireEvent('on' + etype);
                 } else {
                var evObj = document.createEvent('Events');
                evObj.initEvent(etype, true, false);
                el.dispatchEvent(evObj);
            }
          }
            for(i=0;i<document.querySelectorAll(".toclear1").length;i++){
            document.querySelectorAll(".toclear1")[i].value="";
        }
        for(i=0;i<document.querySelectorAll(".toclear").length;i++){
            document.querySelectorAll(".toclear")[i].value="";
        }        
        document.querySelector("#signin").addEventListener("click",function(){
            document.querySelector("#loginform").style.display="table";
            document.querySelector("#mask").style.display="block";
            document.querySelector("body").style.overflow="hidden";
        });
        document.querySelector("#crossbutton1").addEventListener("click",function(){
            document.querySelector("#loginform").style.display="none";
            document.querySelector("#mask").style.display="none";
            document.querySelector("body").style.overflow="";
        });
        document.querySelector("#openreg").addEventListener("click",function(){
            document.querySelector("#loginform").style.display="none";
            document.querySelector("#Register_form").style.display="table";
            document.querySelector("#mask").style.display="block";
            document.querySelector("body").style.overflow="hidden";
        });
        document.querySelector("#crossbutton").addEventListener("click",function(){
            document.querySelector("#Register_form").style.display="none";
            document.querySelector("#mask").style.display="none";
            document.querySelector("body").style.overflow="";
        });
         document.querySelector("#openlogin").addEventListener("click",function(){
            document.querySelector("#Register_form").style.display="none";
            document.querySelector("#loginform").style.display="table";
            document.querySelector("#mask").style.display="block";
            document.querySelector("body").style.overflow="hidden";
        });
        document.querySelector("#item3").addEventListener("click",function(){
            document.querySelector("#loginform").style.display="table";
            document.querySelector("#mask").style.display="block";
            document.querySelector("body").style.overflow="hidden";
        });
        var hover1=false,hover2=false;
        document.querySelector("#states").addEventListener("mouseover",function(){
            this.style.borderBottom="4px solid chocolate";
             hover1=true;
             document.querySelector("#_options").style.marginLeft="150px";
                  document.querySelector("#_options").style.display="block";
                  document.querySelector("#_options").style.overflowY="scroll";
                  document.querySelector("#_options").style.height="280px";
                  var str="";
                  var stateparam;
                  var states=["Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhatisgarh","Goa","Gujarat",
                      "Haryana","Himachal Pradesh","Jammu & Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh",
                  "Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu",
                    "Telangana","Tripura","Uttarakhand","Uttar Pradesh","West Bengal"];
                states.forEach(function(state){
                    stateparam=state.replace(new RegExp(' ', 'g'),"+");
                    stateparam= stateparam.replace(new RegExp('&', 'g'),"%26");
                    str+="<div class=\"menu\"><a href=\"?state="+stateparam+"\"><h4 class=\"menutext\">"+state+"</h4></a></div>";
                });
            document.querySelector("#_options").innerHTML=str;
                                                                                                                 
        });
        document.querySelector("#climate").addEventListener("mouseover",function(){
            this.style.borderBottom="4px solid chocolate";  
             hover1=true;
                  document.querySelector("#_options").style.display="block";
                  document.querySelector("#_options").style.marginLeft="-50px";
                  document.querySelector("#_options").style.height="auto";
                   document.querySelector("#_options").style.overflowY="hidden";
                    document.querySelector("#_options").style.overflowY="hidden";
            document.querySelector("#_options").innerHTML="<div class=\"menu\"><a href=\"?climate=Hot\"><h4 class=\"menutext\">Hot</h4></a></div>"+
                                                                                                                "<div class=\"menu\"><a href=\"?climate=Hot+and+Humid\"><h4 class=\"menutext\">Hot and Humid</h4></a></div>"+
                                                                                                                 "<div class=\"menu\"><a href=\"?climate=Average\"><h4 class=\"menutext\">Average</h4></a></div>"+
                                                                                                                 "<div class=\"menu\"><a href=\"?climate=Cold\"><h4 class=\"menutext\">Cold</h4></a></div>";
        });
        document.querySelector("#type").addEventListener("mouseover",function(){
            this.style.borderBottom="4px solid chocolate";
              hover1=true;
                  document.querySelector("#_options").style.display="block";
                  document.querySelector("#_options").style.marginLeft="50px";
                  document.querySelector("#_options").style.height="auto";
                  document.querySelector("#_options").style.overflowY="hidden";
                    document.querySelector("#_options").style.overflowY="hidden";
            document.querySelector("#_options").innerHTML="<div class=\"menu\"><a href=\"?type=Beach\"><h4 class=\"menutext\">Beach</h4></a></div>"+
                                                                                                                "<div class=\"menu\"><a href=\"?type=Temples+%26+Ancient Ruins\"><h4 class=\"menutext\">Temples & Ancient Ruins</h4></a></div>"+
                                                                                                                 "<div class=\"menu\"><a href=\"?type=Hills+and+Mountains\"><h4 class=\"menutext\">Hills and Mountains</h4></a></div>"+
                                                                                                                 "<div class=\"menu\"><a href=\"?type=Parks+and+Sanctuaries\"><h4 class=\"menutext\">Parks and Sanctuaries</h4></a></div>";                  
        });
                document.querySelector("#states").addEventListener("mouseout",function(){
            this.style.borderBottom="none";
             hover1=false;
                   document.querySelector("#_options").style.display="none";             
        });
        document.querySelector("#climate").addEventListener("mouseout",function(){
            this.style.borderBottom="none";
             hover1=false;
                   document.querySelector("#_options").style.display="none";             
        });
        document.querySelector("#type").addEventListener("mouseout",function(){
            this.style.borderBottom="none";
             hover1=false;
                   document.querySelector("#_options").style.display="none";             
        });
        document.querySelector("#_options").addEventListener("mouseover",function(){
            hover2=true;
             if(hover1 || hover2)
                  document.querySelector("#_options").style.display="block";
              else
                   document.querySelector("#_options").style.display="none";            
        });
        document.querySelector("#_options").addEventListener("mouseout",function(){
            hover2=false;
             if(hover1 || hover2)
                  document.querySelector("#_options").style.display="block";
              else
                   document.querySelector("#_options").style.display="none";            
        });
        var hovera=false,hoveru=false;
        document.querySelector("#user_container").addEventListener("mouseover",function(){
            document.querySelector("#userpanel").style.display="block";
            hovera=true;
        });
        document.querySelector("#userpanel").addEventListener("mouseover",function(){
            document.querySelector("#userpanel").style.display="block";
            hoveru=true;
        });
        document.querySelector("#user_container").addEventListener("mouseout",function(){
            hovera=false;
            if(!hovera && !hoveru)
                document.querySelector("#userpanel").style.display="none";
        });
        document.querySelector("#userpanel").addEventListener("mouseout",function(){
            hoveru=false;
            if(!hovera && !hoveru)
                document.querySelector("#userpanel").style.display="none";
        });