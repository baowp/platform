KISSY.ready(function(S) {
    // just for test

    S.use('editor', function() {

        var KE = S.Editor;

        window.editor = KE("#editor", {
             attachForm:true,
            //编辑器内弹窗z-index底线，防止互相覆盖
            baseZIndex:10000,
            pluginConfig: {
                "image":{
                    upload:{
                        //返回格式
                        //正确：{imgUrl:""}
                        //错误：{error:""}
                        //中文 \uxxxx 转义
                        serverUrl:"/fileUpload/uploadImage",
                        serverParams:{
                            watermark:function() {
                                return S.one("#ke_img_up_watermark_1")[0].checked;
                            }
                        },
                        error:"发送错误",
                        fileInput:"upload",
                        sizeLimit:1000,//k
                        extraHtml:"<p style='margin-top:5px;'><input type='checkbox' id='ke_img_up_watermark_1'> 图片加水印，防止别人盗用</p>"
                    }
                },
                "templates": [
                    {
                        demo: "模板1效果演示html"  ,
                        html: "<div style='border:1px solid red'>模板1效果演示html</div><p></p>"
                    },
                    {
                        demo: "模板2效果演示html",
                        html: "<div style='border:1px solid red'>模板2效果演示html</div>"
                    }
                ],
                "font-size":{
                    items:[
                        {
                            value:"14px",
                            attrs:{
                                style:'position: relative; border: 1px solid #DDDDDD; margin: 2px; padding: 2px;'
                            },
                            name:"" +
                                    " <span style='font-size:14px'>标准</span>" +
                                    "<span style='position:absolute;top:1px;right:3px;'>14px</span>"
                        },
                        {
                            value:"16px",
                            attrs:{
                                style:'position: relative; border: 1px solid #DDDDDD; margin: 2px; padding: 2px;'
                            },
                            name:"" +
                                    " <span style='font-size:16px'>大</span>" +
                                    "<span style='position:absolute;top:1px;right:3px;'>16px</span>"
                        },
                        {
                            value:"18px",
                            attrs:{
                                style:'position: relative; border: 1px solid #DDDDDD; margin: 2px; padding: 2px;'
                            },
                            name:"" +
                                    " <span style='font-size:18px'>特大</span>" +
                                    "<span style='position:absolute;top:1px;right:3px;'>18px</span>"
                        },
                        {
                            value:"20px",
                            attrs:{
                                style:'position: relative; border: 1px solid #DDDDDD; margin: 2px; padding: 2px;'
                            },
                            name:"" +
                                    " <span style='font-size:20px'>极大</span>" +
                                    "<span style='position:absolute;top:1px;right:3px;'>20px</span>"
                        }
                    ],
                    width:"115px"
                }

                //,"font-family":["song"]
                ,"bangpai-upload":{                        
                    serverUrl:"/code/upload/upload.jsp",
                    serverParams:{
                        waterMark:function() {
                            return S.one("#ke_img_up_watermark_2")[0].checked;
                        }
                    },
                    sizeLimit:1000//k,
                    ,numberLimit:15,
                    extraHtml:"<p style='margin-top:10px;'>" +
                            "<input type='checkbox' " +
                            "style='vertical-align:middle;margin:0 5px;' " +
                            "id='ke_img_up_watermark_2'>" +
                            "<span style='vertical-align:middle;'>图片加水印" +
                            "</span></p>"
                },
                "bangpai-video":{
                    urlCfg:[
                        {
                            reg:/tudou\.com/i,
                            url:"http://bangpai.daily.taobao.net/json/getTudouVideo.htm?" +
                                    "url=@url@&callback=@callback@"//"&rand=@rand@"
                        }
                    ]
                },
                "draft":{
                    interval:5,
                    limit:10,
                    helpHtml:  "<div " +
                            "style='width:200px;'>" +
                            "<div style='padding:5px;'>草稿箱能够自动保存您最新编辑的内容，" +
                            "如果发现内容丢失，" +
                            "请选择恢复编辑历史</div></div>"
                },
                "resize":{
                    direction:["y"]
                }
            }
        }).use("elementpaths,sourcearea,preview," +
                "templates,separator," +
                "undo,separator,removeformat,font,format,forecolor,bgcolor,separator," +
                "list,indent,justify,separator,link,image,flash,bangpai-video,music,bangpai-music,smiley," +
                "separator,table,resize,draft,bangpai-upload,pagebreak,separator,maximize"
                );
    });
});
