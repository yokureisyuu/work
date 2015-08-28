$(function () {
	var p = 1;
    function tpl() {
        this.init = function () {
			init_ajax();
        }
        function init_ajax() {
            var number=50;
            $.ajax({ url: "http://lbr.51wxq.com/public/ajaxpage.aspx?action=photos&id="+$("body").attr("data-id")+"&c=" + number + "&p=" + p, 
				dataType: 'json', 
				async:false, 
				success: function(data){ 
					init_view(data)
				}
			});
        }

        function init_view(rs) {
			var first_photo=$("body").attr("first_photo");
			if (first_photo) {
                window.XC_IMG = first_photo;
            }
            if (rs && rs.list && $.isArray(rs.list) && rs.list.length > 0) {
                var str = '';

                for (var i in rs.list) {

                    var datas = rs.list[i];
                    str += '<div class="xc-item">';
                    str += '<img class="page-img" original="' + datas.photofilepath + '">';
                    str += '<div class="content">';
                    if (datas.photoname) {
                        str += '<div class="page-title">' + datas.photoname + '</div>';
                    }
                    if (datas.photomark) {
                        str += '<div class="page-desc">' + datas.photomark + '</div>';
                    }
                    str += '</div>';
                    str += '</div>';
                }

                $('.xc-list').html(str);
            }


            $('.page-img').lazyLoad();
            var music=$("body").attr("data-music");
            if (rs && music) {
                $.audios({src: music, callback: function (a) {
                    if (!$('script[src*="jquery.transit.js"]').length) {
                        var src = '<script type="text/javascript" src="http://lbr.51wxq.com/template/images/albums/jquery.transit.js" charset="utf-8"></script>';
                        $('script[src*="alphoto.js"]').before(src);

                    }

                    $(window).trigger('htmlReady');
                    if (a) {
                        var play = true;
                        $('.canvas_box').on('touchend', function () {
                            if (play) {
                                //a.pause();
                                play = false;
                            } else {
                                a.play();
                                play = true;
                                $('.music').addClass('play');
                                $(window).off('touchend');
                                music_show();
                            }

                        });
                        $(window).on('touchend', function () {
                            if (play) {
                                //a.pause();
                                play = false;
                            } else {
                                a.play();
                                play = true;
                                $('.music').addClass('play');
                                $(window).off('touchend');
                                music_show();
                            }

                        });

                        $('.music').on('touchend', function () {

                            if (play) {
                                a.pause();
                                play = false;
                                $('.music').removeClass('play');
                                $('.music-paly').remove();
                            } else {
                                a.play();
                                play = true;
                                $('.music').addClass('play');
                                music_show();
                            }

                        });
                    }

                }});
            } else {
                $(window).trigger('htmlReady');
            }

        }

        function music_show() {

            var str = '';
            str += '<div class="music-paly" style="width: 30px;height: 50px;top:-10px;position: fixed;right:20px;z-index: 99999;"></div>';
            if (!$('.music-paly').length) {
                $('.music').before(str);
                var deg = $.randnum(-180, 180);
                var bot = $.randnum(20, 50);
                var left = $.randnum(0, 15);
                var ww = $.randnum(4, 8);

                var str1 = '<div class="music-fu" style="width:' + 15 * ww / 10 + 'px;height:' + 25 * ww / 10 + 'px;-webkit-transform: rotate(' + deg + 'deg);left:' + left + 'px"></div>';

                $(str1).appendTo('.music-paly').transit({'transform':'translate(0,-'+bot+'px)', 'opacity': '0', width: '3px', height: '5px'}, 1800, 'easeOutCus');
                var i = 0;
                if (!window.MUSIC_PLAY) {
                    window.MUSIC_PLAY = setInterval(function () {
                        if (i % 5 == 0) {
                            $('.music-fu:not(:animated)').remove();

                        }
                        i++;
                        var deg = $.randnum(-180, 180);
                        var bot = $.randnum(20, 50);
                        var left = $.randnum(0, 15);
                        var ww = $.randnum(4, 8);
                        str1 = '<div class="music-fu" style="width:' + 15 * ww / 10 + 'px;height:' + 25 * ww / 10 + 'px;-webkit-transform: rotate(' + deg + 'deg);left:' + left + 'px"></div>';
                        $(str1).appendTo('.music-paly').transit({'transform':'translate(0,-'+bot+'px)', 'opacity': '0', width: '3px', height: '5px'}, 1800, 'easeOutCus');

                    }, 400);
                }

            }

        }

    }

    var t = new tpl();
    t.init();
})