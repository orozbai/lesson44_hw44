<link rel="stylesheet" href="https://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
<script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script src="https://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>

<br><br><br>

<style>
    body {
        background: url(https://bootstraptema.ru/images/bg/bg-1.png)
    }

    #main {
        background-color: #f2f2f2;
        padding: 20px;
        -webkit-border-radius: 4px;
        -moz-border-radius: 4px;
        -ms-border-radius: 4px;
        -o-border-radius: 4px;
        border-radius: 4px;
        border-bottom: 4px solid #ddd;
    }

    #real-estates-detail #author img {
        -webkit-border-radius: 100%;
        -moz-border-radius: 100%;
        -ms-border-radius: 100%;
        -o-border-radius: 100%;
        border-radius: 100%;
        border: 5px solid #ecf0f1;
        margin-bottom: 10px;
    }

    #real-estates-detail .sosmed-author i.fa {
        width: 30px;
        height: 30px;
        border: 2px solid #bdc3c7;
        color: #bdc3c7;
        padding-top: 6px;
        margin-top: 10px;
    }

    .panel-default .panel-heading {
        background-color: #fff;
    }

    #real-estates-detail .slides li img {
        height: 450px;
    }
</style>

<div class="container">
    <div id="main">


        <div class="row" id="real-estates-detail">
            <div class="col-lg-4 col-md-4 col-xs-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <header class="panel-title">
                            <div class="text-center">
                                <strong>Пользователь сайта</strong>
                            </div>
                        </header>
                    </div>
                    <div class="panel-body">
                        <div class="text-center" id="author">
                            <img src="https://bootstraptema.ru/snippets/element/2016/profilesection/myprofile.jpg">
                            <h3>${profile.name}</h3>
                            <small class="label label-warning">Российская Федерация</small>
                            <p class="sosmed-author">
                                <a href="#"><i class="fa fa-facebook" title="Facebook"></i></a>
                                <a href="#"><i class="fa fa-twitter" title="Twitter"></i></a>
                                <a href="#"><i class="fa fa-google-plus" title="Google Plus"></i></a>
                                <a href="#"><i class="fa fa-linkedin" title="Linkedin"></i></a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8 col-md-8 col-xs-12">
                <div class="panel">
                    <div class="panel-body">
                        <ul id="myTab" class="nav nav-pills">
                            <li class="active"><a href="#detail" data-toggle="tab">О пользователе</a></li>
                            <li class=""><a href="#contact" data-toggle="tab">История книг</a></li>
                            <li class=""><a href="http://localhost:9889/logout">
                                <button onclick="deleteCookie('cookieName')">Logout</button>
                            </a></li>
                            <li class=""><a href="http://localhost:9889/give-books">
                                <button>Get Books</button>
                            </a></li>
                            <li class=""><a href="http://localhost:9889/return-books">
                                <button>Return Books</button>
                            </a></li>
                            <script>
                                function deleteCookie(name) {
                                    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
                                }
                            </script>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <hr>
                            <div class="tab-pane fade active in" id="detail">
                                <h4>История профиля</h4>
                                <table class="table table-th-block">
                                    <tbody>
                                    <tr>
                                        <td class="active">email:</td>
                                        <td>${profile.email}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">User name:</td>
                                        <td>${profile.user}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Password:</td>
                                        <td>${profile.password}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Name:</td>
                                        <td>${profile.name}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Surname:</td>
                                        <td>${profile.surname}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Current Books:</td>
                                        <td>${profile.currentBooks}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Reading Books:</td>
                                        <td>${profile.readingBooks}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Рейтинг пользователя:</td>
                                        <td><i class="fa fa-star" style="color:red"></i> <i class="fa fa-star"
                                                                                            style="color:red"></i> <i
                                                class="fa fa-star" style="color:red"></i> <i class="fa fa-star"
                                                                                             style="color:red"></i> 4/5
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="active">Плагин рейтинга:</td>
                                        <td>
                                            <a href="https://bootstraptema.ru/stuff/plugins_bootstrap/improvement/bootstrap_star_rating/12-1-0-73"
                                               target="_blank">http://goo.gl/bGGXuw</a></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="contact">
                                <#if profile.name != "unknown">
                                    <#if profile.email == history.email>
                                        <p>Название книги: ${history.book}</p>
                                    </#if>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div><!-- /.main -->
</div><!-- /.container -->