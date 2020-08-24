var regeistFlag = 0;

var vm = new Vue({
    el: '#mian',
    data: {
        nowYear: new Date().getFullYear(),
        logindata: {
            uname: '',
            upassword: ''
        },
        registerdata: {
            uname: '',
            upassword: '',
            ucpassword: '',
        },
        forgetdata: {
            wait_timer: 'false',
            findway: '',
            upassword: '',
            ucpassword: '',
            checknum: '',
            // sendAuthCode: '',/*布尔值，通过v-show控制显示‘获取按钮’还是‘倒计时’ */
            // auth_time: '', /*倒计时 计数器*/

        }
    },
    mounted: function () {
        //挂载完毕后的
        this.$.set(this.forgetdata, wait_timer, false);
        console.log(this.forgetdata);
    },


    methods: {


        /*
        用户找回密码
        */
        userForget: function () {
            var a = this.forgetdata.upassword;
            var b = this.forgetdata.ucpassword;
            if (a == b && a != null && b != null) {

                $.ajax({
                    url: "/user/findpassword",
                    data: {
                        findway: this.forgetdata.findway,
                        checknum: this.forgetdata.checknum,
                        upassword: this.forgetdata.upassword,

                    },
                    type: "post",
                    success: function (code) {
                        if (code === 9) {
                            alert("您的验证码错误，请重新获取！")
                        }
                        if (code === 6) {
                            alert("密码修改成功！")
                        }
                        if (code === 7) {
                            alert("密码修改失败，请重新操作！")
                        }
                    }
                })

            } else {
                alert("密码输入不一致，或者您未输入密码！");
                return;
            }
        },


        /*
        用户登录
        */
        userLogin: function () {
            if (this.logindata.uname.length === 0 || this.logindata.uname == null) {
                alert("请输入用户名！");
            } else {
                var loginuname = this.logindata.uname;

                $.ajax({
                    url: "/user/login",
                    data: {
                        uname: this.logindata.uname,
                        upassword: this.logindata.upassword
                    },
                    type: "post",
                    success: function (code) {

                        if (code === 4) {
                            alert("登录成功！正在跳转...");

                            //跳转页面
                            window.location.href = "http://localhost:20000/?uname=" + loginuname;
                            // window.open("http://localhost:20000/?uname=" + this.logindata.uname, "_self");


                        } else {
                            alert("登录失败！请重新输入用户名或密码!")
                        }
                    }, error: function (err) {
                        alert("登录失败！未知错误！")
                    }
                })
            }
        },

        /*
        异步校验用户名是否已注册
         */
        unameSearch: function () {
            if (this.registerdata.uname.length === 0 || this.registerdata.uname == null) {
                alert("用户名不能为空！")
                // return;
            } else {
                // alert(this.registerdata.uname)
                $.ajax({
                    url: "/user/searchuname?uname=",
                    data: {
                        uname: this.registerdata.uname
                    },
                    type: "post",
                    success: function (code) {
                        if (code === 1) {
                            //可以注册
                            // this.registerdata.sameFlag = "true";
                            regeistFlag = 1;
                        } else {
                            alert("用户名已存在，请重新输入！");
                            // this.registerdata.sameFlag = "false";
                            regeistFlag = 0;
                        }

                    }
                })
            }
        },


        /*
        用户注册
         */
        userRegeist: function () {


            if (this.registerdata.uname.length === 0 || this.registerdata.uname == null) {
                alert("用户名不能为空！")
                // return;
            } else {

                if (regeistFlag == 0) {
                    alert("用户名已存在，请重新输入！")
                } else {
                    if (this.registerdata.upassword.replace(/[^\a-\z\A-\Z0-9]/g, '')) {
                        if (this.registerdata.upassword !== this.registerdata.ucpassword) {
                            alert("密码不一致，请重新输入！");
                            // return;
                        } else {

                            $.ajax({
                                url: "/user/regeister",
                                type: "post",
                                data: {
                                    uname: this.registerdata.uname,
                                    upassword: this.registerdata.upassword
                                },

                                success: function (code) {
                                    if (code == 2) {
                                        alert("注册成功");
                                        // $("#option1").checked();
                                    } else {
                                        alert("注册失败")
                                    }

                                }, error: function (err) {
                                    alert("未知错误")
                                }
                            })

                        }
                    } else {
                        alert("密码格式不正确，请重新输入！")
                        // return;
                    }
                }

            }
        },


        /*
        获取验证码
         */
        getMobileCode: function () {
            if (this.forgetdata.wait_timer > 0) {
                return false;
            }
            if (!this.forgetdata.findway) {
                // console.log('验证方式不能为空！');
                alert('验证方式不能为空！')
                return false;
            }

            var reg = RegExp(/@/);
            if (this.forgetdata.findway.match(reg)) {
                // 邮箱校验

                if (!/^\w+@[a-z0-9]+\.[a-z]{2,4}$/.test(this.forgetdata.findway)) {
                    console.log('格式不正确');
                    alert("格式不正确");
                    return false;
                }
            } else {
                if (!/^1[3|4|5|7|8]\d{9}$/.test(this.forgetdata.findway)) {
                    console.log('格式不正确');
                    alert("格式不正确");
                    return false;
                }
            }

            this.forgetdata.wait_timer = 59;
            var that = this;
            var timer_interval = setInterval(function () {
                if (that.forgetdata.wait_timer > 0) {
                    that.forgetdata.wait_timer--;
                } else {
                    clearInterval(timer_interval);
                }
            }, 1000);

            //在这里调取你获取验证码的ajax
            if (this.forgetdata.findway.match(reg)) {
                //调用邮箱api

                $.ajax({
                    url: "/mail/findpw",
                    data: {
                        findway: this.forgetdata.findway
                    },
                    type: "post",
                    success: function (bool) {
                        if (bool === true) {
                            alert("验证码发送成功")
                        } else {
                            alert("验证码发送失败，请重新操作！")
                        }
                    }
                })
            } else {
                //调用手机api

                $.ajax({
                    url: "/shortmsg/findpw",
                    data: {
                        findway: this.forgetdata.findway
                    },
                    type: "post",
                    success: function (bool) {
                        if (bool === true) {
                            alert("验证码发送成功");
                        } else {
                            alert("验证码发送失败，请重新操作！")
                        }
                    }
                })
            }
        }
        ,
        getMobileCodeText: function () {
            if (this.forgetdata.wait_timer > 0) {
                return this.forgetdata.wait_timer + 's后获取';
            }

            if (this.forgetdata.wait_timer === 0) {
                return '重新获取';
            }

            if (this.forgetdata.wait_timer == "false") {
                return '获取验证码';
            }

        }
    }
});