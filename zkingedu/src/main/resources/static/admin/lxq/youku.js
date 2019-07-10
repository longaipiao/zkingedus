function cloud_outside_upload(client_id,access_token,cloudUploadSuccess,uploadAgain){
    paramInit();
    if(!uploadAgain){
        uploadAgain =function() {
            $("#input01").val('');
            $("#textarea").val('');
            $("#input02").val('');
            var postfiles = document.getElementById('postfiles');
            var selectfiles = document.getElementById('selectfiles');
            postfiles.removeAttribute('disabled');
            postfiles.style.background = '#2a8bd0';
            selectfiles.removeAttribute('disabled');
            var ossfileInfor = document.getElementById('ossfileInfor');
            ossfileInfor.style.display = 'none';
            var files = document.getElementById('selectfiles');
            files.value = '';
            var progressBar = document.getElementsByClassName('progress-bar');
            var ossProgress = document.getElementById('oss-progress');
            ossProgress.style.display = 'none';
            progressBar[0].style.width = 0;
        }
    }

    function paramInit() {
        var tpl = '<option value="Others" selected="">其他</option>';
        $("#category-node").html(tpl);
        document.getElementById('ossfile').innerHTML = '';
        var postfiles = document.getElementById('postfiles');
        var selectfiles = document.getElementById('selectfiles');
        selectfiles.addEventListener('change', function(e){
            e.preventDefault();
            var name = selectfiles.files && selectfiles.files[0].name;
            $("#input01").val(name);
            $("#textarea").val(name);
        })

        postfiles.onclick = function() {
            postfiles.setAttribute('disabled', true);
            postfiles.background = '#ccc';
            selectfiles.setAttribute('disabled',true);
            //创建上传任务，获取参数
            var uploadFlag = true;
            var ossFile = '';
            var filename = '';
            if (selectfiles.files && selectfiles.files[0]) {
                ossFile = selectfiles.files[0];
                filename = ossFile.name;
                var filesize = ossFile.size;
                var filetype = ossFile.type;
                var filemd5 = '';
                // client_id = $('#select_style option:selected').attr('data-ckey');
                // access_token = $('#select_style option:selected').attr('access_token');
                var title = $('input[name=title]').val();
                var tags = "zking课堂视频";
                var category = "Others";// Others其他
                var copyright_type = "original";  //original  原创
                var public_type = "all";//视频权限 all公开
                var watch_password = $('input[name=watch_password]').val();
                var description = "zking课堂视频出品";
                if (!client_id) {
                    alert('请选择应用!');
                    postfiles.removeAttribute('disabled');
                    postfiles.style.background = '#2a8bd0';
                    return;
                }
                if (filesize > 4294967296) {
                    alert('文件过大!');
                    postfiles.removeAttribute('disabled');
                    postfiles.style.background = '#2a8bd0';
                    selectfiles.removeAttribute('disabled');
                    return;
                }
                if (!title) {
                    alert('请填写标题!');
                    postfiles.removeAttribute('disabled');
                    postfiles.style.background = '#2a8bd0';
                    return;
                }
                if (title.length > 20) {
                    alert('标题字符请勿大于20个!');
                    postfiles.removeAttribute('disabled');
                    postfiles.style.background = '#2a8bd0';
                    return;
                }
                if (description && description.length > 200) {
                    alert('简介字符请勿大于200个!');
                    postfiles.removeAttribute('disabled');
                    postfiles.style.background = '#2a8bd0';
                    return;
                }
                if (!tags) {
                    alert('请填写标签!');
                    postfiles.removeAttribute('disabled');
                    postfiles.style.background = '#2a8bd0';
                    return;
                }
                if (tags.length > 20) {
                    alert('标签字符请勿大于20个');
                    postfiles.removeAttribute('disabled');
                    postfiles.style.background = '#2a8bd0';
                    return;
                }
                if (public_type == 'password') {
                    if (!watch_password) {
                        alert('请输入密码!');
                        postfiles.removeAttribute('disabled');
                        postfiles.style.background = '#2a8bd0';
                        return false;
                    }
                }
                //回到页面顶部，方面观察上传相关数据
                document.documentElement.scrollTop ?
                    document.documentElement.scrollTop = 0 :
                    document.body.scrollTop ?
                        document.body.scrollTop = 0 :
                        window.pageYOffset ?
                            window.pageYOffset = 0 :
                            null;
                browserMD5File(ossFile, function(err, md5){
                    console.log('md5 error:'+ err);
                    filemd5 = md5;
                    if(filemd5){
                        if (!filename) {
                            alert('请选择上传文件!');
                            postfiles.removeAttribute('disabled');
                            postfiles.style.background = '#2a8bd0';
                            selectfiles.removeAttribute('disabled');
                            return;
                        }
                        $.ajax({
                            type: 'GET',
                            url: '//api.youku.com/uploads/create.json',
                            data: {
                                client_id: client_id,
                                access_token: access_token,
                                file_name: filename,
                                file_size: filesize,
                                title: title,
                                tags: tags,
                                category: category,
                                file_md5: filemd5,
                                copyright_type: copyright_type,
                                public_type: public_type,
                                watch_password: watch_password,
                                description: description,
                                isweb: 1,
                                isnew: 1
                            },
                            dataType: 'JSON',
                            success: function(res) {
                                if (res) {
                                    var accessid = res.temp_access_id;
                                    var accesskey = res.temp_access_secret;
                                    var oss_bucket = res.oss_bucket;
                                    var oss_object = res.oss_object;
                                    var security_token = res.security_token;
                                    var host = res.endpoint;
                                    uploadToken = res.upload_token;
                                    var video_id = res.video_id;
                                    //获取到参数后初始化oss
                                    browserAliOss(accessid, accesskey, security_token, oss_bucket, oss_object, ossFile);
                                } else {
                                    alert('create接口返回失败,请重试!');
                                    postfiles.removeAttribute('disabled');
                                    postfiles.style.background = '#2a8bd0';
                                    selectfiles.removeAttribute('disabled');
                                    return;
                                }
                            },
                            error: function(error) {
                                alert('create接口返回失败,' + error);
                                postfiles.removeAttribute('disabled');
                                postfiles.style.background = '#2a8bd0';
                                selectfiles.removeAttribute('disabled');
                            }
                        });
                    }else{
                        postfiles.removeAttribute('disabled');
                        postfiles.style.background = '#2a8bd0';
                        selectfiles.removeAttribute('disabled');
                        alert('md5加密失败！');
                        return;
                    }
                })
                var ossProgress = document.getElementById('oss-progress');
                var ossfileInfor = document.getElementById('ossfileInfor');
                ossfileInfor.innerText = filename + ' ' + Math.ceil(filesize / 1024 / 1024) + 'mb';
            } else {
                postfiles.removeAttribute('disabled');
                postfiles.style.background = '#2a8bd0';
                selectfiles.removeAttribute('disabled');
                alert('请选择上传文件!');
                return;
            }
        };
    }

    function browserAliOss(accessKeyId, accessKeySecret, stsToken, bucket, oss_object, ossFile) {
        var client = new OSS({
            region: 'oss-cn-shanghai',
            accessKeyId: accessKeyId,
            accessKeySecret: accessKeySecret,
            stsToken: stsToken,
            bucket: bucket
        });
        var result = client.multipartUpload(oss_object, ossFile, {
            progress: function(p, c, r) {
                console.log('progress:' + p);
                if (p == 0) {
                    p = 0.01;
                }
                var ossProgress = document.getElementById('oss-progress');
                ossProgress.style.display = 'block';
                var progressBar = document.getElementsByClassName('progress-bar');
                if (progressBar) {
                    progressBar[0].style.width = p * 100 + '%';
                }
            },
            parallel: 2,
            partSize: 20971520,
            mime: 'video/*'
        }).then(function(result) {
            console.log('上传result:' + JSON.stringify(result));
            if (result && result.res.status == 200) {
                var requestUrls = result.res.requestUrls;
                $.ajax({
                    url: '//api.youku.com/uploads/commit.json',
                    type: 'POST',
                    data: {
                        client_id: client_id,
                        access_token: access_token,
                        upload_token: uploadToken,
                        upload_server_name: requestUrls
                    },
                    dataType: 'JSON',
                    success: function(res) {
                        if (res) {
                            cloudUploadSuccess(res);
                            //$("#success_modal",parent.document).modal('show');
                            //alert('上传成功:' + res.video_id);
                            //uploadAgain();
                        } else {
                            alert('commit接口返回失败!');
                            uploadAgain();
                        }
                        //uploadAgain();
                    },
                    error: function(res) {
                        alert('上传失败！' + res);
                        uploadAgain();
                    }
                })
            } else {
                console.log('上传失败:' + JSON.stringify(result));
                uploadAgain();
            }
        }).then(function(err) {
            console.log('error:' + JSON.stringify(err));
        })
    }
}